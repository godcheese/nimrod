package com.gioov.nimrod.common.security;

import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.druid.DruidConfiguration;
import com.gioov.nimrod.system.System;
import com.gioov.nimrod.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    @Qualifier("simpleUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加验证码校验过滤器
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().antMatchers(System.Api.VERIFY_CODE).permitAll();

        // Druid 需要权限或者系统管理员角色才能访问
        http.authorizeRequests().antMatchers(DruidConfiguration.DRUID_URL).hasAnyAuthority(SimpleUserDetailsServiceImpl.ROLE_PREFIX + SYSTEM_ADMIN, DruidConfiguration.DRUID_URL.toUpperCase());

        // 禁用 csrf，建议不要禁用 csrf
        http.csrf().disable()
                // 解决 in a frame because it set 'X-Frame-Options' to 'deny'. 问题
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                // 静态资源 url ，无需登录认证权限直接访问
                .antMatchers(Url.STATIC).permitAll()
                // 登录页，无需登录认证权限直接访问
                .antMatchers(User.Page.LOGIN).permitAll()
                // 其它请求均需要权限认证
                .anyRequest().authenticated().and()
                // 开启表单登录，设置登录页 url
                .formLogin().loginPage(User.Page.LOGIN).usernameParameter(User.Page.LOGIN_ACCOUNT_STRING).passwordParameter(User.Page.LOGIN_PASSWORD_STRING)
                // 自定义登录表单提交 url
                .loginProcessingUrl(User.Api.LOGIN)
                // 登录成功处理，登录成功，返回 status 200 ， json 返回 SimpleUser
                .successHandler(authenticationSuccessHandler)
                // 登录失败处理，登录失败，返回 status 404 ， json 返回失败提示
                .failureHandler(authenticationFailureHandler).and()
                // 开启记住我功能， cookie 保存登录数据，cookie 7 天有效
                .rememberMe().tokenValiditySeconds(60 * 60 * 60 * 24 * 7).rememberMeParameter(User.Page.LOGIN_REMEMBER_ME_STRING).and()
                // 注销
                .logout().logoutUrl(User.Api.LOGOUT).logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID", "remember-me");

        // 一个帐号只允许同时在线一个 session
        http.sessionManagement().sessionAuthenticationFailureHandler(authenticationFailureHandler).maximumSessions(1).expiredUrl(User.Page.LOGIN);

    }

}

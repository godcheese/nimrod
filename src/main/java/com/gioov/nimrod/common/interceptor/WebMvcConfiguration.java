package com.gioov.nimrod.common.interceptor;

import com.gioov.nimrod.common.Url;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public WebInterceptor webInterceptor() {
        return new WebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebInterceptor
        registry.addInterceptor(webInterceptor())
                .addPathPatterns(Url.ALL_PATH_PATTERN)
                .excludePathPatterns(Url.STATIC);
    }

}

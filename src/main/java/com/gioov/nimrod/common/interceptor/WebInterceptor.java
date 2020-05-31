package com.gioov.nimrod.common.interceptor;

import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl;
import com.gioov.nimrod.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class WebInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebInterceptor.class);

    private static final String HOST_KEY = "_host";

    private static final String CONTEXT_PATH_KEY = "_contextPath";

    private static final String USER_KEY = "_user";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("SYSTEM_ADMIN", SYSTEM_ADMIN);
            String contextPath = Common.Host.contextPath;
            modelAndView.addObject(CONTEXT_PATH_KEY, (contextPath != null && !"".equals(contextPath)) ? contextPath : "/");
            SimpleUser simpleUser = SimpleUserDetailsServiceImpl.getCurrentSimpleUser(request);
            Map<String, Object> userMap = new HashMap<>(2);
            userMap.put("id", null);
            userMap.put("username", null);
            if (simpleUser != null) {
                userMap.put("id", simpleUser.getId());
                userMap.put("username", simpleUser.getUsername());
                userMap.put("avatar", userService.getOne(simpleUser.getId()).getAvatar());
            }
            modelAndView.addObject(USER_KEY, userMap);
            String scheme = Common.Host.scheme;
            String port = Common.Host.port;
            String ip = Common.Host.ip;
            String local = scheme + "://localhost" + ":" + port + contextPath + "/";
            String network = scheme + "://" + ip + ":" + port + contextPath + "/";
            String host = network;
            if (ip == null) {
                host = local;
            }
            modelAndView.addObject(HOST_KEY, host);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}

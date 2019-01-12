package com.gioov.nimrod.common.interceptor;

import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.system.service.OperationLogService;
import com.gioov.nimrod.user.entity.UserEntity;
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

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class WebInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebInterceptor.class);

    private static final String REQUEST_TIME = "requestTime";

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogService operationLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        final String contextPathKey = "_contextPath";
        final String userKey = "_user";
        final String systemAdmin = SYSTEM_ADMIN;
        if (modelAndView != null) {
            modelAndView.addObject(systemAdmin, systemAdmin);
            String contextPath = request.getContextPath();
            modelAndView.addObject(contextPathKey, (contextPath != null && !"".equals(contextPath)) ? contextPath : "/");
            SimpleUser userPrincipal = userService.getUserPrincipal(request);
            Map<String, Object> userMap = new HashMap<>(2);
            userMap.put("id", null);
            userMap.put("username", null);
            if (userPrincipal != null) {
                UserEntity userEntity = userService.getOneByIdNoPassword(userPrincipal.getId());
                if (userEntity != null) {
                    userMap.put("id", userEntity.getId());
                    userMap.put("username", userEntity.getUsername());
                }
            }
            modelAndView.addObject(userKey, userMap);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        operationLogService.log(request, response, handler, System.currentTimeMillis() - (Long) request.getAttribute(REQUEST_TIME));
    }

}

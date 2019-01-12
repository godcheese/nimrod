package com.gioov.nimrod.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-10
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static Object getBean(String name, Class<?> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static Object getBean(Class<?> clazz) {
        return getApplicationContext().getBean(clazz);
    }

}

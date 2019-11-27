package com.gioov.nimrod.common.operationlog;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    /**
     * 操作内容
     * @return String
     */
    @AliasFor("operation")
    String value() default "";

    /**
     * 操作内容
     * @return String
     */
    @AliasFor("value")
    String operation() default "";

    /**
     * 操作类型
     * @return OperationLogType
     */
    OperationLogType type() default OperationLogType.PAGE;

}

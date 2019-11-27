package com.gioov.nimrod.common.exportbyexcel;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExportByExcel {

    /**
     * 导出显示名
     * @return String
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 导出显示名
     * @return String
     */
    @AliasFor("value")
    String name() default "";

    /**
     * 导入时忽略此字段
     * @return boolean
     */
    boolean importIgnore() default false;

    boolean exportIgnore() default false;

    Class<? extends ExportByExcelHandler> handler() default ExportByExcelHandler.class;

}

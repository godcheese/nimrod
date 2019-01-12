package com.gioov.nimrod.common.constant;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Url {

    public static final String PATH_SEPARATOR = "/";
    public static final String ALL_PATH_PATTERN = "/**";

    public static final String API = "/api";

    public static final String API_ALL_PATTERN = API + "/**";

    public static final String SYSTEM = "/system";
    public static final String USER = "/user";

    /**
     * 静态资源 url
     */
    public static final String[] STATIC = {"robots.txt", "/**.ico", "/css/**", "/js/**", "/img/**", "/vendor/**"};
}

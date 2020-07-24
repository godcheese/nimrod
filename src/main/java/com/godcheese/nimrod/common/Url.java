package com.godcheese.nimrod.common;

import com.godcheese.nimrod.system.System;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Url {

    public static final String PATH_SEPARATOR = "/";
    public static final String ALL_PATH_PATTERN = "/**";

    public static final String API = "/api";

    public static final String API_ALL_PATTERN = API + "/**";

    /**
     * 静态资源 url
     */
    public static final String[] STATIC = {"robots.txt", "/**.ico", "/assets/**", "/css/**", "/js/**", "/img/**", "/vendor/**"};

    public static class Api {

        public static final String API_PATH_PATTERN = API + ALL_PATH_PATTERN;
    }

    public static class Page {

        public static final String INDEX = "/index";

        public static final String[] INDEX_ARRAY = {PATH_SEPARATOR, INDEX, System.Page.SYSTEM, System.Page.INDEX};

    }
}

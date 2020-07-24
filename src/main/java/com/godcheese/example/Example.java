package com.godcheese.example;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
public class Example {

    public static class Page {
        public static final String EXAMPLE = "/example";
    }

    public static class Api {
        public static final String EXAMPLE = com.godcheese.nimrod.common.Url.API + Example.Page.EXAMPLE;
    }
}

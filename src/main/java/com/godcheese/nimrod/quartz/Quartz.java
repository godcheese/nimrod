package com.godcheese.nimrod.quartz;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
public class Quartz {

    public static class Page {
        public static final String QUARTZ = "/quartz";
        public static final String JOB = Page.QUARTZ + "/job";
        public static final String JOB_RUNTIME_LOG = Page.QUARTZ + "/job_runtime_log";
    }

    public static class Api {
        public static final String QUARTZ = com.godcheese.nimrod.common.Url.API + Page.QUARTZ;
        public static final String JOB = Api.QUARTZ + "/job";
        public static final String JOB_RUNTIME_LOG = Api.QUARTZ + "/job_runtime_log";
    }
}

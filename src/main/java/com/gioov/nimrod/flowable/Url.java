package com.gioov.nimrod.flowable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
public class Url {

    public class Page {
        public static final String FLOWABLE = "/flowable";
        public static final String ACT_RE_PROCDEF = FLOWABLE + "/act_re_procdef";
        public static final String ACT_HI_PROCINST = FLOWABLE + "/act_hi_procinst";
    }

    public class Api {
        public static final String FLOWABLE = com.gioov.nimrod.common.Url.API + Page.FLOWABLE;
        public static final String ACT_RE_PROCDEF = FLOWABLE + "/act_re_procdef";
        public static final String ACT_HI_PROCINST = FLOWABLE + "/act_hi_procinst";
    }

}

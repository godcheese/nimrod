package com.gioov.nimrod.system;

import com.gioov.nimrod.common.Url;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-20
 */
public class System extends Url {

    public static class Page {

        public static final String SYSTEM = "/system";

        public static final String INDEX = SYSTEM + Url.Page.INDEX;

        public static final String WORKBENCH = SYSTEM + "/workbench";

        public static final String DICTIONARY = SYSTEM + "/dictionary";
        public static final String DICTIONARY_CATEGORY = SYSTEM + "/dictionary_category";

        public static final String MAIL = SYSTEM + "/mail";

        public static final String OPERATION_LOG = SYSTEM + "/operation_log";

        public static final String FILE = SYSTEM + "/file";

    }

    public static class Api {

        public static final String SYSTEM = Url.API + Page.SYSTEM;

        public static final String DICTIONARY = SYSTEM + "/dictionary";
        public static final String DICTIONARY_CATEGORY = SYSTEM + "/dictionary_category";

        public static final String OPERATION_LOG = SYSTEM + "/operation_log";

        public static final String FILE = SYSTEM + "/file";

        public static final String VERIFY_CODE = SYSTEM + "/verify_code";

    }

}

package com.gioov.nimrod.common;

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

        public static final String SYSTEM = API + Url.Page.SYSTEM;
        public static final String USER = API + Url.Page.USER;

        public static final String API_PATH_PATTERN = API + ALL_PATH_PATTERN;

        public static class System {

            public static final String API = SYSTEM + "/api";
            public static final String API_CATEGORY = SYSTEM + "/api_category";

            public static final String VIEW_PAGE = SYSTEM + "/view_page";
            public static final String VIEW_PAGE_CATEGORY = SYSTEM + "/view_page_category";

            public static final String VIEW_PAGE_API = SYSTEM + "/view_page_api";

            public static final String VIEW_PAGE_COMPONENT = SYSTEM + "/view_page_component";
            public static final String VIEW_PAGE_COMPONENT_API = SYSTEM + "/view_page_component_api";

            public static final String DICTIONARY = SYSTEM + "/dictionary";
            public static final String DICTIONARY_CATEGORY = SYSTEM + "/dictionary_category";


            public static final String OPERATION_LOG = SYSTEM + "/operation_log";

//        public static final String REQUEST_LOG = SYSTEM + "/operation_log";

            public static final String ATTACHMENT = SYSTEM + "/attachment";

            public static final String VERIFY_CODE = SYSTEM + "/verify_code";

        }

        public static class User {

            public static final String LOGIN = USER + "/login";
            public static final String REGISTER = USER + "/register";
            public static final String LOGOUT = USER + "/logout";

            public static final String FORGOT_PASSWORD = USER + "/forgot_password";

            public static final String SEND_PASSWORD_EMAIL = USER + "/send_password_email";
            public static final String SEND_PASSWORD_SMS = USER + "/send_password_sms";

            public static final String ROLE = USER + "/role";
            public static final String ROLE_AUTHORITY = USER + "/role_authority";
            public static final String USER_ROLE = USER + "/user_role";

            public static final String VIEW_MENU = USER + "/view_menu";
            public static final String VIEW_MENU_CATEGORY = USER + "/view_menu_category";

            public static final String DEPARTMENT = USER + "/department";

        }

    }

    public static class Page {

        public static final String SYSTEM = "/system";
        public static final String USER = "/user";

        public static final String INDEX = "/index";
        public static final String[] INDEX_ARRAY = {PATH_SEPARATOR, INDEX, SYSTEM, System.INDEX};

        public static class System {

            public static final String INDEX = SYSTEM + Url.Page.INDEX;

            public static final String WORKBENCH = SYSTEM + "/workbench";

            public static final String API = SYSTEM + "/api";
            public static final String API_CATEGORY = SYSTEM + "/api_category";

            public static final String DICTIONARY = SYSTEM + "/dictionary";
            public static final String DICTIONARY_CATEGORY = SYSTEM + "/dictionary_category";

            public static final String VIEW_PAGE = SYSTEM + "/view_page";
            public static final String VIEW_PAGE_CATEGORY = SYSTEM + "/view_page_category";
            public static final String VIEW_PAGE_API = SYSTEM + "/view_page_api";

            public static final String VIEW_PAGE_COMPONENT = SYSTEM + "/view_page_component";
            public static final String VIEW_PAGE_COMPONENT_API = SYSTEM + "/view_page_component_api";

            public static final String MAIL = SYSTEM + "/mail";

            public static final String OPERATION_LOG = SYSTEM + "/operation_log";

//        public static final String REQUEST_LOG = SYSTEM + "/operation_log";

            public static final String ATTACHMENT = SYSTEM + "/attachment";

        }

        public static class User {

            public static final String LOGIN_ACCOUNT_STRING = "account";
            public static final String LOGIN_PASSWORD_STRING = "password";
            public static final String LOGIN_REMEMBER_ME_STRING = "rememberMe";

            public static final String LOGIN = USER + "/login";
            public static final String LOGIN_PATH_PATTERN = LOGIN + ALL_PATH_PATTERN;

            public static final String REGISTER = USER + "/register";
            public static final String REGISTER_PATH_PATTERN = REGISTER + ALL_PATH_PATTERN;

            public static final String LOGOUT = USER + "/logout";
            public static final String LOGOUT_PATH_PATTERN = LOGIN + ALL_PATH_PATTERN;

            public static final String ROLE = USER + "/role";
            public static final String ROLE_AUTHORITY = USER + "/role_authority";

            public static final String USER_ROLE = USER + "/user_role";

            public static final String VIEW_MENU = USER + "/view_menu";
            public static final String VIEW_MENU_CATEGORY = USER + "/view_menu_category";

            public static final String DEPARTMENT = USER + "/department";


        }
    }

}

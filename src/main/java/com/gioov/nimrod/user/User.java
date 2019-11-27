package com.gioov.nimrod.user;

import com.gioov.nimrod.common.Url;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-20
 */
public class User extends Url {

    public static class Page {

        public static final String USER = "/user";

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
        public static final String ROLE_VIEW_MENU = USER + "/role_view_menu";
        public static final String ROLE_VIEW_MENU_CATEGORY = USER + "/role_view_menu_category";

        public static final String USER_ROLE = USER + "/user_role";

        public static final String DEPARTMENT = USER + "/department";

        public static final String API = USER + "/api";
        public static final String API_CATEGORY = USER + "/api_category";

        public static final String VIEW_PAGE = USER + "/view_page";
        public static final String VIEW_PAGE_CATEGORY = USER + "/view_page_category";

        public static final String VIEW_PAGE_API = USER + "/view_page_api";

        public static final String VIEW_PAGE_COMPONENT = USER + "/view_page_component";
        public static final String VIEW_PAGE_COMPONENT_API = USER + "/view_page_component_api";

        public static final String VIEW_MENU = USER + "/view_menu";
        public static final String VIEW_MENU_CATEGORY = USER + "/view_menu_category";


    }

    public static class Api {

        public static final String USER = Url.API + Page.USER;

        public static final String LOGIN = USER + "/login";
        public static final String REGISTER = USER + "/register";
        public static final String LOGOUT = USER + "/logout";

        public static final String FORGOT_PASSWORD = USER + "/forgot_password";

        public static final String SEND_PASSWORD_EMAIL = USER + "/send_password_email";
        public static final String SEND_PASSWORD_SMS = USER + "/send_password_sms";

        public static final String ROLE = USER + "/role";
        public static final String ROLE_AUTHORITY = USER + "/role_authority";
        public static final String ROLE_VIEW_MENU = USER + "/role_view_menu";
        public static final String ROLE_VIEW_MENU_CATEGORY = USER + "/role_view_menu_category";
        public static final String USER_ROLE = USER + "/user_role";

        public static final String DEPARTMENT = USER + "/department";

        public static final String API = USER + "/api";
        public static final String API_CATEGORY = USER + "/api_category";

        public static final String VIEW_MENU = USER + "/view_menu";
        public static final String VIEW_MENU_CATEGORY = USER + "/view_menu_category";

        public static final String VIEW_PAGE = USER + "/view_page";
        public static final String VIEW_PAGE_CATEGORY = USER + "/view_page_category";
        public static final String VIEW_PAGE_API = USER + "/view_page_api";

        public static final String VIEW_PAGE_COMPONENT = USER + "/view_page_component";
        public static final String VIEW_PAGE_COMPONENT_API = USER + "/view_page_component_api";

    }

}

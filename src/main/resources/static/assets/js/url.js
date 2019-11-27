
var Url = Url || {};
Url.PAGE = _contextPath;
if(Url.PAGE.lastIndexOf('/') === 0 && Url.PAGE.length === 1) {
    Url.PAGE = '';
}
Url.API = Url.PAGE + '/api';

// System
{
    Url.System = {};
    page = {};
    page.SYSTEM = Url.PAGE + '/system';
    page.DICTIONARY = page.SYSTEM + '/dictionary';
    page.DICTIONARY_CATEGORY = page.SYSTEM + '/dictionary_category';
    page.OPERATION_LOG = page.SYSTEM + '/operation_log';
    page.FILE = page.SYSTEM + '/file';
    Url.System.Page = page;

    api = {};
    api.SYSTEM = Url.API + '/system';
    api.DICTIONARY = api.SYSTEM + '/dictionary';
    api.DICTIONARY_CATEGORY = api.SYSTEM + '/dictionary_category';
    api.OPERATION_LOG = api.SYSTEM + '/operation_log';
    api.FILE = api.SYSTEM + '/file';
    Url.System.Api = api;
}

// User
{
    Url.User = {};
    page = {};
    page.USER = Url.PAGE + '/user';
    page.LOGIN = page.USER + '/login';
    page.LOGOUT = page.USER + '/logout';
    page.ROLE = page.USER + '/role';
    page.ROLE_AUTHORITY = page.USER + '/role_authority';
    page.ROLE_VIEW_MENU = page.USER + '/role_view_menu';
    page.VIEW_MENU = page.USER + '/view_menu';
    page.VIEW_MENU_CATEGORY = page.USER + '/view_menu_category';
    page.USER_ROLE = page.USER + '/user_role';
    page.DEPARTMENT = page.USER + '/department';
    page.VIEW_PAGE = page.USER + '/view_page';
    page.VIEW_PAGE_CATEGORY = page.USER + '/view_page_category';
    page.VIEW_PAGE_API = page.USER + '/view_page_api';
    page.VIEW_PAGE_COMPONENT = page.USER + '/view_page_component';
    page.VIEW_PAGE_COMPONENT_API = page.USER + '/view_page_component_api';
    page.API = page.USER + '/api';
    page.API_CATEGORY = page.USER + '/api_category';
    Url.User.Page = page;

    api = {};
    api.USER = Url.API + '/user';
    api.LOGIN = api.USER + '/login';
    api.LOGOUT = api.USER + '/logout';
    api.ROLE = api.USER + '/role';
    api.ROLE_AUTHORITY = api.USER + '/role_authority';
    api.ROLE_VIEW_MENU = api.USER + '/role_view_menu';
    api.ROLE_VIEW_MENU_CATEGORY = api.USER + '/role_view_menu_category';
    api.VIEW_MENU = api.USER + '/view_menu';
    api.VIEW_MENU_CATEGORY = api.USER + '/view_menu_category';
    api.USER_ROLE = api.USER + '/user_role';
    api.DEPARTMENT = api.USER + '/department';
    api.VIEW_PAGE = api.USER + '/view_page';
    api.VIEW_PAGE_CATEGORY = api.USER + '/view_page_category';
    api.VIEW_PAGE_API = api.USER + '/view_page_api';
    api.VIEW_PAGE_COMPONENT = api.USER + '/view_page_component';
    api.VIEW_PAGE_COMPONENT_CATEGORY = api.USER + '/view_page_component_category';
    api.VIEW_PAGE_COMPONENT_API = api.USER + '/view_page_component_api';
    api.API = api.USER + '/api';
    api.API_CATEGORY = api.USER + '/api_category';
    Url.User.Api = api;
}

// Mail
{
    Url.Mail = {};
    page = {};
    page.MAIL = Url.PAGE + '/mail';
    Url.Mail.Page = page;

    api = {};
    api.MAIL = Url.API + '/mail';
    Url.Mail.Api = api;
}

// Quartz
{
    Url.Quartz = {};
    page = {};
    page.QUARTZ = Url.PAGE + '/quartz';
    page.JOB = page.QUARTZ + '/job';
    page.JOB_RUNTIME_LOG = page.QUARTZ + '/job_runtime_log';
    Url.Quartz.Page = page;

    api = {};
    api.QUARTZ = Url.API + '/quartz';
    api.JOB = api.QUARTZ + '/job';
    api.JOB_RUNTIME_LOG = api.QUARTZ + '/job_runtime_log';
    Url.Quartz.Api = api;
}


// function getDictionaryCode(field, codeSlug){
//     ajax({url:api.system+'/dictionary/getCode/byFieldAndCodeSlug',data:{field:field,codeSlug:codeSlug},success:function (result) {
//             if(result.data){
//                 return result.data;
//             }
//         }})
// }

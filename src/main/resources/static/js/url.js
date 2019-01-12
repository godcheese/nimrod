

var Url = {};
Url.PAGE = _contextPath;
if(Url.PAGE.lastIndexOf('/') === 0 && Url.PAGE.length === 1) {
    Url.PAGE = '';
}
Url.API = Url.PAGE + '/api';

// 页面 url
var Page = {};
{
    var page = Url.PAGE ;
    Page.SYSTEM = page + '/system';
    Page.USER = page + '/user';

    {
        system = {};
        system.VIEW_PAGE = Page.SYSTEM + '/view_page';
        system.VIEW_PAGE_CATEGORY = Page.SYSTEM + '/view_page_category';
        system.VIEW_PAGE_API = Page.SYSTEM + '/view_page_api';
        system.VIEW_PAGE_COMPONENT = Page.SYSTEM + '/view_page_component';
        system.VIEW_PAGE_COMPONENT_API = Page.SYSTEM + '/view_page_component_api';

        system.API = Page.SYSTEM + '/api';
        system.API_CATEGORY = Page.SYSTEM + '/api_category';

        system.DICTIONARY = Page.SYSTEM + '/dictionary';
        system.DICTIONARY_CATEGORY = Page.SYSTEM + '/dictionary_category';

        system.MAIL = Page.SYSTEM + '/mail';

        system.OPERATION_LOG = Page.SYSTEM + '/operation_log';

        system.ATTACHMENT = Page.SYSTEM + '/attachment';

        Page.System = system;
    }

    {
        user = {};
        user.LOGIN = Page.USER + '/login';
        user.LOGOUT = Page.USER + '/logout';

        user.ROLE = Page.USER + '/role';
        user.ROLE_AUTHORITY = Page.USER + '/role_authority';

        user.VIEW_MENU = Page.USER + '/view_menu';
        user.VIEW_MENU_CATEGORY = Page.USER + '/view_menu_category';

        user.USER_ROLE = Page.USER + '/user_role';

        user.DEPARTMENT = Page.USER + '/department';

        Page.User = user;
    }

}

// api url
var Api = {};
{
    var api = Url.API;
    Api.SYSTEM = api + '/system';
    Api.USER = api + '/user';

    {
        var system = {};
        system.VIEW_PAGE = Api.SYSTEM + '/view_page';
        system.VIEW_PAGE_CATEGORY = Api.SYSTEM + '/view_page_category';
        system.VIEW_PAGE_API = Api.SYSTEM + '/view_page_api';
        system.VIEW_PAGE_COMPONENT = Api.SYSTEM + '/view_page_component';
        system.VIEW_PAGE_COMPONENT_CATEGORY = Api.SYSTEM + '/view_page_component_category';
        system.VIEW_PAGE_COMPONENT_API = Api.SYSTEM + '/view_page_component_api';

        system.API = Api.SYSTEM + '/api';
        system.API_CATEGORY = Api.SYSTEM + '/api_category';

        system.DICTIONARY = Api.SYSTEM + '/dictionary';
        system.DICTIONARY_CATEGORY = Api.SYSTEM + '/dictionary_category';

        system.MAIL = Api.SYSTEM + '/mail';

        system.OPERATION_LOG = Api.SYSTEM + '/operation_log';

        // system.REQUEST_LOG = Api.SYSTEM + '/operation_log';

        system.ATTACHMENT = Api.SYSTEM + '/attachment';

        Api.System = system;
    }

    {
        var user = {};
        user.LOGIN = Api.USER + '/login';
        user.LOGOUT = Api.USER + '/logout';

        user.ROLE = Api.USER + '/role';
        user.ROLE_AUTHORITY = Api.USER + '/role_authority';

        user.VIEW_MENU = Api.USER + '/view_menu';
        user.VIEW_MENU_CATEGORY = Api.USER + '/view_menu_category';

        user.USER_ROLE = Api.USER + '/user_role';

        user.DEPARTMENT = Api.USER + '/department';

        Api.User = user;
    }

}


// function getDictionaryCode(field, codeSlug){
//     ajax({url:api.system+'/dictionary/getCode/byFieldAndCodeSlug',data:{field:field,codeSlug:codeSlug},success:function (result) {
//             if(result.data){
//                 return result.data;
//             }
//         }})
// }

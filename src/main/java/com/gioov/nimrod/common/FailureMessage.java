package com.gioov.nimrod.common;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public enum FailureMessage implements com.gioov.common.web.http.FailureMessage {

    /**
     * 错误代码规则
     *
     * 404 + 01-99 + 0-3 + 001-999 =9位
     *  404：返回的请求状态码 HttpStatus.NOT_FOUND
     *  00-99：表示功能模块，00=表示通用模块
     *  0-3：0=无法分类的操作，1=新增（增），2=删除（删），3=保存（改），4=NOT_FOUND（查）
     *  001-999：失败信息在模块里排名第几。
     */

    /**
     * 用户=01
     */

    /**
     * 用户登录失败
     */
    LOGIN_FAIL("登录失败，帐号或密码错误", 404010001),

    /**
     * 用户登录失败
     */
    LOGIN_FAIL_USER_IS_DISABLED("登录失败，帐号未启用", 404010001),

    /**
     * 用户注销失败
     */
    LOGOUT_FAIL("注销失败", 404010002),

//    ADD_FAIL("新增失败", 404001001),
//
//    DELETE_FAIL("删除失败", 404002002),
//
//    SAVE_FAIL("保存失败", 404003003),

    /**
     * API 分类=02
     */
    DELETE_API_CATEGORY_FAIL1("删除失败，该分类下存在子级分类", 404022001),
    DELETE_API_CATEGORY_FAIL2("删除失败，该分类下存在 API", 404022002),

    /**
     * API=03
     */
    ADD_API_AUTHORITY_FAIL("新增失败，权限（authority）已存在", 404031001),

    /**
     * 数据字典分类=04
     */
    DELETE_DICTIONARY_CATEGORY_FAIL1("删除失败，该分类下存在子级分类", 404042001),
    DELETE_DICTIONARY_CATEGORY_FAIL2("删除失败，该分类下存在字典", 404042002),

    /**
     * 视图页面分类=05
     */
    DELETE_VIEW_PAGE_CATEGORY_FAIL1("删除失败，该分类下存在子级分类", 404052001),
    DELETE_VIEW_PAGE_CATEGORY_FAIL2("删除失败，该分类下存在页面", 404052002),

    /**
     * 视图页面=06
     */

    ADD_VIEW_PAGE_AUTHORITY_FAIL("新增失败，权限（authority）已存在", 404061001),

    /**
     * 视图页面组件=07
     */
    ADD_VIEW_PAGE_COMPONENT_AUTHORITY_FAIL("新增失败，权限（authority）已存在", 404071001),

    /**
     * 角色=08
     */
    ADD_ROLE_VALUE_FAIL("新增失败，角色值已存在", 404081001),
    DELETE_ROLE_FAIL_USER_HAS_EXISTED("删除失败，该角色下存在用户", 404082002),

    /**
     * 视图菜单分类=09
     */
    DELETE_VIEW_MENU_CATEGORY_FAIL1("删除失败，该分类下存在子级分类", 404092001),
    DELETE_VIEW_MENU_CATEGORY_FAIL2("删除失败，该分类下存在菜单", 404092002),
    ;

    private String message;
    private int code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    FailureMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
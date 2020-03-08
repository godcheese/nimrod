$(window).load(function () {
    $("#loading").fadeOut();
});

$(function () {
    parentViewMenuCategory();
    addTab('#tabs', '工作台', '/system/workbench', 'iconfont icon-home', false, -1);
    $('#logoutButton').click(function () {
        if (window.confirm('确定要注销登录吗？')) {
            expressui.ajax({
                // dataType: 'json',
                url: Url.User.Api.LOGOUT,
                success: function (data) {
                    window.location.href = Url.User.Page.LOGIN;
                },
                error: function (xhr) {
                    alert("注销失败");
                }
            });
        }

    });
});

function pageTurning() {
    var page = 0, navItemHeight = 50, pages = ($('.nav-wrap-ul').height() / navItemHeight) - 1;
    if (page < pages) {
        $('.nav-prev,.nav-next').show();
    }

    $(document).on('click', '.nav-prev,.nav-next', function () {
        if ($(this).hasClass('disabled')) return;
        if ($(this).hasClass('nav-next')) {
            page++;
            $('.nav-wrap-ul').stop().animate({'margin-top': - navItemHeight * page}, 200);
            if (page === pages) {
                $(this).addClass('disabled');
                $('.nav-prev').removeClass('disabled');
            } else {
                $('.nav-prev').removeClass('disabled');
            }

        } else {
            page--;
            $('.nav-wrap-ul').stop().animate({'margin-top': - navItemHeight * page}, 200);
            if (page === 0) {
                $(this).addClass('disabled');
                $('.nav-next').removeClass('disabled');
            } else {
                $('.nav-next').removeClass('disabled');
            }
        }
    });
}

function removeSelf() {
    var parent = document.getElementsByTagName('body')[0];
    var getElementsByClassName = function (searchClass, node, tag) {
        if (document.getElementsByClassName) {
            var nodes = (node || document).getElementsByClassName(searchClass), result = [];
            for (var i = 0; node === nodes[i++];) {
                if (tag !== "*" && node.tagName === tag.toUpperCase()) {
                    result.push(node)
                }
            }
            return result
        } else {
            node = node || document;
            tag = tag || "*";
            result = [];
            var classes = searchClass.split(" "),
                elements = (tag === "*" && node.all) ? node.all : node.getElementsByTagName(tag),
                patterns = [],
                current,
                match;
            var i = classes.length;
            while (--i >= 0) {
                patterns.push(new RegExp("(^|\\s)" + classes[i] + "(\\s|$)"));
            }
            var j = elements.length;
            while (--j >= 0) {
                current = elements[j];
                match = false;
                for (var k = 0, kl = patterns.length; k < kl; k++) {
                    match = patterns[k].test(current.className);
                    if (!match) break;
                }
                if (match) result.push(current);
            }
            return result;
        }
    };
    var happy = getElementsByClassName('browser-happy')[0];
    parent.removeChild(happy);
}

/**
 * @desc 父级菜单分类
 */
function parentViewMenuCategory() {
    var parentViewMenuCategory = $('#parentViewMenuCategory');
    expressui.ajax({
        url: Url.User.Api.VIEW_MENU_CATEGORY + '/list_all_parent_by_user_id/' + _user.id,
        success: function (data) {
            if (data) {
                var viewMenuCategory = data;
                if (viewMenuCategory) {
                    for (var i = 0; i < viewMenuCategory.length; i++) {
                        var id = viewMenuCategory[i].id;
                        var name = viewMenuCategory[i].name;
                        var icon = viewMenuCategory[i].icon;
                        if (i === 0) {
                            // 第一个父级菜单默认选中
                            parentViewMenuCategory.append('<li class="parent-view-menu-category nav-item selected" data-view-menu-category="' + id + '"><a href="javascript:"><i class="' + icon + '"></i><span class="nav-item-title">' + name + '</span> </a> </li>')
                        } else {
                            parentViewMenuCategory.append('<li class="parent-view-menu-category nav-item" data-view-menu-category="' + id + '"><a href="javascript:"><i class="' + icon + '"></i><span class="nav-item-title">' + name + '</span> </a> </li>')
                        }
                    }
                }

                pageTurning();

                $('.parent-view-menu-category').on('click', function () {
                    var viewMenuCategoryId = $(this).data('view-menu-category');
                    childViewMenuCategory(this, viewMenuCategoryId);

                    var viewMenuCategoryName = $(this).find('span').html();
                    // $($('#layout').layout('panel', 'west')).panel({title: viewMenuCategoryName});

                });

                // 默认点击第一个父级菜单分类，并显示其所有子级菜单分类
                $('.parent-view-menu-category').eq('0').trigger('click');

            }
        }
    });
}

/**
 * @desc 显示二级子菜单菜单
 * @param currentSelector
 * @param viewMenuCategoryId
 */
function childViewMenuCategory(currentSelector, viewMenuCategoryId) {
    removeAllFirstMenuSelectedClass(currentSelector);
    removeAccordionPanel();
    var slideMenu = $('.slide-menu');
    if (!viewMenuCategoryId) {
        return;
    }

    expressui.ajax({
        url: Url.User.Api.VIEW_MENU_CATEGORY + '/list_all_child_by_parent_id_and_user_id',
        data: {
            parentId: viewMenuCategoryId,
            userId: _user.id
        },
        success: function (data) {
            if (data) {
                var childViewMenuCategory = data;
                for (var i = 0; i < childViewMenuCategory.length; i++) {
                    var childViewMenuCategoryId = childViewMenuCategory[i].id;
                    childViewMenuCategory[i].selected = (i === 0);

                    childViewMenuCategory[i].title = childViewMenuCategory[i].name;
                    childViewMenuCategory[i].iconCls = childViewMenuCategory[i].icon;
                    childViewMenuCategory[i].content = '<div id="childViewMenuCategoryAndViewMenu_' + childViewMenuCategoryId + '"></div>';
                    slideMenu.accordion('add', childViewMenuCategory[i]);

                    childViewMenuCategoryAndViewMenu('#childViewMenuCategoryAndViewMenu_' + childViewMenuCategoryId, childViewMenuCategoryId);
                }
            }
        }
    });

}

/**
 * @desc 显示三级子菜单和四级子菜单
 * @param selector
 * @param parentId
 */
function childViewMenuCategoryAndViewMenu(selector, parentId) {
    expressui.ajax({
        url: Url.User.Api.VIEW_MENU_CATEGORY + '/list_all_child_view_menu_category_and_view_menu_by_parent_id_and_user_id',
        data: {
            parentId: parentId,
            userId: _user.id
        },
        success: function (data) {
            if (data) {
                var childViewMenuCategoryAndViewMenu = data;
                $(selector).tree({
                    loadFilter: function (data) {
                        childViewMenuCategoryAndViewMenu = data.data ? data.data : data;
                        for (var i = 0; i < childViewMenuCategoryAndViewMenu.length; i++) {
                            if (!childViewMenuCategoryAndViewMenu[i].url) {
                                childViewMenuCategoryAndViewMenu[i].state = 'closed';
                            } else {
                                childViewMenuCategoryAndViewMenu[i].state = 'open';
                            }
                            childViewMenuCategoryAndViewMenu[i].text = childViewMenuCategoryAndViewMenu[i].name;
                        }
                        return childViewMenuCategoryAndViewMenu;
                    },
                    data: childViewMenuCategoryAndViewMenu,
                    lines: false,
                    animate: true,
                    onBeforeExpand: function (node, param) {
                        // 列出四级子菜单，直接可点击的菜单
                        $(selector).tree('options').url = Url.User.Api.VIEW_MENU_CATEGORY + '/list_all_child_view_menu_category_and_view_menu_by_parent_id_and_user_id?parentId=' + node.id + '&userId=' + _user.id;
                        $(selector).tree('options').method = 'get';
                    },
                    onClick: function (node) {
                        if (node.url) {
                            addTab('#tabs', node.text, node.url, node.iconCls, true, node.id);
                        } else {
                            $(selector).tree('toggle', node.target);
                        }
                    }
                    // onDblClick:function (node) {
                    // $(selector).tree('toggle',node.target);
                    // }
                });
            }
        }
    });
}

/**
 * @desc 移除左侧Accordion已有的菜单
 */
function removeAccordionPanel() {
    var slideMenu = $('.slide-menu');
    var panels = slideMenu.accordion('panels');
    var panelsLength = panels.length;
    if (panelsLength > 0) {
        for (var i = 0; i < panelsLength; i++) {
            slideMenu.accordion('remove', 0);
        }
    }
}

/**
 * @desc 移除所有一级父菜单的 selected class，然后 selected 当前被点击的一级菜单
 * @param currentSelector
 */
function removeAllFirstMenuSelectedClass(currentSelector) {
    $('.parent-view-menu-category').each(function (i, e) {
        $(e).removeClass('selected');
    });
    $(currentSelector).addClass('selected');
}

/**
 * @desc 添加 tab
 * @param tabsSelector
 * @param title
 * @param url
 * @param iconCls
 * @param closable
 * @param index
 */
function addTab(tabsSelector, title, url, iconCls, closable, index) {
    var tabs = $(tabsSelector).tabs('tabs');
    var tabsLength = tabs.length;
    index = tabsLength + 1;
    if ($(tabsSelector).tabs('exists', title)) {
        $(tabsSelector).tabs('select', title);

    } else {
        var lastMenuClickTime = util.cookie.get("menuClickTime");
        var nowTime = new Date().getTime();
        if ((nowTime - lastMenuClickTime) >= 600) {
            util.cookie.set("menuClickTime", new Date().getTime());
            $(tabsSelector).tabs('add', {
                id: Math.random(),
                title: title,
                index: index,
                selected: true,
                closable: (closable === undefined) || (closable === null) || (closable === true),
                content: '<iframe src="' + Url.PAGE + url + '" scrolling="auto" frameborder="0" class="tab-iframe"></iframe>', // iframe框架内加载
                // href:url, // 可能会出现元素重复加载的情况，js、css等都会出现问题
                // iconCls:'iconfont icon-file',
                iconCls: (iconCls === undefined) ? 'iconfont icon-file' : iconCls,
                fit: true,
                border: false,
                cache: false
            });
        } else {
            $.messager.show({
                title: '信息',
                msg: '操作过快，请稍后重试！'
            });
        }
    }
}

/**
 * @desc 跳转至指定 index
 * @param tabsSelector
 * @param index
 */
function tabsTo(tabsSelector, index) {
    $(tabsSelector).tabs('select', index);
}

/**
 * @desc 关闭选中tab
 * @param tabsSelector
 */
function closeSelectedTab(tabsSelector) {
    var tab = $(tabsSelector).tabs('getSelected');
    var index = $(tabsSelector).tabs('getTabIndex', tab);
    if (index !== 0) {
        $(tabsSelector).tabs('close', index);
    }
}

/**
 * @desc 刷新指定 tabs 内的 tab
 * @param tabsSelector
 */
function refreshTabIframe(tabsSelector) {
    var tab = $(tabsSelector).tabs('getSelected');
    var iframe = tab.find('iframe')[0];
    iframe.contentWindow.location.href = iframe.src;
}

function onContextMenu(event, title, index) {
    event.preventDefault();
    if (index >= 0) {
        tabsTo('#tabs', index);
        $('#tabsContextMenu').menu('show', {
            left: event.pageX,
            top: event.pageY
        }).data('tabTitle', title);
    }
}

function tabsContextMenu(menu, tabsSelector, type) {
    var tabs = tabsSelector.tabs('tabs');
    var tabsTitle = [];
    var refreshTab, refreshIframe;
    var i;

    $.each(tabs, function (i, e) {
        var options = $(e).panel('options');
        if (options.closable)
            tabsTitle.push(options.title);
    });
    var currentTabTitle = $(menu).data('tabTitle');
    var currentTabIndex = tabsSelector.tabs('getTabIndex', tabsSelector.tabs('getTab', currentTabTitle));
    switch (type) {
        case 'tabRefresh': // 重新加载
            refreshTab = tabsSelector.tabs('getSelected');
            refreshIframe = refreshTab.find('iframe')[0];
            refreshIframe.contentWindow.location.href = refreshIframe.src;
            break;
        case 'tabCloseCurrent':// 关闭标签页
            if (currentTabIndex === 0) {
                $.messager.show({
                    title: '操作提示',
                    msg: '工作台不允许关闭'
                });
            }
            if (currentTabIndex > 0) {
                tabsSelector.tabs('close', currentTabTitle);
            }
            break;
        case 'tabCloseAll':// 关闭所有标签页
            for (i = 0; i < tabsTitle.length; i++) {
                tabsSelector.tabs('close', tabsTitle[i]);
            }
            break;
        case 'tabCloseOther':// 关闭其他标签页
            for (i = 0; i < tabsTitle.length; i++) {
                if (currentTabTitle !== tabsTitle[i])
                    tabsSelector.tabs('close', tabsTitle[i]);
            }
            tabsSelector.tabs('select', currentTabTitle);
            break;
        case 'tabCloseRight':// 关闭右侧标签页
            for (i = currentTabIndex; i < tabsTitle.length; i++) {
                tabsSelector.tabs('close', tabsTitle[i]);
            }
            tabsSelector.tabs('select', currentTabTitle);
            break;
        case 'tabCloseLeft': //关闭左侧标签页
            for (i = 0; i < currentTabIndex - 1; i++) {
                tabsSelector.tabs('close', tabsTitle[i]);
            }
            tabsSelector.tabs('select', currentTabTitle);
            break;

        case 7: //在新窗口打开
            refreshTab = tabsSelector.tabs('getSelected');
            refreshIframe = refreshTab.find('iframe')[0];
            window.open(refreshIframe.src);
            break;
    }
}
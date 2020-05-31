/**
 * Expressui
 * jQuery EasyUI extend plugin
 * @version v1.0.0
 * @author godcheese <godcheese@outlook.com>
 * @uri http://www.github.com/godcheese/expressui
 */

/**
 *
 响应状态码
 100~199：信息状态码，代表请求已被接受，需要继续处理。
 200~299：成功状态码，代表请求已成功被服务器接收、理解、并接受。
 300~399：重定向状态码，代表需要客户端采取进一步的操作才能完成请求。
 400~499：客户端错误状态码，代表了客户端看起来可能发生了错误，妨碍了服务器的处理。
 500~599：服务器错误状态码，代表了服务器在处理请求的过程中有错误或者异常状态发生，也有可能是服务器意识到以当前的软硬件资源无法完成对请求的处理。

 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
 202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
 204 NO CONTENT - [DELETE]：用户删除数据成功。
 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。

 如果状态码是4xx，就应该向用户返回出错信息。一般来说，返回的信息中将error作为键名，出错信息作为键值即可。

 错误处理（Error handling）
 {
     error: "Invalid API key"
     errorno: 4041,
 }

 {
  "message": "Requires authentication",
  "documentation_url": "https://developer.github.com/v3"
}

 针对不同操作，服务器向用户返回的结果应该符合以下规范。

 GET /collection：返回资源对象的列表（数组）
 GET /collection/resource：返回单个资源对象
 POST /collection：返回新生成的资源对象
 PUT /collection/resource：返回完整的资源对象
 PATCH /collection/resource：返回完整的资源对象
 DELETE /collection/resource：返回一个空文档
 *
 */

var expressui = expressui || {};

(function (_expressui) {

    if (!window.jQuery) {
        console.error('expressui 需要 jQuery 支持');
        return;
    }

    $.extend(_expressui, {
        util: {},
        datagrid: {},
        dialog: {},
        treegrid: {},
        tree: {},
        messager: {},
        grid: {},
        response: {
            CODE: 'code',
            MESSAGE: 'message',
            DATA: 'data',
            EXCEPTION: 'exception'
        },
        httpStatus: {}
    });

    _expressui.util.toObject = function (json) {
        return eval('(' + json + ')');
    };

    _expressui.util.initSelector = function (options) {
        var selector;
        if (options.selector) {
            selector = options.selector;
            // 初始 selector，仅支持 #id 和 .className 两种选择器
            if (selector.charAt(0) !== '#' && selector.charAt(0) !== '.') {
                throw new Error('Selector ' + selector + ' is not id or class.');
            }
            if (!options[0]) {
                _expressui.util.createElement(options.selector);
            }
        }
        return selector;
    };

    /**
     * 创建 html 元素
     * @param selector
     * @param tag
     * @param innerHTML
     * @returns {Node}
     */
    _expressui.util.createElement = function (selector, tag, innerHTML) {

        /**
         * 生成 node，没 selector 指定 node 就生成一个
         * @param selector
         * @param tag
         * @returns {*}
         * @private
         */
        function _selectorNode(selector, tag) {
            var _selectorId, _selectorClassName, _node;
            if (typeof selector === 'string') {
                var _symbol = selector.charAt(0);
                if (_symbol) {
                    switch (_symbol) {
                        case '#':
                            _selectorId = selector.substring(1, selector.length);
                            _node = document.getElementById(_selectorId);

                            break;
                        case '.':
                            _selectorClassName = selector.substring(1, selector.length);

                            _node = document.getElementsByClassName(_selectorClassName);
                            _node = _node[0];
                            break;
                        default:
                            break;
                    }


                    if (!_node) {
                        if (tag) {
                            _node = document.createElement(tag);
                        } else {
                            _node = document.createElement('div');
                        }

                        if (_selectorId) {
                            _node.id = _selectorId;
                        }

                        if (_selectorClassName) {
                            _node.className = _selectorClassName;
                        }

                        // 标记这些标签为自动生成的
                        if (_node) {
                            _node.setAttribute("data-create", "auto");
                        }
                    }
                }


            }

            return _node;
        }

        var _body = document.body;

        var _node = _selectorNode(selector);

        if (innerHTML) {
            _node.innerHTML = innerHTML;
        }

        return _body.appendChild(_node);
    };

    _expressui.util.replaceAllPlaceholder = function (string, searchValue, replaceValue) {
        if (string) {
            string = string + '';
        }
        return string.replace(new RegExp('{' + searchValue + '}', "gm"), replaceValue);

    };

    _expressui.ajax = function (url, method, data, success, error) {
        var defaults = {
            dataType: 'JSON',
            success: function (XMLHttpRequest, statusText) {
            },
            error: function (XMLHttpRequest, statusText, errorThrown) {
            }
        };

        if (typeof url === 'object') {
            $.extend(defaults, url);
        } else {
            defaults.url = url;
        }

        if (typeof method === 'string') {
            defaults.method = method;
        }

        if (typeof data === 'object') {
            defaults.data = data;
        }

        if (typeof success === 'function') {
            defaults.success = success;
        }

        if (typeof error === 'function') {
            defaults.error = error;
        }
        return $.ajax(defaults);
    };
    _expressui.replaceUrlPlaceholder = function (url, object, prefix) {
        if (url && object) {
            for (var key in object) {
                if (object.hasOwnProperty(key)) {
                    var urlPlaceholder = key;
                    if (prefix) {
                        urlPlaceholder = prefix + "." + key;
                    }
                    url = _expressui.util.replaceAllPlaceholder(url, urlPlaceholder, object[key]);
                }
            }
        }
        return url;
    };

    _expressui.response._httpStatus = function (code, message) {
        var json = '{"' + _expressui.response.CODE + '":' + code + ',"' + _expressui.response.MESSAGE + '":"' + message + '"}';
        return _expressui.util.toObject(json);
    };
    _expressui.response.httpStatus = {
        OK: _expressui.response._httpStatus(200, 'OK'),
        NOT_FOUND: _expressui.response._httpStatus(404, 'NOT FOUND')
    };
    _expressui.response._ok = function (responseResult, fnOkCallback, fnNotOkCallback, fnThenCallback) {

        if (responseResult) {

            var OK = _expressui.response.httpStatus.OK.code;

            var data = responseResult.hasOwnProperty(_expressui.response.DATA) ? responseResult[_expressui.response.DATA] : undefined;

            var message = responseResult.hasOwnProperty(_expressui.response.MESSAGE) ? responseResult[_expressui.response.MESSAGE] : undefined;

            var code = responseResult.hasOwnProperty(_expressui.response.CODE) ? responseResult[_expressui.response.CODE] : undefined;

            if (code === OK) {
                if (typeof fnOkCallback === 'function') {
                    fnOkCallback(data, message, code);
                }
            } else {
                if (typeof fnNotOkCallback === 'function') {
                    fnNotOkCallback(data, message, code);
                }
            }

            if (typeof fnThenCallback === 'function') {
                fnThenCallback(data, message, code);
            }

        }

    };
    _expressui.response.ok = function (responseResult, fnOkCallback, fnNotOkCallback, fnThenCallback) {
        _expressui.response._ok(responseResult, function (data, message, code) {
            if (fnOkCallback) {
                fnOkCallback(data, message, code);
            }
        }, function (data, message, code) {
            if (fnNotOkCallback) {
                fnNotOkCallback(data, message, code);
            }
        }, function (data, message, code) {
            if (fnThenCallback) {
                fnThenCallback(data, message, code);
            }
        });


    };
    _expressui.response.okHasMessage = function (responseResult, statusText, XMLHttpRequest, fnOkCallback, fnNotOkCallback, fnThenCallback) {
        _expressui.response._ok(responseResult, function (responseResult, statusText, XMLHttpRequest) {
            if (typeof fnOkCallback === 'function') {
                fnOkCallback(responseResult, statusText, XMLHttpRequest);
            }
        }, function (responseResult, statusText, XMLHttpRequest) {
            if (typeof fnNotOkCallback === 'function') {
                fnNotOkCallback(responseResult, statusText, XMLHttpRequest);
            }
            // $.messager.show({title:'信息', msg:message});
            // $.messager.alert('信息', message, 'error');
        }, function (responseResult, statusText, XMLHttpRequest) {
            if (fnThenCallback) {
                fnThenCallback(responseResult, statusText, XMLHttpRequest);
            }
        });
    };

    /**
     * 创建并打开一个对话框实例
     * @type {string}
     */
    _expressui.dialog.create = 'create';

    /**
     * 对话框新增数据提交表单方法
     * @type {string}
     */
    _expressui.dialog.add = 'add';

    /**
     * 对话框保存数据提交表单方法
     * @type {string}
     */
    _expressui.dialog.save = 'save';

    /**
     * 对话框实例关闭
     * @type {string}
     */
    _expressui.dialog.close = 'close';

    /**
     * 创建表格实例
     * @type {string}
     */
    _expressui.grid.create = 'create';

    /**
     * 删除多项
     * @type {string}
     */
    _expressui.grid.deleteChecked = 'deleteChecked';

    // 删除一项
    _expressui.grid.deleteCheckedOne = 'deleteCheckedOne';

    /**
     * 获取必须一个勾选项，否则弹出警告信息
     * @type {string}
     */
    _expressui.grid.getCheckedOneOrShowAlert = 'getCheckedOneOrShowAlert';

    /**
     * 勾选一项或不勾选项都不弹出警告信息，否则弹出警告信息
     * @type {string}
     */
    _expressui.grid.getCheckedOneNoCheckedOrShowAlert = 'getCheckedOneNoCheckedOrShowAlert';

    /**
     * 获取勾选的一项
     * @type {string}
     */
    _expressui.grid.getCheckedOne = 'getCheckedOne';

    /**
     * 获取勾选的多项
     * @type {string}
     */
    _expressui.grid.getChecked = 'getChecked';

    // 获取至少勾选一项，否则弹出警告信息
    _expressui.grid.getCheckedLessOneOrShowAlert = 'getCheckedLessOneOrShowAlert';

    // post 勾选或不勾选都可以
    _expressui.grid.post = 'post';

    // post 必须勾选一项或一项以上，否则弹出警告信息
    _expressui.grid.postCheckedLessOneOrShowAlert = 'postCheckedLessOneOrShowAlert';

    // post 必须只勾选一项，否则弹出警告信息
    _expressui.grid.postCheckedMustOneOrShowAlert = 'postCheckedMustOneOrShowAlert';

    // _expressui.messager = {
    //     show: function (msg) {
    //         var options = {
    //             title: '信息',
    //             msg: '',
    //             timeout: 1000,
    //             showType: 'slide',
    //             modal: false,
    //             shadow: false,
    //             draggable: false,
    //             resizable: false,
    //             closed: true,
    //             style: {
    //                 left: "",
    //                 top: "",
    //                 right: 0,
    //                 zIndex: 999,
    //                 bottom: -document.body.scrollTop - document.documentElement.scrollTop
    //             },
    //             width: 260,
    //             height: 120,
    //             minHeight: 0,
    //             showSpeed: 600
    //         };
    //
    //         if (msg && typeof msg === 'string') {
    //             options.msg = msg;
    //         }
    //         $.extend(options, msg);
    //         $.messager.show(options);
    //     },
    //     confirm: function (msg, okCallback) {
    //         var options = {
    //             title: '信息',
    //             msg: '确认操作吗？',
    //             fn: function (ok) {}
    //         };
    //         if (msg && typeof msg === 'string') {
    //             options.msg = msg;
    //         }
    //         if (msg && (typeof okCallback === 'function')) {
    //             options.msg = msg;
    //             options.fn = function (ok) {
    //                 if (ok) {
    //                     okCallback(ok);
    //                 }
    //             }
    //         }
    //         if (msg && typeof msg === 'object') {
    //             $.extend(options, msg);
    //         }
    //         $.messager.confirm(options);
    //     },
    //     progress: function (msg) {
    //         var options = {};
    //         if (typeof msg === 'string') {
    //             options.msg = msg;
    //         }
    //         if (typeof msg === 'object') {
    //             $.extend(options, msg);
    //         }
    //
    //         if (msg === 'close') {
    //             $.messager.progress(msg);
    //         } else {
    //             $.messager.progress(options);
    //         }
    //     },
    //     alert: function (msg, icon) {
    //         var options = {
    //             title: '提示',
    //             msg: '',
    //             icon: 'warning'
    //         };
    //         if (typeof msg === 'string') {
    //             options.msg = msg;
    //         }
    //
    //         if (msg && icon) {
    //             options.msg = msg;
    //             options.icon = icon;
    //         }
    //
    //         if (typeof msg === 'object') {
    //             $.extend(options, msg);
    //         }
    //
    //         $.messager.alert(options);
    //     }
    // };

    _expressui.grid._reload = function (grid) {
        switch (grid.type) {
            case 'datagrid':
                if (typeof grid.data === 'object') {
                    $(grid.selector).datagrid('loadData', grid.data);
                } else {
                    if (grid.url) {
                        $(grid.selector).datagrid('reload', grid.url);
                    } else {
                        $(grid.selector).datagrid('reload');
                    }
                }
                break;
            case 'tree':
                if (typeof grid.data === 'object') {
                    $(grid.selector).tree('loadData', grid.data);
                } else {
                    if (grid.url) {
                        $(grid.selector).tree('options').url = grid.url;
                    }
                    $(grid.selector).tree('reload');
                }
                break;
            case 'treegrid':
                if (typeof grid.data === 'object') {
                    $(grid.selector).treegrid('loadData', grid.data);
                } else {
                    if (grid.url) {
                        $(grid.selector).treegrid('options').url = grid.url;
                    }
                    $(grid.selector).treegrid('reload');
                }
                break;
        }
    };

    _expressui.grid.reload = function (grid) {
        if (grid) {
            if (!grid.type) {
                for (var i = 0; i < grid.length; i++) {
                    _expressui.grid._reload(grid[i]);
                }
            } else {
                _expressui.grid._reload(grid);
            }
        }
    };

    _expressui.grid.getChecked = function (grid) {
        var checked;
        if (grid.type) {
            switch (grid.type) {
                case 'datagrid':
                    checked = $(grid.selector).datagrid('getChecked');
                    break;
                case 'treegrid':
                    checked = $(grid.selector).treegrid('getChecked');
                    break;
            }
        }
        return checked;
    };

    _expressui.grid.getCheckedOne = function (grid) {
        var checked = _expressui.grid.getChecked(grid);
        if (checked && checked.length > 0) {
            return checked[0];
        }
    };

    _expressui.grid.getCheckedHasMessage = function (grid) {
        var checked;
        if (grid.type) {
            checked = _expressui.grid.getChecked(grid);
            if (!checked) {
                $.messager.alert('信息', '请勾选要操作项', 'warning');
                return;
            }
        }
        return checked;
    };

    _expressui.grid.getCheckedOneHasMessage = function (grid) {
        var checked = _expressui.grid.getCheckedHasMessage(grid);
        if (checked) {
            if (checked.length === 1) {
                return checked[0];
            } else {
                if (checked.length <= 0) {
                    $.messager.alert('信息', '请勾选要操作项', 'warning');
                } else {
                    $.messager.alert('信息', '最多只能勾选一项操作', 'warning');
                }
            }
        }
    };

    _expressui.grid.appendToolbar = function (grid) {
        if (!grid.toolbar && grid.selector) {
            grid.toolbar = grid.selector + 'Toolbar';
        }
    };

    /**
     * express.grid.formatter(
     * value,
     * [{value:0,valueName:'未知'},{value:1,valueName:'男'}],
     * function() {}
     * );
     *
     * @param value
     * @param values
     * @param callback
     * @returns {undefined}
     */
    _expressui.grid.formatter = function (value, values, callback) {

        var valueName = undefined;

        function f(v, vs) {
            for (var i = 0; i < vs.length; i++) {
                if ((vs[i].value + "") === (v + "")) {
                    return vs[i].valueName;
                }
            }

        }

        if (typeof values === 'object') {
            valueName = f(value, values);
            if (!valueName) {
                var defaultValue = f('default', values);
                valueName = f(defaultValue, values);
            }
        }

        if (typeof callback === 'function') {
            valueName = callback(valueName, value, values);
        }

        return valueName;
    };

    _expressui.dialog._replacePlaceHolder = function (options, grid) {
        var checked;
        checked = _expressui.grid.getCheckedOne(grid);
        if (grid.prefix) {
            if (options.get) {
                if (typeof options.get === 'string') {
                    options.get.url = _expressui.replaceUrlPlaceholder(options.get, checked);
                    options.get.method = 'get';
                }
                if (typeof options.get === 'object') {
                    if (options.get.url) {
                        options.get.url = _expressui.replaceUrlPlaceholder(options.get.url, checked);
                    }
                    options.get.method = options.get.method ? options.get.method : 'get';
                }
            }
            if (options.save) {
                if (typeof options.save === 'string') {
                    options.save = _expressui.replaceUrlPlaceholder(options.save, checked, grid.prefix);
                }
                if (typeof options.save === 'object') {
                    if (options.save.url) {
                        options.save.url = _expressui.replaceUrlPlaceholder(options.save.url, checked, grid.prefix);
                    }
                    if (options.save.data) {
                        params = options.save.data;
                        paramArray = [];
                        for (var i = 0; i < params.length; i++) {
                            paramArray.push(_expressui.replaceUrlPlaceholder(params[i], checked, grid.prefix));
                        }
                        options.save.data = paramArray;
                    }
                }

                options.url = options.save.url;
            }
            if (options.href) {
                options.href = _expressui.replaceUrlPlaceholder(options.href, checked, grid.prefix);
            }
        } else {

            if (options.get) {
                if (typeof options.get === 'string') {
                    options.get.url = _expressui.replaceUrlPlaceholder(options.get, checked);
                    options.get.method = 'get';
                }

                if (typeof options.get === 'object') {
                    options.get.url = options.get.url ? _expressui.replaceUrlPlaceholder(options.get.url, checked) : '';
                    options.get.method = options.get.method ? options.get.method : 'get';
                }
            }

            if (options.save) {
                if (typeof options.save === 'string') {
                    options.save.url = _expressui.replaceUrlPlaceholder(options.save, checked);
                    options.save.method = 'post';
                }

                if (typeof options.save === 'object') {

                    options.save.url = options.save.url ? _expressui.replaceUrlPlaceholder(options.save.url, checked) : '';
                    options.save.method = options.save.method ? options.save.method : 'post';

                    if (options.save.data) {

                        // var params = Object.keys(options.save.data);
                        var params = Object.getOwnPropertyNames(options.save.data);
                        var paramArray = [];
                        for (i = 0; i < params.length; i++) {
                            if (options.save.data.hasOwnProperty(params[i])) {
                                var temp = {};
                                temp['name'] = params[i];
                                temp['value'] = _expressui.replaceUrlPlaceholder(options.save.data[params[i]], checked);
                                paramArray.push(temp);
                            }
                        }
                        options.save.data = paramArray;
                    }
                }

                options.url = options.save.url;
            }

            if (options.href) {
                options.href = _expressui.replaceUrlPlaceholder(options.href, checked);
            }
        }

    };

    _expressui.form = {};

    _expressui.form.submit = function (selector, url, method) {
        var defaultOptions = {type: 'post'};
        if (typeof selector === 'object') {
            $.extend(defaultOptions, selector);
        } else {
            defaultOptions.selector = selector || undefined;
            defaultOptions.url = url || undefined;
            defaultOptions.method = method || 'post';
        }
        var isValid = $(defaultOptions.selector).form('validate');
        if (isValid) {
            $.messager.progress({title: '请稍等', msg: '正在操作...'});
            var serializeArray = $(defaultOptions.selector).find('form').serializeArray();
            var paramArray = defaultOptions.data || [];
            for (var i = 0; i < paramArray.length; i++) {
                serializeArray.push(paramArray[i]);
            }
            try {
                expressui.ajax({
                    beforeSend: function () {
                        if (typeof defaultOptions.beforeSend === 'function') {
                            defaultOptions.beforeSend();
                        }
                    },
                    complete: function () {
                        if (typeof defaultOptions.complete === 'function') {
                            defaultOptions.complete();
                        }
                    },
                    url: defaultOptions.url || undefined,
                    type: defaultOptions.method || 'post',
                    data: serializeArray,
                    success: function (res, statusText, XMLHttpRequest) {
                        if (typeof defaultOptions.success === 'function') {
                            defaultOptions.success(res, statusText, XMLHttpRequest);
                        } else {
                            if (typeof defaultOptions.reset === 'string') {
                                $(defaultOptions.reset).form('clear');
                            }
                        }
                        if (typeof defaultOptions.success === 'string') {
                            $.messager.show({title: '信息', msg: defaultOptions.success});
                            if (typeof defaultOptions.reset === 'string') {
                                $(defaultOptions.reset).form('clear');
                            }
                        }
                        $.messager.progress('close');
                    },
                    error: function (XMLHttpRequest, statusText, errorThrown) {
                        if (defaultOptions.error) {
                            if (typeof defaultOptions.error === 'function') {
                                defaultOptions.error(XMLHttpRequest, statusText, errorThrown);
                            }
                            if (typeof defaultOptions.error === 'string') {
                                $.messager.alert('信息', defaultOptions.error, 'error');
                            }
                        } else {
                            $.messager.alert('信息', XMLHttpRequest.responseJSON.message, 'error');
                        }
                        $.messager.progress('close');
                    }
                })
            } catch (e) {
                $.messager.alert('信息', e.message, 'error');
            } finally {
                $.messager.progress('close');
            }
        }
    };

    _expressui.form.reset = function (selector) {
        return $(selector).form('clear');
    };


    /**
     * 避免组件移除窗口外
     * @param left
     * @param top
     */
    _expressui.onMove = function (left, top) {
        if (left < 1) {
            left = 1;
        }
        if (top < 1) {
            top = 1;
        }
        var width = parseInt($(this).parent().css('width')) + 14;
        var height = parseInt($(this).parent().css('height')) + 14;
        var right = left + width;
        var bottom = top + height;
        var browserWidth = $(window).width();
        var browserHeight = $(window).height();
        if (right > browserWidth) {
            left = browserWidth - width;
        }
        if (bottom > browserHeight) {
            top = browserHeight - height;
        }
        // 修正面板位置
        $(this).parent().css({
            left: left,
            top: top
        });
    };

})(expressui);

$.extend($.fn.dialog.defaults, {onMove: expressui.onMove});
$.extend($.fn.window.defaults, {onMove: expressui.onMove});
$.extend($.fn.panel.defaults, {onMove: expressui.onMove});

$.extend($.fn.panel.defaults, {
    /**
     * panel关闭时回收内存
     * tabs 引用 iframe，关闭 tab 时 iframe 不会被销毁从而导致内存不能释放，大量使用 tab、iframe 容易导致内存溢出
     */
    onBeforeDestroy: function () {
        var iframe = $('iframe', this);
        try {
            if (iframe.length > 0) {
                for (var i = 0; i < iframe.length; i++) {
                    iframe[i].src = '';
                    iframe[i].contentWindow.document.write('');
                    iframe[i].contentWindow.close();
                }
                iframe.remove();

                // IE 特有内存回收方法
                if (navigator.userAgent.indexOf('MSIE') > 0) {
                    try {
                        CollectGarbage();
                    } catch (e) {
                    }
                }
            }
        } catch (e) {

        }
    }
});

$.extend($.fn.calendar.defaults, {
    // 设置周一为一周开始日
    firstDay: 1
});

$.extend($.fn.dialog.defaults, {
    // dialog
    // title: '对话框',
    collapsible: true,
    minimizable: false,
    maximizable: true,
    resizable: true,
    toolbar: null,
    width: 400,
    height: 311,

    // window
    closable: true,
    closed: false,
    zIndex: 9000,
    draggable: true,
    shadow: true,
    inline: false,
    modal: true,
    border: true, // true,false,'thin','thick'
    constrain: false,
    // panel
    // id	null,
    iconCls: null,
    fit: false,
    content: null,
    halign: 'top', // top/left/right
    titleDirection: 'down', // up/down
    header: null,
    openAnimation: 'show',//  'slide','fade','show'
    openDuration: 0,
    closeAnimation: 'show', // 'slide','fade','show'
    closeDuration: 0,
    href: null,
    loadingMessage: '加载中…',
    method: 'get',
    queryParams: null,
    onLoad: function () {
        var _this = this;
        var options = $(this).dialog('options');
        $(this).wrapInner('<form method="' + (options.formMethod || 'post') + '"></form>');

        if (options.data) {

            if (typeof options.data === 'object') {
                $(_this).form('load', options.data);
            }

            if (typeof options.data === 'function') {
                $(_this).form('load', options.data());
            }

        } else {
            if (options.get) {
                $.ajax({
                    url: options.get.url,
                    type: options.get.method,
                    dataType: 'json',
                    success: function (data) {
                        $(_this).form('load', data);
                    }
                });
            }
        }
    },
    onClose: function () {
        // 由系统自动生成(带有：data-create="auto")的关闭后即时销毁
        if ($(this).data('create') === 'auto') {
            return $(this).dialog('destroy');
        }
    }

});

$.extend($.fn.dialog.methods, {

    /**
     * dialog.create
     * 创建并打开一个 dialog 对话框实例
     * @param jq
     * @param options
     * @returns {jQuery|*}
     */
    create: function (jq, options) {
        options.selector = expressui.util.initSelector(jq);
        if (!options.buttons) {
            options.buttons = [{
                text: '保存',
                iconCls: 'iconfont icon-save',
                handler: expressui.dialog.save
            }, {
                text: '关闭',
                iconCls: 'iconfont icon-close',
                handler: expressui.dialog.close
            }];
        }

        if (options.buttons) {
            var _buttons = options.buttons;
            for (var i = 0; i < _buttons.length; i++) {
                var _button = _buttons[i];
                if (typeof _button.handler === 'string') {
                    switch (_button.handler) {
                        case 'add':
                            var _buttonOptions = {
                                success: _button.success || undefined,
                                error: _button.error || undefined,
                                reload: _button.reload || undefined
                            };
                            options.buttons[i].handler = function () {
                                $(options.selector).dialog(expressui.dialog.add, _buttonOptions);
                            };
                            break;
                        case 'save':
                            _buttonOptions = {
                                success: _button.success || undefined,
                                error: _button.error || undefined,
                                reload: _button.reload || undefined
                            };
                            options.buttons[i].handler = function () {
                                $(options.selector).dialog(expressui.dialog.save, _buttonOptions);
                            };
                            break;
                        case 'close':
                            var _reload = _button.reload || undefined;
                            options.buttons[i].handler = function () {
                                $(options.selector).dialog(expressui.dialog.close, function () {
                                    // 按钮回调刷新表
                                    if (_reload) {
                                        expressui.grid.reload(_reload);
                                    }
                                });
                            };
                            break;
                    }

                }
            }
        }

        if (options.grid) {
            expressui.dialog._replacePlaceHolder(options, options.grid);
        }

        return $(options.selector).dialog(options);
    },

    /**
     * dialog.add
     * @param jq
     * @param options
     */
    add: function (jq, options) {
        var selector = expressui.util.initSelector(jq);
        var dialogOptions = $(selector).dialog('options');
        var isValid = $(selector).form('validate');
        if (isValid) {
            $.messager.progress({title: '请稍等', msg: '正在操作...'});
            var serializeArray = $(selector).find('form').serializeArray();
            var paramArray = typeof dialogOptions.save === 'object' ?
                (dialogOptions.save.data || []) : [];
            for (var i = 0; i < paramArray.length; i++) {
                serializeArray.push(paramArray[i]);
            }

            if (typeof dialogOptions.save === 'function') {

                try {
                    var sA = {};
                    for (i = 0; i < serializeArray.length; i++) {
                        sA[serializeArray[i].name] = serializeArray[i].value;
                    }

                    if (dialogOptions.save(sA) !== true) {
                        if (typeof options.success === 'function') {
                            options.success(data);
                        }

                        if (typeof options.success === 'string') {
                            $.messager.show({title: '信息', msg: options.success});
                            if (typeof options.reload === 'object') {
                                expressui.grid.reload(options.reload);
                            }
                        } else {
                            if (typeof options.reload === 'object') {
                                expressui.grid.reload(options.reload);
                            }
                        }
                        $(selector).dialog('close');
                    }
                } catch (e) {
                    console.error(e);
                } finally {
                    $.messager.progress('close');
                }

            } else {

                try {
                    expressui.ajax({
                        beforeSend: function () {
                        },
                        url: typeof dialogOptions.save === 'string' ? dialogOptions.save : dialogOptions.save.url,
                        type: dialogOptions.save.method || 'post',
                        data: serializeArray,

                        success: function (data, statusText, XMLHttpRequest) {
                            if (typeof options.success === 'function') {
                                options.success(data);
                            }

                            // 此处 bug 会出现多次 reload
                            // } else {
                            //     if (typeof options.reload === 'object') {
                            //         expressui.grid.reload(options.reload);
                            //     }
                            // }

                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }
                            $(selector).dialog('close');
                            $.messager.progress('close');
                        },
                        error: function (XMLHttpRequest, statusText, errorThrown) {
                            if (options.error) {
                                if (typeof options.error === 'function') {
                                    options.error(XMLHttpRequest, statusText, errorThrown);
                                }
                                if (typeof options.error === 'string') {
                                    $.messager.alert('信息', options.error, 'error');
                                }
                            } else {
                                $.messager.alert('信息', XMLHttpRequest.responseJSON.message, 'error');
                            }
                            $.messager.progress('close');
                        }
                    })
                } catch (e) {
                    $.messager.alert('信息', e.message, 'error');
                } finally {
                    $.messager.progress('close');
                }
            }

        }
    },

    /**
     * dialog.save
     * @param jq
     * @param options
     */
    save: function (jq, options) {
        var selector = expressui.util.initSelector(jq);
        var dialogOptions = $(selector).dialog('options');
        var isValid = $(selector).form('validate');
        if (isValid) {
            $.messager.progress({title: '请稍等', msg: '正在操作...'});
            var serializeArray = $(selector).find('form').serializeArray();
            var paramArray = typeof dialogOptions.save === 'object' ?
                dialogOptions.save.data || [] : [];
            for (var i = 0; i < paramArray.length; i++) {
                serializeArray.push(paramArray[i]);
            }

            if (typeof dialogOptions.save === 'function') {

                try {
                    var sA = {};
                    for (i = 0; i < serializeArray.length; i++) {
                        sA[serializeArray[i].name] = serializeArray[i].value;
                    }

                    if (dialogOptions.save(sA) !== true) {
                        if (typeof options.success === 'function') {
                            options.success(data);
                        }
                        if (typeof options.success === 'string') {
                            $.messager.show({title: '信息', msg: options.success});
                            if (typeof options.reload === 'object') {
                                expressui.grid.reload(options.reload);
                            }
                        } else {
                            if (typeof options.reload === 'object') {
                                expressui.grid.reload(options.reload);
                            }
                        }

                        $(selector).dialog('close');
                    }
                } catch (e) {
                    console.error(e);
                } finally {
                    $.messager.progress('close');
                }

            } else {

                try {
                    expressui.ajax({
                        beforeSend: function () {
                        },
                        url: (typeof dialogOptions.save) === 'string' ? dialogOptions.save : (dialogOptions.save.url || ''),
                        type: dialogOptions.save.method || 'post',
                        data: serializeArray,
                        success: function (data, statusText, XMLHttpRequest) {
                            if (typeof options.success === 'function') {
                                options.success(data);
                            }
                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            $(selector).dialog('close');
                            $.messager.progress('close');
                        },
                        error: function (XMLHttpRequest, statusText, errorThrown) {
                            if (options.error) {
                                if (typeof options.error === 'function') {
                                    options.error(XMLHttpRequest, statusText, errorThrown);
                                }
                                if (typeof options.error === 'string') {
                                    $.messager.alert('信息', options.error, 'error');
                                }
                            } else {
                                $.messager.alert('信息', XMLHttpRequest.responseJSON.message, 'error');
                            }
                            $.messager.progress('close');
                        }
                    })
                } catch (e) {
                    $.messager.alert('信息', e.message, 'error');
                } finally {
                    $.messager.progress('close');
                }
            }
        }
    }
});

$.extend($.fn.datagrid.defaults, {
    idField: 'id',
    fit: true,
    fitColumns: true,
    autoRowHeight: true,
    selectOnCheck: false, // 单独 select
    checkOnSelect: true, // 单独 select
    checkbox: true,
    method: 'get',
    pagination: true,
    pageNumber: 1,
    pageList: [10, 20, 50, 100],
    pageSize: 10,
    loadMsg: '正在加载，请稍等...',
    emptyMsg: '暂无记录',
    onLoadSuccess: function (data) {
        $(this).datagrid('clearSelections');
        $(this).datagrid('clearChecked');
    },
    onLoadError: function () {
        $(this).datagrid('clearSelections');
        $(this).datagrid('clearChecked');
    },
    loadFilter: function (data) {
        return data ? data : [];
    },
    /**
     * 单独 select
     * @param row
     */
    onBeforeSelect: function (index, row) {
        $(this).datagrid('clearSelections');
    },
});

$.extend($.fn.datagrid.methods, {
    // 创建一个 datagrid 实例
    create: function (jq, options) {
        // 初始 selector
        options.selector = expressui.util.initSelector(jq);
        // 追加工具栏
        expressui.grid.appendToolbar(options);
        return $(options.selector).datagrid(options);
    },

    // 获取必须一个勾选项，否则弹出警告信息
    getCheckedOneOrShowAlert: function (jq, alertMessage) {
        var selector = expressui.util.initSelector(jq);
        var checked = expressui.grid.getCheckedHasMessage({type: 'datagrid', selector: selector});

        var options = {alertMessage: '请勾选要操作项'};
        if (typeof alertMessage === 'string') {
            options.alertMessage = alertMessage;
        }
        if (typeof alertMessage === 'object') {
            options = alertMessage;
        }

        if (checked) {
            if (checked.length === 1) {
                return checked[0];
            } else {
                if (checked.length <= 0) {
                    $.messager.alert('信息', options.alertMessage, 'warning');
                } else {
                    $.messager.alert('信息', '最多只能勾选一项操作', 'warning');
                }
            }
        }
    },

    // 获取至少勾选一项，否则弹出警告信息
    getCheckedLessOneOrShowAlert: function (jq) {
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getCheckedHasMessage({type: 'datagrid', selector: selector});
        if (selections) {
            if (selections.length <= 0) {
                $.messager.alert('信息', '请勾选要操作项', 'warning');
            } else {
                return selections;
            }
        }
    },

    // 勾选一项或不勾选项都不弹出警告信息，否则弹出警告信息
    getCheckedOneNoCheckedOrShowAlert: function (jq) {
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        if (selections) {
            if (selections.length > 1) {
                $.messager.alert('信息', '最多只能勾选一项操作', 'warning');
                return false;
            } else {
                if (selections.length === 1) {
                    return selections[0];
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    },

    // 获取勾选的一项
    getCheckedOne: function (jq) {
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        if (selections) {
            return selections[0];
        }
    },

    // // 删除多项
    // ajax: function (jq, url) {
    //     $.messager.progress({title:'请稍等', msg:'正在操作...'});
    //     var selector = expressui.util.initSelector(jq);
    //     var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
    //     if (!selections || selections.length <= 0) {
    //         $.messager.progress('close');
    //         $.messager.alert('信息', '请勾选要操作项', 'warning');
    //         return;
    //     }
    //
    //     var options = {url: '', method:'post', paramName: $(selector).datagrid('options').idField, paramData: [], confirmMessage: '确定要操作勾选项吗'};
    //     if (typeof url === 'string') {
    //         options.url = url;
    //     }
    //     if (typeof url === 'object') {
    //         $.extend(options, url);
    //     }
    //
    //     var data = {};
    //     for (var i = 0; i < selections.length; i++) {
    //         var idField = $(selector).datagrid('options').idField;
    //         options.paramData.push(selections[i][idField]);
    //     }
    //     data[options.paramName] = options.paramData;
    //
    //     try {
    //         $.messager.confirm('信息', options.confirmMessage, function (ok) {
    //             if(ok) {
    //                 expressui.ajax({
    //                     url: options.url,
    //                     type: options.method,
    //                     data: options.data || data,
    //                     success: function (data) {
    //
    //                         if (typeof options.success === 'function') {
    //                             options.success(data);
    //                         } else {
    //                             if(typeof options.reload === 'object') {
    //                                 expressui.grid.reload(options.reload);
    //                             }
    //                         }
    //
    //                         if (typeof options.success === 'string') {
    //                             $.messager.show({title:'信息', msg:options.success});
    //                             if(typeof options.reload === 'object') {
    //                                 expressui.grid.reload(options.reload);
    //                             }
    //                         }
    //
    //                         $(selector).datagrid('clearSelections');
    //                         $(selector).datagrid('clearChecked');
    //
    //                     },
    //                     error: function (XMLHttpRequest, textStatus, errorThrown) {
    //
    //                         if (typeof options.error === 'function') {
    //                             options.error(XMLHttpRequest, textStatus, errorThrown);
    //                         }
    //
    //                         if (typeof options.error === 'string') {
    //                             $.messager.show({title:'信息', msg:options.error});
    //                         }
    //
    //                         if(!options.error) {
    //                             $.messager.show({title:'信息', msg:XMLHttpRequest.responseJSON.message});
    //                         }
    //
    //                         $.messager.progress('close');
    //                     }
    //                 });
    //             }
    //         });
    //
    //     } catch (e) {
    //         $.messager.show({title: '信息', msg: '发生错误，操作失败'});
    //     } finally {
    //         $.messager.progress('close');
    //     }
    // },

    // 删除多项
    deleteChecked: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }

        var options = {
            url: '',
            method: 'post',
            paramName: $(selector).datagrid('options').idField,
            paramData: [],
            confirmMessage: '确定要删除勾选项吗'
        };
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }

        var data = {};
        for (var i = 0; i < selections.length; i++) {
            var idField = $(selector).datagrid('options').idField;
            options.paramData.push(selections[i][idField]);
        }
        data[options.paramName] = options.paramData;

        try {
            $.messager.confirm('信息', options.confirmMessage, function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {

                            if (typeof options.success === 'function') {
                                options.success(data);
                            }

                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            $(selector).datagrid('clearSelections');
                            $(selector).datagrid('clearChecked');

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }

                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }

                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }

                            $.messager.progress('close');
                        }
                    });
                }
            });

        } catch (e) {
            $.messager.show({title: '信息', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    },

    // post 不管多少项
    post: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        // if (!selections || selections.length <= 0) {
        //     $.messager.progress('close');
        //     $.messager.alert('信息', '请勾选要操作项', 'warning');
        //     return;
        // }
        var options = {
            url: '',
            method: 'post',
            paramName: $(selector).datagrid('options').idField,
            paramData: [],
            confirmMessage: '确定要操作吗'
        };
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }
        var data = {};
        for (var i = 0; i < selections.length; i++) {
            var idField = $(selector).datagrid('options').idField;
            options.paramData.push(selections[i][idField]);
        }
        data[options.paramName] = options.paramData;
        try {
            $.messager.confirm('信息', options.confirmMessage, function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {
                            if (typeof options.success === 'function') {
                                options.success(data);
                            }
                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }
                            $(selector).datagrid('clearSelections');
                            $(selector).datagrid('clearChecked');
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }
                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }
                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        } catch (e) {
            $.messager.show({title: '信息', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    },

    // post 必须勾选一项或一项以上，否则弹出警告
    postCheckedLessOneOrShowAlert: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }
        var options = {
            url: '',
            method: 'post',
            paramName: $(selector).datagrid('options').idField,
            paramData: [],
            confirmMessage: '确定要操作勾选项吗'
        };
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }
        var data = {};
        for (var i = 0; i < selections.length; i++) {
            var idField = $(selector).datagrid('options').idField;
            options.paramData.push(selections[i][idField]);
        }
        data[options.paramName] = options.paramData;
        try {
            $.messager.confirm('信息', options.confirmMessage, function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {
                            if (typeof options.success === 'function') {
                                options.success(data);
                            }
                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }
                            $(selector).datagrid('clearSelections');
                            $(selector).datagrid('clearChecked');
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }
                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }
                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        } catch (e) {
            $.messager.show({title: '信息', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    },

    // post 必须只勾选一项，否则弹出警告
    postCheckedMustOneOrShowAlert: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'datagrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }
        if (!selections || selections.length > 1) {
            $.messager.progress('close');
            $.messager.alert('信息', '最多只允许勾选一项操作', 'warning');
            return;
        }
        var options = {url: '', method: 'post', paramName: $(selector).datagrid('options').idField, paramData: []};
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }
        var data = {};
        var idField = $(selector).datagrid('options').idField;
        var paramName = options.paramName;
        data[paramName] = selections[0][idField];

        try {
            $.messager.confirm('信息', '确定要操作勾选项吗？', function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {
                            if (typeof options.success === 'function') {
                                options.success(data);
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }
                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }
                            $(selector).datagrid('clearSelections');
                            $(selector).datagrid('clearChecked');
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }
                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }
                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }
                            $.messager.progress('close');
                        }
                    });
                }
                $.messager.progress('close');

            });
        } catch (e) {
            $.messager.show({title: '请稍等', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    }
});

$.extend($.fn.treegrid.defaults, {
    idField: 'id',
    treeField: 'name',
    fit: true,
    fitColumns: true,
    autoRowHeight: true,
    onlyLeafCheck: true,
    selectOnCheck: false, // 单独 select
    checkOnSelect: true, // 单独 select
    checkbox: false,
    pagination: false,
    scrollbarSize: 0,
    method: 'get',
    pageNumber: 1,
    pageList: [10, 20, 50, 100],
    pageSize: 10,
    loadMsg: '正在加载，请稍等...',
    emptyMsg: '暂无记录',
    queryParams: {},
    onLoadSuccess: function (data) {
        $(this).treegrid('options').url = $(this).treegrid('options')._url;
        $(this).treegrid('clearSelections');
        $(this).treegrid('clearChecked');
    },
    onLoadError: function () {
        $(this).treegrid('clearSelections');
        $(this).treegrid('clearChecked');
    },
    onBeforeExpand: function (row) {
        var options = $(this).treegrid('options');
        if (options.expandUrl) {
            options.url = expressui.replaceUrlPlaceholder(options.expandUrl, row);
        }
        return true;
    },
    // 单独 select
    onBeforeSelect: function (row) {
        $(this).treegrid('clearSelections');
    }
});

$.extend($.fn.treegrid.methods, {
    // 创建一个 treegrid 实例
    create: function (jq, options) {
        // 初始 selector
        options.selector = expressui.util.initSelector(jq);
        // 追加工具栏
        expressui.grid.appendToolbar(options);
        if (options.url) {
            options._url = options.url;
        }
        return $(options.selector).treegrid(options);
    },

    // 获取至少勾选一项，否则弹出警告信息
    getCheckedLessOneOrShowAlert: function (jq) {
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getCheckedHasMessage({type: 'treegrid', selector: selector});
        if (selections) {
            if (selections.length <= 0) {
                $.messager.alert('信息', '请勾选要操作项', 'warning');
            } else {
                return selections;
            }
        }
    },

    // // 不勾选项或勾选一项都不弹出警告信息，否则弹出警告信息
    // getSelectOneOrMoreShowAlert: function (jq) {
    //     var selector = expressui.util.initSelector(jq);
    //     var selections = expressui.grid.getSelections({type: 'treegrid', selector: selector});
    //     if(selections){
    //         if(selections.length >1 ){
    //             $.messager.alert('信息', '最多只能勾选一项操作', 'warning');
    //             return false;
    //         } else{
    //             if(selections.length === 1){
    //                 return selections[0];
    //             } else {
    //                 return true;
    //             }
    //         }
    //     } else{
    //         return true;
    //     }
    // },
    //
    // // 获取勾选的一项
    // getSelectOne: function (jq) {
    //     var selector = expressui.util.initSelector(jq);
    //     var selections = expressui.grid.getChecked({type: 'treegrid', selector: selector});
    //     if(selections){
    //         return selections[0];
    //     }
    // },

    ajax: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'treegrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }

        var options = {
            url: '',
            method: 'post',
            paramName: $(selector).treegrid('options').idField,
            paramData: [],
            confirmMessage: '确定要操作勾选项吗？'
        };
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }

        var data = {};
        for (var i = 0; i < selections.length; i++) {
            var idField = $(selector).treegrid('options').idField;
            options.paramData.push(selections[i][idField]);
        }
        data[options.paramName] = options.paramData;
        try {
            $.messager.confirm('信息', options.confirmMessage, function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {

                            if (typeof options.success === 'function') {
                                options.success(data);
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            $(selector).treegrid('clearSelections');
                            $(selector).treegrid('clearChecked');

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {

                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }

                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }

                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }

                            $.messager.progress('close');
                        }
                    });
                }
            });
        } catch (e) {
            $.messager.show({title: '请稍等', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    },

    // 删除多项
    deleteChecked: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'treegrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }

        var options = {
            url: '',
            method: 'post',
            paramName: $(selector).treegrid('options').idField,
            paramData: [],
            confirmMessage: '确定要删除勾选项吗？'
        };
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }

        var data = {};
        for (var i = 0; i < selections.length; i++) {
            var idField = $(selector).treegrid('options').idField;
            options.paramData.push(selections[i][idField]);
        }
        data[options.paramName] = options.paramData;

        try {
            $.messager.confirm('信息', options.confirmMessage, function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {

                            if (typeof options.success === 'function') {
                                options.success(data);
                            }

                            // 此处会出现多次 reload 的代码，已经注释并移动到下方 string 判断的 reload
                            // } else {
                            //     if(typeof options.reload === 'object') {
                            //         expressui.grid.reload(options.reload);
                            //     }
                            // }

                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            $(selector).treegrid('clearSelections');
                            $(selector).treegrid('clearChecked');

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {

                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }

                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }

                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }

                            $.messager.progress('close');
                        }
                    });
                }
            });
        } catch (e) {
            $.messager.show({title: '请稍等', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    },

    // 删除一项
    deleteCheckedOne: function (jq, url) {
        $.messager.progress({title: '请稍等', msg: '正在操作...'});
        var selector = expressui.util.initSelector(jq);
        var selections = expressui.grid.getChecked({type: 'treegrid', selector: selector});
        if (!selections || selections.length <= 0) {
            $.messager.progress('close');
            $.messager.alert('信息', '请勾选要操作项', 'warning');
            return;
        }

        if (!selections || selections.length > 1) {
            $.messager.progress('close');
            $.messager.alert('信息', '最多只允许勾选一项操作', 'warning');
            return;
        }

        var options = {url: '', method: 'post', paramName: $(selector).treegrid('options').idField, paramData: []};
        if (typeof url === 'string') {
            options.url = url;
        }
        if (typeof url === 'object') {
            $.extend(options, url);
        }

        var data = {};
        var idField = $(selector).treegrid('options').idField;
        var paramName = options.paramName;
        data[paramName] = selections[0][idField];

        try {
            $.messager.confirm('信息', '确定要删除勾选项吗？', function (ok) {
                if (ok) {
                    expressui.ajax({
                        url: options.url,
                        type: options.method,
                        data: options.data || data,
                        success: function (data) {

                            if (typeof options.success === 'function') {
                                options.success(data);
                            } else {
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            if (typeof options.success === 'string') {
                                $.messager.show({title: '信息', msg: options.success});
                                if (typeof options.reload === 'object') {
                                    expressui.grid.reload(options.reload);
                                }
                            }

                            $(selector).treegrid('clearSelections');
                            $(selector).treegrid('clearChecked');

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {

                            if (typeof options.error === 'function') {
                                options.error(XMLHttpRequest, textStatus, errorThrown);
                            }

                            if (typeof options.error === 'string') {
                                $.messager.show({title: '信息', msg: options.error});
                            }

                            if (!options.error) {
                                $.messager.show({title: '信息', msg: XMLHttpRequest.responseJSON.message});
                            }

                            $.messager.progress('close');
                        }
                    });
                }
                $.messager.progress('close');

            });
        } catch (e) {
            $.messager.show({title: '信息', msg: '发生错误，操作失败'});
        } finally {
            $.messager.progress('close');
        }
    }
});

$.extend($.fn.combobox.defaults, {
    valueField: 'id',
    textField: 'text',
    method: 'get',
    editable: false,
    panelHeight: '180px',
    // onBeforeLoad: function(param){
    //     if(param && param.q) {
    //         var value = param.q.replace('/ /g','');
    //         if(value!=='') {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
});

$.extend($.fn.combotree.defaults, {
    panelHeight: '180px',
    method: 'get',
});

$.extend($.fn.form.methods, {
    submit: function (jq, selector) {
        selector.selector = jq.selector;
        return expressui.form.submit(selector);
    }
});
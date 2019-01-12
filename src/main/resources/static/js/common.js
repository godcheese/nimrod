/**
 * jQuery Eova Common
 */
(function ($) {

    /**
     * 拓展全局静态方法
     */
    $.extend({
        /** 同步Post **/
        syncPost: function (url, data, success) {
            $.ajax({
                async: false,
                type: 'POST',
                url: url,
                data: data,
                success: success,
                dataType: "json"
            });
        },
        /** 同步获取JSON **/
        syncGetJson: function (url, success) {
            $.ajax({
                async: false,
                type: 'GET',
                url: url,
                success: success,
                dataType: "json"
            });
        },
        /** Html转义 **/
        encodeHtml: function (s) {
            return (typeof s != "string") ? s :
                s.replace(/"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g,
                    function ($0) {
                        var c = $0.charCodeAt(0), r = ["&#"];
                        c = (c == 0x20) ? 0xA0 : c;
                        r.push(c);
                        r.push(";");
                        return r.join("");
                    });
        },
        /** 追加URL参数 **/
        appendUrlPara: function (url, key, val) {
            if(!val || val == ''){
                return url;
            }
            if(url.indexOf('?') == -1){
                url = url + '?';
            } else {
                url = url + '&';
            }
            return url + key + '=' + val;
        },
        /** 获取URL参数 **/
        getUrlPara: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return;
        },
        /** 获取URL QueryString **/
        getUrlParas: function () {
            var url = location.href;
            if(url.indexOf("?") == -1){
                return;
            }
            return url.substring(url.indexOf("?")+1,url.length);
        },
        /** 获取Form参数对象-用于Post请求 **/
        getFormParasObj: function (form) {
            var o = {};
            $.each(form.serializeArray(), function(index) {
                if (o[this['name']]) {
                    o[this['name']] = o[this['name']] + "," + this['value'];
                } else {
                    o[this['name']] = this['value'];
                }
            });
            return o;
        },
        /** 获取Form参数字符-用于get请求 **/
        getFormParasStr: function (form) {
            var o = "";
            $.each(form.serializeArray(), function(index) {
                var key = this['name'], val = this['value'];
                if(val && val.length > 0){
                    o = o + key + "=" + val + "&";
                }
            });
            return o.substring(0, o.length-1);
        },
        /** 获取浏览器类型 **/
        getBrowser: function() {
            var explorer = window.navigator.userAgent;
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            } else if (explorer.indexOf("Firefox") >= 0) {
                return 'firefox';
            } else if (explorer.indexOf("Chrome") >= 0) {
                return 'chrome';
            } else if (explorer.indexOf("Opera") >= 0) {
                return 'opera';
            } else if (explorer.indexOf("Safari") >= 0) {
                return 'safari';
            }
        },
        /** 格式化自动2位补零，制保留2位小数，如：2，会在2后面补上00.即2.00 **/
        formatDouble : function(x) {
            var f = Math.round(x * 100) / 100;
            var s = f.toString();
            var rs = s.indexOf('.');
            if (rs < 0) {
                rs = s.length;
                s += '.';
            }
            while (s.length <= rs + 2) {
                s += '0';
            }
            return s;
        },
        /**
         * 格式化JSON
         * @param txt
         * @param compress 是否压缩
         * @returns
         */
        jsonformat : function(json, compress) {
            var indentChar = '    ';
            if (/^\s*$/.test(json)) {
                alert('数据为空,无法格式化! ');
                return;
            }
            try {
                var data = eval('(' + json + ')');
            } catch (e) {
                return;
            }
            var draw = [], last = false, This = this, line = compress ? '' : '\n', nodeCount = 0, maxDepth = 0;
            var notify = function(name, value, isLast, indent, formObj) {
                nodeCount++;
                for ( var i = 0, tab = ''; i < indent; i++)
                    tab += indentChar;
                tab = compress ? '' : tab;
                maxDepth = ++indent;
                if (value && value.constructor == Array) {
                    draw.push(tab + (formObj ? ('"' + name + '":') : '') + '[' + line);
                    for ( var i = 0; i < value.length; i++)
                        notify(i, value[i], i == value.length - 1, indent, false);
                    draw.push(tab + ']' + (isLast ? line : (',' + line)));
                } else if (value && typeof value == 'object') {
                    draw.push(tab + (formObj ? ('"' + name + '":') : '') + '{' + line);
                    var len = 0, i = 0;
                    for ( var key in value)
                        len++;
                    for ( var key in value)
                        notify(key, value[key], ++i == len, indent, true);
                    draw.push(tab + '}' + (isLast ? line : (',' + line)));
                } else {
                    if (typeof value == 'string')
                        value = '"' + value + '"';
                    draw.push(tab + (formObj ? ('"' + name + '":') : '') + value + (isLast ? '' : ',') + line);
                }
                ;
            };
            var isLast = true, indent = 0;
            notify('', data, isLast, indent, false);
            return draw.join('');
        },
        /**
         * 压缩空格
         * @param s
         * @returns
         */
        compressWhiteSpace : function(s) {
            s = s.replace(/\s+/g, " ");
            s = s.replace(/^\s(.*)/, "$1");
            s = s.replace(/(.*)\s$/, "$1");
            s = s.replace(/\s([\x21\x25\x26\x28\x29\x2a\x2b\x2c\x2d\x2f\x3a\x3b\x3c\x3d\x3e\x3f\x5b\x5d\x5c\x7b\x7c\x7d\x7e])/g, "$1");
            s = s.replace(/([\x21\x25\x26\x28\x29\x2a\x2b\x2c\x2d\x2f\x3a\x3b\x3c\x3d\x3e\x3f\x5b\x5d\x5c\x7b\x7c\x7d\x7e])\s/g, "$1");
            return s;
        },
        /**
         * 自动获取焦点
         * @param $input
         * @returns
         */
        autoFocus : function($input) {
            $input.focus();
            var s = $input.val();
            $input.val("");
            $input.val(s);
        },
        /**
         * 异步文件上传
         * @param $input
         * @returns
         */
        ajaxUpload : function(url, fileId, fileName, success) {
            var file = document.getElementById(fileId).files[0];

            var data = new FormData();
            data = new FormData();
            data.append(fileName, file);

            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: data,
                processData: false,
                contentType: false,
            }).done(success);
        }

    });
})(jQuery);
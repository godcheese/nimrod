/**
 * jQuery Utils
 */


var util = util || {};

(function (_util) {

    _util ={
        response:{},
        request:{},
        string:{},
        object:{},
        json:{},
        document:{},
        cookie:{}
    };

    {
        _util.json.toObject = function (json) {
            return eval('(' + json + ')');
        };

        _util.response.MESSAGE = 'message';
        _util.response.CODE = 'code';
        _util.response.DATA = 'data';
        _util.response.EXCEPTION = 'exception';
        _util.response._httpStatus = function (code, message) {
            var json = '{"' + _util.response.CODE + '":' + code + ',"' + _util.response.MESSAGE + '":"' + message + '"}';
            return _util.json.toObject(json);
        };

        _util.response.httpStatus = {
            OK: _util.response._httpStatus(200, 'OK'),
            NOT_FOUND: _util.response._httpStatus(404, 'NOT FOUND')
        };

        _util.response.ok = function (responseResult, fnOkCallback, fnNotOkCallback, fnThenCallback) {
            if (responseResult) {

                var OK = _util.response.httpStatus.OK.code;

                var data = responseResult.hasOwnProperty(_util.response.DATA) ? responseResult[_util.response.DATA] : undefined;

                var message = responseResult.hasOwnProperty(_util.response.MESSAGE) ? responseResult[_util.response.MESSAGE] : undefined;

                var code = responseResult.hasOwnProperty(_util.response.CODE) ? responseResult[_util.response.CODE] : undefined ;

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

        _util.request.getQueryParam = function (name){
            var href = window.location.href;
            var arr = href.split('?');
            if(arr){
                if(arr.length > 1){
                    var arr1 = arr.splice(1);
                    if(!arr1) {

                    }
                    var arr2 = arr1[0];
                    if(!arr2) {
                        return;
                    }
                    var arr3 = arr2.split('&');
                    if (!arr3) {
                        return;
                    }
                    if (arr3.length >= 1) {
                        for (var i = 0; arr3.length > i; i++) {
                            var arr4 = arr3[i];

                            if (arr4) {
                                var arr5 = arr4.split('=');
                                if (arr5) {
                                    if (arr5.length >= 2) {
                                        if (name === arr5[0]) {
                                            return arr5[1];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        };

        _util.ajax = function (options) {
            if(!window.jQuery){
                alert('util.ajax 需要jQuery支持');
                return;
            }
            var defaults = {
                async: true,
                type: 'get',
                dataType: 'json',
                processData: true,
                error: function (xhr, textStatus, errorThrown) {
                    console.log(xhr);
                }
            };

            $.extend(defaults, options);
            return $.ajax(defaults);
        };

        _util.string.replaceAllPlaceholder = function (string,searchValue,replaceValue) {
            return string.replace(new RegExp('{' + searchValue + '}', "gm"),replaceValue);
        };

        // 获取字符的长度
        _util.string.countSymbols = function(string) {
            var regexAstralSymbols = /[\uD800-\uDBFF][\uDC00-\uDFFF]/g;
            return string
            // 把代理对改为一个BMP的字符.
                .replace(regexAstralSymbols, '_')
                // …这时候取长度就妥妥的啦.
                .length;
        };

        // 获取前6个字符
        _util.string.sliceSymbols = function (str, limit) {
            var regexAstralSymbols = /[\uD800-\uDBFF][\uDC00-\uDFFF]/g;
            var output = [];
            var index = 0;
            var oldStr = str;
            str = str.replace(regexAstralSymbols, function(input, offset, match) {
                if( offset > index ) {
                    output = output.concat(match.slice(index, offset).split(""));
                }
                index = offset + input.length;
                output.push(input);
                return "";
            });

            if( index < oldStr.length  ) {
                output = output.concat(oldStr.slice(index, oldStr.length).split(""));
            }
            return output.slice(0, limit).join("");
        };

        _util.document.create = function (options,tag) {
            var _body = document.body;
            var _tag;
            if(tag) {
                _tag = document.createElement(tag);
            }else{
                _tag = document.createElement('div');
            }
            if(typeof options === 'string'){
                var selector = options.charAt(0);
                if(selector){
                    switch (selector){
                        case '#':
                            options = options.substring(1,options.length);
                            _tag.id = options;
                            break;
                        case '.':
                            options = options.substring(1,options.length);
                            _tag.className = options;
                            break;
                        default:
                            break;
                    }
                }
                return _body.appendChild(_tag);
            }
        };

        _util.formatter = function (key, defaultValue, keyValue, formatterCallback) {
            var result;
            if(typeof keyValue === 'object'){
                for(var k in keyValue) {
                    switch (typeof key){
                        case 'number':
                            k = parseInt(k);
                            break;
                        case 'string':
                            k = k.toString();
                            break;
                    }
                    if (key === k) {
                        result = keyValue[k];
                        break;
                    }
                }
                if(typeof result === 'undefined'){
                    if(typeof defaultValue !== 'undefined'){
                        result = defaultValue;
                    }
                }
            }


            if(typeof formatterCallback === 'function'){
                result = formatterCallback(result, key, defaultValue, keyValue);
            }

            return result;
        };


        /**
         * 某个按键按下操作
         * 回车键（enter） = 13
         * @param keyCode
         * @param callback
         */
        _util.document.keyDown = function (keyCode, callback) {

            document.onkeydown = function(e){
                var ev = document.all ? window.event : e;
                if(ev.keyCode === keyCode) {
                    if (typeof callback === 'function') {
                        callback();
                    }
                }
            }

        }

        _util.cookie.set = function (name,value,expires,path,domain,secure) {
            var expDays = expires*24*60*60*1000;
            var expDate = new Date();
            expDate.setTime(expDate.getTime()+expDays);
            var expString = ((expires==null) ? "": (";expires="+expDate.toGMTString()));
            var pathString = ((path==null) ? "": (";path="+path));
            var domainString = ((domain==null) ? "": (";domain="+domain));
            var secureString = ((secure==true) ? ";secure": "");
            document.cookie = name + "="+ escape(value) + expString + pathString + domainString + secureString;
        };

        _util.cookie.get = function(name) {
            var result = null;
            var myCookie = document.cookie + ";";
            var searchName = name + "=";
            var startOfCookie = myCookie.indexOf(searchName);
            var endOfCookie;
            if (startOfCookie != -1)
            {
                startOfCookie += searchName.length;
                endOfCookie = myCookie.indexOf(";",startOfCookie);
                result = unescape(myCookie.substring(startOfCookie,endOfCookie));
            }
            return result;
        }
    }

    util = _util;
})(util);




//
// <div id="pageloading" style="display: none;"></div>
//
// #pageloading
// {
//     position: absolute;
//     left: 0
//     px;
//     top: 0
//     px;
//     background: white
//     url(loading.gif)
//     no - repeat
//     center;
//     width: 100 %;
//     height: 100 %;
//     z - index
// :
//     99999;
// }
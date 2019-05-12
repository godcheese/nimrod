$(function () {
    // iframe 框架刷新父页面
    if (window.top !== window.self) {
        window.top.location = window.location;
    }

    var verifyCodeImageSrc = $('#verifyCodeImage').attr('src');
    $('#verifyCodeImage').click(function () {
        refreshVerifyCode($(this), verifyCodeImageSrc);
    });

    function refreshVerifyCode(_this, verifyCodeImageSrc) {
        _this.attr('src', verifyCodeImageSrc + '?_=' + Math.random());
    }

    $('#loginButton').click(function () {
        var account = $('#account').val();
        var password = $('#password').val();
        var rememberMe = $('#rememberMe').is(':checked');
        var verifyCode = $('#verifyCode').val();
        if (account === '' || password === '') {
            $('#alertMessage').html(' <div class="alert alert-error">\n' +
                '<i class="fa fa-exclamation-triangle"></i>\n' +
                '<span id="alertMessage">请先输入账号和密码</span>\n' +
                '</div>');
            return;
        }

        $.ajax({
            url: Url.User.Api.LOGIN,
            data: {
                account: account,
                password: password,
                rememberMe: rememberMe,
                verifyCode: verifyCode
            },
            type: 'post',
            success: function (XMLHttpRequest, statusText) {
                $('#alertMessage').html('<div class="alert alert-success">\n' +
                    '<i class="fa fa-exclamation-triangle"></i>\n' +
                    '<span id="alertMessage">登录成功</span>\n' +
                    '</div>');

                // 刷新页面跳转到首页
                window.location.href = _contextPath;
            },
            error: function (XMLHttpRequest, statusText, errorThrown) {
                $('#alertMessage').html('<div class="alert alert-error">\n' +
                    '<i class="fa fa-exclamation-triangle"></i>\n' +
                    '<span id="alertMessage">' + XMLHttpRequest.responseJSON.message + '</span>\n' +
                    '</div>');
                $('#verifyCodeImage').click();
                // refreshVerifyCode($('#verifyCodeImage'),verifyCodeImageSrc);
            }
        });
    });

    // var state = util.request.getQueryParam('state');
    // switch (state) {
        // case 'logout':
        //     $('#alertMessage').html('<div class="alert alert-success">\n' +
        //         '<i class="fa fa-exclamation-triangle"></i>\n' +
        //         '<span id="alertMessage">注销成功</span>\n' +
        //         '</div>');
        //     break;
    //     default:
    //         break;
    // }

});

util.document.keyDown(13, function () {
    $('#loginButton').click();
});
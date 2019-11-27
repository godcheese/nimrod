$(function () {
    // $('input, textarea').placeholder();

    // /**
    //  * iframe 框架刷新父页面
    //  */
    // if (window.top !== window.self) {
    //     window.top.location = window.location;
    // }

    /**
     * 刷新验证码
     * @type {*|jQuery}
     */
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
            alert('请先输入账号和密码');
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
                // 刷新页面跳转到首页
                window.location.href = _contextPath;
            },
            error: function (XMLHttpRequest, statusText, errorThrown) {
                alert(XMLHttpRequest.responseJSON.message);
                $('#verifyCodeImage').click();
            }
        });
    });

});

util.document.keyDown(13, function () {
    $('#loginButton').click();
});
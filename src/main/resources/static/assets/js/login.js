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
        var account = $('#account');
        var password = $('#password');
        var rememberMe = $('#rememberMe');
        var verifyCode = $('#verifyCode');
        if (account === '' || password === '') {
            alert('请先输入账号和密码');
            return;
        }

        account.attr('disabled', 'disabled');
        password.attr('disabled', 'disabled');
        verifyCode.attr('disabled', 'disabled');
        rememberMe.attr('disabled', 'disabled');
        $.ajax({
            url: Url.User.Api.LOGIN,
            data: {
                account: account.val(),
                password: password.val(),
                rememberMe: rememberMe.is(':checked'),
                verifyCode: verifyCode.val()
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
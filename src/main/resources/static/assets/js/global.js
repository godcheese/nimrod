
if($) {
    if($.fn.validatebox) {
        $.extend($.fn.validatebox.defaults.rules, {
            confirmPassword: {
                validator: function (value, param) {
                    return value === $(param[0]).val();
                },
                message: '密码输入不一致'
            },
            password: {
                validator: function (value) {
                    return value.length >= 6 && value.length <= 32
                },
                message: '密码长度为6-32个字符'
            }
        });
    }
}
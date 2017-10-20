layui.use('form', function () {
    var $ = layui.jquery, form = layui.form(), layer = parent.layer === undefined ? layui.layer : parent.layer;
    //自定义验证规则
    form.verify({
        rePassword: function (value) {
            var password = $("#password").val();
            var rePassword = $("#rePassword").val();
            if (password == '') {
                return '新密码不能为空！';
            }
            if (rePassword == '') {
                return '确认密码不能为空！';
            }
            if (password != rePassword) {
                return '新密码与确认密码不一致！';
            }
        }
    });

    //监听提交
    form.on('submit(*)', function (data) {
        return submitFormNewTip(data);
    });
});
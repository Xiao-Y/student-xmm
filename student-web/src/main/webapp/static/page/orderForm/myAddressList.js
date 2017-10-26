layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer', 'form'], function () {
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer;
    var form = layui.form();
    //自定义验证规则
    form.verify({
        consigneePhone: function (value) {
            var consigneePhone = $("input[name='consigneePhone']").val();
            if (consigneePhone == '') {
                return '收货人电话不能为空！';
            } else {
                var reg = /^1[0-9]{10}$/;
                if (!reg.test(consigneePhone)) {
                    return '手机号码格式不正确！';
                }
            }
        }
    });

    //监听提交
    form.on('submit(*)', function(data) {
        return submitFormNewTip(data);
    });
});

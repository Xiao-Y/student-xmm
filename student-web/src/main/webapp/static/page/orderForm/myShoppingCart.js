layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer'], function () {
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer;
    //表单校验
    /*$('input').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });*/
});
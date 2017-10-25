layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer', 'form'], function () {
    var $ = layui.jquery, form = layui.form(), layer = parent.layer === undefined ? layui.layer : parent.layer;
    $('#search').on('click', function () {
        parent.layer.alert('你点击了搜索按钮');
    });

    //自定义验证规则
    form.verify({
        userName: function (value) {
            var message = '';
            var userName = $("#userName").val();
            if (userName == '') {
                return '用户名不能为空！';
            }
            $.ajaxSettings.async = false;
            var url = path + "/home/checkUserName/" + userName;
            $.post(url, function (result) {
                if (result == '1') {
                    message = '该用户名已经存在，请更新换其它用户名！';
                }
            });
            if (message != '') {
                return message;
            }
        },
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
        },
        phoneNumber: function (value) {
            var phoneNumber = $("#phoneNumber").val();
            if (phoneNumber == '') {
                return '手机号码不能为空！';
            }
            var reg = /^1[0-9]{10}$/;
            if (!reg.test(phoneNumber)) {
                return '手机号码格式不正确！';
            }
        }
    });

    //切换收货地址
    $('#add').on('click', function () {
        $.get(path + '/address/myAddressEdit', null, function (form) {
            layer.open({
                type: 1,
                title: '修改/添加收货地址',
                content: form,
                btn: ['确定', '取消'],
                area: ['600px', '400px'],
                maxmin: true,
                yes: function (index, layero) {
                    var saveUrl = layero.find("form").attr("action");
                    var data = layero.find("form").serialize();
                    if (data) {
                        $.ajax({
                            type: 'POST',
                            url: saveUrl,
                            data: data,
                            dataType: 'json',
                            success: function (data) {
                                var message = data.message;
                                var type = data.type;
                                new TipBox({
                                    type: type, str: message, hasBtn: true, setTime: 2500, callBack: function () {
                                        if (type === 'success') {
                                            location.reload();//刷新一下列表
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        layer.msg("表单序列化错误！");
                    }
                    layer.close(index);
                },
                full: function (elem) {
                    var win = window.top === window.self ? window : parent.window;
                    $(win).on('resize', function () {
                        var $this = $(this);
                        elem.width($this.width()).height($this.height()).css({
                            top: 0,
                            left: 0
                        });
                        elem.children('div.layui-layer-content').height($this.height() - 95);
                    });
                }
            });
        });
    });
});

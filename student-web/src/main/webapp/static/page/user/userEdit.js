layui.use('form', function () {
    var $ = layui.jquery, form = layui.form(), layer = parent.layer === undefined ? layui.layer : parent.layer;
    //自定义验证规则
    form.verify({
        userName: function (value) {
            var message = '';
            var userName = $("#userName").val();
            if (userName == '') {
                message = '用户名不能为空！';
            }
            var userId = $("#userId").val();
            if (userId == '') {
                $.ajaxSettings.async = false;
                var url = path + "/home/checkUserName/" + userName;
                $.post(url, function (result) {
                    if (result == '1') {
                        message = '该用户名已经存在，请更新换其它用户名！';
                    }
                });
            }
            if (message != '') {
                return message;
            }
        },
        rePassword: function (value) {
            var password = $("#password").val();
            var rePassword = $("#rePassword").val();
            var userId = $("#userId").val();
            //修改个人信息或者用户信息
            if (userId != '') {
                if (password != '') {
                    if (rePassword == '') {
                        return '确认密码不能为空！';
                    }
                    if (password != rePassword) {
                        return '新密码与确认密码不一致！';
                    }
                } else {
                    $("#rePassword").val("");
                }
            } else {
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

    //监听提交
    form.on('submit(*)', function (data) {
        return submitFormNewTip(data);
    });

    //返回首页
    $("#returnIndex").on("click", function () {
        history.go(-1);
    });

    /*$("#userName").on("blur", function () {
        var userName = $("#userName").val();
        var url = path + "/home/checkUserName/" + userName;
        $.post(url, function (result) {
            if (result == '1') {
                alert('该用户名已经存在，请更新换其它用户名！');
            }
        })
    });*/
});

$(function () {
    var userId = $("#userId").val();
    if (userId != '') {
        $("#userName").attr("readonly", true);
    }

});
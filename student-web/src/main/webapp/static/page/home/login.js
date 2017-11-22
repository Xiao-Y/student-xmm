layui.use(['layer', 'form'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form();
    form.on('submit(login)', function (data) {
        return true;
    });

    $("#registered").on("click", function () {
        location.href = path + "/home/register";
    });

    //服务商信息
    $("#serviceInfo").on("click", function () {
        layer.open({
            type: 1,
            //不显示标题栏
            title: false,
            closeBtn: false,
            area: '600px;',
            shade: 0.8,
            //设定一个id，防止重复弹出
            id: 'LAY_layuipro',
            btn: ['关闭'],
            //拖拽模式，0或者1
            moveType: 1,
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
            '你知道吗？亲！<br>' +
            'layer ≠ layui<br><br>' +
            'layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>' +
            'layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>' +
            '我们此后的征途是星辰大海 ^_^' +
            '</div>',
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                /*btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    , target: '_blank'
                });*/
            }
        });
    });

    //系统公告
    /*layer.open({
        type: 1,
        title: "系统公告",
        closeBtn: false,
        area: '600px;',
        shade: 0.8,
        //设定一个id，防止重复弹出
        id: 'LAY_layuipro',
        btn: ['关闭'],
        //拖拽模式，0或者1
        moveType: 1,
        content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
        '你知道吗？亲！<br>' +
        'layer ≠ layui<br><br>' +
        'layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>' +
        'layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>' +
        '我们此后的征途是星辰大海 ^_^' +
        '</div>',
        success: function (layero) {
            var btn = layero.find('.layui-layer-btn');
            btn.css('text-align', 'center');
            /!*btn.find('.layui-layer-btn0').attr({
                href: 'http://www.layui.com/'
                , target: '_blank'
            });*!/
        }
    });*/
});

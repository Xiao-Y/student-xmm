$(function () {
    //分页使用
    $('.M-box').pagination({
        coping: true,
        jump: true,//是否开启跳转到指定页数
        isHide: false,//总页数为0或1时隐藏分页控件
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        pageCount: $("#pages").val(),//总数据
        showData: $("#pageSize").val(),//每页显示
        current: $("#pageNum").val(),//当前第几页
        callback: function (api) {
            location.href = path + '/commodity/commodityView?pageNo=' + api.getCurrent();
        }
    });

    //加入清单
    $("a[name='addDetailed']").on('click', function () {
        var index = $("a[name='addDetailed']").index(this);
        var id = $("input[name='id']").eq(index).val();
        var url = path + '/shoppingCart/addShoppingCart/' + id;
        var tipBox = null;
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            beforeSend: function (XHR) {
                tipBox = new TipBox({type: 'load', str: "正在加入购物车..."});
            },
            success: function (data) {
                tipBox.close();
                new TipBox({type: data.type, str: data.message, hasBtn: true, setTime: 2500});
            },
            error: function () {
                new TipBox({type: 'error', str: '系统错误,请稍后!', hasBtn: true});
            }
        });
    })
});
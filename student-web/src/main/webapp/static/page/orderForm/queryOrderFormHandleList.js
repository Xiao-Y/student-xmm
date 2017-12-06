layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer', 'form'], function () {
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer;
    var form = layui.form();
    //用于显示radio的选种
    var arry = ['status_radio'];
    pubPopForm.checkedDisplay(arry, form);

    $('#search').on('click', function () {
        //parent.layer.alert('你点击了搜索按钮');
        $("#searchForm").submit();
    });

    //修改订单状态
    $(document).on('click', 'a[name="optionButton"]', function () {
        var tipBox = null;
        var title = $(this).attr("title").split("&");
        var orderFormId = title[0];
        var statusCode = title[1];
        var statusName = title[2];
        var str1 = "确定" + statusName;
        var str2 = "正在操作中...";
        var url = path + "/orderForm/updateOrderForm?id=" + orderFormId + "&statusCode=" + statusCode;
        if (statusCode == 'APPLICATION_REFUND_AGREE') {
            url = path + "/aliPay/tradeRefund/" + orderFormId;
        }
        new TipBox({
            type: "confirm",
            str: str1,
            hasBtn: true,
            callBack: function () {
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    beforeSend: function (XHR) {
                        tipBox = new TipBox({type: 'load', str: str2});
                    },
                    success: function (data) {
                        tipBox.close();
                        var message = data.message;
                        var type = data.type;
                        new TipBox({
                            type: type, str: message, hasBtn: true, setTime: 1500, callBack: function () {
                                if (type === 'success') {
                                    new TipBox({type: 'load', str: "页面加载中..."});
                                    location.reload();//刷新一下列表
                                }
                            }
                        });
                    }
                });
            }
        });
    });
    // $(document).on('click', 'a[name="cancelOrderForm"]', function () {
    //     var tipBox = null;
    //     var url = $(this).attr("url");
    //     var status = url.split("&")[1].split("=")[1];
    //     var str1 = "";
    //     var str2 = "";
    //     var type = "confirm";
    //     // 1-客户提交，2-商家确认，3-客户取消，4-商家取消，5-交易完成
    //     if (status == '1') {//delFlag=1
    //         str1 = "确定删除记录？";
    //         str2 = "正在删除记录...";
    //     } else if (status == '2') {
    //         str1 = "是否确认订单？";
    //         str2 = "正在确认订单...";
    //         type = "confirm1";
    //     } else if (status == '3' || status == '4') {
    //         str1 = "确认取消订单？";
    //         str2 = "正在取消订单...";
    //     } else if (status == '5') {
    //         str1 = "确认交易完成？";
    //         str2 = "正在交易完成...";
    //     }
    //     new TipBox({
    //         type: type,
    //         str: str1,
    //         hasBtn: true,
    //         callBack: function () {
    //             $.ajax({
    //                 type: 'POST',
    //                 url: url,
    //                 dataType: 'json',
    //                 beforeSend: function (XHR) {
    //                     tipBox = new TipBox({type: 'load', str: str2});
    //                 },
    //                 success: function (data) {
    //                     tipBox.close();
    //                     tipsTable(data);
    //                 }
    //             });
    //         }
    //     });
    // });
});

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
            var id = $("#id").val();
            var status = $("input[name='status']:checked").val();
            location.href = path + '/orderForm/queryOrderFormHandleList?pageNo=' + api.getCurrent() + "&id=" + id + "&status=" + status;
        }
    });
});

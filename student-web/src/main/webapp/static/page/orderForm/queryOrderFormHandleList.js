layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer', 'form'], function () {
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer;
    var form = layui.form();

    //用于显示radio的选种
    var arry = ['status_select'];
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
            var status = $("#status").val();
            location.href = path + '/orderForm/queryOrderFormHandleList?pageNo=' + api.getCurrent() + "&id=" + id + "&status=" + status;
        }
    });
});

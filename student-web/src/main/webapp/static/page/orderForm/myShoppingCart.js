layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer', 'icheck'], function () {
    var $ = layui.jquery, iCheck = layui.icheck,
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    //表单校验
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

    var checkItemObj = $('.site-table tbody tr').find("input[name='checkItem']");
    checkItemObj.on('click', function (event) {
        var $input = $(this);
        var $thisTr = $input.parents('tr');
        $input.on('ifChecked', function (e) {
            $thisTr.css('background-color', '#EEEEEE');
        });
        $input.on('ifUnchecked', function (e) {
            $thisTr.removeAttr('style');
        });
        $input.iCheck('toggle');
    });
    checkItemObj.each(function (index) {
        var $this = $(this);
        $this.on('ifChecked', function (e) {
            $this.parents('tr').css('background-color', '#EEEEEE');
            total();
        });
        $this.on('ifUnchecked', function (e) {
            $this.parents('tr').removeAttr('style');
            total();
        });
    });
    $('#selected-all').on('ifChanged', function (event) {
        var $input = $("input[name='checkItem']");
        var $chedked = event.currentTarget.checked;
        $input.each(function (index) {
            var $this = $(this);
            if (!$this.eq(index).prop("disabled")) {
                $this.iCheck($chedked ? 'check' : 'uncheck');
            }
        });
    });

    //切换收货地址
    $('#add').on('click', function () {
        var indexLoad = layer.load();
        $.get(path + '/address/myAddressView', null, function (form) {
            layer.close(indexLoad);
            layer.open({
                type: 1,
                title: '',
                content: form,
                btn: ['确定', '取消'],
                area: ['600px', '400px'],
                maxmin: true,
                yes: function (index, layero) {
                    console.info(layero.find("input[name='address']").size());
                    var addressObj = layero.find("input[name='address']:checked");
                    if (addressObj.val() !== null) {
                        var addressDetailed = addressObj.parent().find(".addressDetailed");
                        $(".addresId").html(addressDetailed);
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

$(function () {
    //修改商品数量
    $("a[name='editComNum']").on('click', function () {
        var $this = $(this);
        var text = "修改";
        //将要修改的 数量修改状态 0-修改完成,1-修改未完成
        var statusTemp = "0";
        var flag = true;
        //修改商品数量按钮
        var buttonClass = "layui-btn layui-btn-mini";
        //提交订单按钮
        var submitOrderClass = "layui-btn layui-btn-warm";
        var submitOrderDisable = false;

        var index = $("a[name='editComNum']").index($this)
        //商品数量对象
        var commodityNumObj = $("input[name='commodityNum']").eq(index);
        //数量修改状态:0-修改完成,1-修改未完成
        var status = commodityNumObj.attr("title");
        if (status == '0') {//修改
            text = "完成";
            statusTemp = "1";
            flag = false;
            buttonClass = "layui-btn layui-btn-warm layui-btn-mini";
            //禁用提交订单按钮
            submitOrderClass = "layui-btn layui-btn-warm layui-btn-disabled";
            submitOrderDisable = true;
        } else if (status == '1') {//完成
            var commodityId = $this.prev().val();
            var commodityNum = commodityNumObj.val();
            $.ajaxSettings.async = false;
            var url = path + "/shoppingCart/updateShoppingCartComNum/" + commodityId + "/" + commodityNum;
            $.post(url, function (result) {
                if (result == '0') {
                    //计算小计
                    var unitPrice = $(".unitPrice").eq(index).attr("title");
                    $(".subtotal").eq(index).text('￥' + fmoney((commodityNum * unitPrice), 2));
                } else {//更新出错时
                    new TipBox({type: 'error', str: '商品数量更新出错！', hasBtn: true});
                    text = "完成";
                    statusTemp = "1";
                    flag = false;
                    buttonClass = "layui-btn layui-btn-warm layui-btn-mini";
                }
                commodityNumObj.attr("title", statusTemp);
            });
            //计算所有选中的商品总价格
            total();
            //所有的status为0时,放开提交订单按钮
            $("input[name='commodityNum']").each(function () {
                if ($(this).attr("title") == '1') {
                    submitOrderClass = "layui-btn layui-btn-warm layui-btn-disabled";
                    submitOrderDisable = true;
                    return;
                }
            });
        }
        $this.text(text);
        $this.attr("class", buttonClass);
        commodityNumObj.attr("title", statusTemp);
        commodityNumObj.attr("readonly", flag);
        $("#submitOrder").attr("class", submitOrderClass);
        $("#submitOrder").attr("disabled", submitOrderDisable);
    });

    //提交订单
    $("#submitOrder").on("click", function () {
        var size = $("input[name='checkItem']:checked").size();
        if (size <= 0) {
            new TipBox({type: 'error', str: '请选择要购买的商品!', hasBtn: true});
            return;
        }
        //收货地址id
        var addressId = $("input[name='addressId']").val();
        if (typeof(addressId) == 'undefined' || addressId == '') {
            new TipBox({type: 'error', str: '请选择收货地址!', hasBtn: true});
            return;
        }

        var tipBox = new TipBox({type: 'load', str: "加载中..."});
        //要购买的商品
        var commodityIds = new Array();
        //要购买的商品数量
        var commodityNums = new Array();
        $("input[name='checkItem']:checked").each(function (index) {
            var $this = $(this);
            var $thisTr = $this.parents("tr");
            //要购买的商品id
            var commodityId = $thisTr.find("input[name='commodityId']").val();
            commodityIds[index] = commodityId;
            //要购买的商品数量
            var commodityNum = $thisTr.find("input[name='commodityNum']").val();
            commodityNums[index] = commodityNum;
        });
        var url = path + "/orderForm/saveOrderForm";
        var data = {"addressId": addressId, "commodityIds": commodityIds, "commodityNums": commodityNums};
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            success: function (obj) {
                if (tipBox != null) {
                    tipBox.close();
                }
                var message = obj.message;
                var type = obj.type;
                var objData = obj.data;
                if (type == 'success') {
                    new TipBox({
                        type: type, str: message, hasBtn: true, setTime: 1500, callBack: function () {
                            if (objData) {
                                //订单号
                                var orderFormId = objData.orderFormId;
                                //是否打开支付页面
                                var isOpen = objData.isOpen;
                                if (isOpen) {
                                    $(window.location).attr('href', path + "/aliPay/openAliPayPage/" + orderFormId);
                                } else {
                                    $(window.location).attr('href', path + "/shoppingCart/myShoppingCart");
                                }
                            } else {
                                $(window.location).attr('href', path + "/shoppingCart/myShoppingCart");
                            }
                        }
                    });
                } else {
                    new TipBox({type: type, str: message, hasBtn: true})
                }
            },
            error: function (obj) {
                if (tipBox != null) {
                    tipBox.close();
                }
                new TipBox({type: 'error', str: '网络错误', hasBtn: true})
            }
        });
    });
});

/**
 * 计算所有选种商品的总价格
 */
function total() {
    var total = 0.00;
    $("input[name='checkItem']:checked").each(function () {
        $this = $(this);
        var $thisTr = $this.parent().parent().parent();
        var commodityNumObj = $thisTr.find("input[name='commodityNum']");
        var statusTemp = commodityNumObj.attr("title");
        if (statusTemp == '0') {//修改完成状态
            //商品数量
            var commodityNum = rmoney(commodityNumObj.val());
            //商品单价
            var unitPrice = rmoney($thisTr.find(".unitPrice").attr("title"));
            total += commodityNum * unitPrice;
        }
    });
    $("#total").val(fmoney(total, 2));
}
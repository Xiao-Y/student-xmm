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

    $('.site-table tbody tr').on('click', function (event) {
        var $this = $(this);
        var $input = $this.children('td').eq(0).find('input');
        $input.on('ifChecked', function (e) {
            $this.css('background-color', '#EEEEEE');
        });
        $input.on('ifUnchecked', function (e) {
            $this.removeAttr('style');
        });
        $input.iCheck('toggle');
    }).find("input[name='checkItem']").each(function (index) {
        var $this = $(this);
        $this.on('ifChecked', function (e) {
            $this.parents('tr').css('background-color', '#EEEEEE');
        });
        $this.on('ifUnchecked', function (e) {
            $this.parents('tr').removeAttr('style');
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
});

$(function () {
    //修改商品数量
    $("a[name='editComNum']").on('click', function () {
        var $this = $(this);
        var text = "修改";
        var statusTemp = "0";
        var flag = true;
        var buttonClass = "layui-btn layui-btn-mini";

        var index = $("a[name='editComNum']").index($this)
        var commodityNumObj = $("input[name='commodityNum']").eq(index);
        var status = commodityNumObj.attr("class");
        if (status == '0') {//修改
            text = "完成";
            statusTemp = "1";
            flag = false;
            buttonClass = "layui-btn layui-btn-warm layui-btn-mini";
            //禁用提交订单按钮
            //todo
        } else if (status == '1') {//完成
            var commodityId = $this.attr("title");
            var commodityNum = commodityNumObj.val();
            $.ajaxSettings.async = false;
            var url = path + "/shoppingCart/updateShoppingCartComNum/" + commodityId + "/" + commodityNum;
            $.post(url, function (result) {
                if (result == '0') {
                    //计算小计
                    var unitPrice = $(".unitPrice").eq(index).attr("title");
                    $(".subtotal").eq(index).text((commodityNum * unitPrice).toFixed(2));

                }else{//更新出错时
                    new TipBox({type:'error',str:'商品数量更新出错！',hasBtn:true});
                    text = "完成";
                    statusTemp = "1";
                    flag = false;
                    buttonClass = "layui-btn layui-btn-warm layui-btn-mini";
                }
            });
            //检查所有商品数量是否为只读,如果是,放开提交订单按钮,计算所有选中的商品总价格
        }
        $this.text(text);
        $this.attr("class", buttonClass);
        commodityNumObj.attr("class", statusTemp);
        commodityNumObj.attr("readonly", flag);
    });
});
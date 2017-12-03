<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="payModal">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button id="buttonClose" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
                <h4 class="modal-title">支付选择</h4>
            </div>
            <div class="modal-body">
                <p id="payContent">请选择你想要的支付方式</p>
            </div>
            <div class="modal-footer">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a id="aliPay" href="#" class="btn btn-success">支付宝支付</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a id="wexinPay" href="#" class="btn btn-success" disabled="">微信支付</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </div>
</div>

<script>
    /**
     * 绑定支付选择
     */
    function showPayModal(orderFormId) {
        $("#aliPay").attr("href", path + "/aliPay/openAliPayPage/" + orderFormId);
        $("#wexinPay").attr("href", "#");
        $("#payModal").modal();
    }
</script>

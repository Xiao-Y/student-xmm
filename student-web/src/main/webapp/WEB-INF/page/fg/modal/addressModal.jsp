<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pub/taglib.jsp" %>
<div class="modal fade" id="addressModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                &nbsp;&nbsp;<h4>更换收货地址</h4>
            </div>
            <div class="modal-body">
                <div class="table-content table-responsive">
                    <c:if test="${empty addressDtos}">
                        <h4>还没有收货地址，请在“收货地址”-“添加新的收货地址”中添加...</h4>
                    </c:if>
                    <c:if test="${not empty addressDtos}">
                        <table>
                            <thead>
                            <tr>
                                <th></th>
                                <th class="product-consignee">收货人</th>
                                <th class="product-phone">收货人电话</th>
                                <th class="product-status">收货人地址</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="address" items="${addressDtos}">
                                <tr>
                                    <td>
                                        <input style="width:20px;" name="addressId" type="radio" value="${address.id}">
                                    </td>
                                    <td class="product-consignee">${address.consignee}</td>
                                    <td class="product-phone">${address.consigneePhone}</td>
                                    <td class="product-address">${address.consigneeAddress}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div class="buttons-cart">
                        <input type="button" name="updateAddress" value="确定">
                        <input type="button" data-dismiss="modal" aria-label="Close" value="关闭">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {

        //更换收货地址
        $("input[name='updateAddress']").on("click", function () {
            var addressIdChecked = $("[name='addressId']:checked");
            var addressId = addressIdChecked.val();
            if (typeof(addressId) == 'undefined' || addressId == '') {
                $("#addressModal").modal('hide');
                new TipBox({
                    type: 'tip', str: '请选择一个收货地址！', hasBtn: false, setTime: 1500, callBack: function () {
                        $("#addressModal").modal('show');
                    }
                });
            } else {
                var tr = addressIdChecked.parents("tr");
                var consignee = tr.find(".product-consignee").text();
                var phone = tr.find(".product-phone").text();
                var address = tr.find(".product-address").text();

                $("#addressId").val(addressId);
                $("#consignee").text(consignee);
                $("#consigneePhone").text(phone);
                $("#consigneeAddress").text(address);
                $("#addressModal").modal('hide');
            }
        });
    });
</script>

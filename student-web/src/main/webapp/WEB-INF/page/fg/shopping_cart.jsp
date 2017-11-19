<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>我的购物车</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
    ============================================ -->
    <jsp:include page="/static/fg_js_css/pubCss.jsp" flush="true"/>

</head>

<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<!-- header start -->
<jsp:include page="/WEB-INF/page/fg/pub/header.jsp" flush="true"/>
<!-- header end -->

<div class="space-custom"></div>
<!-- breadcrumb start -->
<div class="breadcrumb-area">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-home"></i></a></li>
            <li class="active">我的购物车</li>
        </ol>
    </div>
</div>
<!-- breadcrumb end -->
<!-- cart-main-area start -->
<div class="cart-main-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <form action="#">
                    <div class="table-content table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th><input style="width:20px;" id="checkBoxAll" type="checkbox" checked></th>
                                <th class="product-thumbnail">商品图片</th>
                                <th class="product-name">商品名称</th>
                                <th class="product-price">商品单价</th>
                                <th class="product-quantity">商品数量</th>
                                <th class="product-subtotal">商品小计</th>
                                <th class="product-remove">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="shoppingCartDto" items="${list}">
                                <c:set var="commodity" value="${shoppingCartDto.commodityDto}"/>
                                <tr>
                                    <td class="product-thumbnail">
                                        <c:if test="${commodity.status == '1' && commodity.valid == '1'}">
                                            <input style="width:20px;" type="checkbox" name="checkBoxItem" checked>
                                        </c:if>
                                    </td>
                                    <td class="product-thumbnail">
                                            <%--<img class="lazy" alt="商品图片"
                                                 data-original="/upload/${commodity.img }"/>--%>
                                        <div class="product-img">
                                            <a href="#">
                                                <img class="lazy" alt="商品图片"
                                                     data-original="/upload/${commodity.img }"/>
                                            </a>
                                            <c:if test="${commodity.valid == '0'}">
                                                <span class="hot-label">已下架</span>
                                            </c:if>
                                            <c:if test="${commodity.valid == '1'}">
                                                <c:if test="${commodity.status == '0'}">
                                                    <span class="hot-label">无货</span>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </td>
                                    <td class="product-name">
                                        <input type="hidden" name="commodityId" value="${shoppingCartDto.commodityId }">
                                        <a href="#">${commodity.commodityName }</a>
                                    </td>
                                    <td class="product-price unitPrice" title="${commodity.unitPrice }"><span
                                            class="amount">${commodity.unitPrice }/${commodity.spec }</span></td>
                                    <td class="product-quantity">
                                        <input type="number" name="commodityNum"
                                               value="${shoppingCartDto.commodityNum }"/>
                                    </td>
                                    <td class="product-subtotal">${commodity.unitPrice * shoppingCartDto.commodityNum }</td>
                                    <td class="product-remove">
                                        <a href="javascript:void(0);" name="removeCommodity"
                                           url="${ctx }/shoppingCart/deleteShoppingCart/${shoppingCartDto.commodityId }">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <%--<tr>
                                <td class="product-thumbnail"><input name="checkBoxItem" type="checkbox"></td>
                                <td class="product-thumbnail">
                                    <a href="#"><img src="/static/fg_js_css/img/product/2.jpg" alt=""/></a>
                                </td>
                                <td class="product-name"><a href="#">Vestibulum dictum magna</a></td>
                                <td class="product-price"><span class="amount">£50.00</span></td>
                                <td class="product-quantity"><input type="number" value="1"/></td>
                                <td class="product-subtotal">£50.00</td>
                                <td class="product-remove"><a href="#"><i class="fa fa-times"></i></a></td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-md-8 col-sm-7 col-xs-12">
                            <h2>配送信息</h2>
                            <div class="buttons-cart">
                                <input type="button" onclick="javascript:location.reload();" value="刷新购物车"/>
                                <a href="#" data-toggle="modal" data-target="#addressModal">更换收货地址</a>
                            </div>
                            <div class="coupon address">
                                <c:if test="${empty address}">
                                    <h3>还没有收货地址，请在“收货地址”-“添加新的收货地址”中添加...</h3>
                                </c:if>
                                <c:if test="${not empty address}">
                                    <input type="hidden" id="addressId" name="addressId" value="${address.id}">
                                    <h3>
                                        <strong>收货人：</strong>
                                        <span style="color:#ffae00;"
                                              id="consignee">${address.consignee}</span>
                                    </h3>
                                    <h3>
                                        <strong>手机号码：</strong>
                                        <span style="color:#ffae00;"
                                              id="consigneePhone">${address.consigneePhone}</span>
                                    </h3>
                                    <h3>
                                        <strong>详细地址：</strong>
                                        <span style="color:#ffae00;"
                                              id="consigneeAddress">${address.consigneeAddress}</span>
                                    </h3>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-5 col-xs-12">
                            <div class="cart_totals">
                                <h2>订单信息</h2>
                                <table>
                                    <tbody>

                                    <tr class="order-total">
                                        <th>订单总计(￥)</th>
                                        <td><span class="amount" id="total">00.00</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="wc-proceed-to-checkout">
                                    <a href="#" id="submitOrder">提交订单</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- cart-main-area end -->
<!-- footer start -->
<jsp:include page="/WEB-INF/page/fg/pub/footer.jsp" flush="true"/>
<!-- footer end -->
<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
<jsp:include page="/pub/pubTips.jsp" flush="true"/>

<!-- Modal start -->
<jsp:include page="/WEB-INF/page/fg/pub/addressModal.jsp" flush="true"/>
<!-- Modal end -->

<script type="text/javascript" src="${ctx }/static/js/common/dataTool.js"></script>
<script src="/static/plugins/tuupola-jquery_lazyload/jquery.lazyload.js"></script>

<script>

    $("img.lazy").lazyload({
        placeholder: "/upload/load.gif",
        effect: "fadeIn"
    });

    $(function () {

        total();

        $("#checkBoxAll").on("click", function () {
            var checkBoxAll = $(this).is(':checked');
            $("input[name='checkBoxItem']").each(function () {
                $(this).prop("checked", checkBoxAll);
            });
        });

        $("#checkBoxAll,input[name='checkBoxItem']").on("click", function () {
            total();
        });
        $("input[name='commodityNum']").on("blur", function () {
            total();
        });

        $("a[name='removeCommodity']").on('click', function () {
            var $this = $(this);
            var url = $this.attr("url");
            var tipBox = null;
            new TipBox({
                type: 'confirm',
                str: '确定删除？',
                hasBtn: true,
                callBack: function () {
                    $.ajax({
                        type: 'POST',
                        url: url,
                        dataType: 'json',
                        beforeSend: function (XHR) {
                            tipBox = new TipBox({type: 'load', str: "正在删除..."});
                        },
                        success: function (data) {
                            if (tipBox != null) {
                                tipBox.close();
                            }
                            var message = data.message;
                            var type = data.type;
                            new TipBox({type: type, str: message, hasBtn: true, setTime: 2500});
                            if (type == 'success') {
                                $this.parents("tr").remove();
                            }
                            total();
                        },
                        error: function (obj) {
                            if (tipBox != null) {
                                tipBox.close();
                            }
                            new TipBox({type: 'error', str: '网络错误', hasBtn: true})
                        }
                    });
                }
            });
        });

        /**
         * 计算所有选种商品的总价格
         */
        function total() {
            var total = 0.00;
            $("input[name='checkBoxItem']:checked").each(function () {
                var $this = $(this);
                var $thisTr = $this.parents("tr");
                var commodityNumObj = $thisTr.find("input[name='commodityNum']");
                //商品数量
                var commodityNum = rmoney(commodityNumObj.val());
                //商品单价
                var unitPrice = rmoney($thisTr.find(".unitPrice").attr("title"));
                total += commodityNum * unitPrice;
            });
            $("#total").html(fmoney(total, 2));
        }

        //提交订单
        $("#submitOrder").on("click", function () {
            var size = $("input[name='checkBoxItem']:checked").size();
            if (size <= 0) {
                new TipBox({type: 'error', str: '请选择要购买的商品!', hasBtn: true});
                return;
            }
            //收货地址id
            var addressId = $("#addressId").val();
            if (typeof(addressId) == 'undefined' || addressId == '') {
                new TipBox({type: 'error', str: '请选择收货地址!', hasBtn: true});
                return;
            }

            var tipBox = new TipBox({type: 'load', str: "正在提交订单..."});
            //要购买的商品
            var commodityIds = new Array();
            //要购买的商品数量
            var commodityNums = new Array();
            $("input[name='checkBoxItem']:checked").each(function (index) {
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
                    if (type == 'success') {
                        new TipBox({
                            type: type, str: message, hasBtn: true, setTime: 1500, callBack: function () {
                                new TipBox({type: 'load', str: "页面加载中..."});
                                location.reload();
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
    })
</script>
</body>

</html>

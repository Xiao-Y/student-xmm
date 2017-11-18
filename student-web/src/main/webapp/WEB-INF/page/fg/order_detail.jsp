<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Cart</title>
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
            <li><a href="/fg/fgHome/index"><i class="fa fa-home"></i></a></li>
            <li><a href="/fg/fgHome/order?pageNo=${pageNo }">我的订单</a></li>
            <li class="active">订单详细</li>
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
                                <th class="product-thumbnail">商品图片</th>
                                <th class="product-name">商品名称</th>
                                <th class="product-price">商品单价</th>
                                <th class="product-quantity">商品数量</th>
                                <th class="product-subtotal">商品小计</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="orderFormDetail" items="${list}">
                                <tr>
                                    <td class="product-thumbnail commodityOption">
                                        <input type="hidden" value="${orderFormDetail.commodityId}" name="commodityId">
                                        <a href="#" name="modelView">
                                            <img src="/upload/${orderFormDetail.commodityImg}" alt="商品图片"/>
                                        </a>
                                    </td>
                                    <td class="product-name">
                                        <a href="#">${orderFormDetail.commodityName }</a>
                                    </td>
                                    <td class="product-price">
                                        <span class="amount">${orderFormDetail.unitPrice }/${orderFormDetail.spec }</span>
                                    </td>
                                    <td class="product-quantity">
                                        <input type="number" value="${orderFormDetail.commodityNum }" readonly/>
                                    </td>
                                    <td class="product-subtotal">
                                        <fmt:formatNumber type="currency"
                                                          value="${orderFormDetail.unitPrice * orderFormDetail.commodityNum }"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            <%--<tr>
                                <td class="product-thumbnail"><a href="#"><img src="/static/fg_js_css/img/product/2.jpg"
                                                                               alt=""/></a></td>
                                <td class="product-name"><a href="#">Vestibulum dictum magna</a></td>
                                <td class="product-price"><span class="amount">£50.00</span></td>
                                <td class="product-quantity"><input type="number" value="1" readonly/></td>
                                <td class="product-subtotal">£50.00</td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-md-8 col-sm-7 col-xs-12">
                            <h2>配送信息</h2>
                            <br>
                            <div class="coupon">
                                <h3>
                                    <strong>收货人：</strong>
                                    <span style="color:#ffae00;">${orderForm.consignee}</span>
                                </h3>
                                <h3>
                                    <strong>手机号码：</strong>
                                    <span style="color:#ffae00;">${orderForm.consigneePhone}</span>
                                </h3>
                                <h3>
                                    <strong>详细地址：</strong>
                                    <span style="color:#ffae00;">${orderForm.consigneeAddress}</span>
                                </h3>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-5 col-xs-12">
                            <div class="cart_totals">
                                <h2>订单信息</h2>
                                <table>
                                    <tbody>
                                    <tr class="cart-subtotal">
                                        <th>订单号</th>
                                        <td><span class="amount">${orderForm.id}</span></td>
                                    </tr>
                                    <tr class="cart-subtotal">
                                        <th>下单时间</th>
                                        <td>
                                            <strong>
                                                <span class="amount">
                                                    <fmt:formatDate value="${orderForm.createDate }"
                                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </span>
                                            </strong>
                                        </td>
                                    </tr>
                                    <tr class="order-total">
                                        <th>订单总计</th>
                                        <td>
                                            <span class="amount">
                                                <fmt:formatNumber type="currency"
                                                                  value="${orderForm.orderformAmount }"/>
                                            </span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
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
<jsp:include page="/WEB-INF/page/fg/pub/procuctModal.jsp" flush="true"/>
<!-- Modal end -->

</body>

</html>

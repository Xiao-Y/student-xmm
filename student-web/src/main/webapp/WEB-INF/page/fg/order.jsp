<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>我的订单</title>
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
            <li><a href="#">Shop</a></li>
            <li class="active">我的订单</li>
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
                                <th class="product-date">订单号码</th>
                                <th class="product-consignee">收货人</th>
                                <th class="product-phone">收货人电话</th>
                                <th class="product-price">订单金额</th>
                                <th class="product-status">订单状态</th>
                                <th class="product-date">下单时间</th>
                                <th class="product-remove">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="product-thumbnail"><a href="/fg/fgHome/orderDetail">E20171107084048655001</a></td>
                                <td class="product-consignee">billow</td>
                                <td class="product-phone">15507529497</td>
                                <td class="product-price"><span class="amount">￥8.63</span></td>
                                <td class="product-status">客户提交</td>
                                <td class="product-date">2017-11-07 08:40:48</td>
                                <td class="product-remove"><a href="#">确认订单</a><a href="#">取消订单</a></td>
                            </tr>
                            <tr>
                                <td class="product-thumbnail"><a href="/fg/fgHome/orderDetail">E20171107084048655001</a></td>
                                <td class="product-consignee">billow</td>
                                <td class="product-phone">15507529497</td>
                                <td class="product-price"><span class="amount">￥8.63</span></td>
                                <td class="product-status">客户提交</td>
                                <td class="product-date">2017-11-07 08:40:48</td>
                                <td class="product-remove"><a href="#">确认订单</a><a href="#">取消订单</a></td>
                            </tr>
                            <tr>
                                <td class="product-thumbnail"><a href="/fg/fgHome/orderDetail">E20171107084048655001</a></td>
                                <td class="product-consignee">billow</td>
                                <td class="product-phone">15507529497</td>
                                <td class="product-price"><span class="amount">￥8.63</span></td>
                                <td class="product-status">客户提交</td>
                                <td class="product-date">2017-11-07 08:40:48</td>
                                <td class="product-remove"><a href="#">确认订单</a><a href="#">取消订单</a></td>
                            </tr>
                            </tbody>
                        </table>
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
</body>

</html>

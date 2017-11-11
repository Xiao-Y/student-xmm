<!doctype html>
<html class="no-js" lang="en">

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
            <li><a href="#"><i class="fa fa-home"></i></a></li>
            <li><a href="#">我的订单</a></li>
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
                            <tr>
                                <td class="product-thumbnail"><a href="#"><img src="/static/fg_js_css/img/product/1.jpg" alt=""/></a></td>
                                <td class="product-name"><a href="#">Vestibulum suscipit</a></td>
                                <td class="product-price"><span class="amount">£165.00</span></td>
                                <td class="product-quantity"><input type="number" value="1" readonly/></td>
                                <td class="product-subtotal">£165.00</td>
                            </tr>
                            <tr>
                                <td class="product-thumbnail"><a href="#"><img src="/static/fg_js_css/img/product/2.jpg" alt=""/></a></td>
                                <td class="product-name"><a href="#">Vestibulum dictum magna</a></td>
                                <td class="product-price"><span class="amount">£50.00</span></td>
                                <td class="product-quantity"><input type="number" value="1" readonly/></td>
                                <td class="product-subtotal">£50.00</td>
                            </tr>
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
                                    <span style="color:#ffae00;">billow</span>
                                </h3>
                                <h3>
                                    <strong>手机号码：</strong>
                                    <span style="color:#ffae00;">15507529497</span>
                                </h3>
                                <h3>
                                    <strong>详细地址：</strong>
                                    <span style="color:#ffae00;">上海上海上海上海上海上海上海上海上海上海上海上海</span>
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
                                        <td><span class="amount">E20171107084048655001</span></td>
                                    </tr>
                                    <tr class="cart-subtotal">
                                        <th>下单时间</th>
                                        <td>
                                            <strong><span class="amount">2017-11-07 08:40:48</span></strong>
                                        </td>
                                    </tr>
                                    <tr class="order-total">
                                        <th>订单总计</th>
                                        <td><span class="amount">￥215.00</span></td>
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

<!-- Modal -->
<div class="modal fade" id="productModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">x</span></button>
            </div>
            <div class="modal-body">
                <div class="modal-img">
                    <a href="shop.html"><img src="/static/fg_js_css/img/product/1.jpg" alt=""/></a>
                </div>
                <div class="modal-pro-content">
                    <h3><a href="#">Phasellus Vel Hendrerit</a></h3>
                    <div class="pro-rating">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star-o"></i>
                    </div>
                    <span>(2 customer reviews)</span>
                    <div class="price">
                        <span>$70.00</span>
                        <span class="old">$80.11</span>
                    </div>
                    <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                        Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet.</p>
                    <form action="#">
                        <input type="number" value="1"/>
                        <button>Add to cart</button>
                    </form>
                    <div class="product_meta">
                        <span class="posted_in">Categories: <a rel="tag" href="#">Albums</a>, <a rel="tag" href="#">Music</a></span>
                        <span class="tagged_as">Tags: <a rel="tag" href="#">Albums</a>, <a rel="tag" href="#">Music</a></span>
                    </div>
                    <div class="social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-google-plus"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal end -->

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
</body>

</html>

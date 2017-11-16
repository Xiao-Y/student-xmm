<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header-pos">
    <div class="header-area header-middle">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 col-sm-3 col-xs-12">
                    <div class="logo">
                        <a href="index.html"><img src="/static/fg_js_css/img/logo/logo.jpg" alt=""/></a>
                    </div>
                </div>
                <div class="col-md-10 col-sm-9 col-xs-12 text-right xs-center">
                    <div class="main-menu display-inline hidden-sm hidden-xs">
                        <nav>
                            <ul>
                                <li><a href="/fg/fgHome/index">首页</a></li>
                                <li><a href="/fg/fgHome/shop">商品列表</a></li>
                                <li>
                                    <a href="javascript:void(0);">个人中心</a>
                                    <div class="mega-menu">
                                        <ul>
                                            <li><a href="/fg/fgHome/order">我的订单</a></li>
                                            <li><a href="/fg/fgHome/userInfo">修改信息</a></li>
                                            <li><a href="/fg/fgHome/editPassword">修改密码</a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li><a href="javascript:void(0);">收货地址</a>
                                    <div class="mega-menu">
                                        <ul>
                                            <li><a href="/fg/fgHome/address">我的收货地址</a></li>
                                            <li><a href="/fg/fgHome/addressEdit">添加新收货地址</a></li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- 搜索start -->
                    <!-- <div class="search-block-top display-inline">
                        <div class="icon-search"></div>
                        <div class="toogle-content">
                            <form action="#" id="searchbox">
                                <input type="text" placeholder="Search" />
                                <button class="button-search"></button>
                            </form>
                        </div>
                    </div> -->
                    <!-- 搜索end -->
                    <!-- 购物车start -->
                    <div class="shopping-cart ml-20 display-inline">
                        <a href="/fg/fgHome/shoppingCart" id="myShoppingCart"><b>我的购物车</b>(${shoppingCount})</a>
                        <%--<ul>
                            <li>
                                <div class="cart-img">
                                    <a href="#"><img src="/static/fg_js_css/img/cart/1.jpg" alt=""/></a>
                                </div>
                                <div class="cart-content">
                                    <h3><a href="#"> 1 X Faded...</a></h3>
                                    <span><b>S, Orange</b></span>
                                    <span class="cart-price">£ 16.84</span>
                                </div>
                                <div class="cart-del">
                                    <i class="fa fa-times-circle"></i>
                                </div>
                            </li>
                            <li>
                                <div class="cart-img">
                                    <a href="#"><img src="/static/fg_js_css/img/cart/1.jpg" alt=""/></a>
                                </div>
                                <div class="cart-content">
                                    <h3><a href="#"> 1 X Faded...</a></h3>
                                    <span><b>S, Orange</b></span>
                                    <span class="cart-price">£ 16.84</span>
                                </div>
                                <div class="cart-del">
                                    <i class="fa fa-times-circle"></i>
                                </div>
                            </li>
                            <li>
                                <div class="shipping">
                                    <span class="f-left">Shipping </span>
                                    <span class="f-right cart-price"> $7.00</span>
                                </div>
                                <hr class="shipping-border"/>
                                <div class="shipping">
                                    <span class="f-left"> Total </span>
                                    <span class="f-right cart-price">$692.00</span>
                                </div>
                            </li>
                            <li class="checkout m-0"><a href="#">checkout <i class="fa fa-angle-right"></i></a></li>
                        </ul>--%>
                    </div>
                    <!-- 购物车end -->
                    <!-- 设置start -->
                    <!-- <div class="setting-menu display-inline">
                        <div class="icon-nav current"></div>
                        <ul class="content-nav toogle-content">
                            <li class="currencies-block-top">
                                <div class="current"><b>Currency : USD</b></div>
                                <ul>
                                    <li><a href="#">Dollar (USD)</a></li>
                                    <li><a href="#">Pound (GBP)</a></li>
                                </ul>
                            </li>
                            <li class="currencies-block-top">
                                <div class="current"><b>English</b></div>
                                <ul>
                                    <li><a href="#">English</a></li>
                                    <li><a href="#">اللغة العربية</a></li>
                                </ul>
                            </li>
                            <li class="currencies-block-top">
                                <div class="current"><b>My Account</b></div>
                                <ul>
                                    <li><a href="#">My account</a></li>
                                    <li><a href="#">My wishlist</a></li>
                                    <li><a href="#">Checkout</a></li>
                                    <li><a href="#">Log in</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div> -->
                    <!-- 设置end -->
                </div>
            </div>
        </div>
    </div>
    <!-- 手机显示菜单start -->
    <div class="mobile-menu-area visible-sm visible-xs">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="mobile-menu">
                        <nav id="mobile-menu-active">
                            <ul>
                                <li><a href="#">Home</a></li>
                                <li><a href="shop.html">Sofa</a></li>
                                <li><a href="#">Elements</a></li>
                                <li><a href="shop.html">Lighting</a></li>
                                <li><a href="#">Pages</a></li>
                                <li><a href="#">What's New</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 手机显示菜单end -->
</header>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>商品列表</title>
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
<!-- 面包屑 start -->
<div class="breadcrumb-area">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-home"></i></a></li>
            <li class="active">商品列表</li>
        </ol>
    </div>
</div>
<!-- 面包屑 end -->
<!-- shop-area start -->
<div class="shop-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-8">
                <div class="shop-page-bar">
                    <div>
                        <!-- 商品显示样式 start -->
                        <div class="shop-bar">
                            <!-- Nav tabs -->
                            <ul class="shop-tab f-left" role="tablist">
                                <li role="presentation" class="active"><a href="#home" data-toggle="tab"><i
                                        class="fa fa-th-large" aria-hidden="true"></i></a></li>
                                <li role="presentation"><a href="#profile" data-toggle="tab"><i class="fa fa-th-list"
                                                                                                aria-hidden="true"></i></a>
                                </li>
                            </ul>
                        </div>
                        <!-- 商品显示样式 end -->
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <!-- 商品显示1 start -->
                            <div role="tabpanel" class="tab-pane active" id="home">
                                <div class="row">
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/5.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">¥262.00</span>
                                                        <span class="old-price product-price">¥262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/6.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/4.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/12.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/3.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/5.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/12.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/3.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/4.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/5.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/6.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-6">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/7.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                                <div class="product-action">
                                                    <a href="#"><i class="pe-7s-cart"></i></a>


                                                    <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                            class="pe-7s-look"></i></a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">$262.00</span>
                                                        <span class="old-price product-price">$262.00</span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 商品显示1 end -->
                            <!-- 商品显示2 start -->
                            <div role="tabpanel" class="tab-pane" id="profile">
                                <div class="row mb-50">
                                    <div class="col-xs-5 col-sm-5 col-md-3">
                                        <div class="product-wrapper">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/1.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-7 col-sm-7 col-md-8">
                                        <div class="product-content product-list">
                                            <div class="pro-title">
                                                <h3><a href="product-details.html">Mattis Lobortis </a></h3>
                                            </div>
                                            <div class="price-reviews">
                                                <div class="price-box">
                                                    <span class="price product-price">$500.00</span>
                                                </div>

                                            </div>
                                            <p>Faded short sleeves t-shirt with high neckline. Soft and stretchy
                                                material for a comfortable fit. Accessorize with a straw hat and you're
                                                ready for summer!</p>
                                            <div class="product-action">
                                                <a class="cart" href="#"><span>Add to cart</span></a>


                                                <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                        class="pe-7s-look"></i></a>
                                            </div>
                                            <div class="color-list">
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mb-50">
                                    <div class="col-xs-5 col-sm-5 col-md-3">
                                        <div class="product-wrapper">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/7.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-7 col-sm-7 col-md-8">
                                        <div class="product-content product-list">
                                            <div class="pro-title">
                                                <h3><a href="product-details.html">Cras Neque Metus</a></h3>
                                            </div>
                                            <div class="price-reviews">
                                                <div class="price-box">
                                                    <span class="price product-price">$262.00</span>
                                                    <span class="old-price product-price">$262.00</span>
                                                </div>

                                            </div>
                                            <p>Faded short sleeves t-shirt with high neckline. Soft and stretchy
                                                material for a comfortable fit. Accessorize with a straw hat and you're
                                                ready for summer!</p>
                                            <div class="product-action">
                                                <a class="cart" href="#"><span>Add to cart</span></a>


                                                <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                        class="pe-7s-look"></i></a>
                                            </div>
                                            <div class="color-list">
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mb-50">
                                    <div class="col-xs-5 col-sm-5 col-md-3">
                                        <div class="product-wrapper">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/2.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-7 col-sm-7 col-md-8">
                                        <div class="product-content product-list">
                                            <div class="pro-title">
                                                <h3><a href="product-details.html">Proin Lectus </a></h3>
                                            </div>
                                            <div class="price-reviews">
                                                <div class="price-box">
                                                    <span class="price product-price">$262.00</span>
                                                    <span class="old-price product-price">$262.00</span>
                                                </div>

                                            </div>
                                            <p>Faded short sleeves t-shirt with high neckline. Soft and stretchy
                                                material for a comfortable fit. Accessorize with a straw hat and you're
                                                ready for summer!</p>
                                            <div class="product-action">
                                                <a class="cart" href="#"><span>Add to cart</span></a>


                                                <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                        class="pe-7s-look"></i></a>
                                            </div>
                                            <div class="color-list">
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mb-50">
                                    <div class="col-xs-5 col-sm-5 col-md-3">
                                        <div class="product-wrapper">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/4.jpg" alt=""/></a>
                                                <span class="new-label">New</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-7 col-sm-7 col-md-8">
                                        <div class="product-content product-list">
                                            <div class="pro-title">
                                                <h3><a href="product-details.html">Convallis Interdum </a></h3>
                                            </div>
                                            <div class="price-reviews">
                                                <div class="price-box">
                                                    <span class="price product-price">$262.00</span>
                                                    <span class="old-price product-price">$26200.00</span>
                                                </div>

                                            </div>
                                            <p>Faded short sleeves t-shirt with high neckline. Soft and stretchy
                                                material for a comfortable fit. Accessorize with a straw hat and you're
                                                ready for summer!</p>
                                            <div class="product-action">
                                                <a class="cart" href="#"><span>Add to cart</span></a>


                                                <a href="#" data-toggle="modal" data-target="#productModal"><i
                                                        class="pe-7s-look"></i></a>
                                            </div>
                                            <div class="color-list">
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 商品显示2 end -->
                            <div class="content-sortpagibar">
                                <div class="product-count display-inline">Showing 1 - 12 of 13 items</div>
                                <ul class="shop-pagi display-inline">
                                    <li><a href="#"><i class="fa fa-angle-left"></i></a></li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                </ul>
                                <div class="selector-field f-right">
                                    <form action="#">
                                        <button class="compare">Compare (1)</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- shop-area end -->

<!-- footer start -->
<jsp:include page="/WEB-INF/page/fg/pub/footer.jsp" flush="true"/>
<!-- footer end -->

<!-- Modal start -->
<jsp:include page="/WEB-INF/page/fg/pub/procuctModal.jsp" flush="true"/>
<!-- Modal end -->

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
</body>

</html>

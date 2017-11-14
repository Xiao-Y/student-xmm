<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>首页</title>
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

<!-- slider-container 首页顶部显示图片 start -->
<div class="slider-container">
    <!-- Slider Image -->
    <div id="mainSlider" class="nivoSlider slider-image">
        <img src="/static/fg_js_css/img/slider/8.jpg" alt="" title="#htmlcaption1">
        <img src="/static/fg_js_css/img/slider/6.jpg" alt="" title="#htmlcaption2">
        <img src="/static/fg_js_css/img/slider/9.jpg" alt="" title="#htmlcaption3">
    </div>
    <!-- Slider Caption 1 -->
    <div id="htmlcaption1" class="nivo-html-caption slider-caption-1">
        <div class="container">
            <div class="slide1-text">
                <div class="middle-text">
                    <div class="cap-dec cap-1 wow bounceInRight" data-wow-duration="0.9s" data-wow-delay="0s">
                        <h2>A BRAND</h2>
                    </div>
                    <div class="cap-dec cap-2 wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.2s">
                        <h2>NEW ARRIVALS</h2>
                    </div>
                    <div class="cap-text wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.3s">
                        Fascinating outdoor lounge chair with wooden chairs for outdoor ideas with outdoor chaise lounge
                        chair.
                    </div>
                    <div class="cap-readmore wow bounceInUp" data-wow-duration="1.3s" data-wow-delay=".5s">
                        <a href="#">Shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Slider Caption 2 -->
    <div id="htmlcaption2" class="nivo-html-caption slider-caption-2">
        <div class="container">
            <div class="slide1-text">
                <div class="middle-text">
                    <div class="cap-dec cap-1 wow bounceInRight" data-wow-duration="0.9s" data-wow-delay="0s">
                        <h2>A BRAND</h2>
                    </div>
                    <div class="cap-dec cap-2 wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.2s">
                        <h2>NEW ARRIVALS</h2>
                    </div>
                    <div class="cap-text wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.3s">
                        Fascinating outdoor lounge chair with wooden chairs for outdoor ideas with outdoor chaise lounge
                        chair.
                    </div>
                    <div class="cap-readmore wow bounceInUp" data-wow-duration="1.3s" data-wow-delay=".5s">
                        <a href="#">Shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="htmlcaption3" class="nivo-html-caption slider-caption-2">
        <div class="container">
            <div class="slide1-text">
                <div class="middle-text">
                    <div class="cap-dec cap-1 wow bounceInRight" data-wow-duration="0.9s" data-wow-delay="0s">
                        <h2>A BRAND</h2>
                    </div>
                    <div class="cap-dec cap-2 wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.2s">
                        <h2>NEW ARRIVALS</h2>
                    </div>
                    <div class="cap-text wow bounceInRight" data-wow-duration="1.2s" data-wow-delay="0.3s">
                        Fascinating outdoor lounge chair with wooden chairs for outdoor ideas with outdoor chaise lounge
                        chair.
                    </div>
                    <div class="cap-readmore wow bounceInUp" data-wow-duration="1.3s" data-wow-delay=".5s">
                        <a href="#">Shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- slider-container 首页顶部显示图片 end -->

<!-- new-arrival-area start -->
<div class="new-arrival-area pt-100">
    <div class="container">
        <div class="row">
            <div class="section-title text-center mb-20">
                <h2>新品菜单</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="product-tab">
                    <!-- Nav tabs -->
                    <ul class="custom-tab text-center mb-40">
                        <li class="active"><a href="javascript:void(0);" data-toggle="tab">蔬菜/鱼/肉/配料类</a></li>
                        <li><a href="/fg/fgHome/shop">查看更多......</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="row">
                        <div class="tab-content">
                            <div class="tab-pane active" id="home">
                                <div class="product-carousel">

                                    <c:forEach var="commodity" items="${newList}">
                                        <div class="col-md-12">
                                            <div class="product-wrapper mb-40">
                                                <div class="product-img">
                                                    <a href="#"><img src="/static/fg_js_css/img/product/12.jpg" alt=""/></a>
                                                    <span class="new-label">New</span>
                                                    <div class="product-action commodityOption">
                                                        <input type="hidden" value="${commodity.id}" name="commodityId">
                                                        <a href="#" name="AddCart"><i class="pe-7s-cart"></i></a>
                                                        <a href="#" name="modelView" data-toggle="modal" data-target="#productModal">
                                                            <i class="pe-7s-look"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="product-content">
                                                    <div class="pro-title">
                                                        <h3>
                                                            <a href="product-details.html">${commodity.commodityName }</a>
                                                        </h3>
                                                    </div>
                                                    <div class="price-reviews">
                                                        <div class="price-box">
                                                            <span class="price product-price">¥${commodity.unitPrice }/<span>${commodity.spec }</span>
                                                        </div>
                                                        <div class="pro-rating">
                                                            <a href="#"><i class="fa fa-star"></i></a>
                                                            <a href="#"><i class="fa fa-star"></i></a>
                                                            <a href="#"><i class="fa fa-star-o"></i></a>
                                                            <a href="#"><i class="fa fa-star-o"></i></a>
                                                            <a href="#"><i class="fa fa-star-o"></i></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br>
<%-- 热销商品--%>
<div class="container">
    <div class="row">
        <div class="section-title text-center mb-20">
            <h2>热销菜单</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="product-tab">
                <!-- Nav tabs -->

                <!-- Tab panes -->
                <div class="row">
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">
                            <div class="product-carousel">

                                <c:forEach var="commodity" items="${hotList}">
                                    <div class="col-md-12">
                                        <div class="product-wrapper mb-40">
                                            <div class="product-img">
                                                <a href="#"><img src="/static/fg_js_css/img/product/12.jpg" alt=""/></a>
                                                <span class="hot-label">Hot</span>
                                                <div class="product-action commodityOption">
                                                    <input type="hidden" value="${commodity.id}" name="commodityId">
                                                    <a href="#" name="AddCart"><i class="pe-7s-cart"></i></a>
                                                    <a href="#" name="modelView" data-toggle="modal" data-target="#productModal">
                                                        <i class="pe-7s-look"></i>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="pro-title">
                                                    <h3>
                                                        <a href="product-details.html">${commodity.commodityName }</a>
                                                    </h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                            <span class="price product-price">¥${commodity.unitPrice }/<span>${commodity.spec }</span>
                                                    </div>
                                                    <div class="pro-rating">
                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                        <a href="#"><i class="fa fa-star"></i></a>
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- new-arrival-area end -->

<!-- latest-blog-area 底部小图 start -->
<div class="latest-blog-area ptb-60">
    <div class="container">
        <div class="row">
            <div class="section-title text-center mb-50">
                <h2>latest blog</h2>
            </div>
        </div>
        <div class="row">
            <div class="blog-active">
                <div class="col-lg-12">
                    <div class="blog-wrapper mb-40">
                        <div class="blog-img">
                            <a href="#"><img src="/static/fg_js_css/img/blog/1.jpg" alt=""/></a>
                        </div>
                        <div class="blog-info">
                            <h3><a href="#">查看更多...</a></h3>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="blog-wrapper mb-40">
                        <div class="blog-img">
                            <a href="#"><img src="/static/fg_js_css/img/blog/2.jpg" alt=""/></a>
                        </div>
                        <div class="blog-info">
                            <h3><a href="#">查看更多...</a></h3>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="blog-wrapper mb-40">
                        <div class="blog-img">
                            <a href="#"><img src="/static/fg_js_css/img/blog/1.jpg" alt=""/></a>
                        </div>
                        <div class="blog-info">
                            <h3><a href="#">查看更多...</a></h3>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="blog-wrapper mb-40">
                        <div class="blog-img">
                            <a href="#"><img src="/static/fg_js_css/img/blog/2.jpg" alt=""/></a>
                        </div>
                        <div class="blog-info">
                            <h3><a href="#">查看更多...</a></h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- latest-blog-area 底部小图 end -->
<!-- footer start -->
<jsp:include page="/WEB-INF/page/fg/pub/footer.jsp" flush="true"/>
<!-- footer end -->

<!-- Modal start -->
<jsp:include page="/WEB-INF/page/fg/pub/procuctModal.jsp" flush="true"/>
<!-- Modal end -->

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
<jsp:include page="/pub/pubTips.jsp" flush="true"/>
<script>
    $(function () {
        $("[name='modelView']").on('click', function () {
            var commodityId = $(this).parent(".commodityOption").find("input[name='commodityId']").val();
            getProcutModalByCommodityId(commodityId);
        });
    });
</script>

</body>
</html>


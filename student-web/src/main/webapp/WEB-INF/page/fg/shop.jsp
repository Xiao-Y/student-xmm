<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>商品列表</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
    ============================================ -->
    <jsp:include page="/static/fg_js_css/pubCss.jsp" flush="true"/>
    <link rel="stylesheet" href="${ctx }/static/plugins/pagination/pagination.css" media="screen">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>--%>
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
                                <li role="presentation" class="active">
                                    <a href="#home" data-toggle="tab">
                                        <i class="fa fa-th-large" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li role="presentation">
                                    <a href="#profile" data-toggle="tab">
                                        <i class="fa fa-th-list" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- 商品显示样式 end -->
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <!-- 商品显示1 start -->
                            <div role="tabpanel" class="tab-pane active" id="home">
                                <div class="row">

                                    <c:forEach var="commodity" items="${page.list}">
                                        <div class="col-md-3 col-sm-6">
                                            <div class="product-wrapper mb-40">
                                                <div class="product-img">
                                                    <a href="#">
                                                        <img src="/upload/${commodity.img }" alt="商品图片"/>
                                                    </a>
                                                    <c:if test="${commodity.quantity < 10}">
                                                        <span class="new-label">新品</span>
                                                    </c:if>
                                                    <c:if test="${commodity.quantity >= 10 || commodity.quantity >= 30}">
                                                        <span class="pop-label">人气</span>
                                                    </c:if>
                                                    <c:if test="${commodity.quantity > 30}">
                                                        <span class="hot-label">热销</span>
                                                    </c:if>
                                                    <div class="product-action commodityOption">
                                                        <input type="hidden" value="${commodity.id}" name="commodityId">
                                                        <a href="javascript:void(0);" name="addCart"><i
                                                                class="pe-7s-cart"></i></a>
                                                        <a href="javascript:void(0);" name="modelView">
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
                            <!-- 商品显示1 end -->
                            <!-- 商品显示2 start -->
                            <div role="tabpanel" class="tab-pane" id="profile">

                                <c:forEach var="commodity" items="${page.list}">
                                    <div class="row mb-50">
                                        <div class="col-xs-5 col-sm-5 col-md-3">
                                            <div class="product-wrapper">
                                                <div class="product-img">
                                                    <a href="#">
                                                        <img src="/upload/${commodity.img }" alt="商品图片"/>
                                                    </a>
                                                    <span class="new-label">New</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-7 col-sm-7 col-md-8">
                                            <div class="product-content product-list">
                                                <div class="pro-title">
                                                    <h3><a href="product-details.html">${commodity.commodityName } </a>
                                                    </h3>
                                                </div>
                                                <div class="price-reviews">
                                                    <div class="price-box">
                                                        <span class="price product-price">¥${commodity.unitPrice }/<span>${commodity.spec }</span>
                                                    </div>
                                                    <div class="pro-rating">
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                        <a href="#"><i class="fa fa-star-o"></i></a>
                                                    </div>
                                                </div>
                                                <p>
                                                    <c:choose>
                                                        <c:when test="${empty commodity.commodityInfo}">暂无</c:when>
                                                        <c:otherwise>${commodity.commodityInfo}</c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <div class="product-action commodityOption">
                                                    <input type="hidden" value="${commodity.id}" name="commodityId">
                                                    <a class="cart" href="javascript:void(0);" name="addCart"><span>加入购物车</span></i>
                                                    </a>
                                                    <a href="javascript:void(0);" name="modelView">
                                                        <i class="pe-7s-look"></i>
                                                    </a>
                                                </div>
                                                    <%--<div class="color-list">
                                                        <a href="#"></a>
                                                        <a href="#"></a>
                                                    </div>--%>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- 商品显示2 end -->
                            <%-- 分页数据 start --%>
                            <div class="content-sortpagibar">
                                <div class="product-count display-inline">每页${page.pageSize }条,共${page.pages }页</div>
                                <div class="M-box"></div>
                                <input type="hidden" value="${page.pages }" id="pages">
                                <input type="hidden" value="${page.pageNum }" id="pageNum">
                                <input type="hidden" value="${page.pageSize }" id="pageSize">
                            </div>
                            <%-- 分页数据 end --%>
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

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
<jsp:include page="/pub/pubTips.jsp" flush="true"/>

<!-- Modal start -->
<jsp:include page="/WEB-INF/page/fg/pub/procuctModal.jsp" flush="true"/>
<!-- Modal end -->

<script type="text/javascript" src="${ctx }/static/plugins/pagination/jquery.pagination.js"></script>
<script>
    //分页使用
    $('.M-box').pagination({
        coping: true,
        jump: true,//是否开启跳转到指定页数
        isHide: false,//总页数为0或1时隐藏分页控件
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        pageCount: $("#pages").val(),//总数据
        showData: $("#pageSize").val(),//每页显示
        current: $("#pageNum").val(),//当前第几页
        callback: function (api) {
            location.href = path + '/fg/fgHome/shop?' + 'pageNo=' + api.getCurrent();
        }
    });
</script>
</body>

</html>

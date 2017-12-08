<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>我的订单</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
    ============================================ -->
    <jsp:include page="/static/fg_js_css/pubCss.jsp" flush="true"/>
    <link rel="stylesheet" href="${ctx }/static/plugins/pagination/pagination.css" media="screen">

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
                        <c:forEach var="orderFormDto" items="${page.list}">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="${ctx}/fg/fgHome/orderDetail?pageNo=${page.pageNum }&orderFormId=${orderFormDto.id }"
                                       title="查看订单详细信息">${orderFormDto.id }</a>
                                </td>
                                <td class="product-consignee">${orderFormDto.consignee }</td>
                                <td class="product-phone">${orderFormDto.consigneePhone }</td>
                                <td class="product-price">
                                    <span class="amount">
                                        <fmt:formatNumber type="currency" value="${orderFormDto.orderformAmount }"/>
                                    </span>
                                </td>
                                <td class="product-status">
                                        ${orderFormDto.statusName }
                                        <%--<c:choose>
                                            <c:when test="${orderFormDto.status == '1'}">
                                                <span class="btn-primary btn-sm">客户提交</span>
                                            </c:when>
                                            <c:when test="${orderFormDto.status == '2'}">
                                                <span class="btn-info btn-sm">商家确认</span>
                                            </c:when>
                                            <c:when test="${orderFormDto.status == '3'}">
                                                <span class="btn-warning btn-sm">客户取消</span>
                                            </c:when>
                                            <c:when test="${orderFormDto.status == '4'}">
                                                <span class="btn-danger btn-sm">商家取消</span>
                                            </c:when>
                                            <c:when test="${orderFormDto.status == '5'}">
                                                <span class="btn-success btn-sm">交易完成</span>
                                            </c:when>
                                        </c:choose>--%>
                                </td>
                                <td class="product-date">
                                    <fmt:formatDate value="${orderFormDto.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="product-remove">
                                    <c:forEach var="button" items="${orderFormDto.optionButton}">
                                        <c:choose>
                                            <%-- 删除订单 --%>
                                            <c:when test="${button.key == 'DELETE_ORDER_FORM'}">
                                                <a href="javascript:;" name="optionButton"
                                                    param="${orderFormDto.id }&${button.key}&${button.value}">
                                                    <span class="btn-danger btn-sm">${button.value}</span>
                                                </a>
                                            </c:when>
                                            <%-- 继续支付 --%>
                                            <c:when test="${button.key == 'AGPAID'}">
                                                <c:if test="${isOpen}">
                                                    <a href="javascript:;" name="agPaidButton"
                                                       param="${orderFormDto.id }&${button.key}">
                                                        <span class="btn-warning btn-sm">${button.value}</span>
                                                    </a>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="javascript:;" name="optionButton"
                                                   param="${orderFormDto.id }&${button.key}&${button.value}">
                                                    <span class="btn-info btn-sm">${button.value}</span>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
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
<!-- cart-main-area end -->
<!-- footer start -->
<jsp:include page="/WEB-INF/page/fg/pub/footer.jsp" flush="true"/>
<!-- footer end -->

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
<jsp:include page="/pub/pubTips.jsp" flush="true"/>
<!-- Modal start -->
<%-- 选择支付方式 --%>
<jsp:include page="/WEB-INF/page/fg/modal/payModal.jsp" flush="true"/>
<!-- Modal end -->
<script type="text/javascript" src="${ctx }/static/plugins/pagination/jquery.pagination.js"></script>
<script>

    $(document).on('click', 'a[name="agPaidButton"]', function () {
        var title = $(this).attr("title").split("&");
        var orderFormId = title[0];
        showPayModal(orderFormId);
    });
    $(document).on('click', 'a[name="optionButton"]', function () {
        var tipBox = null;
        var param = $(this).attr("param").split("&");
        var orderFormId = param[0];
        var statusCode = param[1];
        var statusName = param[2];
        var str1 = "确定" + statusName;
        var str2 = "正在操作中...";
        var url = path + "/orderForm/updateOrderForm?id=" + orderFormId + "&statusCode=" + statusCode;
        new TipBox({
            type: "confirm",
            str: str1,
            hasBtn: true,
            callBack: function () {
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    beforeSend: function (XHR) {
                        tipBox = new TipBox({type: 'load', str: str2});
                    },
                    success: function (data) {
                        tipBox.close();
                        var message = data.message;
                        var type = data.type;
                        new TipBox({
                            type: type, str: message, hasBtn: true, setTime: 1500, callBack: function () {
                                if (type === 'success') {
                                    new TipBox({type: 'load', str: "页面加载中..."});
                                    location.reload();//刷新一下列表
                                }
                            }
                        });
                    }
                });
            }
        });
    });

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
            location.href = path + '/fg/fgHome/order?pageNo=' + api.getCurrent();
        }
    });
</script>
</body>

</html>

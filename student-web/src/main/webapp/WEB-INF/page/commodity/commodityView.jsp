<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <link rel="stylesheet" href="${ctx}/static/responsive-price-plans/css/style.css">
    <link rel="stylesheet" href="${ctx }/static/css/table.css">

    <link rel="stylesheet" href="${ctx }/static/plugins/pagination/pagination.css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/pagination/jquery.pagination.js"></script>
    <style type="text/css">
        <!--
        a:link {
            color: #000000;
            text-decoration: none
        }

        a:visited {
            color: #000000;
            text-decoration: none
        }

        a:hover {
            color: #000000;
            text-decoration: none
        }

        -->
    </style>
</head>
<body>
<section id="pricePlans">
    <c:forEach var="commodity" items="${page.list}" varStatus="row">
        <c:if test="${row.index % 4 == 0}">
            <ul id="plans">
        </c:if>
        <li class="plan">
            <ul class="planContainer">
                <c:if test="${commodity.status == '0'}">
                    <li class="title"><h2 class="bestPlanTitle">${commodity.commodityName }</h2></li>
                    <li class="price"><p class="bestPlanPrice">${commodity.unitPrice }/${commodity.spec }</p></li>
                </c:if>
                <c:if test="${commodity.status == '1'}">
                    <li class="title"><h2>${commodity.commodityName }</h2></li>
                    <li class="price"><p>${commodity.unitPrice }/<span>${commodity.spec }</span></p></li>
                </c:if>
                <li>
                    <ul class="options">
                        <li>状态:
                            <c:if test="${commodity.status == '1'}"><span> 有货</span> </c:if>
                            <c:if test="${commodity.status == '0'}"><span style="color: red"> 无货</span> </c:if>
                        </li>
                        <li>包装:
                            <span>
                                <c:choose>
                                    <c:when test="${empty commodity.packing}">暂无</c:when>
                                    <c:when test="${fn:length(commodity.packing) > 13}">${fn:substring(commodity.packing, 0, 12)}...</c:when>
                                    <c:otherwise>${commodity.packing}</c:otherwise>
                                </c:choose>
                            </span>
                        </li>
                        <li>介绍:
                            <span>
                                <c:choose>
                                    <c:when test="${empty commodity.commodityInfo}">暂无</c:when>
                                    <c:when test="${fn:length(commodity.commodityInfo) > 13}">${fn:substring(commodity.commodityInfo, 0, 12)}...</c:when>
                                    <c:otherwise>${commodity.commodityInfo}</c:otherwise>
                                </c:choose>
                            </span>
                        </li>
                    </ul>
                </li>
                <li class="button">
                    <c:choose>
                        <c:when test="${commodity.status == '0'}">
                            <a class="bestPlanButton" href="javascript:void(0)">暂时无货</a>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="id" value="${commodity.id}">
                            <a href="javascript:void(0)" name="addDetailed">加入购物车</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </li>
        <c:if test="${row.index % 4 == 3}">
            </ul>
        </c:if>
    </c:forEach>
</section>

<!-- 分页 -->
<jsp:include page="/pub/pubPage.jsp"/>

<script>
    $(function () {
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
                location.href = path + '/comView/index?pageNo=' + api.getCurrent();
            }
        });

        //加入清单
        $("a[name='addDetailed']").on('click', function () {
            var index = $("a[name='addDetailed']").index(this);
            var id = $("input[name='id']").eq(index).val();
            var url = path + '/shoppingCart/addShoppingCart/' + id;
            var tipBox = null;
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                beforeSend: function (XHR) {
                    tipBox = new TipBox({type: 'load', str: "正在加入购物车..."});
                },
                success: function (data) {
                    tipBox.close();
                    new TipBox({type: data.type, str: data.message, hasBtn: true, setTime: 2500});
                },
                error: function () {
                    new TipBox({type: 'error', str: '系统错误,请稍后!', hasBtn: true});
                }
            });
        })
    });
</script>
</body>
</html>
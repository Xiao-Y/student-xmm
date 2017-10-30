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
    <script type="text/javascript" src="${ctx}/static/page/commodity/commodityView.js"></script>

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
</body>
</html>
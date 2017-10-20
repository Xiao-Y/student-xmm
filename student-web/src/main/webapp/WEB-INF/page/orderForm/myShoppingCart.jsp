<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的购物车</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <%--<script type="text/javascript" src="${ctx }/static/page/orderForm/myShoppingCart.js"></script>--%>

</head>
<body>
<div class="admin-main">
    <fieldset class="layui-elem-field">
        <legend>我的购物车</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th><input type="checkbox" lay-skin="primary" id="selected-all"></th>
                    <th>商品名称</th>
                    <th>数量</th>
                    <th>单价</th>
                    <th>包装</th>
                    <th>状态</th>
                    <th>有效性</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="shoppingCartDto" items="${list}">
                    <c:set var="commodity" value="${shoppingCartDto.commodityDto}"/>
                    <tr>
                        <td><input type="checkbox" lay-skin="primary"></td>
                        <td>${commodity.commodityName }</td>
                        <td>${shoppingCartDto.commodityNum }</td>
                        <td>${commodity.unitPrice }/${commodity.spec }</td>
                        <td>${commodity.packing }</td>
                        <td>
                            <c:if test="${commodity.status == '1'}"> 有货 </c:if>
                            <c:if test="${commodity.status == '0'}"> 无货 </c:if>
                        </td>
                        <td>
                            <c:if test="${commodity.valid == '1'}"> 有效 </c:if>
                            <c:if test="${commodity.valid == '0'}"> 无效 </c:if>
                        </td>
                        <td>
                            <a href="${ctx }/comModify/commodityEdit?id=${commodity.id }&pageNo=${page.pageNum }"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:;" data-opt="del"
                               url="${ctx }/comModify/commodityDel?id=${commodity.id }"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>
</body>

</html>
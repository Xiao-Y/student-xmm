<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的购物车</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/orderForm/myShoppingCart.js"></script>

</head>
<body>
<div class="admin-main">
    <fieldset class="layui-elem-field">
        <legend>我的购物车</legend>
        <div class="layui-field-box">
            <table class="site-table" lay-skin="line">
                <thead>
                <tr>
                    <th><input type="checkbox" lay-skin="primary" id="selected-all"></th>
                    <th>商品名称</th>
                    <th>数量</th>
                    <th>单价（元）</th>
                    <th>小计（元）</th>
                    <th>包装</th>
                    <th>是否有货</th>
                    <th>是否有效</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="shoppingCartDto" items="${list}">
                    <c:set var="commodity" value="${shoppingCartDto.commodityDto}"/>
                    <tr>
                        <td>
                            <c:if test="${commodity.status == '1' && commodity.valid == '1'}">
                                <input type="checkbox" name="checkItem" lay-skin="primary">
                            </c:if>
                        </td>
                        <td>${commodity.commodityName }</td>
                        <td align="center">
                            <input type="text" name="commodityNum" value="${shoppingCartDto.commodityNum }"
                                   style="text-align:center" class="0" readonly="readonly">
                        </td>
                        <td class="unitPrice"
                            title="${commodity.unitPrice }">${commodity.unitPrice }/${commodity.spec }</td>
                        <td class="subtotal">${commodity.unitPrice * shoppingCartDto.commodityNum }</td>
                        <td>${commodity.packing }</td>
                        <td style="text-align:center;" class="status" title="${commodity.status}">
                            <c:if test="${commodity.status == '1'}">
                                <i class="fa fa-check-circle fa-lg" aria-hidden="true" style="color:green;"></i>
                            </c:if>
                            <c:if test="${commodity.status == '0'}">
                                <i class="fa fa-times-circle fa-lg" aria-hidden="true" style="color:red;"></i>
                            </c:if>
                        </td>
                        <td class="valid" title="${commodity.valid}">
                            <c:if test="${commodity.valid == '1'}">
                                <i class="fa fa-check-circle fa-lg" aria-hidden="true" style="color:green;"></i>
                            </c:if>
                            <c:if test="${commodity.valid == '0'}">
                                <i class="fa fa-times-circle fa-lg" aria-hidden="true" style="color:red;"></i>
                            </c:if>
                        </td>
                        <td>
                            <a href="javascript:void(0);" title="${shoppingCartDto.commodityId }" name="editComNum"
                               class="layui-btn layui-btn-mini">修改</a>
                            <a href="javascript:void(0);" data-opt="del"
                               url="${ctx }/shoppingCart/deleteShoppingCart/${shoppingCartDto.commodityId }"
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
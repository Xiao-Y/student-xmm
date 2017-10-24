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
    <script type="text/javascript" src="${ctx }/static/js/common/dataTool.js"></script>

</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/shoppingCart/myShoppingCart" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="javascript:;" id="add" class="layui-btn layui-btn-small">
            <i class="layui-icon">&#xe608;</i>
            切换收货地址
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>收货地址</legend>
        <div class="addresId">
            <div class="layui-field-box">
                <input type="hidden" name="addressId" value="TTTT">
                <p>&nbsp;&nbsp;&nbsp;收货人：TTTT</p>
                <p>手机号码：15555555555</p>
                <p>详细地址：武汉市，武昌区.....</p>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>我的购物车</legend>
        <div class="layui-field-box">
            <table class="site-table" lay-skin="line">
                <thead>
                <tr>
                    <th><input type="checkbox" lay-skin="primary" id="selected-all"></th>
                    <th>商品名称</th>
                    <th width="10%">数量</th>
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
                        <td>
                            <input type="number" name="commodityNum" title="0" class="layui-input"
                                   value="${shoppingCartDto.commodityNum }" readonly=" readonly" autocomplete="off"
                                   onkeydown="checkNumberInput()">
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
                            <input type="hidden" name="commodityId" value="${shoppingCartDto.commodityId }">
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
        <br><br><br>
    </fieldset>
    <div class="admin-table-page">
        <form class="layui-form" style="margin: 5px 50px 0px 0px;" action="">
            <div class="layui-form-item" style="text-align: right">
                <div class="layui-inline">
                    <label class="layui-form-label"><strong>总价(￥)：</strong></label>
                    <div class="layui-input-inline">
                        <input type="text" id="total" name="total" value="0.00" readonly autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-warm" type="button" id="submitOrder">提交订单</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>

</html>
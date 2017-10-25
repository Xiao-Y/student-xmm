<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>收货地址</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
</head>
<body>
<div class="admin-main">
    <fieldset class="layui-elem-field">
        <legend>收货地址</legend>
        <c:if test="${empty addressDtos}">
            <br>
            &nbsp;&nbsp;&nbsp;还没有收货地址，请在“个人中心”-“收货地址”中添加...
        </c:if>
        <c:forEach var="address" items="${addressDtos}" varStatus="status">
            <div class="addressDiv">
                <input type="radio" name="address" value="${address.id}">
                <div class="layui-field-box addressDetailed">
                    <input type="hidden" name="addressId" value="${address.id}">
                    <p>&nbsp;&nbsp;&nbsp;收货人：${address.consignee}</p>
                    <p>手机号码：${address.consigneePhone}</p>
                    <p>详细地址：${address.consigneeAddress}</p>
                </div>
            </div>
            <c:if test="${status.index != status.count}">
                <hr class="layui-bg-orange">
            </c:if>
        </c:forEach>
    </fieldset>
</div>
</body>
</html>
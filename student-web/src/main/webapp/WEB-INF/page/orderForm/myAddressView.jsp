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
    <hr class="layui-bg-orange">
    <div class="addressDiv">
        <input type="radio" name="address" value="AAAA">
        <div class="layui-field-box addressDetailed">
            <input type="hidden" name="addressId" value="AAAA">
            <p>&nbsp;&nbsp;&nbsp;收货人：AAAA</p>
            <p>手机号码：15555555555</p>
            <p>详细地址：武汉市，武昌区.....</p>
        </div>
    </div>
    <div class="addressDiv">
        <input type="radio" name="address" value="BBBB">
        <div class="layui-field-box addressDetailed">
            <input type="hidden" name="addressId" value="BBBB">
            <p>&nbsp;&nbsp;&nbsp;收货人：BBBB</p>
            <p>手机号码：15555555555</p>
            <p>详细地址：武汉市，武昌区.....</p>
        </div>
    </div>
    <hr class="layui-bg-orange">
    <div class="addressDiv">
        <input type="radio" name="address" value="CCCC">
        <div class="layui-field-box addressDetailed">
            <input type="hidden" name="addressId" value="CCCC">
            <p>&nbsp;&nbsp;&nbsp;收货人：CCCC</p>
            <p>手机号码：15555555555</p>
            <p>详细地址：武汉市，武昌区.....</p>
        </div>
    </div>
</div>
</body>
</html>
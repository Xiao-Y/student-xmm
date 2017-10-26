<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx}/static/page/orderForm/myAddressList.js"></script>
</head>
<body style="padding: 10px;">

<div style="margin: 30px 240px 30px 100px;">
    <form class="layui-form" data-type="ajax" action="${ctx }/address/myAddressSave">
        <input type="hidden" name="id" value="${address.id }">
        <div class="layui-form-item">
            <label class="layui-form-label">收货人</label>
            <div class="layui-input-block">
                <input type="text" name="consignee" placeholder="这里输入收货人" autocomplete="off"
                       lay-verify="required" required class="layui-input" value="${address.consignee }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">收货人电话</label>
            <div class="layui-input-block">
                <input type="text" name="consigneePhone" placeholder="这里输入收货人电话" autocomplete="off"
                       lay-verify="consigneePhone" class="layui-input" value="${address.consigneePhone }">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">收货人地址</label>
            <div class="layui-input-block">
            <textarea lay-verify="required" required placeholder="这里输入收货人地址" name="consigneeAddress"
                      class="layui-textarea">${address.consigneeAddress }</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="checkbox" name="status" value="1" title="设置为默认地址" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">保存</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<br>
<form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/address/myAddressSave">
    <input type="hidden" name="id" value="${address.id }">
    <div class="layui-form-item">
        <label class="layui-form-label">收货人</label>
        <div class="layui-input-block">
            <input type="text" name="consignee" lay-verify="required" required placeholder="这里输入收货人" autocomplete="off"
                   class="layui-input" value="${address.consignee }">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">收货人电话</label>
        <div class="layui-input-block">
            <input type="text" name="consigneePhone" lay-verify="required" required placeholder="这里输入收货人电话"
                   autocomplete="off" class="layui-input" value="${address.consigneePhone }">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">收货人地址</label>
        <div class="layui-input-block">
            <textarea placeholder="这里输入收货人地址" name="consigneeAddress"
                      class="layui-textarea">${address.consigneeAddress }</textarea>
        </div>
    </div>
</form>
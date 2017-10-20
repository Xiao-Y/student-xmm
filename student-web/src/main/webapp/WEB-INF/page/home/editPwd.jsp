<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/home/editPwd.js"></script>
</head>
<body style="padding: 10px;">
<blockquote class="layui-elem-quote">
    注意：<br><font style="color: red">*</font>为必填项
</blockquote>
<div style="margin: 30px 400px 30px 400px;">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改密码</legend>
    </fieldset>
    <form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/home/updatePwd">
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" lay-verify="required" required placeholder="用户名" autocomplete="off"
                       class="layui-input" value="${userName}" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>新密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" lay-verify="rePassword" required placeholder="这里输入密码"
                       autocomplete="off" class="layui-input" id="password">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="rePassword" lay-verify="rePassword" required placeholder="这里输入密码"
                       autocomplete="off" class="layui-input" id="rePassword">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>

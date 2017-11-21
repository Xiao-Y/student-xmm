<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/user/userEdit.js"></script>
</head>
<body style="padding: 10px;">
<blockquote class="layui-elem-quote">
    注意：<br><font style="color: red">*</font>为必填项<br>
    电子邮箱将用于以后的密码的找回，请保证邮箱的正确性！！！<br>
    用户名注册后不可以更改，请牢记用户名！！！<br>
    如果不修改密码，请务填写新密码和确认密码！！！<br>
</blockquote>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>个人信息修改</legend>
    </fieldset>
    <form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/user/savePersonalInfo" target="_blank">
        <input type="hidden" id="userId" name="userId" value="${user.userId }">
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" lay-verify="userName" required placeholder="这里输入用户名"
                       autocomplete="off" class="layui-input" id="userName" value="${user.userName }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>手机号码</label>
            <div class="layui-input-block">
                <input type="text" name="phoneNumber" lay-verify="phoneNumber" required placeholder="这里输入手机号码"
                       autocomplete="off" class="layui-input" id="phoneNumber" value="${user.phoneNumber }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>电子邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="mail" lay-verify="email" required placeholder="这里输入电子邮箱"
                       autocomplete="off" class="layui-input" value="${user.mail }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" lay-verify="rePassword" required placeholder="这里输入密码"
                       autocomplete="off" class="layui-input" id="password">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="rePassword" lay-verify="rePassword" required placeholder="这里输入密码"
                       autocomplete="off" class="layui-input" id="rePassword">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</body>
</html>
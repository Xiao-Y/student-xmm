<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
    var path = "${ctx}";
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <link rel="stylesheet" href="${ctx }/static/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx }/static/css/login.css"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <style type="text/css">
        i {
            color: green;
        }
    </style>
</head>

<body class="beg-login-bg">
<div class="beg-login-box">
    <header>
        <h1>系统登录</h1>
    </header>
    <div class="beg-login-main">
        <form action="${ctx }/home/homeIndex" class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="beg-login-icon"> <i class="layui-icon">&#xe612;</i></label>
                <input type="text" name="userName" value="${userName}" lay-verify="required" autocomplete="off"
                       placeholder="这里输入登录名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="beg-login-icon"> <i class="layui-icon">&#xe642;</i>
                </label>
                <input type="password" name="password" value="${password}" lay-verify="required" autocomplete="off"
                       placeholder="这里输入密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="beg-pull-left beg-login-remember">
                    <label>记住帐号？</label>
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
                </div>
                <div class="beg-pull-left beg-login-remember">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${ctx}/home/forgetPwd">忘记密码？</a>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="beg-pull-left">
                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                        <i class="fa fa-leaf fa-lg"></i> 登录
                    </button>
                </div>
                <div class="beg-pull-right">
                    <button type="button" class="layui-btn layui-btn-primary" id="registered">
                        <i class="fa fa-registered fa-lg"></i> 注册
                    </button>
                </div>
            </div>
        </form>
    </div>
    <footer>
        <div style="text-align: center">
            <font style="color: red;">
                <strong>${errorMsg}</strong>
            </font>
        </div>
    </footer>
</div>

<p>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;billow ©2017
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0);" id="serviceInfo">服务商信息</a>
</p>
<script type="text/javascript" src="${ctx }/static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/static/page/home/login.js"></script>
</body>
</html>
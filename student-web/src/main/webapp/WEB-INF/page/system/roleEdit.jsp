<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <%-- 插件zTree start --%>
    <link rel="stylesheet" href="${ctx }/static/plugins/zTree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx }/static/plugins/zTree/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/zTree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/zTree/js/jquery.ztree.exedit.js"></script>
    <%-- 插件zTree start --%>

    <script type="text/javascript" src="${ctx }/static/page/system/roleEdit.js"></script>
</head>
<body style="padding: 10px;">
<blockquote class="layui-elem-quote">
    注意：<br>
    <font style="color: red">*</font>为必填项<br>
    菜单权限显示为红色的为无效菜单，如果要显示请在菜单管理里面修改为有效<br>
</blockquote>
<form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/sysPower/roleSave">
    <input type="hidden" id="id" name="id" value="${role.id }">
    <input type="hidden" name="pageNo" value="${role.pageNo }">
    <div class="layui-form-item">
        <label class="layui-form-label"><font style="color: red">*</font>角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="roleName" lay-verify="required" required placeholder="请输入角色名称" autocomplete="off"
                   class="layui-input" value="${role.roleName }">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label"><font style="color: red">*</font>描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" name="remark" lay-verify="required" required
                      class="layui-textarea">${role.remark }</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单权限</label>
        <div class="layui-input-block">
            <ul id="treeDemo" class="ztree"></ul>
            <input type="hidden" id="menuIds" name="menuIds" value="">
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
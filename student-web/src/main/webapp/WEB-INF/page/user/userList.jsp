<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/user/userList.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/user/queryUsers" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-huserIdden="true"></i>
            刷新
        </a>
        <a href="${ctx }/user/userEdit" class="layui-btn layui-btn-small">
            <i class="layui-icon">&#xe608;</i>
            添加信息
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名称</th>
                    <th>联系方式</th>
                    <th>电子邮箱</th>
                    <th>角色</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${page.list}">
                    <tr>
                        <td>${user.userId }</td>
                        <td>${user.userName }</td>
                        <td>${user.phoneNumber }</td>
                        <td>${user.mail }</td>
                        <td>${user.roles }</td>
                        <td>
                            <a href="${ctx }/user/userEdit?userId=${user.userId }&pageNo=${page.pageNum }"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:;" data-opt="del" url="${ctx }/user/userDel?userId=${user.userId }"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
    <!-- 分页 -->
    <jsp:include page="/pub/pubPage.jsp"/>
</div>
</body>

</html>
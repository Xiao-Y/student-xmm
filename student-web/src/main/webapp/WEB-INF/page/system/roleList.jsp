<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/system/roleList.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/sysPower/queryRoles" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="${ctx }/sysPower/roleEdit" class="layui-btn layui-btn-small">
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
                    <th>角色名称</th>
                    <th>说明</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="role" items="${page.list}">
                    <tr>
                        <td>${role.id }</td>
                        <td>${role.roleName }</td>
                        <td>${role.remark }</td>
                        <td>
                            <fmt:formatDate value="${role.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${role.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a href="${ctx }/sysPower/roleEdit?id=${role.id }&pageNo=${page.pageNum }"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:;" data-opt="del" url="${ctx }/sysPower/roleDel?id=${role.id }"
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
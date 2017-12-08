<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>自动任务管理</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/autoTask/autoTaskManage.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubTableNew.js"></script>
</head>
<body>
<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <a href="${ctx }/sysAutoTask/findAutoTask?pageNo=${page.pageNum }" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="${ctx }/sysAutoTask/editAutoTask" class="layui-btn layui-btn-small" id="add">
            <i class="layui-icon">&#xe608;</i>
            添加
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#xe615;</i>
            搜索
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>自动任务列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>任务分组</th>
                    <th>任务名称</th>
                    <th>cron表达式</th>
                    <th style="width: 28px">多线程</th>
                    <th>执行方法</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th style="width: 28px">任务状态</th>
                    <th style="width: 28px">是否运行</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="task" items="${page.list}">
                    <tr>
                        <td>${task.jobGroup }</td>
                        <td>
                            <a href="${ctx }/sysAutoTask/editAutoTask?pageNo=${page.pageNum}&jobId=${task.jobId}" title="查看详细">${task.jobName }</a>
                        </td>
                        <td>${task.cronExpression }</td>
                        <td>${task.isConcurrentName}</td>
                        <td>${task.methodName }</td>
                        <td>
                            <fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${task.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>${task.statusName }</td>
                        <td>
                            <input type="checkbox" value="${task.jobId }" style="width: 20px"
                                   <c:if test="${task.jobStatus == 1 }">checked</c:if> name="jobStatus"/>
                        </td>
                        <td>
                            <a url="${ctx }/sysAutoTask/immediateExecutionTask?jobId=${task.jobId}"
                               href="javascript:void (0);" name="executionTask" class="layui-btn layui-btn-mini"
                               jobGroup="${task.jobGroup }" jobName="${task.jobName }">立即执行</a>
                            <a href="javascript:;" data-opt="del" url="${ctx }/sysAutoTask/deleteAutoTask/${task.jobId}"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
    <jsp:include page="/pub/pubPage.jsp"/>
</div>
</body>
</html>
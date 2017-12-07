<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加/修改自动任务</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/autoTask/autoTaskEdit.js"></script>
</head>
<body style="padding: 10px;">
<form class="layui-form" data-type="ajax" action="${ctx }/sysAutoTask/saveAutoTask">
    <input type="hidden" name="jobId" value="${task.jobId }">
    <div class="layui-form-item">
        <label class="layui-form-label">任务分组</label>
        <div class="layui-input-block">
            <input type="text" name="jobGroup" lay-verify="required" required placeholder="请输入标题" autocomplete="off"
                   class="layui-input"
                   value="${task.jobGroup }">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">任务名称</label>
        <div class="layui-input-block">
            <input type="tel" name="jobName" lay-verify="required" autocomplete="off" class="layui-input"
                   value="${task.jobName }">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">cron表达式</label>
        <div class="layui-input-block">
            <input type="tel" id="cronExpression" name="cronExpression" lay-verify="cronExpression"
                   autocomplete="off" class="layui-input" value="${task.cronExpression }">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">有无状态</label>
        <div class="layui-input-inline">
            <input type="radio" name="isConcurrent" value="1" title="有">
            <input type="radio" name="isConcurrent" value="0" title="无">
            <input type="hidden" id="isConcurrent_radio" def="0" value="${task.isConcurrent}">
        </div>
        <label class="layui-form-label">是否开启任务</label>
        <div class="layui-input-inline">
            <input type="radio" name="jobStatus" value="0" title="否">
            <input type="radio" name="jobStatus" value="1" title="是">
            <input type="hidden" id="jobStatus_radio" def="1" value="${task.jobStatus}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">BeanClass</label>
        <div class="layui-input-block">
            <input type="tel" name="beanClass" id="beanClass" lay-verify="bean" value="${task.beanClass }"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">SpringId</label>
        <div class="layui-input-block">
            <input type="tel" name="springId" id="springId" lay-verify="bean" value="${task.springId }"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">执行方法</label>
        <div class="layui-input-block">
            <input type="tel" id="methodName" name="methodName" lay-verify="methodName" value="${task.methodName }"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" name="description" class="layui-textarea">${task.description }</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <font color="#FF0000" size="23"></font>
</form>
</body>
</html>

<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>地址列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx}/static/page/orderForm/myAddressList.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubTableNew.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/address/myAddressList" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="javascript:void(0);" id="add" class="layui-btn layui-btn-small">
            <i class="layui-icon">&#xe608;</i>
            添加地址
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>收货人</th>
                    <th>收货人电话</th>
                    <th>收货人地址</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="address" items="${addressDtos}">
                    <tr>
                        <td>${address.id }</td>
                        <td>${address.consignee }</td>
                        <td>${address.consigneePhone }</td>
                        <td>${address.consigneeAddress }</td>
                        <td>
                            <a href="${ctx }/address/myAddressEdit?id=${address.id }" class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:;" data-opt="del" url="${ctx }/address/myAddressDel?id=${address.id }"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                            <c:if test="${address.status == '1'}">
                                <i class="fa fa-flag-checkered" aria-hidden="true" title="默认收货地址"></i>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>
</body>

</html>
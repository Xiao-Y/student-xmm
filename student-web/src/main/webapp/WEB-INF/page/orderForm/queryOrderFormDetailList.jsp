<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详细信息</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/orderForm/queryOrderFormList.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="javascript:;" onclick="window.history.go(-1);" class="layui-btn layui-btn-small">
            <i class="fa fa-reply" aria-hidden="true"></i>
            返回
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>订单详细信息</legend>
        <div class="layui-field-box">
            <table class="site-table" lay-skin="line">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>数量</th>
                    <th>单价（元）</th>
                    <th>小计（元）</th>
                    <th>包装</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderFormDetail" items="${list}">
                    <tr>
                        <td>${orderFormDetail.commodityName }</td>
                        <td>${orderFormDetail.commodityNum }</td>
                        <td>${orderFormDetail.unitPrice }/${orderFormDetail.spec }</td>
                        <td>${orderFormDetail.unitPrice * orderFormDetail.commodityNum }</td>
                        <td>${orderFormDetail.packing }</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <br>
    </fieldset>
</div>
</body>

</html>
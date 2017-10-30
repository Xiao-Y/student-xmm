<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详细信息</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubPopForm.js"></script>
    <script type="text/javascript" src="${ctx }/static/page/orderForm/queryOrderFormList.js"></script>
    <%-- 打印引入 start --%>
    <script type="text/javascript" src="${ctx}/static/plugins/dayin/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/plugins/dayin/jquery.jqprint-0.3.js"></script>
    <%-- 打印引入 end --%>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <c:if test="${handleType != '1'}">
            <%-- 返回订单查询页面 --%>
            <a href="${ctx}/orderForm/queryOrderFormList?pageNo=${pageNo }" class="layui-btn layui-btn-small">
                <i class="fa fa-reply" aria-hidden="true"></i>
                返回
            </a>
        </c:if>
        <c:if test="${handleType == '1'}">
            <%-- 返回订单处理页面 --%>
            <a href="${ctx}/orderForm/queryOrderFormHandleList?pageNo=${pageNo }" class="layui-btn layui-btn-small">
                <i class="fa fa-reply" aria-hidden="true"></i>
                返回
            </a>
        </c:if>
        <c:if test="${handleType == '1'}">
            <a href="javascript:;" onclick="print();" class="layui-btn layui-btn-small">
                <i class="fa fa-print" aria-hidden="true"></i>
                打印订单
            </a>
        </c:if>
    </blockquote>
    <div id="printArea">
        <fieldset class="layui-elem-field">
            <legend>订单信息</legend>
            <div class="addresId">
                <div class="layui-field-box">
                    <p>&nbsp;&nbsp;&nbsp;订单号：${orderForm.id}</p>
                    <p>&nbsp;&nbsp;&nbsp;收货人：${orderForm.consignee}</p>
                    <p>手机号码：${orderForm.consigneePhone}</p>
                    <p>详细地址：${orderForm.consigneeAddress}</p>
                    <p>订单金额：<fmt:formatNumber type="currency" value="${orderForm.orderformAmount }"/></p>
                    <p>下单时间：<fmt:formatDate value="${orderForm.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                </div>
            </div>
        </fieldset>
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
                            <td>
                                <fmt:formatNumber type="currency"
                                                  value="${orderFormDetail.unitPrice * orderFormDetail.commodityNum }"/>
                            </td>
                            <td>${orderFormDetail.packing }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
        </fieldset>
    </div>
</div>
</body>
<script>
    function print() {
        $("#printArea").jqprint();
    }
</script>
</html>
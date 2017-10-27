<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubPopForm.js"></script>

    <script type="text/javascript" src="${ctx}/static/page/orderForm/queryOrderFormList.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubTableNew.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/common/dataTool.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/orderForm/queryOrderFormList?pageNo=${page.pageNum }" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="javascript:;" id="search" name="search" class="layui-btn layui-btn-small">
            <i class="fa fa-search" aria-hidden="true"></i>
            查询
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>查询条件</legend>
        <div class="layui-field-box">
            <form class="layui-form layui-form-pane1" id="searchForm" data-type="ajax"
                  action="${ctx}/orderForm/queryOrderFormList">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">订单号</label>
                        <div class="layui-input-block">
                            <input type="text" id="id" name="id" placeholder="请输入订单号" autocomplete="off"
                                   class="layui-input" value="${id}">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">订单状态</label>
                        <div class="layui-input-block">
                            <input type="radio" name="status" value="" title="所有状态" checked>
                            <input type="radio" name="status" value="1" title="客户提交">
                            <input type="radio" name="status" value="2" title="商家确认">
                            <input type="radio" name="status" value="3" title="客户取消">
                            <input type="radio" name="status" value="4" title="商家取消">
                            <input type="radio" name="status" value="5" title="交易完成">
                            <input type="hidden" id="status_radio" name="status_radio" value="${status}">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>订单信息</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>订单号</th>
                    <th>收货人</th>
                    <th>收货人电话</th>
                    <th>订单金额</th>
                    <th>订单状态</th>
                    <th>下单时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderFormDto" items="${page.list}">
                    <tr>
                        <td width="30">
                            <a href="${ctx}/orderForm/queryOrderFormDetailList?orderFormId=${orderFormDto.id }"
                               title="查看订单详细信息">${orderFormDto.id }</a>
                        </td>
                        <td>${orderFormDto.consignee }</td>
                        <td>${orderFormDto.consigneePhone }</td>
                        <td>
                            <fmt:formatNumber type="number" value="${orderFormDto.orderformAmount }"
                                              pattern="#,#00.0#"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${orderFormDto.status == '1'}">
                                    客户提交
                                </c:when>
                                <c:when test="${orderFormDto.status == '2'}">
                                    商家确认
                                </c:when>
                                <c:when test="${orderFormDto.status == '3'}">
                                    客户取消
                                </c:when>
                                <c:when test="${orderFormDto.status == '4'}">
                                    商家取消
                                </c:when>
                                <c:when test="${orderFormDto.status == '5'}">
                                    交易完成
                                </c:when>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${orderFormDto.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <c:if test="${orderFormDto.status == '1'}">
                                <a href="javascript:;" class="layui-btn layui-btn-mini" id="cancelOrderForm"
                                   url="${ctx }/orderForm/updateOrderForm?id=${orderFormDto.id }&status=3">取消订单</a>
                            </c:if>
                            <c:if test="${orderFormDto.status == '3' || orderFormDto.status == '4' || orderFormDto.status == '5'}">
                                <a href="javascript:;" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini"
                                   url="${ctx }/orderForm/updateOrderForm?id=${orderFormDto.id }&delFlag=1">删除订单</a>
                            </c:if>
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
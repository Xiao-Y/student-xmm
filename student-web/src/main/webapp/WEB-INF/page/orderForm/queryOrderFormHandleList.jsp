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

    <script type="text/javascript" src="${ctx}/static/page/orderForm/queryOrderFormHandleList.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubTableNew.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/common/dataTool.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/orderForm/queryOrderFormHandleList?pageNo=${page.pageNum }" class="layui-btn layui-btn-small">
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
                  action="${ctx}/orderForm/queryOrderFormHandleList">
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
                    <th width="120">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderFormDto" items="${page.list}">
                    <tr>
                        <td width="30">
                            <a href="${ctx}/orderForm/queryOrderFormDetailList?pageNo=${page.pageNum }&orderFormId=${orderFormDto.id }&handleType=1"
                               title="查看订单详细信息">${orderFormDto.id }</a>
                        </td>
                        <td>${orderFormDto.consignee }</td>
                        <td>${orderFormDto.consigneePhone }</td>
                        <td>
                            <fmt:formatNumber type="currency" value="${orderFormDto.orderformAmount }"/>
                        </td>
                        <td>
                                ${orderFormDto.statusName }
                                <%--<c:choose>
                                    <c:when test="${orderFormDto.status == '1'}">
                                        <span class="layui-btn layui-btn-normal layui-btn-radius layui-btn-small">客户提交</span>
                                    </c:when>
                                    <c:when test="${orderFormDto.status == '2'}">
                                        <span class="layui-btn layui-btn-warm layui-btn-radius layui-btn-small">商家确认</span>
                                    </c:when>
                                    <c:when test="${orderFormDto.status == '3'}">
                                        <span class="layui-btn layui-btn-danger layui-btn-radius layui-btn-small">客户取消</span>
                                    </c:when>
                                    <c:when test="${orderFormDto.status == '4'}">
                                        <span class="layui-btn layui-btn-danger layui-btn-radius layui-btn-small">商家取消</span>
                                    </c:when>
                                    <c:when test="${orderFormDto.status == '5'}">
                                        <span class="layui-btn layui-btn-primary layui-btn-radius layui-btn-small">交易完成</span>
                                    </c:when>
                                </c:choose>--%>
                        </td>
                        <td><fmt:formatDate value="${orderFormDto.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                                <%-- 1-客户提交，2-商家确认，3-客户取消，4-商家取消，5-交易完成 --%>
                                <%--<c:if test="${orderFormDto.status == '1'}">
                                    <a href="javascript:;" class="layui-btn layui-btn-small" name="cancelOrderForm"
                                       url="${ctx }/orderForm/updateOrderForm?id=${orderFormDto.id }&status=2">确认订单</a>
                                    <a href="javascript:;" class="layui-btn layui-btn-small" name="cancelOrderForm"
                                       url="${ctx }/orderForm/updateOrderForm?id=${orderFormDto.id }&status=4">取消订单</a>
                                </c:if>
                                <c:if test="${orderFormDto.status == '2'}">
                                    <a href="javascript:;" class="layui-btn layui-btn-small" name="cancelOrderForm"
                                       url="${ctx }/orderForm/updateOrderForm?id=${orderFormDto.id }&status=5">交易完成</a>
                                </c:if>--%>
                            <c:forEach var="button" items="${orderFormDto.optionButton}">
                                <c:choose>
                                    <%-- 商家已取消订单 --%>
                                    <c:when test="${button.key == 'BUSINESS_CANCELLATION'}">
                                        <a href="javascript:;" name="optionButton"
                                           class="layui-btn layui-btn-small layui-btn-danger"
                                           title="${orderFormDto.id }&${button.key}&${button.value}">${button.value}</a>
                                    </c:when>
                                    <%-- 申请退款-不同意 --%>
                                    <c:when test="${button.key == 'APPLICATION_REFUND_DISAGREE'}">
                                        <a href="javascript:;" name="optionButton"
                                           class="layui-btn layui-btn-small layui-btn-warm"
                                           title="${orderFormDto.id }&${button.key}&${button.value}">${button.value}</a>
                                    </c:when>
                                    <%-- 申请退款-同意 --%>
                                    <c:when test="${button.key == 'APPLICATION_REFUND_AGREE'}">
                                        <a href="javascript:;" name="optionButton"
                                           class="layui-btn layui-btn-small"
                                           title="${orderFormDto.id }&${button.key}&${button.value}">${button.value}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:;" name="optionButton"
                                           class="layui-btn layui-btn-small layui-btn-normal"
                                           title="${orderFormDto.id }&${button.key}&${button.value}">${button.value}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
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
<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubTableJs.jsp"/>
    <script type="text/javascript" src="${ctx}/static/page/commodity/commodityList.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubTableNew.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx}/commodity/index?pageNo=${page.pageNum }" class="layui-btn layui-btn-small">
            <i class="fa fa-refresh" aria-hidden="true"></i>
            刷新
        </a>
        <a href="${ctx }/commodity/commodityEdit" class="layui-btn layui-btn-small">
            <i class="layui-icon">&#xe608;</i>
            添加商品
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>规格</th>
                    <th>包装</th>
                    <th>状态</th>
                    <th>有效性</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="commodity" items="${page.list}">
                    <tr>
                        <td>${commodity.commodityName }</td>
                        <td>${commodity.unitPrice }</td>
                        <td>${commodity.spec }</td>
                        <td>${commodity.packing }</td>
                        <td>
                            <c:if test="${commodity.status == '1'}"> 有货 </c:if>
                            <c:if test="${commodity.status == '0'}"> 无货 </c:if>
                        </td>
                        <td>
                            <c:if test="${commodity.valid == '1'}"> 有效 </c:if>
                            <c:if test="${commodity.valid == '0'}"> 无效 </c:if>
                        </td>
                        <td style="width: 180px">
                            <a href="${ctx }/commodity/commodityEdit?id=${commodity.id }&pageNo=${page.pageNum }"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:;" data-opt="del"
                               url="${ctx }/commodity/commodityDel?id=${commodity.id }"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                            <a href="${ctx }/commodity/getCommodityImg?id=${commodity.id }&img=${commodity.img }"
                               target="_blank" class="layui-btn layui-btn-mini">查看图片</a>
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
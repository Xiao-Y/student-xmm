<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品修改/添加</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/commodity/commodityEdit.js"></script>
</head>
<body style="padding: 10px;">
<blockquote class="layui-elem-quote">
    注意：<br><font style="color: red">*</font>为必填项
</blockquote>
<div style="margin: 30px 240px 30px 100px;">
    <form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/commodity/commoditySave">
        <input type="hidden" name="id" value="${commodity.id }">
        <input type="hidden" name="pageNo" value="${commodity.pageNo }">
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>商品名称</label>
            <div class="layui-input-block">
                <input type="text" name="commodityName" lay-verify="required" required placeholder="这里输入商品名称"
                       autocomplete="off" class="layui-input" value="${commodity.commodityName }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><font style="color: red">*</font>单价</label>
            <div class="layui-form-mid"><strong>￥</strong></div>
            <div class="layui-input-inline">
                <input type="text" name="unitPrice" lay-verify="required" required placeholder="这里输入单价"
                       autocomplete="off" class="layui-input" value="${commodity.unitPrice }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否有货</label>
            <div class="layui-input-inline">
                <input type="hidden" id="status_radio" def="1" value="${commodity.status}">
                <input type="radio" name="status" value="1" title="是">
                <input type="radio" name="status" value="0" title="否">
            </div>
            <label class="layui-form-label">是否有效</label>
            <div class="layui-input-inline">
                <input type="hidden" id="valid_radio" def="1" value="${commodity.valid }">
                <input type="radio" name="valid" value="1" title="是">
                <input type="radio" name="valid" value="0" title="否">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label"><font style="color: red">*</font>规格</label>
            <div class="layui-input-block">
                <input type="text" name="spec" value="${commodity.spec }" lay-verify="required" required
                       placeholder="这里输入规格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">产品分类</label>
            <div class="layui-input-block">
                <%-- 产品分类，1-蔬菜，2-肉/鱼类，3-配料类，4-其它 --%>
                <select name="grade" id="grade" lay-filter="grade">
                    <option value="1">1-蔬菜类</option>
                    <option value="2">2-肉/鱼类</option>
                    <option value="3">3-配料类</option>
                    <option value="4" selected="">4-其它</option>
                </select>
                <input type="hidden" id="grade_select" def="4" value="${commodity.grade }">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">产地</label>
            <div class="layui-input-block">
                <input type="text" name="localityGrowth" value="${commodity.localityGrowth }" autocomplete="off"
                       placeholder="这里输入产地" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">包装</label>
            <div class="layui-input-block">
                <input type="text" name="packing" value="${commodity.packing }" autocomplete="off" placeholder="这里输入包装"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">商品信息</label>
            <div class="layui-input-block">
                <textarea placeholder="这里输入商品信息" name="commodityInfo" maxlength="250"
                          class="layui-textarea">${commodity.commodityInfo }</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
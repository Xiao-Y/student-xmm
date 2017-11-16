<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>我的收货地址</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
    ============================================ -->
    <jsp:include page="/static/fg_js_css/pubCss.jsp" flush="true"/>

</head>

<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<!-- header start -->
<jsp:include page="/WEB-INF/page/fg/pub/header.jsp" flush="true"/>
<!-- header end -->

<div class="space-custom"></div>
<!-- breadcrumb start -->
<div class="breadcrumb-area">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-home"></i></a></li>
            <li><a href="#">Shop</a></li>
            <li class="active">我的收货地址</li>
        </ol>
    </div>
</div>
<!-- breadcrumb end -->
<!-- cart-main-area start -->
<div class="cart-main-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <form action="#">
                    <h4 style="color: #1AA094">最多添加4个收货地址！！</h4>
                    <div class="table-content table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th class="product-consignee">收货人</th>
                                <th class="product-phone">收货人电话</th>
                                <th class="product-status">收货人地址</th>
                                <th class="product-remove">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="address" items="${addressDtos}">
                                <tr>
                                    <td class="product-consignee">${address.consignee }</td>
                                    <td class="product-phone">${address.consigneePhone }</td>
                                    <td class="product-status">${address.consigneeAddress }</td>
                                    <td class="product-remove">
                                        <a href="/fg/fgHome/editAddress?type=edit&id=${address.id }"
                                           class="btn-primary btn-sm">修改</a>
                                        <a class="btn-danger btn-sm" href="#" name="del"
                                           url="/fg/fgHome/deleteAddress?id=${address.id }">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <%--<tr>
                                <td class="product-consignee">billow</td>
                                <td class="product-phone">15507529497</td>
                                <td class="product-status">武汉</td>
                                <td class="product-remove"><a href="/fg/fgHome/addressEdit">修改</a><a href="#">删除</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="product-consignee">billow</td>
                                <td class="product-phone">15507529497</td>
                                <td class="product-status">武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉</td>
                                <td class="product-remove"><a href="/fg/fgHome/addressEdit">修改</a><a href="#">删除</a>
                                </td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- cart-main-area end -->
<!-- footer start -->
<jsp:include page="/WEB-INF/page/fg/pub/footer.jsp" flush="true"/>
<!-- footer end -->

<jsp:include page="/static/fg_js_css/pubJs.jsp" flush="true"/>
<jsp:include page="/pub/pubTips.jsp" flush="true"/>

<script>
    $("[name='del']").click(function () {
        var $this = $(this);
        var url = $this.attr("url");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            beforeSend: function (XHR) {
                tipBox = new TipBox({type: 'load', str: "加载中..."});
            },
            success: function (obj) {
                if (tipBox != null) {
                    tipBox.close();
                }
                var message = obj.message;
                var type = obj.type;
                if (type == 'success') {
                    new TipBox({type: type, str: message, hasBtn: true, setTime: 1500});
                    $this.parents("tr").remove();
                } else {
                    new TipBox({type: type, str: message, hasBtn: true});
                }
            },
            error: function (obj) {
                if (tipBox != null) {
                    tipBox.close();
                }
                new TipBox({type: 'error', str: '网络错误', hasBtn: true});
            }
        });
    });
</script>
</body>

</html>

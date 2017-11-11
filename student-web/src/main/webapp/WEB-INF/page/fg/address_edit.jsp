<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>收货地址</title>
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
            <li><a href="#">收货地址列表</a></li>
            <li class="active">添加/修改收货地址</li>
        </ol>
    </div>
</div>
<!-- breadcrumb end -->
<!-- cart-main-area start -->
<div class="cart-main-area">
    <div class="container">
        <div class="row">
            <div id="content" class="col-sm-12">
                <form action="" method="post" class="form-horizontal account-register clearfix">
                    <input type="hidden" name="id" value="${address.id }">
                    <fieldset id="account">
                        <legend>添加/修改收货地址</legend>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="consignee"><strong
                                    style="color: red;">*</strong>收货人</label>
                            <div class="col-sm-10">
                                <input type="text" name="consignee" required placeholder="这里输入收货人"
                                       class="form-control" id="consignee" value="${address.consignee }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="consigneePhone"><strong
                                    style="color: red;">*</strong>收货人电话</label>
                            <div class="col-sm-10">
                                <input type="text" name="consigneePhone" required
                                       placeholder="这里输入收货人电话" class="form-control" id="consigneePhone"
                                       value="${address.consigneePhone }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="consigneeAddress"><strong
                                    style="color: red;">*</strong>收货人地址</label>
                            <div class="col-sm-10">
                                <textarea type="text" name="consigneeAddress" required placeholder="这里输入收货人地址"
                                          id="consigneeAddress"
                                          class="form-control">${address.consigneeAddress }</textarea>
                            </div>
                        </div>
                    </fieldset>
                    <div class="buttons">
                        <div class="pull-right">
                            <div class="buttons-cart">
                                <input type="submit" value="提交信息"/>
                            </div>
                        </div>
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
</body>

</html>

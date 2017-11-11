<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>个人信息</title>
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
            <li class="active">我的订单</li>
        </ol>
    </div>
</div>
<!-- breadcrumb end -->
<!-- cart-main-area start -->
<div class="cart-main-area">
    <div class="container">
        <div class="row">
            <div id="content" class="col-sm-12">
                <h2 class="title">我的个人信息</h2>
                <p>注意：<br/>
                    <strong style="color: red;">*</strong>为必填项<br/>
                    电子邮箱将用于以后的密码的找回，请保证邮箱的正确性！！！<br/>
                    用户名注册后不可以更改，请牢记用户名！！！<br/>
                </p>
                <form action="" method="post" class="form-horizontal account-register clearfix">
                    <input type="hidden" id="userId" name="userId" value="${user.userId }">
                    <fieldset id="account">
                        <legend>信息修改</legend>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="userName"><strong
                                    style="color: red;">*</strong>用户名</label>
                            <div class="col-sm-10">
                                <input type="text" name="userName" required placeholder="这里输入用户名"
                                       class="form-control" id="userName" value="${user.userName }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="phoneNumber"><strong
                                    style="color: red;">*</strong>手机号码</label>
                            <div class="col-sm-10">
                                <input type="text" name="phoneNumber" required
                                       placeholder="这里输入手机号码" class="form-control" id="phoneNumber"
                                       value="${user.phoneNumber }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="mail"><strong style="color: red;">*</strong>电子邮箱</label>
                            <div class="col-sm-10">
                                <input type="text" name="mail" required placeholder="这里输入电子邮箱"
                                       id="mail" class="form-control" value="${user.mail }">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>密码修改</legend>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="password"><strong style="color: red;">*</strong>新密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="password" placeholder="这里输入密码"
                                       required class="form-control" id="password" value="${user.password }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="rePassword"><strong
                                    style="color: red;">*</strong>确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="rePassword" placeholder="这里输入密码"
                                       required class="form-control" id="rePassword" value="${user.password }">
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

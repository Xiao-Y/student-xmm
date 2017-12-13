<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<%@ include file="/pub/taglib.jsp" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>个人中心</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
    ============================================ -->
    <jsp:include page="/static/fg_js_css/pubCss.jsp" flush="true"/>
    <link rel="stylesheet" href="${ctx }/static/plugins/bootstrapValidator/bootstrapValidator.min.css">

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
            <li><a href="#">个人中心</a></li>
            <li class="active">修改个人信息</li>
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
                    更改用户名信息后需要重新登陆！！！<br/>
                </p>
                <form id="userInfoForm" action="" method="post" class="form-horizontal account-register clearfix">
                    <fieldset id="account">
                        <legend>信息修改</legend>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" required for="userName"><strong
                                    style="color: red;">*</strong>用户名</label>
                            <div class="col-sm-10">
                                <input type="text" name="userName" required readonly placeholder="这里输入用户名"
                                       class="form-control" id="userName" value="${user.userName }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="phoneNumber">
                                <strong style="color: red;">*</strong>手机号码</label>
                            <div class="col-sm-10">
                                <input type="text" name="phoneNumber" required readonly
                                       placeholder="这里输入手机号码" class="form-control" id="phoneNumber"
                                       value="${user.phoneNumber }">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="mail"><strong style="color: red;">*</strong>电子邮箱</label>
                            <div class="col-sm-10">
                                <input type="text" name="mail" required readonly placeholder="这里输入电子邮箱"
                                       id="mail" class="form-control" value="${user.mail }">
                            </div>
                        </div>
                    </fieldset>
                    <div class="buttons">
                        <div class="pull-right">
                            <div class="buttons-cart">
                                <input type="button" id="editBtn" value="修改信息"/>
                                <input type="button" id="submitBtn" value="提交信息"/>
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
<jsp:include page="/pub/pubTips.jsp" flush="true"/>

<script src="${ctx }/static/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>
<script src="${ctx }/static/plugins/bootstrapValidator/zh_CN.js"></script>

<script>

    //初始化隐藏 “提交信息”
    $("#submitBtn").hide();

    //表单验证器
    $('#userInfoForm').bootstrapValidator({
        message: '表单还没有验证',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            phoneNumber: {
                validators: {
                    regexp: {
                        regexp: /^1[0-9]{10}$/,
                        message: '手机号码格式不正确'
                    }
                }
            },
            mail: {
                validators: {
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            }
        }
    });

    //点“修改信息”后，显示“提交信息”，隐藏“修改信息”
    $("#editBtn").click(function () {
        $("#phoneNumber").attr("readonly", false);
        $("#mail").attr("readonly", false);
        $("#editBtn").hide();
        $("#submitBtn").show();
    });

    //提交表单
    $('#submitBtn').click(function () {

        //校验表单
        $('#userInfoForm').bootstrapValidator('validate');
        var flag = $("#userInfoForm").data('bootstrapValidator').isValid();
        //校验通过
        if (flag) {
            var tipBox = null;
            var url = path + "/fg/fgHome/updateUserInfo";
            var data = $("#userInfoForm").serialize();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: url,
                data: data,
                beforeSend: function (XHR) {
                    tipBox = new TipBox({type: 'load', str: "加载中..."});
                },
                success: function (obj) {
                    if (tipBox != null) {
                        tipBox.close();
                    }
                    var message = obj.message;
                    var type = obj.type;
                    var root = obj.root;
                    if (type == 'success') {
                        $("#phoneNumber").attr("readonly", true);
                        $("#mail").attr("readonly", true);
                        $("#submitBtn").hide();
                        $("#editBtn").show();
                        new TipBox({
                            type: type, str: message, hasBtn: true, setTime: 2000, callBack: function () {
                                $(window.location).attr('href', path + root);
                            }
                        });
                    } else {
                        new TipBox({type: type, str: message, hasBtn: true});
                    }
                },
                error: function (obj) {
                    if (tipBox != null) {
                        tipBox.close();
                    }
                    //$('#userInfoForm').data('bootstrapValidator').resetForm(true);
                    new TipBox({type: 'error', str: '网络错误', hasBtn: true})
                }
            });
        }
    });
</script>
</body>

</html>

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
            <li class="active">修改密码</li>
        </ol>
    </div>
</div>
<!-- breadcrumb end -->
<!-- cart-main-area start -->
<div class="cart-main-area">
    <div class="container">
        <div class="row">
            <div id="content" class="col-sm-12">
                <h2 class="title">修改密码</h2>
                <p>注意：<br/>
                    <strong style="color: red;">*</strong>为必填项<br/>
                    注意保护个人密码！！！<br/>
                    修改密码后需要重新登陆系统！！！<br/>
                </p>
                <form id="updatePassword" action="" method="post" class="form-horizontal account-register clearfix">
                    <fieldset>
                        <legend>密码修改</legend>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="oldPassword"><strong
                                    style="color: red;">*</strong>原密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="oldPassword" placeholder="这里输入密码"
                                       required class="form-control" id="oldPassword">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="password"><strong style="color: red;">*</strong>新密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="password" placeholder="这里输入密码"
                                       required class="form-control" id="password">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="rePassword"><strong
                                    style="color: red;">*</strong>确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="rePassword" placeholder="这里输入密码"
                                       required class="form-control" id="rePassword">
                            </div>
                        </div>
                    </fieldset>
                    <div class="buttons">
                        <div class="pull-right">
                            <div class="buttons-cart">
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
    //表单验证器
    $('#updatePassword').bootstrapValidator({
        message: '表单还没有验证',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            rePassword: {
                validators: {
                    identical: {
                        field: 'password',
                        message: '两次密码不同请重新输入'
                    }
                }
            }
        }
    });

    //提交表单
    $('#submitBtn').click(function () {
        //校验表单
        $('#updatePassword').bootstrapValidator('validate');
        var flag = $("#updatePassword").data('bootstrapValidator').isValid();
        //校验通过
        if (flag) {
            var tipBox = null;
            var url = path + "/fg/fgHome/updatePassword";
            var data = $("#updatePassword").serialize();
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
                        new TipBox({
                            type: type, str: message, hasBtn: true, setTime: 1500, callBack: function () {
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
                    //$('#updatePassword').data('bootstrapValidator').resetForm(true);
                    new TipBox({type: 'error', str: '网络错误', hasBtn: true});
                }
            });
        }
    });
</script>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册验证</title>
    <link rel="stylesheet" href="/static/plugins/jquery-cropper/assets/css/bootstrap.min.css">
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>注册验证</h1>
    </header>

    <div class="modal fade" id="tipModel">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <h4 class="modal-title">注册验证</h4>
                </div>
                <div class="modal-body">
                    <p id="content">注册验证成功，进入登陆页面？</p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="type"/>
                    <a id="tipOk" class="btn btn-success" data-dismiss="modal">确定</a>
                    <a id="tipClose" class="btn btn-success" data-dismiss="modal">关闭</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script src="/static/plugins/jquery-cropper/assets/js/jquery.min.js"></script>
    <script src="/static/plugins/jquery-cropper/assets/js/bootstrap.min.js"></script>
    <script>

        $("#tipModel").modal();

        $(function () {

            $("#tipOk").click(function () {
                $(window.location).attr('href', path + "/home/login");
            });

            $("#tipClose").click(function () {
                window.close();
            });
        });
    </script>
</body>
</html>

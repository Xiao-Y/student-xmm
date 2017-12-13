<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑商品图片</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/jquery-cropper/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/jquery-cropper/css/default.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/jquery-cropper/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/jquery-cropper/dist/cropper.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/jquery-cropper/css/main.css">
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>商品图片剪裁器</h1>
    </header>
    <!-- Content -->
    <div class="container">
        <div class="row">
            <input type="hidden" id="commodityId" name="commodityId" value="${commodityDto.id}">
            <div class="col-md-9">
                <div class="img-container">
                    <img src="${commodityDto.img}" alt="Picture">
                </div>
            </div>
            <div class="col-md-3">
                <div class="docs-preview clearfix">
                    <div class="img-preview preview-lg"></div>
                    <div class="img-preview preview-md"></div>
                    <div class="img-preview preview-sm"></div>
                    <div class="img-preview preview-xs"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9 docs-buttons">
                <div class="btn-group">
                    <button class="btn btn-primary" data-method="rotate" data-option="-45" type="button">
                        <span class="docs-tooltip" data-toggle="tooltip" title="每次向左旋转45度">左旋转</span>
                    </button>
                    <button class="btn btn-primary" data-method="rotate" data-option="45" type="button">
                        <span class="docs-tooltip" data-toggle="tooltip" title="每次向右旋转45度">右旋转</span>
                    </button>
                </div>

                <div class="btn-group">
                    <button class="btn btn-primary" data-method="reset" type="button" title="Reset">
                        <span class="docs-tooltip" data-toggle="tooltip" title="重新裁剪图片">重置</span>
                    </button>
                    <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
                        <input class="sr-only" id="inputImage" name="file" type="file" accept="image/*">
                        <span class="docs-tooltip" data-toggle="tooltip" title="打开新图片">打开</span>
                    </label>
                </div>

                <div class="btn-group btn-group-crop">
                    <button class="btn btn-primary" data-method="getCroppedCanvas" type="button">
                        <span class="docs-tooltip" data-toggle="tooltip" title="裁剪预览">裁剪预览</span>
                    </button>
                    <button class="btn btn-primary" data-method="getCroppedCanvas"
                            data-option="{ &quot;width&quot;: 388, &quot;height&quot;: 500 }" type="button">
                        <span class="docs-tooltip" data-toggle="tooltip" title="商品预览">商品预览</span>
                    </button>
                </div>
                <div class="btn-group btn-group-crop">
                    <button class="btn btn-primary" data-method="getCroppedCanvas" data-type="save"
                            data-option="{ &quot;width&quot;: 388, &quot;height&quot;: 500 }" type="button">
                        <span class="docs-tooltip" data-toggle="tooltip" title="上传图片到服务器">保存</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Alert -->
<div class="docs-alert"><span class="warning message"></span></div>

<!-- 图片裁剪后显示 Show the cropped image in modal -->
<div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true"
     aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal" type="button" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="getCroppedCanvasTitle">商品预览</h4>
            </div>
            <div class="modal-body"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="tipModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p id="content">您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="type"/>
                <a id="tipOk" class="btn btn-success" data-dismiss="modal">确定</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${ctx}/static/plugins/jquery-cropper/assets/js/jquery.min.js"></script>
<script src="${ctx}/static/plugins/jquery-cropper/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/static/plugins/jquery-cropper/dist/cropper.js"></script>
<script src="${ctx}/static/page/commodity/commodityImg.js"></script>
</body>
</html>

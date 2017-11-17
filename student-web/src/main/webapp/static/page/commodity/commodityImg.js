$(function () {
    'use strict';
    var console = window.console || {
            log: function () {
            }
        },
        $alert = $('.docs-alert'),
        $message = $alert.find('.message'),
        showMessage = function (message, type) {
            $message.text(message);
            if (type) {
                $message.addClass(type);
            }
            $alert.fadeIn();
            setTimeout(function () {
                $alert.fadeOut();
            }, 3000);
        };

    // Demo
    // -------------------------------------------------------------------------

    (function () {
        var $image = $('.img-container > img'),
            options = {
                aspectRatio: 97 / 125,
                resizable: false,//是否允许改变剪裁框的大小
                dragCrop: false,//是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
                preview: '.img-preview'
            };

        $image.cropper(options);

        // Methods
        $(document.body).on('click', '[data-method]', function () {
            var data = $(this).data(),
                $target,
                result;

            if (data.method) {
                data = $.extend({}, data); // Clone a new one
                if (typeof data.target !== 'undefined') {
                    $target = $(data.target);
                    if (typeof data.option === 'undefined') {
                        try {
                            data.option = JSON.parse($target.val());
                        } catch (e) {
                            console.log(e.message);
                        }
                    }
                }

                result = $image.cropper(data.method, data.option);

                if (data.method === 'getCroppedCanvas') {
                    //点击保存
                    if (data.type == 'save') {
                        var tipBox = null;
                        var fileImg = result.toDataURL('image/jpg');
                        var formData = new FormData();
                        formData.append("imgBase64", fileImg);
                        $.ajax({
                            url: "/commodity/uploadCommodityImg/" + $("#commodityId").val(),
                            type: 'POST',
                            data: formData,
                            contentType: false,
                            processData: false,
                            timeout: 10000, //超时时间设置，单位毫秒
                            beforeSend: function (XHR) {
                                tipBox = new TipBox({type: 'load', str: "加载中..."});
                            },
                            success: function (obj) {
                                tipBox.close();
                                $("#content").text(obj.message);
                                $("#type").val(obj.type);
                                $('#tipModel').modal();
                            },
                            error: function (returndata) {
                                tipBox.close();
                                $("#content").text('网络错误');
                                $("#type").val('error');
                                $('#tipModel').modal();
                            }
                        });
                    } else {
                        $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);
                    }
                }

                if ($.isPlainObject(result) && $target) {
                    try {
                        $target.val(JSON.stringify(result));
                    } catch (e) {
                        console.log(e.message);
                    }
                }
            }
        }).on('keydown', function (e) {
            switch (e.which) {
                case 37:
                    e.preventDefault();
                    $image.cropper('move', -1, 0);
                    break;
                case 38:
                    e.preventDefault();
                    $image.cropper('move', 0, -1);
                    break;
                case 39:
                    e.preventDefault();
                    $image.cropper('move', 1, 0);
                    break;
                case 40:
                    e.preventDefault();
                    $image.cropper('move', 0, 1);
                    break;
            }
        });

        // Import image
        var $inputImage = $('#inputImage'),
            URL = window.URL || window.webkitURL,
            blobURL;

        if (URL) {
            $inputImage.change(function () {
                var files = this.files,
                    file;

                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {
                        blobURL = URL.createObjectURL(file);
                        $image.one('built.cropper', function () {
                            URL.revokeObjectURL(blobURL); // Revoke when load complete
                        }).cropper('reset', true).cropper('replace', blobURL);
                        $inputImage.val('');
                    } else {
                        showMessage('Please choose an image file.');
                    }
                }
            });
        } else {
            $inputImage.parent().remove();
        }

        // Tooltips
        $('[data-toggle="tooltip"]').tooltip();

        //图片上传成后弹出层按键事件
        $("#tipOk").click(function () {
            var type = $("#type").val();
            if (type == "success") {
                window.close();
            }
            $('#tipModel').modal("hide");
        });
    }());

});

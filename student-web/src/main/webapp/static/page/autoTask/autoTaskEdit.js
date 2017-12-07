layui.use('form', function () {
    var $ = layui.jquery, form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    //自定义验证规则
    form.verify({
        bean: function (value) {
            var beanClass = $("#beanClass").val();
            var springId = $("#springId").val();
            var jobStatus = $("input[name='jobStatus']:checked").val();
            if (beanClass == '' && springId == '') {
                return 'beanClass和springId必填一个';
            }
            var url = path + "/sysAutoTask/checkAutoTask?jobStatus=" + jobStatus;
            var message = checkAutoTask(url);
            if (message != '') {
                return message;
            }
        },
        cronExpression: function (value) {
            var cronExpression = $("#cronExpression").val();
            $.ajaxSettings.async = false;
            var url = path + "/sysAutoTask/checkAutoTask?cronExpression=" + cronExpression;
            $.post(url, function (result) {
                message = result.message;
            });
            if (message != '') {
                return message;
            }
        },
        methodName: function (value) {
            var methodName = $("#methodName").val();
            var jobStatus = $("input[name='jobStatus']:checked").val();
            if (methodName == '') {
                return '执行方法不能为空';
            }
            var url = path + "/sysAutoTask/checkAutoTask?jobStatus=" + jobStatus + "&methodName=" + methodName;
            var message = checkAutoTask(url);
            if (message != '') {
                return message;
            }
        }
    });

    //异步检查数据正确性
    function checkAutoTask(url) {
        $.ajaxSettings.async = false;
        var message = '';
        var beanClass = $("#beanClass").val();
        var springId = $("#springId").val();
        //jobStatus 1-启用，0-禁用
        var jobStatus = $("input[name='jobStatus']:checked").val();
        if (jobStatus == "1") {
            $.post(
                url,
                {beanClass: beanClass, springId: springId},
                function (result) {
                    message = result.message;
                }
            );
        }
        return message;
    }

    //监听提交
    form.on('submit(*)', function (data) {
        return submitFormNewTip(data);
    });

    //用于显示radio的选种
    var arry = ['isConcurrent_radio', 'jobStatus_radio'];
    pubPopForm.checkedDisplay(arry, form);
});
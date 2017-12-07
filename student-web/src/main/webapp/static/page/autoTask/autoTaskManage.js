layui.config({
    base: path + '/plugins/layui/modules/'
});

layui.use(['layer', 'form'], function () {
    var $ = layui.jquery, form = layui.form(), layer = parent.layer === undefined
        ? layui.layer
        : parent.layer;
});


$(function () {

    $("input[name='jobStatus']").on('click', function (field) {
        var checked = $(this).is(':checked');
        var jobId = $(this).val();
        //checked true 表示启用，否则禁用
        var jobStatus = checked === true ? 1 : 0;
        var url = path + "/sysAutoTask/updateJobStatus/" + jobId;
        $.post(
            url,
            {jobStatus: jobStatus},
            function (obj, status) {
                var message = obj.message;
                var type = obj.type;
                if (type == 'success') {
                    new TipBox({
                        type: type, str: message, hasBtn: true, setTime: 1500
                    });
                } else {
                    new TipBox({type: type, str: message, hasBtn: true})
                }
            });
    });

    $("a[name='executionTask']").on('click', function (field) {
        var $this = this;
        var index = $("a[name='executionTask']").index($this);
        var checked = $("input[name='jobStatus']").eq(index).is(':checked');
        if (!checked) {
            new TipBox({type: 'error', str: "不是计划任务", hasBtn: true});
            return;
        }
        var jobGroup = $($this).attr("jobGroup");
        var jobName = $($this).attr("jobName");
        var url = $($this).attr("url");
        $.post(
            url,
            {jobGroup: jobGroup, jobName: jobName},
            function (obj, status) {
                var message = obj.message;
                var type = obj.type;
                if (type == 'success') {
                    new TipBox({
                        type: type, str: message, hasBtn: true, setTime: 1500
                    });
                } else {
                    new TipBox({type: type, str: message, hasBtn: true})
                }
            });
    });

    //分页使用
    $('.M-box').pagination({
        coping: true,
        jump: true,//是否开启跳转到指定页数
        isHide: false,//总页数为0或1时隐藏分页控件
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        pageCount: $("#pages").val(),//总数据
        showData: $("#pageSize").val(),//每页显示
        current: $("#pageNum").val(),//当前第几页
        callback: function (api) {
            location.href = path + '/sysAutoTask/findAutoTask?pageNo=' + api.getCurrent();
        }
    });
});
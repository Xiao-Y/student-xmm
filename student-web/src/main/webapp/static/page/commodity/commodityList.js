layui.config({
    base: path + '/static/plugins/layui/modules/'
});

layui.use(['layer'], function () {
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer;
    $('#search').on('click', function () {
        parent.layer.alert('你点击了搜索按钮');
    });
});

$(function () {
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
            location.href = path + '/commodity/index?pageNo=' + api.getCurrent();
        }
    });
});

layui.use(['layer', 'form'], function () {
    var layer = layui.layer, $ = layui.jquery, form = layui.form();
    form.on('submit(login)', function (data) {
        return true;
    });

    $("#registered").on("click",function(){
        location.href = path + "/home/register";
    });
});

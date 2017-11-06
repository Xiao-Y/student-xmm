layui.use('form', function () {
    var $ = layui.jquery, form = layui.form(), layer = parent.layer === undefined ? layui.layer : parent.layer;

    //监听提交
    form.on('submit(*)', function (data) {
        return submitFormNewTip(data);
    });
});

//zTree参数设置
var setting = {
    view: {
        selectedMulti: false//是否单选
    },
    check: {
        enable: true//是否显示check
    },
    data: {
        simpleData: {//数据绑定
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: 0
        },
        key: {
            url: ""
        }
    },
    edit: {
        enable: false//是否启用编辑
    },
    callback: {
        onCheck: zTreeOnCheck//check改变时触发
    }
};

//点击复选框时触发
function zTreeOnCheck(e, treeId, treeNode) {
    var menuIds = new Array();
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    //所有选中的
    var nodes = treeObj.getCheckedNodes(true);
    for (var i = 0; i < nodes.length; i++) {
        menuIds.push(nodes[i].id);
        //alert(nodes[i].id + "---" + nodes[i].isParent); //获取选中节点的值
    }
    $("#menuIds").val(menuIds);
    //alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
}

$(function () {
    //加载树形权限菜单
    var tipBox = null;
    var roleId = $("#id").val();
    var url = path + "/sysPower/getRolePermission?id=" + roleId;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        beforeSend: function (XHR) {
            tipBox = new TipBox({type: 'load', str: "加载中..."});
        },
        success: function (zNodes) {
            if (tipBox != null) {
                tipBox.close();
            }
            //初始化权限树
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            //初始化选中的权限
            zTreeOnCheck();
        },
        error: function (obj) {
            if (tipBox != null) {
                tipBox.close();
            }
            new TipBox({type: 'error', str: '网络错误', hasBtn: true})
        }
    });
});


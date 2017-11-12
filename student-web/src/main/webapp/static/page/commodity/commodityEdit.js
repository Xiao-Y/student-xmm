layui.use('form', function() {
	var $ = layui.jquery,form = layui.form(),layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		return submitFormNewTip(data);
	});
	
	//用于显示radio的选种
	var arry = ['status_radio','valid_radio','grade_select'];
	pubPopForm.checkedDisplay(arry,form);
});
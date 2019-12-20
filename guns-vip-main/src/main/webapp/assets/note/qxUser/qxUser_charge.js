/**
 * 用户充值界面
 */
var QxUserChargeDlg = {
		data: {
			userId: "",
			packageId: ""
		}
};

layui.use(['form', 'admin', 'ax'], function() {
	var $ = layui.jquery;
	var $ax = layui.ax;
	var form = layui.form;
	var admin = layui.admin;
	
	// 获取套餐列表
	var getPackages = function() {
		$("#packageId").html('<option value="">请选择充值套餐</option>');
		var ajax = new $ax(Feng.ctxPath + "/qxPackage/list", function(data) {
			for (var i = 0; i < data.data.length;i++) {
				var packageId = data.data[i].id;
				var packageName = data.data[i].amount + "元 - " + data.data[i].coins + "金币";
				$("#packageId").append('<option value="' + packageId + '">' + packageName + '</option>');
			}
			form.render();
		}, function (data){
			
		});
		ajax.start();
	}
	getPackages();
	// 表单提交
	form.on('submit(btnSubmit)', function(data) {
		var ajax = new $ax(Feng.ctxPath + "/qxUser/chargeUser", function(data) {
			Feng.success("充值成功!");
			
			admin.putTempData('formOk', true);
			
			admin.closeThisDialog();
		}, function(data) {
			Feng.error("添加失败!" + data.responseJSON.message)
		});
		ajax.set(data.field);
		ajax.start();
		
		return false;
	});
	
});
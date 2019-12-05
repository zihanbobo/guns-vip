/**
 * 详情对话框
 */
var QxProductInfoDlg = {
    data: {
        version: "",
        createdBy: "",
        createdTime: "",
        updatedBy: "",
        updatedTime: "",
        deleted: "",
        categoryId: "",
        productName: "",
        headImages: "",
        detailImages: "",
        price: "",
        stock: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var formSelects = layui.formSelects;

    //让当前iframe弹层高度适应
    // admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/qxProduct/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('qxProductForm', result.data);

    var getCategories = function() {
        // 初始化产品类别
        $("#categoryId").html('<option value="">请选择产品分类</option>');
        var ajax = new $ax(Feng.ctxPath + "/qxCategory/list", function(data){
        	for (var i = 0; i < data.data.length;i++) {
        		var categoryId = data.data[i].id;
        		var categoryName = data.data[i].name;
        		$("#categoryId").append('<option value="' + categoryId + '">' + categoryName + '</option>');
        	}
        	form.render();
        }, function (data) {
        	
        });
        ajax.start();
    }
    getCategories();
    
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/qxProduct/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
    
    $("#categoryId").val(result.data.categoryId);
    form.render();
});
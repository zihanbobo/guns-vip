/**
 * 添加或者修改页面
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
var $;
//轮播图列表
var headImageArray = [];
//商品详情图片
var detailImageArray = [];

layui.use(['form', 'admin', 'ax', 'upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;

    //让当前iframe弹层高度适应
    // admin.iframeAuto();

 	// 商品类别列表
    var getCategories = function() {
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
    
    // 轮播图
    upload.render({
    	elem: '#head_images_upload',
    	url: Feng.ctxPath + "/api/file/upload",
    	multiple: true,
    	before: function(obj) {
    		obj.preview(function(index, file, result){
    			$('#head_images_slide_show').append('<img src="' + result + '" alt="' + file.name + '" title="双击删除" class="layui-upload-img" ondblclick="delHeadImage(this)">');
    		});
    	},
    	done: function(res) {
    		if (res.code == 200) {
    			headImageArray.push(res.data);
    			$('#headImages').val(headImageArray.join(","));
    			Feng.success("图片上传成功!");
    		} else {
    			Feng.error(res.msg);
    		}
    	}
    });
    
    // 详情图
    upload.render({
    	elem: '#detail_images_upload',
    	url: Feng.ctxPath + "/api/file/upload",
    	multiple: true,
    	before: function(obj) {
    		obj.preview(function(index, file, result){
    			$('#detail_images_slide_show').append('<img src="' + result + '" alt="' + file.name + '" title="双击删除" class="layui-upload-img" ondblclick="delDetailImage(this)">');
    		});
    	},
    	done: function(res) {
    		if (res.code == 200) {
    			detailImageArray.push(res.data);
    			$('#detailImages').val(detailImageArray.join(","));
    			Feng.success("图片上传成功!");
    		} else {
    			Feng.error(res.msg);
    		}
    	}
    });
    
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/qxProduct/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
});

function delHeadImage(this_img) {
	console.log(this_img);
	//获取下标
    var subscript=$("#head_images_slide_show img").index(this_img);
    //删除图片
    this_img.remove();
    //删除数组
    headImageArray.splice(subscript, 1);
    //重新排序
//    headImageArray.sort();
    $('#headImages').val(headImageArray.join(","));
}

function delDetailImage(this_img) {
	console.log(this_img);
	//获取下标
    var subscript=$("#detail_images_slide_show img").index(this_img);
    //删除图片
    this_img.remove();
    //删除数组
    detailImageArray.splice(subscript, 1);
    //重新排序
//    detailImageArray.sort();
    $('#detailImages').val(detailImageArray.join(","));
}
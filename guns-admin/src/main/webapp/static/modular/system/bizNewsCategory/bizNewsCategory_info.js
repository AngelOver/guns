/**
 * 初始化详情对话框
 */
var BizNewsCategoryInfoDlg = {
	bizNewsCategoryInfoData : {},
	validateFields: {
		categoryName: {
		        validators: {
		            notEmpty: {
		                message: '分类名称不能为空 '
		            }
		        }
		    },    
		    sort: {
		        validators: {
		            notEmpty: {
		                message: '排序不能为空 '
		            }
		        }
		    },
	}
};

/**
 * 清除数据
 */
BizNewsCategoryInfoDlg.clearData = function() {
    this.bizNewsCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizNewsCategoryInfoDlg.set = function(key, val) {
    this.bizNewsCategoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizNewsCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizNewsCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.BizNewsCategory.layerIndex);
}

/**
 * 收集数据
 */
BizNewsCategoryInfoDlg.collectData = function() {
    this
    .set('categoryId')
    .set('categoryName')
    .set('parentCategoryId')
    .set('sort')
    .set('status')
    ;
}

/**
 * 验证数据是否为空
 */
BizNewsCategoryInfoDlg.validate = function () {	
    $('#bizNewsCategoryInfoForm').data("bootstrapValidator").resetForm();

    $('#bizNewsCategoryInfoForm').bootstrapValidator('validate');    

    return $('#bizNewsCategoryInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizNewsCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizNewsCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizNewsCategory.table.refresh();
        BizNewsCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizNewsCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizNewsCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizNewsCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizNewsCategory.table.refresh();
        BizNewsCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizNewsCategoryInfoData);
    ajax.start();
}

$(function() {
	
	Feng.initValidator("bizNewsCategoryInfoForm", BizNewsCategoryInfoDlg.validateFields);
	
	//初始化状态
	 $("#status").val($("#statusValue").val());

});

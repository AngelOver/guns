/**
 * 初始化详情对话框
 */
var BizLinksInfoDlg = {
	bizLinksInfoData : {},
	validateFields: {
		linksName: {
	        validators: {
	            notEmpty: {
	                message: '链接名称不能为空 '
	            }
	        }
	    },    
	    linksUrl: {
	        validators: {
	            notEmpty: {
	                message: '链接地址不能为空 '
	            }
	        }
	    },    	    
	}
};

/**
 * 清除数据
 */
BizLinksInfoDlg.clearData = function() {
    this.bizLinksInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizLinksInfoDlg.set = function(key, val) {
    this.bizLinksInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizLinksInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizLinksInfoDlg.close = function() {
    parent.layer.close(window.parent.BizLinks.layerIndex);
}

/**
 * 收集数据
 */
BizLinksInfoDlg.collectData = function() {
    this
    .set('linksId')
    .set('linksName')
    .set('linksUrl')
    ;
}

/**
 * 验证数据是否为空
 */
BizLinksInfoDlg.validate = function () {	
    $('#bizLinksInfoForm').data("bootstrapValidator").resetForm();

    $('#bizLinksInfoForm').bootstrapValidator('validate');    

    return $('#bizLinksInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizLinksInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();   
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizLinks/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizLinks.table.refresh();
        BizLinksInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizLinksInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizLinksInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //验证表单
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizLinks/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizLinks.table.refresh();
        BizLinksInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizLinksInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizLinksInfoForm", BizLinksInfoDlg.validateFields);
	
});

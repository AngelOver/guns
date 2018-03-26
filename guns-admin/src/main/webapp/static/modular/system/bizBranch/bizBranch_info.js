/**
 * 初始化详情对话框
 */
var BizBranchInfoDlg = {
	bizBranchInfoData : {},
	validateFields: {
		name: {
	        validators: {
	            notEmpty: {
	                message: '名称不能为空 '
	            }
	        }
	    },    
	    address: {
	        validators: {
	            notEmpty: {
	                message: '地址不能为空 '
	            }
	        }
	    },  
	    telephone: {
	        validators: {
	            notEmpty: {
	                message: '电话号码不能为空 '
	            }
	        }
	    },    	  
	}
};

/**
 * 清除数据
 */
BizBranchInfoDlg.clearData = function() {
    this.bizBranchInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizBranchInfoDlg.set = function(key, val) {
    this.bizBranchInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizBranchInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizBranchInfoDlg.close = function() {
    parent.layer.close(window.parent.BizBranch.layerIndex);
}

/**
 * 收集数据
 */
BizBranchInfoDlg.collectData = function() {
    this
    .set('branchId')
    .set('name')
    .set('address')
    .set('telephone')
    ;
}

/**
 * 验证数据是否为空
 */
BizBranchInfoDlg.validate = function () {	
    $('#bizBranchInfoForm').data("bootstrapValidator").resetForm();

    $('#bizBranchInfoForm').bootstrapValidator('validate');    

    return $('#bizBranchInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizBranchInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();   
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizBranch/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizBranch.table.refresh();
        BizBranchInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizBranchInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizBranchInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //验证表单
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizBranch/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizBranch.table.refresh();
        BizBranchInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizBranchInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizBranchInfoForm", BizBranchInfoDlg.validateFields);
	
});

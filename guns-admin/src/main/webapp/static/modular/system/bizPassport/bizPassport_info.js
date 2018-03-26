/**
 * 初始化详情对话框
 */
var BizPassportInfoDlg = {
    bizPassportInfoData : {},
	validateFields: {
	    account: {
	        validators: {
	            notEmpty: {
	                message: '登录帐号不能是空 '
	            }
	        }
	    },    
	    email: {
	        validators: {
	            notEmpty: {
	                message: '邮箱不能是空 '
	            }
	        }
	    },    
	    password: {
	        validators: {
	            notEmpty: {
	                message: '密码不能为空'
	            },	           
	        }
	    },
	    mobile: {
	        validators: {
	            notEmpty: {
	                message: '手机号不能为空'
	            },	           
	        }
	    },	    
	    sex: {
	        validators: {
	            notEmpty: {
	                message: '请选择性别'
	            },	           
	        }
	    },	    
	    role: {
	        validators: {
	            notEmpty: {
	                message: '请选择角色'
	            },	           
	        }
	    },	    
	}
};

/**
 * 清除数据
 */
BizPassportInfoDlg.clearData = function() {
    this.bizPassportInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportInfoDlg.set = function(key, val) {
    this.bizPassportInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizPassportInfoDlg.close = function() {
    parent.layer.close(window.parent.BizPassport.layerIndex);
}

/**
 * 收集数据
 */
BizPassportInfoDlg.collectData = function() {
    this
    .set('passportId')
    /*.set('verifyApplyId')
    .set('agentId')
    .set('industryId')*/
    .set('account')
    .set('password')
    .set('role')
    .set('nickname')
    .set('headimg')
    .set('mobile')
    .set('email')
    .set('sex')
    .set('verify')
    .set('score')
    .set('balance')
    .set('status')
    ;
}

/**
 * 验证数据是否为空
 */
BizPassportInfoDlg.validate = function () {	
    $('#bizpassportInfoForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizpassportInfoForm').data("bootstrapValidator").isValid();   
    $('#bizpassportInfoForm').bootstrapValidator('validate');    
    var flag = $('#bizpassportInfoForm').data("bootstrapValidator").isValid();   
    return $('#bizpassportInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizPassportInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();   
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassport/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizPassport.table.refresh();
        BizPassportInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizPassportInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //验证表单
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassport/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizPassport.table.refresh();
        BizPassportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizpassportInfoForm", BizPassportInfoDlg.validateFields);
	
	//初始化性别
	 $("#sex").val($("#sexValue").val());
	 //初始化状态
	 $("#verify").val($("#verifyValue").val());	
	 $("#role").val($("#roleValue").val());
	 
	  // 初始化头像上传
	    var avatarUp = new $WebUpload("headimg");
	    console.log(avatarUp);
	    avatarUp.setUploadBarId("progressBar");
	    avatarUp.init();
});

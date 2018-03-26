/**
 * 初始化详情对话框
 */
var SetupInfoDlg = {
    setupInfoData : {},
    validateFields: {
    	websiteName: {
	        validators: {
	            notEmpty: {
	                message: '网站名称不能是空 '
	            }
	        }
	    },    
	    logo: {
	        validators: {
	            notEmpty: {
	                message: 'logo不能是空 '
	            }
	        }
	    },    
	    keyword: {
	        validators: {
	            notEmpty: {
	                message: '关键字不能是空'
	            },	           
	        }
	    },	   	
	    describe: {
	        validators: {
	            notEmpty: {
	                message: '描述不能是空'
	            },	           
	        }
	    },
	    email: {
	        validators: {
	            notEmpty: {
	                message: '请输入正确的邮箱'
	            },	           
	        }
	    },
	    address: {
	        validators: {
	            notEmpty: {
	                message: '请输入地址'
	            },	           
	        }
	    },	
	    hotline: {
	        validators: {
	            notEmpty: {
	                message: '请输入服务热线'
	            },
	            regexp: {  
                    regexp: /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/,  
                    message: '请输入正确的固定电话如0714-xxxxxx'  
                }      
	        }
	    },	   	 
	}
};

/**
 * 清除数据
 */
SetupInfoDlg.clearData = function() {
    this.setupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SetupInfoDlg.set = function(key, val) {
    this.setupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SetupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SetupInfoDlg.close = function() {
    parent.layer.close(window.parent.Setup.layerIndex);
}

/**
 * 收集数据
 */
SetupInfoDlg.collectData = function() {
    this
    .set('websiteName')
    .set('logo')
    .set('keyword')
    .set('id')
    .set('describe')
    .set('email')
    .set('address')
     .set('hotline')
    ;
}

/**
 * 验证数据是否为空
 */
SetupInfoDlg.validate = function () {	
    $('#SetupInfoForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#SetupInfoForm').data("bootstrapValidator").isValid();   
    $('#SetupInfoForm').bootstrapValidator('validate');    
    var flag = $('#SetupInfoForm').data("bootstrapValidator").isValid();    
    return $('#SetupInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
/*SetupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/setup/add", function(data){
        Feng.success("添加成功!");
        window.parent.Setup.table.refresh();
        SetupInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.setupInfoData);
    ajax.start();
}*/

/**
 * 提交修改
 */
SetupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/setup/update", function(data){
        Feng.success("修改成功!");
        window.parent.Setup.table.refresh();
        SetupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.setupInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("SetupInfoForm", SetupInfoDlg.validateFields);
	
	 // 初始化头像上传
    var avatarUp = new $WebUpload("logo");
    console.log(avatarUp);
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
});

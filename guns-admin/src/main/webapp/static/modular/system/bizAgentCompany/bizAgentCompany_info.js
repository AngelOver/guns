/**
 * 初始化代理商详情对话框
 */
var BizAgentCompanyInfoDlg = {
    bizAgentCompanyInfoData : {},
	validateFields: {
		agentCompanyName: {
	        validators: {
	            notEmpty: {
	                message: '代理所名称不能为空 '
	            }
	        }
	    },    
	    agentCompanyContactor: {
	        validators: {
	            notEmpty: {
	                message: '联系人姓名不能为空 '
	            }
	        }
	    },  
	    agentCompanyMobile: {
	        validators: {
	            notEmpty: {
	                message: '电话号码不能为空 '
	            },
	            regexp: {  
                    regexp:"^1[3|4|5|6|7|8|9][0-9]\\d{8}$",  
                    message: '请输入正确的手机号码'  
                }
	        }
	    },
	    businessLicenseNo: {
	        validators: {
	            notEmpty: {
	                message: '营业执照号码不能为空 '
	            }
	        }
	    },
	    businessLicense: {
    		validators: {
    			notEmpty: {
    				message: '请上传营业执照'
    			},		           
    		}
    	},
    	organizationCodeCertificate: {
    		validators: {
    			notEmpty: {
    				message: '请上传组织机构代码证'
    			},		           
    		}
    	},
    	taxRegistrationCertificate: {
    		validators: {
    			notEmpty: {
    				message: '请上传税务登记证'
    			},		           
    		}
    	},
    	bankCompany: {
    		validators: {
    			notEmpty: {
    				message: '开户行不能为空'
    			},		           
    		}
    	},	 
    	bankCardNum: {
    		validators: {
    			notEmpty: {
    				message: '卡号不能为空'
    			},		           
    		}
    	},	 
    	powerOfAttorney: {
    		validators: {
    			notEmpty: {
    				message: '委托书不能为空'
    			},		           
    		}
    	},
    	trustee: {
    		validators: {
    			notEmpty: {
    				message: '请上传委托人身份证'
    			},		           
    		}
    	},
    	agentVertificate1: {
    		validators: {
    			notEmpty: {
    				message: '请上传资格证书1'
    			},		           
    		}
    	},
    	agentVertificate2: {
    		validators: {
    			notEmpty: {
    				message: '请上传资格证书2'
    			},		           
    		}
    	},
    	companyIntroduce: {
    		validators: {
    			notEmpty: {
    				message: '公司简介不能为空'
    			},		           
    		}
    	},
    	companyLogo: {
    		validators: {
    			notEmpty: {
    				message: '公司logo不能为空'
    			},		           
    		}
    	},
    	companyScale: {
    		validators: {
    			notEmpty: {
    				message: '企业规模不能为空'
    			},		           
    		}
    	},
    	companyBuildTime: {
    		validators: {
    			notEmpty: {
    				message: '成立时间不能为空'
    			},		           
    		}
    	}
	}
};

/**
 * 清除数据
 */
BizAgentCompanyInfoDlg.clearData = function() {
    this.bizAgentCompanyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentCompanyInfoDlg.set = function(key, val) {
    this.bizAgentCompanyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentCompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizAgentCompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.BizAgentCompany.layerIndex);
}

/**
 * 收集数据
 */
BizAgentCompanyInfoDlg.collectData = function() {
    this
    .set('agentCompanyId')
    .set('agentId')
    .set('agentCompanyName')
    .set('agentCompanyContactor')
    .set('agentCompanyMobile')
    .set('businessLicenseNo')
    .set('businessLicense')
    .set('organizationCodeCertificate')
    .set('taxRegistrationCertificate')
    .set('bankCompany')
    .set('bankCardNum')
    .set('powerOfAttorney')
    .set('trustee')
    .set('agentCertificate1')
    .set('agentCertificate2')
    .set('companyIntroduce')
    .set('companyLogo')
    .set('companyScale')
    .set('companyBuildTime')
    .set('indexShow')
    ;
}

/**
 * 验证数据是否为空
 */
BizAgentCompanyInfoDlg.validate = function () {	
    $('#bizAgentCompanyForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizAgentCompanyForm').data("bootstrapValidator").isValid();   
    $('#bizAgentCompanyForm').bootstrapValidator('validate');    
    var flag = $('#bizAgentCompanyForm').data("bootstrapValidator").isValid();   
    return $('#bizAgentCompanyForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizAgentCompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentCompany/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizAgentCompany.table.refresh();
        BizAgentCompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentCompanyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizAgentCompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentCompany/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizAgentCompany.table.refresh();
        BizAgentCompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentCompanyInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizAgentCompanyForm", BizAgentCompanyInfoDlg.validateFields);
	
	// 初始身份证照片上传
    var avatarUp = new $WebUpload("businessLicense");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
    
    var avatarUp2 = new $WebUpload("organizationCodeCertificate");
    avatarUp2.setUploadBarId("progressBar");
    avatarUp2.init();
    
    var avatarUp3 = new $WebUpload("taxRegistrationCertificate");
    avatarUp3.setUploadBarId("progressBar");
    avatarUp3.init();
    
    var avatarUp4 = new $WebUpload("trustee");
    avatarUp4.setUploadBarId("progressBar");
    avatarUp4.init();
    
    var avatarUp5 = new $WebUpload("agentCertificate1");
    avatarUp5.setUploadBarId("progressBar");
    avatarUp5.init();
    
    var avatarUp6 = new $WebUpload("agentCertificate2");
    avatarUp6.setUploadBarId("progressBar");
    avatarUp6.init();
    
    var avatarUp6 = new $WebUpload("companyLogo");
    avatarUp6.setUploadBarId("progressBar");
    avatarUp6.init();

});

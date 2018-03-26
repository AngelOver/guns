/**
 * 初始化详情对话框
 */
var BizPassportCouponInfoDlg = {
    bizPassportCouponInfoData : {},
    validateFields: {   	
    	couponId: {
	        validators: {
	            notEmpty: {
	                message: '优惠卷不能是空 '
	            }
	        }
	    },    
	    passportId: {
	        validators: {
	            notEmpty: {
	                message: '会员id不能是空 '
	            }
	        }
	    },    
	    min: {
	        validators: {
	        	notEmpty: {
	                message: '请输入最低金额 '
	            },
	            regexp: {  
                    regexp: /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/,  
                    message: '请输入数字格式'  
                }        
	        }
	    },	   	     	        
	    cost: {
	        validators: {
	        	notEmpty: {
	                message: '请输入减免金额'
	            },	
	            regexp: {  
                    regexp: /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/,  
                    message: '请输入数字格式'  
                }  
	        }
	    },	   	     	   
	    createdAt: {
	        validators: {
	            notEmpty: {
	                message: '请输入获取时间'
	            },	           
	        }
	    },	 
	    usedAt: {
	        validators: {
	            notEmpty: {
	                message: '请输入使用时间'
	            },	           
	        }
	    },	   	 
	    expiredAt: {
	        validators: {
	            notEmpty: {
	                message: '请输入过期时间'
	            },	           
	        }
	    },	   	 
	    isUsed: {
	        validators: {
	            notEmpty: {
	                message: '请选择是否使用'
	            },	           
	        }
	    },	
    }    
};

/**
 * 清除数据
 */
BizPassportCouponInfoDlg.clearData = function() {
    this.bizPassportCouponInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportCouponInfoDlg.set = function(key, val) {
    this.bizPassportCouponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportCouponInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizPassportCouponInfoDlg.close = function() {
    parent.layer.close(window.parent.BizPassportCoupon.layerIndex);
}

/**
 * 收集数据
 */
BizPassportCouponInfoDlg.collectData = function() {
    this
    .set('couponUserId')
    .set('couponId')
    .set('passportId')
    .set('min')
    .set('cost')
    .set('isUsed')
    .set('createdAt')
    .set('usedAt')
    ;
}

/**
 * 验证数据是否为空
 */
BizPassportCouponInfoDlg.validate = function () {	
    $('#bizPassportCouponsForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizPassportCouponsForm').data("bootstrapValidator").isValid();   
    $('#bizPassportCouponsForm').bootstrapValidator('validate');    
    var flag = $('#bizPassportCouponsForm').data("bootstrapValidator").isValid();   
    return $('#bizPassportCouponsForm').data("bootstrapValidator").isValid();
};


/**
 * 提交添加
 */
BizPassportCouponInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassportCoupon/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizPassportCoupon.table.refresh();
        BizPassportCouponInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportCouponInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizPassportCouponInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassportCoupon/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizPassportCoupon.table.refresh();
        BizPassportCouponInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportCouponInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizPassportCouponsForm", BizPassportCouponInfoDlg.validateFields);
	 $("#isUsed").val($("#isUsedValue").val());
});

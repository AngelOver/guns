/**
 * 初始化详情对话框
 */
var BizCouponsInfoDlg = {
    bizCouponsInfoData : {},
    validateFields: {
    	agentId: {
	        validators: {
	            notEmpty: {
	                message: '代理商id不能是空 '
	            }
	        }
	    },    
	    couponName: {
	        validators: {
	            notEmpty: {
	                message: '优惠卷名称不能是空 '
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
	    num: {
	        validators: {
	        	notEmpty: {
	                message: '请输入发行量'
	            },	
	            digits: {
	                message: '请输入数字'
	            },
	        }
	    },	
	    begin_time: {
	        validators: {
	            notEmpty: {
	                message: '请输入开始时间'
	            },	           
	        }
	    },	 
	    end_time: {
	        validators: {
	            notEmpty: {
	                message: '请输入结束时间'
	            },	           
	        }
	    },	   	 
	    status: {
	        validators: {
	            notEmpty: {
	                message: '请输入状态'
	            },	           
	        }
	    },	   	 
	}
};

/**
 * 清除数据
 */
BizCouponsInfoDlg.clearData = function() {
    this.bizCouponsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizCouponsInfoDlg.set = function(key, val) {
    this.bizCouponsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizCouponsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizCouponsInfoDlg.close = function() {
    parent.layer.close(window.parent.BizCoupons.layerIndex);
}

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizCouponsInfoDlg.onClickAgent = function (e, treeId, treeNode) {
	
	if (treeNode.isParent) {
		 Feng.error("请选择子分类");
       return false;
	};
	
	$("#agentId_name").attr("value", instance.getSelectedVal());
    $("#agentId").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
BizCouponsInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#agentId_name");
    var cityOffset = $("#agentId_name").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
BizCouponsInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
BizCouponsInfoDlg.collectData = function() {
    this
    .set('couponId')
    .set('agentId')
    .set('couponName')
    .set('min')
    .set('cost')
    .set('num')
    .set('expires')
    .set('status')
    ;
}

/**
 * 验证数据是否为空
 */
BizCouponsInfoDlg.validate = function () {	
    $('#bizCouponsForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizCouponsForm').data("bootstrapValidator").isValid();   
    $('#bizCouponsForm').bootstrapValidator('validate');    
    var flag = $('#bizCouponsForm').data("bootstrapValidator").isValid();   
    return $('#bizCouponsForm').data("bootstrapValidator").isValid();
};


/**
 * 提交添加
 */
BizCouponsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }
    
    var begin_time = $("#begin_time").val();
    var end_time = $("end_time").val();
   
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizCoupons/add", function(data){  	
    	if(data=="0000"){
    		Feng.error("开始时间必须要小于结束时间");
    		return;
    	}
    	if(data=="1111"){
    		Feng.error("格式转换异常");
    		return;
    	}
    	if(data=="2222"){
    		Feng.error("减免金额大于最低消费");
    		return;
    	}
        Feng.success("添加成功!");
        window.parent.BizCoupons.table.refresh();
        BizCouponsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizCouponsInfoData);
    ajax.set("begin_time", begin_time);
    ajax.set("end_time", end_time);
    ajax.start();
}

/**
 * 提交修改
 */
BizCouponsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }
    
    var begin_time = $("#begin_time").val();
    var end_time = $("end_time").val();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizCoupons/update", function(data){
    	if(data=="0000"){
    		Feng.error("开始时间必须要小于结束时间");
    		return;
    	}
    	if(data=="1111"){
    		Feng.error("格式转换异常");
    		return;
    	}
    	if(data=="2222"){
    		Feng.error("减免金额大于最低消费");
    		return;
    	}
        Feng.success("修改成功!");
        window.parent.BizCoupons.table.refresh();
        BizCouponsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizCouponsInfoData);
    ajax.set("begin_time", begin_time);
    ajax.set("end_time", end_time);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	BizCouponsInfoDlg.hideDeptSelectTree();
    }
}

$(function() {	
	Feng.initValidator("bizCouponsForm", BizCouponsInfoDlg.validateFields);
	
	 var ztree = new $ZTree("treeDemo", "/bizPassportCoupon/tree");
	    ztree.bindOnClick(BizCouponsInfoDlg.onClickAgent);
	    ztree.init();
	    instance = ztree;
	    
	 $("#status").val($("#statusValue").val());
});

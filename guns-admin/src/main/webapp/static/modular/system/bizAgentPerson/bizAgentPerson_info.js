/**
 * 初始化代理人列表详情对话框
 */
var BizAgentPersonInfoDlg = {
    bizAgentPersonInfoData : {},
    validateFields: {
    	agentId: {
    		validators: {
    			notEmpty: {
    				message: '请选择代理商 '
    			}
    		}
    	},    
    	username: {
	        validators: {
	        	notEmpty: {
	                message: '代理人姓名不能为空 '
	            },
	            regexp: {  
                    regexp: "^[a-zA-Z\u4E00-\u9FA5]{1,20}$",  
                    message: '请输入正确代理人姓名'  
                }        
	        }
	    },
    	mobile: {
	        validators: {
	        	notEmpty: {
	                message: '手机号不能为空 '
	            },
	            regexp: {  
                    regexp:"^1[3|4|5|6|7|8|9][0-9]\\d{8}$",  
                    message: '请输入正确的手机号码'  
                }        
	        }
	    },
	    idCard: {
	        validators: {
	        	notEmpty: {
	                message: '身份证号码不能是空 '
	            },
	            regexpx: {  
                    regexp: "^\\d{17}[\\d|x|X]$",  
                    message: '请输入正确的身份证号码'  
                }        
	        }
	    }, 	     
    	idCard_positive: {
    		validators: {
    			notEmpty: {
    				message: '请上传身份证正面照'
    			},		           
    		}
    	},	 
    	idCard_negative: {
    		validators: {
    			notEmpty: {
    				message: '请上传身份证反面照'
    			},		           
    		}
    	},	
    	workYear: {
    		validators: {
    				regexp: {
    				regexp:"^\\d{1,3}$",
    				message: '请输入数字格式'
    			},	           
    		}
    	},	
    }
 };

/**
 * 清除数据
 */
BizAgentPersonInfoDlg.clearData = function() {
    this.bizAgentPersonInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentPersonInfoDlg.set = function(key, val) {
    this.bizAgentPersonInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentPersonInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizAgentPersonInfoDlg.close = function() {
    parent.layer.close(window.parent.BizAgentPerson.layerIndex);
}

/**
 * 收集数据
 */
BizAgentPersonInfoDlg.collectData = function() {
    this
    .set('id')
    .set('agentId')
    .set('username')
    .set('mobile')
    .set('idCard')
    .set('idCardPositive')
    .set('idCardNegative')
    .set('agentCertificate1')
    .set('agentCertificate2')
    .set('businessIntroduce')
    .set('workYear')
    .set('serveCusomter')
    .set('goodAt')
    .set('showPicture')
    .set('indexShow')
    ;
}

/**
 * 验证数据是否为空
 */
BizAgentPersonInfoDlg.validate = function () {	
    $('#bizAgentPersonForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizAgentPersonForm').data("bootstrapValidator").isValid();   
    $('#bizAgentPersonForm').bootstrapValidator('validate');    
    var flag = $('#bizAgentPersonForm').data("bootstrapValidator").isValid();   
    return $('#bizAgentPersonForm').data("bootstrapValidator").isValid();
};

/**
 * 点击代理商input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizAgentPersonInfoDlg.onClickAgent = function (e, treeId, treeNode) {
	
	if (treeNode.isParent) {
		 Feng.error("请选择代理商");
       return false;
	};
	
	$("#agentId_name").attr("value", instance.getSelectedVal());
    $("#agentId").attr("value", treeNode.id);
};

/**
 * 显示代理商选择的树
 *
 * @returns
 */
BizAgentPersonInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#agentId_name");
    var cityOffset = $("#agentId_name").offset();   
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mouseover", onBodyDown);
};

/**
 * 隐藏代理商选择的树
 */
BizAgentPersonInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mouseover", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

//代理商划入结束
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	BizAgentPersonInfoDlg.hideDeptSelectTree();
    }
}

/**
 * 提交添加
 */
BizAgentPersonInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentPerson/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizAgentPerson.table.refresh();
        BizAgentPersonInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentPersonInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizAgentPersonInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentPerson/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizAgentPerson.table.refresh();
        BizAgentPersonInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentPersonInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizAgentPersonForm", BizAgentPersonInfoDlg.validateFields);
	
	 var ztree = new $ZTree("treeDemo", "/bizPassportCoupon/tree");
	    ztree.bindOnClick(BizAgentPersonInfoDlg.onClickAgent);
	    ztree.init();
	    instance = ztree;
	    
	    
	 $("#status").val($("#statusValue").val());
		//初始化性别
	 $("#sex").val($("#sexValue").val());
	 //初始化状态
	 $("#verify").val($("#verifyValue").val());	
	 $("#role").val($("#roleValue").val());
	 
	  // 初始身份证照片上传
	    var avatarUp = new $WebUpload("idCardPositive");
	    console.log(avatarUp);
	    avatarUp.setUploadBarId("progressBar");
	    avatarUp.init();
	    
	    var avatarUp2 = new $WebUpload("idCardNegative");
	    console.log(avatarUp2);
	    avatarUp2.setUploadBarId("progressBar");
	    avatarUp2.init();
	    
	    var avatarUp3 = new $WebUpload("showPicture");
	    console.log(avatarUp3);
	    avatarUp3.setUploadBarId("progressBar");
	    avatarUp3.init();
	    
	    var avatarUp4 = new $WebUpload("agentCertificate1");
	    console.log(avatarUp4);
	    avatarUp4.setUploadBarId("progressBar");
	    avatarUp4.init();
	    
	    var avatarUp5 = new $WebUpload("agentCertificate2");
	    console.log(avatarUp5);
	    avatarUp5.setUploadBarId("progressBar");
	    avatarUp5.init();

});


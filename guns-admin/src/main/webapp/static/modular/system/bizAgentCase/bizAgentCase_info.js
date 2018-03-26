/**
 * 初始化详情对话框
 */
var BizAgentCaseInfoDlg = {
    bizAgentCaseInfoData : {},
	validateFields: {
		agentId: {
		        validators: {
		            notEmpty: {
		                message: '代理商Id不能为空 '
		            }
		        }
		    },    
		    content: {
		        validators: {
		            notEmpty: {
		                message: '案例内容不能为空 '
		            }
		        }
		    },    
		    title: {
		        validators: {
		            notEmpty: {
		                message: '标题不能为空'
		            },	           
		        }
		    },
		    linkUrl: {
		        validators: {
		            notEmpty: {
		                message: '相关链接不能为空'
		            },	           
		        }
		    },	 
		    
	}
};

/**
 * 清除数据
 */
BizAgentCaseInfoDlg.clearData = function() {
    this.bizAgentCaseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentCaseInfoDlg.set = function(key, val) {
    this.bizAgentCaseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentCaseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizAgentCaseInfoDlg.close = function() {
    parent.layer.close(window.parent.BizAgentCase.layerIndex);
}

/**
 * 收集数据
 */
BizAgentCaseInfoDlg.collectData = function() {
    this
    .set('agentCaseId')
    .set('agentId')
    .set('cover')
    .set('content')
    .set('title')
    .set('linkUrl')
    ;
}


/**
 * 验证数据是否为空
 */
BizAgentCaseInfoDlg.validate = function () {	
    $('#bizAgentCaseInfoForm').data("bootstrapValidator").resetForm();

    $('#bizAgentCaseInfoForm').bootstrapValidator('validate');    

    return $('#bizAgentCaseInfoForm').data("bootstrapValidator").isValid();
};


/**
 * 提交添加
 */
BizAgentCaseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentCase/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizAgentCase.table.refresh();
        BizAgentCaseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentCaseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizAgentCaseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgentCase/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizAgentCase.table.refresh();
        BizAgentCaseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentCaseInfoData);
    ajax.start();
}

$(function() {
	
	Feng.initValidator("bizAgentCaseInfoForm", BizAgentCaseInfoDlg.validateFields);
	
	// 初始化头像上传
    var avatarUp = new $WebUpload("cover");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});

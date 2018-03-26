/**
 * 初始化详情对话框
 */
var BizNewsInfoDlg = {
    bizNewsInfoData : {},
    validateFields: {
    	categoryId: {
		        validators: {
		            notEmpty: {
		                message: '分类ID不能为空 '
		            }
		        }
		    },    
		    agentId: {
		        validators: {
		            notEmpty: {
		                message: '代理商ID不能为空 '
		            }
		        }
		    },
		    title: {
		        validators: {
		            notEmpty: {
		                message: '新闻标题不能为空 '
		            }
		        }
		    },
		    
	}
};

/**
 * 清除数据
 */
BizNewsInfoDlg.clearData = function() {
    this.bizNewsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizNewsInfoDlg.set = function(key, val) {
    this.bizNewsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizNewsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizNewsInfoDlg.close = function() {
    parent.layer.close(window.parent.BizNews.layerIndex);
}

/**
 * 收集数据
 */
BizNewsInfoDlg.collectData = function() {
    this
    .set('newsId')
    .set('categoryId')
    .set('agentId')
    .set('title')
    .set('cover')
    .set('content')
    .set('status')
    .set('applyStatus')
    .set('createdTime')
    ;
}

/**
 * 验证数据是否为空
 */
BizNewsInfoDlg.validate = function () {	
    $('#bizNewsInfoForm').data("bootstrapValidator").resetForm();

    $('#bizNewsInfoForm').bootstrapValidator('validate');    

    return $('#bizNewsInfoForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizNewsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizNews/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizNews.table.refresh();
        BizNewsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizNewsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizNewsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizNews/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizNews.table.refresh();
        BizNewsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizNewsInfoData);
    ajax.start();
}

$(function() {
	
	Feng.initValidator("bizNewsInfoForm", BizNewsInfoDlg.validateFields);
	
	//初始化状态
	 $("#status").val($("#statusValue").val());
	
	 $("#applyStatus").val($("applyStatusValue").val());

	  
	// 初始化头像上传
    var avatarUp = new $WebUpload("cover");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
	
});

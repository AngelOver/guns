/**
 * 初始化详情对话框
 */
var BizBannerInfoDlg = {
    bizBannerInfoData : {},
	validateFields: {
	   	type: {
		        validators: {
		            notEmpty: {
		                message: '类型不能为空 '
		            }
		        }
		    },    
		    imageUrl: {
		        validators: {
		            notEmpty: {
		                message: 'Banner图片不能为空 '
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
	}
};


/**
 * 清除数据
 */
BizBannerInfoDlg.clearData = function() {
    this.bizBannerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizBannerInfoDlg.set = function(key, val) {
    this.bizBannerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizBannerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizBannerInfoDlg.close = function() {
    parent.layer.close(window.parent.BizBanner.layerIndex);
}

/**
 * 收集数据
 */
BizBannerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('title')
    .set('spec')
    .set('sort')
    .set('imageUrl')
    .set('agentId')
    .set('status')
    .set('url')
    ;
}



/**
 * 验证数据是否为空
 */
BizBannerInfoDlg.validate = function () {	

    var flag1 = $('#bizBannerInfoForm').data("bootstrapValidator").isValid();
    console.log(flag1);
    $('#bizBannerInfoForm').data("bootstrapValidator").resetForm();
    $('#bizBannerInfoForm').bootstrapValidator('validate');    
    var flag = $('#bizBannerInfoForm').data("bootstrapValidator").isValid();
    console.log(flag);
    return $('#bizBannerInfoForm').data("bootstrapValidator").isValid();
};
/**
 * 提交添加
 */
BizBannerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizBanner/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizBanner.table.refresh();
        BizBannerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizBannerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizBannerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizBanner/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizBanner.table.refresh();
        BizBannerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizBannerInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("bizBannerInfoForm", BizBannerInfoDlg.validateFields);
	
	//初始化状态
	 $("#status").val($("#statusValue").val());

	// 初始化Banner上传
    var avatarUp = new $WebUpload("imageUrl");
    console.log(avatarUp);
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});

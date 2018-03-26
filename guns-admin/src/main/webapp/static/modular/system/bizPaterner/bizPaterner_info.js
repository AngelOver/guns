/**
 * 初始化详情对话框
 */
var BizPaternerInfoDlg = {
bizPaternerInfoData : {},    
   validateFields: {
    name: {
        validators: {
            notEmpty: {
                message: '公司名称不能为空 '
            }
        }
    },    
    url: {
        validators: {
            notEmpty: {
                message: '公司链接地址不能为空 '
            }
        }
    }   
 }
};

/**
 * 清除数据
 */
BizPaternerInfoDlg.clearData = function() {
    this.bizPaternerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPaternerInfoDlg.set = function(key, val) {
    this.bizPaternerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPaternerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizPaternerInfoDlg.close = function() {
    parent.layer.close(window.parent.BizPaterner.layerIndex);
}

/**
 * 收集数据
 */
BizPaternerInfoDlg.collectData = function() {
    this
    .set('paternerId')
    .set('logo')
    .set('name')
    .set('url')
    ;
}


/**
 * 验证数据是否为空
 */
BizPaternerInfoDlg.validate = function () {	
	
    $('#bizpaternerInfoForm').data("bootstrapValidator").resetForm();   
    $('#bizpaternerInfoForm').bootstrapValidator('validate');
    
    return $('#bizpaternerInfoForm').data("bootstrapValidator").isValid();
};


/**
 * 提交添加
 */
BizPaternerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
   
  //验证表单
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPaterner/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizPaterner.table.refresh();
        BizPaternerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPaternerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizPaternerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPaterner/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizPaterner.table.refresh();
        BizPaternerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPaternerInfoData);
    ajax.start();
}

$(function() {
	
	Feng.initValidator("bizpaternerInfoForm", BizPaternerInfoDlg.validateFields);
    
	// 初始化头像上传
    var avatarUp = new $WebUpload("logo");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
	
});

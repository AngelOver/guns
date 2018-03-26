/**
 * 初始化详情对话框
 */
var BizEvaluateInfoDlg = {
    bizEvaluateInfoData : {}
};

/**
 * 清除数据
 */
BizEvaluateInfoDlg.clearData = function() {
    this.bizEvaluateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizEvaluateInfoDlg.set = function(key, val) {
    this.bizEvaluateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizEvaluateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizEvaluateInfoDlg.close = function() {
    parent.layer.close(window.parent.BizEvaluate.layerIndex);
}

/**
 * 收集数据
 */
BizEvaluateInfoDlg.collectData = function() {
    this
    .set('evaluateId')
    .set('passportId')
    .set('serviceId')
    .set('agentId')
    .set('star')
    .set('content')
    ;
}

/**
 * 提交添加
 */
BizEvaluateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizEvaluate/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizEvaluate.table.refresh();
        BizEvaluateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizEvaluateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizEvaluateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizEvaluate/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizEvaluate.table.refresh();
        BizEvaluateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizEvaluateInfoData);
    ajax.start();
}

$(function() {

});

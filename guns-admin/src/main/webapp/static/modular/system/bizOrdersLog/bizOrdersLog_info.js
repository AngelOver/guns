/**
 * 初始化详情对话框
 */
var BizOrdersLogInfoDlg = {
    bizOrdersLogInfoData : {}
};

/**
 * 清除数据
 */
BizOrdersLogInfoDlg.clearData = function() {
    this.bizOrdersLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersLogInfoDlg.set = function(key, val) {
    this.bizOrdersLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizOrdersLogInfoDlg.close = function() {
    parent.layer.close(window.parent.BizOrdersLog.layerIndex);
}

/**
 * 收集数据
 */
BizOrdersLogInfoDlg.collectData = function() {
    this
    .set('logId')
    .set('orderId')
    .set('passportId')
    .set('type')
    .set('note')
    .set('createdAt')
    ;
}

/**
 * 提交添加
 */
BizOrdersLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizOrdersLog.table.refresh();
        BizOrdersLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizOrdersLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizOrdersLog.table.refresh();
        BizOrdersLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersLogInfoData);
    ajax.start();
}

$(function() {

});

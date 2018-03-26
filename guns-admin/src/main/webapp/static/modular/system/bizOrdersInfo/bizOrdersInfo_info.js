/**
 * 初始化详情对话框
 */
var BizOrdersInfoInfoDlg = {
    bizOrdersInfoInfoData : {}
};

/**
 * 清除数据
 */
BizOrdersInfoInfoDlg.clearData = function() {
    this.bizOrdersInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersInfoInfoDlg.set = function(key, val) {
    this.bizOrdersInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizOrdersInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.BizOrdersInfo.layerIndex);
}

/**
 * 收集数据
 */
BizOrdersInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orderId')
    .set('serviceId')
    .set('serviceName')
    .set('serviceNum')
    .set('servicePrice')
    .set('serviceCount')
    ;
}

/**
 * 提交添加
 */
BizOrdersInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizOrdersInfo.table.refresh();
        BizOrdersInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizOrdersInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizOrdersInfo.table.refresh();
        BizOrdersInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersInfoInfoData);
    ajax.start();
}

$(function() {

});

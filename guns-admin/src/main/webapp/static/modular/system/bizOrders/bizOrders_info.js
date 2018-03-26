/**
 * 初始化详情对话框
 */
var BizOrdersInfoDlg = {
    bizOrdersInfoData : {}
};

/**
 * 清除数据
 */
BizOrdersInfoDlg.clearData = function() {
    this.bizOrdersInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersInfoDlg.set = function(key, val) {
    this.bizOrdersInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizOrdersInfoDlg.close = function() {
    parent.layer.close(window.parent.BizOrders.layerIndex);
}

/**
 * 收集数据
 */
BizOrdersInfoDlg.collectData = function() {
    this
    .set('orderId')
    .set('agentId')
    .set('passportId')
    .set('orderNumber')
    .set('totalPrice')
    .set('username')
    .set('mobile')
    .set('province')
    .set('city')
    .set('county')
    .set('address')
    .set('telphone')
    .set('email')
    .set('subject')
    .set('certificateNum')
    .set('recommender')
    .set('note')
    .set('invoice')
    .set('cost1')
    .set('cost2')
    .set('ext1')
    .set('ext2')
    .set('status')
    .set('createdAt')
    .set('payedAt')
    .set('completedAt')
    .set('receivedAt')
    ;
}

/**
 * 提交添加
 */
BizOrdersInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrders/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizOrders.table.refresh();
        BizOrdersInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizOrdersInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrders/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizOrders.table.refresh();
        BizOrdersInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersInfoData);
    ajax.start();
}

$(function() {

});

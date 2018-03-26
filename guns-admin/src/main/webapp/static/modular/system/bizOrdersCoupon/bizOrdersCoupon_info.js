/**
 * 初始化详情对话框
 */
var BizOrdersCouponInfoDlg = {
    bizOrdersCouponInfoData : {}
};

/**
 * 清除数据
 */
BizOrdersCouponInfoDlg.clearData = function() {
    this.bizOrdersCouponInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersCouponInfoDlg.set = function(key, val) {
    this.bizOrdersCouponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrdersCouponInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizOrdersCouponInfoDlg.close = function() {
    parent.layer.close(window.parent.BizOrdersCoupon.layerIndex);
}

/**
 * 收集数据
 */
BizOrdersCouponInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orderId')
    .set('couponUserId')
    .set('couponId')
    ;
}

/**
 * 提交添加
 */
BizOrdersCouponInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersCoupon/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizOrdersCoupon.table.refresh();
        BizOrdersCouponInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersCouponInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizOrdersCouponInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrdersCoupon/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizOrdersCoupon.table.refresh();
        BizOrdersCouponInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrdersCouponInfoData);
    ajax.start();
}

$(function() {

});

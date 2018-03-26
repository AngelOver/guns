/**
 * 初始化实名认证申请详情对话框
 */
var BizPassportVerifyApplyInfoDlg = {
    bizPassportVerifyApplyInfoData : {}
};

/**
 * 清除数据
 */
BizPassportVerifyApplyInfoDlg.clearData = function() {
    this.bizPassportVerifyApplyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportVerifyApplyInfoDlg.set = function(key, val) {
    this.bizPassportVerifyApplyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPassportVerifyApplyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizPassportVerifyApplyInfoDlg.close = function() {
    parent.layer.close(window.parent.BizPassportVerifyApply.layerIndex);
}

/**
 * 收集数据
 */
BizPassportVerifyApplyInfoDlg.collectData = function() {
    this
    .set('applyId')
    .set('passportId')
    .set('applyNumber')
    .set('username')
    .set('idCard')
    .set('idCardPositive')
    .set('idCardNegative')
    .set('idCardHand')
    .set('mobile')
    .set('refuseReason')
    .set('applyStatus')
    .set('applyTime')
    ;
}

/**
 * 提交添加
 */
BizPassportVerifyApplyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassportVerifyApply/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizPassportVerifyApply.table.refresh();
        BizPassportVerifyApplyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportVerifyApplyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizPassportVerifyApplyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassportVerifyApply/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizPassportVerifyApply.table.refresh();
        BizPassportVerifyApplyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPassportVerifyApplyInfoData);
    ajax.start();
}

$(function() {

});

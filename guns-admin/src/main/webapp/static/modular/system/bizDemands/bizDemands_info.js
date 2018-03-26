/**
 * 初始化详情对话框
 */
var BizDemandsInfoDlg = {
    bizDemandsInfoData : {}
};

/**
 * 清除数据
 */
BizDemandsInfoDlg.clearData = function() {
    this.bizDemandsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsInfoDlg.set = function(key, val) {
    this.bizDemandsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizDemandsInfoDlg.close = function() {
    parent.layer.close(window.parent.BizDemands.layerIndex);
}

/**
 * 收集数据
 */
BizDemandsInfoDlg.collectData = function() {
    this
    .set('demandId')
    .set('passportId')
    .set('industry1')
    .set('industry2')
    .set('industry3')
    .set('title')
    .set('priceBudget')
    .set('contentSupplement')
    .set('image')
    .set('label')
    .set('contacts')
    .set('mobile')
    .set('wechat')
    .set('qq')
    .set('mail')
    .set('releaseTime')
    .set('expiryDate')
     .set('status')
    ;
}

/**
 * 提交添加
 */
BizDemandsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemands/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizDemands.table.refresh();
        BizDemandsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizDemandsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemands/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizDemands.table.refresh();
        BizDemandsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsInfoData);
    ajax.start();
}

$(function() {
	

});

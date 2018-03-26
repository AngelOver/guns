/**
 * 初始化详情对话框
 */
var BizDemandsQuoteInfoDlg = {
    bizDemandsQuoteInfoData : {}
};

/**
 * 清除数据
 */
BizDemandsQuoteInfoDlg.clearData = function() {
    this.bizDemandsQuoteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsQuoteInfoDlg.set = function(key, val) {
    this.bizDemandsQuoteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsQuoteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizDemandsQuoteInfoDlg.close = function() {
    parent.layer.close(window.parent.BizDemandsQuote.layerIndex);
}

/**
 * 收集数据
 */
BizDemandsQuoteInfoDlg.collectData = function() {
    this
    .set('id')
    .set('price')
    .set('quoteTime')
    .set('status')
    .set('demandId')
     .set('agentId')
    ;
}

/**
 * 提交添加
 */
BizDemandsQuoteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemandsQuote/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizDemandsQuote.table.refresh();
        BizDemandsQuoteInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsQuoteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizDemandsQuoteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemandsQuote/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizDemandsQuote.table.refresh();
        BizDemandsQuoteInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsQuoteInfoData);
    ajax.start();
}

$(function() {

});

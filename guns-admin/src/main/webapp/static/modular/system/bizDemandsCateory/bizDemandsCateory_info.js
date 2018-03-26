/**
 * 初始化详情对话框
 */
var BizDemandsCateoryInfoDlg = {
    bizDemandsCateoryInfoData : {}
};

/**
 * 清除数据
 */
BizDemandsCateoryInfoDlg.clearData = function() {
    this.bizDemandsCateoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsCateoryInfoDlg.set = function(key, val) {
    this.bizDemandsCateoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizDemandsCateoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizDemandsCateoryInfoDlg.close = function() {
    parent.layer.close(window.parent.BizDemandsCateory.layerIndex);
}

/**
 * 收集数据
 */
BizDemandsCateoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('categoryName')
    .set('parentCategoryId')
    .set('status')
    ;
}

/**
 * 提交添加
 */
BizDemandsCateoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemandsCateory/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizDemandsCateory.table.refresh();
        BizDemandsCateoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsCateoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizDemandsCateoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizDemandsCateory/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizDemandsCateory.table.refresh();
        BizDemandsCateoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizDemandsCateoryInfoData);
    ajax.start();
}

$(function() {

});

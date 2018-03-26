/**
 * 初始化详情对话框
 */
var BizAgentInfoDlg = {
    bizAgentInfoData : {}
};

/**
 * 清除数据
 */
BizAgentInfoDlg.clearData = function() {
    this.bizAgentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentInfoDlg.set = function(key, val) {
    this.bizAgentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizAgentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizAgentInfoDlg.close = function() {
    parent.layer.close(window.parent.BizAgent.layerIndex);
}

/**
 * 收集数据
 */
BizAgentInfoDlg.collectData = function() {
    this
    .set('agentId')
    .set('industry1')
    .set('industry2')
    .set('industry3')
    .set('passportId')
    .set('province')
    .set('city')
    .set('county')
    .set('address')
    .set('lat')
    .set('lng')
    .set('star')
    .set('orderTotalCount')
    .set('isStar')
    .set('agentType')
    .set('name')
    .set('recommender')
    .set('agentStatus')
    .set('applyStatus')
    .set('createdAt')
    ;
}

/**
 * 提交添加
 */
BizAgentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgent/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizAgent.table.refresh();
        BizAgentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizAgentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizAgent/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizAgent.table.refresh();
        BizAgentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizAgentInfoData);
    ajax.start();
}

$(function() {
	
	 $("#isStar").val($("#isStarValue").val());
	 //初始化状态	 
	 $("#agentType").val($("#agentTypeValue").val());
	 $("#applyStatus").val($("#applyStatusValue").val());	
	 $("#agentStatus").val($("#agentStatusValue").val());

});

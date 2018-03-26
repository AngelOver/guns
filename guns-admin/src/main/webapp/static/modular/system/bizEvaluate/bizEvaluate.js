/**
 * 管理初始化
 */
var BizEvaluate = {
    id: "BizEvaluateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizEvaluate.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '评价ID', field: 'evaluateId', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passportId', visible: true, align: 'center', valign: 'middle'},
            {title: '服务ID', field: 'serviceId', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agentId', visible: true, align: 'center', valign: 'middle'},
            {title: '星级', field: 'star', visible: true, align: 'center', valign: 'middle'},
            {title: '评价内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdAt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizEvaluate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizEvaluate.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizEvaluate.openAddBizEvaluate = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizEvaluate/bizEvaluate_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizEvaluate.openBizEvaluateDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizEvaluate/bizEvaluate_update/' + BizEvaluate.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizEvaluate.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizEvaluate/delete", function (data) {
            Feng.success("删除成功!");
            BizEvaluate.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizEvaluateId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizEvaluate.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizEvaluate.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizEvaluate.initColumn();
    var table = new BSTable(BizEvaluate.id, "/bizEvaluate/list", defaultColunms);
    table.setPaginationType("client");
    BizEvaluate.table = table.init();
});

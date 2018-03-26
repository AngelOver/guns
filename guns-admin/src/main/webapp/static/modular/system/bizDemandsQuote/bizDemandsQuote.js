/**
 * 管理初始化
 */
var BizDemandsQuote = {
    id: "BizDemandsQuoteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizDemandsQuote.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '报价主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '报价金额', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '报价时间', field: 'quoteTime', visible: true, align: 'center', valign: 'middle'},
            {title: '状态(0未选择1已选择2已作废3已过期)', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '需求ID', field: 'demandId', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agentId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizDemandsQuote.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizDemandsQuote.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizDemandsQuote.openAddBizDemandsQuote = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizDemandsQuote/bizDemandsQuote_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizDemandsQuote.openBizDemandsQuoteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizDemandsQuote/bizDemandsQuote_update/' + BizDemandsQuote.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizDemandsQuote.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizDemandsQuote/delete", function (data) {
            Feng.success("删除成功!");
            BizDemandsQuote.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizDemandsQuoteId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizDemandsQuote.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizDemandsQuote.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizDemandsQuote.initColumn();
    var table = new BSTable(BizDemandsQuote.id, "/bizDemandsQuote/list", defaultColunms);
    table.setPaginationType("client");
    BizDemandsQuote.table = table.init();
});

/**
 * 管理初始化
 */
var BizOrdersLog = {
    id: "BizOrdersLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizOrdersLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日志ID', field: 'logId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单ID', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passportId', visible: true, align: 'center', valign: 'middle'},
            {title: '操作类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'note', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'createdAt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizOrdersLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizOrdersLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizOrdersLog.openAddBizOrdersLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizOrdersLog/bizOrdersLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizOrdersLog.openBizOrdersLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizOrdersLog/bizOrdersLog_update/' + BizOrdersLog.seItem.logId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizOrdersLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizOrdersLog/delete", function (data) {
            Feng.success("删除成功!");
            BizOrdersLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizOrdersLogId",this.seItem.logId);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizOrdersLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizOrdersLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizOrdersLog.initColumn();
    var table = new BSTable(BizOrdersLog.id, "/bizOrdersLog/list", defaultColunms);
    table.setPaginationType("client");
    BizOrdersLog.table = table.init();
});

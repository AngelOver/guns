/**
 * 管理初始化
 */
var BizOrdersInfo = {
    id: "BizOrdersInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizOrdersInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单ID', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '服务ID', field: 'serviceId', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'serviceName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务数量', field: 'serviceNum', visible: true, align: 'center', valign: 'middle'},
            {title: '服务价格', field: 'servicePrice', visible: true, align: 'center', valign: 'middle'},
            {title: '服务小计', field: 'serviceCount', visible: true, align: 'center', valign: 'middle'},
            {title: '当前状态(0未开始1已完成)', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizOrdersInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizOrdersInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizOrdersInfo.openAddBizOrdersInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizOrdersInfo/bizOrdersInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizOrdersInfo.openBizOrdersInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizOrdersInfo/bizOrdersInfo_update/' + BizOrdersInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizOrdersInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizOrdersInfo/delete", function (data) {
            Feng.success("删除成功!");
            BizOrdersInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizOrdersInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizOrdersInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizOrdersInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizOrdersInfo.initColumn();
    var table = new BSTable(BizOrdersInfo.id, "/bizOrdersInfo/list", defaultColunms);
    table.setPaginationType("client");
    BizOrdersInfo.table = table.init();
});

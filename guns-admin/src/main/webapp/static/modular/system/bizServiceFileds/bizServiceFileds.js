/**
 * 管理初始化
 */
var BizServiceFileds = {
    id: "BizServiceFiledsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizServiceFileds.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '服务字段ID', field: 'filed_id', visible: true, align: 'center', valign: 'middle'},
            {title: '分类名称', field: 'category_name', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '服务字段名', field: 'field_name', visible: true, align: 'center', valign: 'middle'},
            {title: '字段类型(1文本2单选3复选4文件5时间)', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '可选范围', field: 'scale', visible: true, align: 'center', valign: 'middle'},
            {title: '必填', field: 'is_must', visible: true, align: 'center', valign: 'middle'},
            {title: '字段值', field: 'value', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizServiceFileds.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizServiceFileds.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizServiceFileds.openAddBizServiceFileds = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizServiceFileds/bizServiceFileds_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizServiceFileds.openBizServiceFiledsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizServiceFileds/bizServiceFileds_update/' + BizServiceFileds.seItem.filed_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizServiceFileds.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizServiceFileds/delete", function (data) {
            Feng.success("删除成功!");
            BizServiceFileds.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizServiceFiledsId",this.seItem.filed_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizServiceFileds.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizServiceFileds.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizServiceFileds.initColumn();
    var table = new BSTable(BizServiceFileds.id, "/bizServiceFileds/list", defaultColunms);
    table.setPaginationType("client");
    BizServiceFileds.table = table.init();
});

/**
 * 管理初始化
 */
var BizLinks = {
    id: "BizLinksTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizLinks.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'links_id', visible: false, align: 'center', valign: 'middle'},
            {title: '链接名称', field: 'links_name', visible: true, align: 'center', valign: 'middle'},
            {title: '链接路径', field: 'links_url', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizLinks.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	BizLinks.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizLinks.openAddBizLinks = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizLinks/bizLinks_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizLinks.openBizLinksDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizLinks/bizLinks_update/' + BizLinks.seItem.links_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizLinks.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizLinks/delete", function (data) {
            Feng.success("删除成功!");
            BizLinks.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizLinksId",this.seItem.links_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizLinks.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizLinks.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizLinks.initColumn();
    var table = new BSTable(BizLinks.id, "/bizLinks/list", defaultColunms);
    table.setPaginationType("client");
    BizLinks.table = table.init();
});

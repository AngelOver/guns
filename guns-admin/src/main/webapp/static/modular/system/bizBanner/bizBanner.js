/**
 * 管理初始化
 */
var BizBanner = {
    id: "BizBannerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizBanner.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '显示标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '规格', field: 'spec', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '图片URL', field: 'imageUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '代理ID', field: 'agentId', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: 'URL', field: 'url', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizBanner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizBanner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizBanner.openAddBizBanner = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizBanner/bizBanner_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizBanner.openBizBannerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizBanner/bizBanner_update/' + BizBanner.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizBanner.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizBanner/delete", function (data) {
            Feng.success("删除成功!");
            BizBanner.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizBannerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizBanner.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizBanner.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizBanner.initColumn();
    var table = new BSTable(BizBanner.id, "/bizBanner/list", defaultColunms);
    table.setPaginationType("client");
    BizBanner.table = table.init();
});

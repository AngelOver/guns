/**
 * 地址管理管理初始化
 */
var BizRegions = {
    id: "BizRegionsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizRegions.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '上级单位编号', field: 'parentId', visible: true, align: 'center', valign: 'middle'},
            {title: '简称', field: 'shortName', visible: true, align: 'center', valign: 'middle'},
            {title: '层级', field: 'level', visible: true, align: 'center', valign: 'middle'},
            {title: '城市编码', field: 'cityCode', visible: true, align: 'center', valign: 'middle'},
            {title: '邮政编码', field: 'zipCode', visible: true, align: 'center', valign: 'middle'},
            {title: '地址全称', field: 'mergerName', visible: true, align: 'center', valign: 'middle'},
            {title: '纬度', field: 'lng', visible: true, align: 'center', valign: 'middle'},
            {title: '经度', field: 'lat', visible: true, align: 'center', valign: 'middle'},
            {title: '地址拼音', field: 'fullPinyin', visible: true, align: 'center', valign: 'middle'},
            {title: '地址简拼', field: 'pinyin', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizRegions.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizRegions.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加地址管理
 */
BizRegions.openAddBizRegions = function () {
    var index = layer.open({
        type: 2,
        title: '添加地址管理',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizRegions/bizRegions_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看地址管理详情
 */
BizRegions.openBizRegionsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '地址管理详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizRegions/bizRegions_update/' + BizRegions.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除地址管理
 */
BizRegions.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizRegions/delete", function (data) {
            Feng.success("删除成功!");
            BizRegions.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizRegionsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询地址管理列表
 */
BizRegions.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizRegions.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizRegions.initColumn();
    var table = new BSTable(BizRegions.id, "/bizRegions/list", defaultColunms);
    table.setPaginationType("client");
    BizRegions.table = table.init();
});

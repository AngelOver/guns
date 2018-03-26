/**
 * 管理初始化
 */
var BizNewsCategory = {
    id: "BizNewsCategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizNewsCategory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'category_id', visible: false, align: 'center', valign: 'middle'},
            {title: '分类名称', field: 'category_name', visible: true, align: 'center', valign: 'middle'},
            {title: '分类父ID', field: 'parent_category_id', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizNewsCategory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	BizNewsCategory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizNewsCategory.openAddBizNewsCategory = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizNewsCategory/bizNewsCategory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizNewsCategory.openBizNewsCategoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizNewsCategory/bizNewsCategory_update/' + BizNewsCategory.seItem.category_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizNewsCategory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizNewsCategory/delete", function (data) {
            Feng.success("删除成功!");
            BizNewsCategory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizNewsCategoryId",this.seItem.category_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizNewsCategory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizNewsCategory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizNewsCategory.initColumn();
    var table = new BSTable(BizNewsCategory.id, "/bizNewsCategory/list", defaultColunms);
    table.setPaginationType("client");
    BizNewsCategory.table = table.init();
});

/**
 * 管理初始化
 */
var BizBranch = {
    id: "BizBranchTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizBranch.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'branch_id', visible: false, align: 'center', valign: 'middle'},
            {title: '分公司名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '分公司地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '电话号码', field: 'telephone', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizBranch.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	BizBranch.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizBranch.openAddBizBranch = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizBranch/bizBranch_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizBranch.openBizBranchDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizBranch/bizBranch_update/' + BizBranch.seItem.branch_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizBranch.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizBranch/delete", function (data) {
            Feng.success("删除成功!");
            BizBranch.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizBranchId",this.seItem.branch_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizBranch.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizBranch.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizBranch.initColumn();
    var table = new BSTable(BizBranch.id, "/bizBranch/list", defaultColunms);
    table.setPaginationType("client");
    BizBranch.table = table.init();
});

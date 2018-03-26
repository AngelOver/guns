/**
 * 管理初始化
 */
var BizService = {
    id: "BizServiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizService.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '服务ID', field: 'service_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '分类名称', field: 'category_name', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '服务价格', field: 'shop_price', visible: true, align: 'center', valign: 'middle'},
           /* {title: '列表图', field: 'list_pic', visible: true, align: 'center', valign: 'middle'},*/
            {title: '服务详情描述', field: 'detail', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizService.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizService.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizService.openAddBizService = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizService/bizService_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizService.openBizServiceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizService/bizService_update/' + BizService.seItem.service_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizService.delete = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');	
	if(selected[0].status=="禁用"){
		Feng.error("已经禁用!")
		return false;
	}
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizService/delete", function (data) {
            Feng.success("禁用成功!");
            BizService.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizServiceId",this.seItem.service_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizService.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizService.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizService.initColumn();
    var table = new BSTable(BizService.id, "/bizService/list", defaultColunms);
    table.setPaginationType("client");
    BizService.table = table.init();
});

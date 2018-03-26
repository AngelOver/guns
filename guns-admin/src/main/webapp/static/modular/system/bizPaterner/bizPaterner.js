/**
 * 管理初始化
 */
var BizPaterner = {
    id: "BizPaternerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizPaterner.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'paterner_id', visible: false, align: 'center', valign: 'middle'},
            {title: '公司logo', field: 'logo', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {            		  
                    return '<img  src='+"static/img/"+value+' width=50 class="img-rounded" >';  
                } 
            },
            {title: '公司名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '公司链接', field: 'url', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
BizPaterner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	BizPaterner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizPaterner.openAddBizPaterner = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizPaterner/bizPaterner_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizPaterner.openBizPaternerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizPaterner/bizPaterner_update/' + BizPaterner.seItem.paterner_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizPaterner.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizPaterner/delete", function (data) {
            Feng.success("删除成功!");
            BizPaterner.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizPaternerId",this.seItem.paterner_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizPaterner.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizPaterner.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizPaterner.initColumn();
    var table = new BSTable(BizPaterner.id, "/bizPaterner/list", defaultColunms);
    table.setPaginationType("client");
    BizPaterner.table = table.init();
});

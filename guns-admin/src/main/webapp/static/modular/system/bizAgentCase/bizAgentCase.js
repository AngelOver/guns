/**
 * 管理初始化
 */
var BizAgentCase = {
    id: "BizAgentCaseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizAgentCase.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '案例Id', field: 'agent_case_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商Id', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '案例封面', field: 'cover', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {            		  
                    return '<img  src='+"static/img/"+value+' width=50 class="img-rounded" >';  
                } 
            },
            {title: '案例内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '案例标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '相关链接', field: 'link_url', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'created_at', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizAgentCase.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizAgentCase.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizAgentCase.openAddBizAgentCase = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizAgentCase/bizAgentCase_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizAgentCase.openBizAgentCaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizAgentCase/bizAgentCase_update/' + BizAgentCase.seItem.agent_case_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizAgentCase.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizAgentCase/delete", function (data) {
            Feng.success("删除成功!");
            BizAgentCase.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizAgentCaseId",this.seItem.agent_case_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizAgentCase.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizAgentCase.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizAgentCase.initColumn();
    var table = new BSTable(BizAgentCase.id, "/bizAgentCase/list", defaultColunms);
    table.setPaginationType("client");
    BizAgentCase.table = table.init();
});

/**
 * 管理初始化
 */
var BizAgent = {
    id: "BizAgentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizAgent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'agent_id', visible: false, align: 'center', valign: 'middle'},
            {title: '行业1', field: 'industry1', visible: true, align: 'center', valign: 'middle'},
            {title: '行业2', field: 'industry2', visible: true, align: 'center', valign: 'middle'},
            {title: '行业3', field: 'industry3', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passport_id', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '区', field: 'county', visible: true, align: 'center', valign: 'middle'},
            {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '星级', field: 'star', visible: true, align: 'center', valign: 'middle'},
            {title: '接单量', field: 'order_total_count', visible: true, align: 'center', valign: 'middle'},
            {title: '是否明星代理', field: 'is_star', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商类型', field: 'agent_type', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '推荐人', field: 'recommender', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商状态', field: 'agent_status', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'apply_status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'created_at', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizAgent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizAgent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizAgent.openAddBizAgent = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizAgent/bizAgent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizAgent.openBizAgentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizAgent/bizAgent_update/' + BizAgent.seItem.agent_id
        });
        this.layerIndex = index;
    }
};


/**
 * 打开查看代理商详情
 */
BizAgent.info = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizAgent/bizAgent_info/' + BizAgent.seItem.agent_id
        });
        this.layerIndex = index;
    }
};



/**
 * 删除
 */
BizAgent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizAgent/delete", function (data) {
            Feng.success("删除成功!");
            BizPassport.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizAgentId",this.seItem.agent_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizAgent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizAgent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizAgent.initColumn();
    var table = new BSTable(BizAgent.id, "/bizAgent/list", defaultColunms);
    table.setPaginationType("client");
    BizAgent.table = table.init();
});

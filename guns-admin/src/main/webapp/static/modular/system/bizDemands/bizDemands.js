/**
 * 管理初始化
 */
var BizDemands = {
    id: "BizDemandsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizDemands.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '需求ID', field: 'demandId', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passportId', visible: true, align: 'center', valign: 'middle'},
            {title: '行业1', field: 'industry1', visible: true, align: 'center', valign: 'middle'},
            {title: '行业2', field: 'industry2', visible: true, align: 'center', valign: 'middle'},
            {title: '行业3', field: 'industry3', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '价格预算', field: 'priceBudget', visible: true, align: 'center', valign: 'middle'},
            {title: '内容补充', field: 'contentSupplement', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'image', visible: true, align: 'center', valign: 'middle'},
            {title: '标签', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contacts', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '微信号', field: 'wechat', visible: true, align: 'center', valign: 'middle'},
            {title: 'QQ', field: 'qq', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'mail', visible: true, align: 'center', valign: 'middle'},
            {title: '发布时间', field: 'releaseTime', visible: true, align: 'center', valign: 'middle'},
            {title: '有效期', field: 'expiryDate', visible: true, align: 'center', valign: 'middle'},
            {title: '状态(0关闭1开启)', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizDemands.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizDemands.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizDemands.openAddBizDemands = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizDemands/bizDemands_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizDemands.openBizDemandsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizDemands/bizDemands_update/' + BizDemands.seItem.demandId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizDemands.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizDemands/delete", function (data) {
            Feng.success("删除成功!");
            BizDemands.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizDemandsId",this.seItem.demandId);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizDemands.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizDemands.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizDemands.initColumn();
    var table = new BSTable(BizDemands.id, "/bizDemands/list", defaultColunms);
    table.setPaginationType("client");
    BizDemands.table = table.init();
});

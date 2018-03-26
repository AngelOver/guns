/**
 * 管理初始化
 */
var BizCoupons = {
    id: "BizCouponsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizCoupons.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '优惠券ID', field: 'coupon_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券名称', field: 'coupon_name', visible: true, align: 'center', valign: 'middle'},
            {title: '最低消费', field: 'min', visible: true, align: 'center', valign: 'middle'},
            {title: '减免金额', field: 'cost', visible: true, align: 'center', valign: 'middle'},
            {title: '发行量', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '有效期', field: 'expires', visible: true, align: 'center', valign: 'middle'},
            {title: '发布时间', field: 'created_at', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizCoupons.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizCoupons.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizCoupons.openAddBizCoupons = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizCoupons/bizCoupons_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizCoupons.openBizCouponsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizCoupons/bizCoupons_update/' + BizCoupons.seItem.coupon_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizCoupons.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizCoupons/delete", function (data) {
            Feng.success("删除成功!");
            BizCoupons.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizCouponsId",this.seItem.coupon_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizCoupons.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizCoupons.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizCoupons.initColumn();
    var table = new BSTable(BizCoupons.id, "/bizCoupons/list", defaultColunms);
    table.setPaginationType("client");
    BizCoupons.table = table.init();
});

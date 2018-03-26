/**
 * 管理初始化
 */
var BizOrdersCoupon = {
    id: "BizOrdersCouponTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizOrdersCoupon.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单ID', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '会员优惠券ID', field: 'couponUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券ID', field: 'couponId', visible: true, align: 'center', valign: 'middle'},
            {title: '抵扣金额', field: 'cost', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizOrdersCoupon.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizOrdersCoupon.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizOrdersCoupon.openAddBizOrdersCoupon = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizOrdersCoupon/bizOrdersCoupon_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizOrdersCoupon.openBizOrdersCouponDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizOrdersCoupon/bizOrdersCoupon_update/' + BizOrdersCoupon.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizOrdersCoupon.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizOrdersCoupon/delete", function (data) {
            Feng.success("删除成功!");
            BizOrdersCoupon.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizOrdersCouponId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizOrdersCoupon.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizOrdersCoupon.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizOrdersCoupon.initColumn();
    var table = new BSTable(BizOrdersCoupon.id, "/bizOrdersCoupon/list", defaultColunms);
    table.setPaginationType("client");
    BizOrdersCoupon.table = table.init();
});

/**
 * 管理初始化
 */
var BizPassportCoupon = {
    id: "BizPassportCouponTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizPassportCoupon.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '会员优惠券ID', field: 'coupon_user_id', visible: false, align: 'center', valign: 'middle'},
            {title: '优惠券名称', field: 'coupon_name', visible: true, align: 'center', valign: 'middle'},
            {title: '会员名称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '最低消费', field: 'min', visible: true, align: 'center', valign: 'middle'},
            {title: '减免金额', field: 'cost', visible: true, align: 'center', valign: 'middle'},
            {title: '是否使用', field: 'is_used', visible: true, align: 'center', valign: 'middle'},
            {title: '获取时间', field: 'created_at', visible: true, align: 'center', valign: 'middle'},
            {title: '使用时间', field: 'used_at', visible: true, align: 'center', valign: 'middle'},
            {title: '过期时间', field: 'expired_at', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizPassportCoupon.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizPassportCoupon.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizPassportCoupon.openAddBizPassportCoupon = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizPassportCoupon/bizPassportCoupon_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizPassportCoupon.openBizPassportCouponDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizPassportCoupon/bizPassportCoupon_update/' + BizPassportCoupon.seItem.coupon_user_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizPassportCoupon.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizPassportCoupon/delete", function (data) {
            Feng.success("删除成功!");
            BizPassportCoupon.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizPassportCouponId",this.seItem.coupon_user_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizPassportCoupon.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizPassportCoupon.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizPassportCoupon.initColumn();
    var table = new BSTable(BizPassportCoupon.id, "/bizPassportCoupon/list", defaultColunms);
    table.setPaginationType("client");
    BizPassportCoupon.table = table.init();
});

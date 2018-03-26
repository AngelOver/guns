/**
 * 管理初始化
 */
var BizOrders = {
    id: "BizOrdersTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizOrders.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '订单ID', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agentId', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passportId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单编号', field: 'orderNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '订单金额', field: 'totalPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
//            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
//            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle'},
//            {title: '区', field: 'county', visible: true, align: 'center', valign: 'middle'},
//            {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '固定电话', field: 'telphone', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '主体名称(个人或者企业名)', field: 'subject', visible: true, align: 'center', valign: 'middle'},
//            {title: '证件号码(营业执照或者身份证)', field: 'certificateNum', visible: true, align: 'center', valign: 'middle'},
            {title: '推荐人', field: 'recommender', visible: true, align: 'center', valign: 'middle'},
//            {title: '留言', field: 'note', visible: true, align: 'center', valign: 'middle'},
            {title: '是否开票', field: 'invoice', visible: true, align: 'center', valign: 'middle',
            	 formatter:function (value,row,index) {
            		 var str = '';
            		 if(value ==0){
            			 str = '开票';
            		 }else if(value == 1){
            			 str = '不开票';
            		 }
                     return str;  
                 }  
            },
            {title: '优惠1', field: 'cost1', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠2', field: 'cost2', visible: true, align: 'center', valign: 'middle'},
       //     {title: '附加字段1', field: 'ext1', visible: true, align: 'center', valign: 'middle'},
//            {title: '附加字段2', field: 'ext2', visible: true, align: 'center', valign: 'middle'},
            {title: '状态(0未支付1已取消2已支付)', field: 'status', visible: true, align: 'center', valign: 'middle',
            	 formatter:function (value,row,index) {
            		 var str = '';
            		 if(value ==0){
            			 str = '未支付';
            		 }else if(value == 1){
            			 str = '已取消';
            		 }else if(value == 2){
            			 str = '已支付';
            		 }
                     return str;  
                 }  
            },
            {title: '下单时间', field: 'createdAt', visible: true, align: 'center', valign: 'middle'},
            {title: '支付时间', field: 'payedAt', visible: true, align: 'center', valign: 'middle'},
            {title: '完成时间', field: 'completedAt', visible: true, align: 'center', valign: 'middle'},
            {title: '接单时间', field: 'receivedAt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizOrders.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizOrders.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizOrders.openAddBizOrders = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizOrders/bizOrders_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizOrders.openBizOrdersDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '620px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizOrders/bizOrders_update/' + BizOrders.seItem.orderId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizOrders.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizOrders/delete", function (data) {
            Feng.success("删除成功!");
            BizOrders.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizOrdersId",this.seItem.orderId);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizOrders.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizOrders.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizOrders.initColumn();
    var table = new BSTable(BizOrders.id, "/bizOrders/list", defaultColunms);
    table.setPaginationType("client");
    BizOrders.table = table.init();
});

/**
 * 实名认证申请管理初始化
 */
var BizPassportVerifyApply = {
    id: "BizPassportVerifyApplyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizPassportVerifyApply.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '申请ID', field: 'apply_id', visible: true, align: 'center', valign: 'middle'},
            {title: '会员ID', field: 'passport_id', visible: true, align: 'center', valign: 'middle'},
            {title: '申请单号', field: 'apply_number', visible: true, align: 'center', valign: 'middle'},
            {title: '真实姓名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '身份照号码', field: 'id_card', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证正面照', field: 'id_card_positive', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '身份证反面照', field: 'id_card_negative', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            
            {title: '手持身份证照', field: 'id_card_hand', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '驳回理由', field: 'refuse_reason', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'apply_status', visible: true, align: 'center', valign: 'middle'},
            {title: '申请时间', field: 'apply_time', visible: true, align: 'center', valign: 'middle'},
            {title: 'ͨ通过时间', field: 'pass_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

window.operateEvents = {
		'click .img-rounded': function (e, value, row, index) {
			layer.open({
				type: 1,
				title: false,
				closeBtn: 0,
				area: 'Visible ',
				skin: 'layui-layer-nobg', //没有背景色
				shadeClose: true,
				content: '<img src="http://119.23.49.139/'+value+'"/>'
			});
		},
};

/**
 * 检查是否选中
 */
BizPassportVerifyApply.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizPassportVerifyApply.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加实名认证申请
 */
BizPassportVerifyApply.openAddBizPassportVerifyApply = function () {
    var index = layer.open({
        type: 2,
        title: '添加实名认证申请',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizPassportVerifyApply/bizPassportVerifyApply_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看实名认证申请详情
 */
BizPassportVerifyApply.openBizPassportVerifyApplyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实名认证申请详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizPassportVerifyApply/bizPassportVerifyApply_update/' + BizPassportVerifyApply.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除实名认证申请
 */
BizPassportVerifyApply.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizPassportVerifyApply/delete", function (data) {
            Feng.success("删除成功!");
            BizPassportVerifyApply.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizPassportVerifyApplyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询实名认证申请列表
 */
BizPassportVerifyApply.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizPassportVerifyApply.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizPassportVerifyApply.initColumn();
    var table = new BSTable(BizPassportVerifyApply.id, "/bizPassportVerifyApply/list", defaultColunms);
    table.setPaginationType("client");
    BizPassportVerifyApply.table = table.init();
});

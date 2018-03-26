/**
 * 管理初始化
 */
var BizPassport = {
    id: "BizPassportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizPassport.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'passport_id', visible: false, align: 'center', valign: 'middle'},
            {title: '登录账户', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '登录密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '角色', field: 'role', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '头像', field: 'headimg', visible: true, align: 'center', valign: 'middle',
            	  formatter:function (value,row,index) {            		  
                      return '<img  src="http://119.23.49.139/'+value+'" width=50 class="img-rounded" >';  
                  }  
            },
            {title: '手机号码', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '实名认证', field: 'verify', visible: true, align: 'center', valign: 'middle'},
            {title: '积分', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '余额', field: 'balance', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'created_at', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizPassport.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizPassport.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizPassport.openAddBizPassport = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizPassport/bizPassport_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizPassport.openBizPassportDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizPassport/bizPassport_update/' + BizPassport.seItem.passport_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizPassport.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizPassport/delete", function (data) {
            Feng.success("删除成功!");
            BizPassport.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizPassportId",this.seItem.passport_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizPassport.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizPassport.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizPassport.initColumn();
    var table = new BSTable(BizPassport.id, "/bizPassport/list", defaultColunms);
    table.setPaginationType("client");
    BizPassport.table = table.init();
});

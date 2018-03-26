/**
 * 代理商管理初始化
 */
var BizAgentCompany = {
    id: "BizAgentCompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizAgentCompany.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '代理所ID', field: 'agent_company_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理所名称', field: 'agent_company_name', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人姓名', field: 'agent_company_contactor', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人手机号', field: 'agent_company_mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '营业执照号码', field: 'business_license_no', visible: true, align: 'center', valign: 'middle'},
            {title: '营业执照', field: 'business_license', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            
            {title: '组织机构代码证', field: 'organization_code_certificate', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },

            {title: '税务登记证', field: 'tax_registration_certificate', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '开户行', field: 'bank_company', visible: true, align: 'center', valign: 'middle'},
            {title: '卡号', field: 'bank_card_num', visible: true, align: 'center', valign: 'middle'},
            {title: '委托书', field: 'power_of_attorney', visible: true, align: 'center', valign: 'middle'},
            {title: '委托人身份证', field: 'trustee', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },          
            {title: '资格证书1', field: 'agent_certificate1', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '资格证书2', field: 'agent_certificate2', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '公司logo', field: 'company_logo', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
            {title: '企业规模', field: 'company_scale', visible: true, align: 'center', valign: 'middle'},
            {title: '成立时间', field: 'company_build_time', visible: true, align: 'center', valign: 'middle'},
            {title: '是否首页展示', field: 'index_show', visible: true, align: 'center', valign: 'middle'}
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
BizAgentCompany.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizAgentCompany.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理商
 */
BizAgentCompany.openAddBizAgentCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理商',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizAgentCompany/bizAgentCompany_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理商详情
 */
BizAgentCompany.openBizAgentCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理商详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizAgentCompany/bizAgentCompany_update/' + BizAgentCompany.seItem.agent_company_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理商
 */
BizAgentCompany.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizAgentCompany/delete", function (data) {
            Feng.success("删除成功!");
            BizAgentCompany.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizAgentCompanyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询代理商列表
 */
BizAgentCompany.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizAgentCompany.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizAgentCompany.initColumn();
    var table = new BSTable(BizAgentCompany.id, "/bizAgentCompany/list", defaultColunms);
    table.setPaginationType("client");
    BizAgentCompany.table = table.init();
});

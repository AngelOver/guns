/**
 * 代理人列表管理初始化
 */
var BizAgentPerson = {
    id: "BizAgentPersonTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizAgentPerson.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: '代理人ID', field: 'id', visible: true, align: 'center', valign: 'middle'},//
            {title: '代理商名称', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理人姓名 ', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'id_card', visible: true, align: 'center', valign: 'middle'},
//            {title: '身份证正面照', field: 'id_card_positive', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证正面照', field: 'id_card_positive', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
//            {title: '身份证反面照', field: 'id_card_negative', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证反面照', field: 'id_card_negative', visible: true, align: 'center', valign: 'middle',
          	  formatter:function (value,row,index) {   
          		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                    return s 
                },  
              events: 'operateEvents'
          },
//            {title: '资格证书1', field: 'agent_certificate1', visible: true, align: 'center', valign: 'middle'},
            {title: '资格证书1', field: 'agent_certificate1', visible: true, align: 'center', valign: 'middle',
        	  formatter:function (value,row,index) {   
          		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                    return s 
                },  
              events: 'operateEvents'
          },
//            {title: '资格证书2', field: 'agent_certificate2', visible: true, align: 'center', valign: 'middle'},
            {title: '资格证书2', field: 'agent_certificate2', visible: true, align: 'center', valign: 'middle', 
        	  formatter:function (value,row,index) {   
          		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                    return s 
                },  
              events: 'operateEvents'
          },
            {title: '业务介绍', field: 'business_introduce', visible: true, align: 'center', valign: 'middle'},
            {title: '工作年限', field: 'work_year', visible: true, align: 'center', valign: 'middle'},
            {title: '服务客户', field: 'serve_cusomter', visible: true, align: 'center', valign: 'middle'},
            {title: '擅长领域', field: 'good_at', visible: true, align: 'center', valign: 'middle'},
//            {title: '展示照片', field: 'show_picture', visible: true, align: 'center', valign: 'middle'}
            {title: '展示照片', field: 'show_picture', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {   
            		  var s = '<a class = "img-rounded"  href="javascript:void(0)"><img style="width:50px;height:40px;"  src="http://119.23.49.139/'+value+'" /></a>';
                      return s 
                  },  
                events: 'operateEvents'
            },
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
BizAgentPerson.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizAgentPerson.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理人列表
 */
BizAgentPerson.openAddBizAgentPerson = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理人列表',
        area: ['1200px', '820px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizAgentPerson/bizAgentPerson_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理人列表详情
 */
BizAgentPerson.openBizAgentPersonDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理人列表详情',
            area: ['1200px', '820px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizAgentPerson/bizAgentPerson_update/' + BizAgentPerson.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理人列表
 */
BizAgentPerson.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/bizAgentPerson/delete", function (data) {
            Feng.success("删除成功!");
            BizAgentPerson.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizAgentPersonId",BizAgentPerson.seItem.id);
        ajax.start();
    	 }
    }
    Feng.confirm("是否删除代理人?",operation);
};

/**
 * 查询代理人列表列表
 */
BizAgentPerson.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizAgentPerson.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizAgentPerson.initColumn();
    var table = new BSTable(BizAgentPerson.id, "/bizAgentPerson/list", defaultColunms);
    table.setPaginationType("client");
    BizAgentPerson.table = table.init();
});


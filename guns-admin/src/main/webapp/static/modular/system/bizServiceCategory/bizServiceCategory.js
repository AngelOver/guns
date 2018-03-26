/**
 * 管理初始化
 */
var BizServiceCategory = {
    id: "BizServiceCategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizServiceCategory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
           /* {title: '分类ID', field: 'category_id', visible: true, align: 'center', valign: 'middle'},*/
            /*{title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},*/
            {title: '分类名称', field: 'category_name', visible: true, align: 'center', valign: 'middle'},
            {title: '父级分类', field: 'parent_category_id', visible: true, align: 'center', valign: 'middle'},
            {title: '状态(0禁用1启用)', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '分类描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '分类详情', field: 'details', visible: true, align: 'center', valign: 'middle'},
            /*{title: '分类图片', field: 'category_img', visible: true, align: 'center', valign: 'middle',
            	  formatter:function (value,row,index) {            		  
                      return '<img  src='+"static/img/"+value+' width=50 class="img-rounded" >';  
                  }  
            },*/
    ];
};

/**
 * 检查是否选中
 */
BizServiceCategory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizServiceCategory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizServiceCategory.openAddBizServiceCategory = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizServiceCategory/bizServiceCategory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizServiceCategory.openBizServiceCategoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1200px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizServiceCategory/bizServiceCategory_update/' + BizServiceCategory.seItem.category_id
        });
        this.layerIndex = index;
    }
};

/**
 * 分类字段
 */
/*BizServiceCategory.openFiledBizServiceCategory = function () {
	if (this.check()) {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1200px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizServiceCategory/bizServiceCategory_filed/' + BizServiceCategory.seItem.category_id
    });
    this.layerIndex = index;
  }
};*/

/**
 * 删除
 */
BizServiceCategory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizServiceCategory/delete", function (data) {
            Feng.success("删除成功!");
            BizServiceCategory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizServiceCategoryId",this.seItem.category_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizServiceCategory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizServiceCategory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizServiceCategory.initColumn();
    var table = new BSTable(BizServiceCategory.id, "/bizServiceCategory/list", defaultColunms);
    table.setPaginationType("client");
    BizServiceCategory.table = table.init();
});

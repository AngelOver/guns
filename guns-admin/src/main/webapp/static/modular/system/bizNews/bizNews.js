/**
 * 管理初始化
 */
var BizNews = {
    id: "BizNewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizNews.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'news_id', visible: false, align: 'center', valign: 'middle'},
            {title: '分类ID', field: 'category_id', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商ID', field: 'agent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '新闻标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '新闻封面', field: 'cover', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {            		  
                    return '<img  src='+"static/img/"+value+' width=50 class="img-rounded" >';  
                } 
            },
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'apply_status', visible: true, align: 'center', valign: 'middle'},
            {title: '发布时间', field: 'created_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizNews.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	BizNews.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BizNews.openAddBizNews = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1600px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizNews/bizNews_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
BizNews.openBizNewsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['1600px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizNews/bizNews_update/' + BizNews.seItem.news_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BizNews.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizNews/delete", function (data) {
            Feng.success("删除成功!");
            BizNews.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizNewsId",this.seItem.news_id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BizNews.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizNews.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizNews.initColumn();
    var table = new BSTable(BizNews.id, "/bizNews/list", defaultColunms);
    table.setPaginationType("client");
    BizNews.table = table.init();
});

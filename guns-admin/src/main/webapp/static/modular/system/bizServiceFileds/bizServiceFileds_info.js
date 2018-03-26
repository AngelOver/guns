/**
 * 初始化详情对话框
 */
var BizServiceFiledsInfoDlg = {
    bizServiceFiledsInfoData : {},
	count: $("#itemSize").val(),
	dictName: '',			//字典的名称
	mutiString: '',		//拼接字符串内容(拼接字典条目)
	itemTemplate: $("#itemTemplate").html(),
	validateFields: {
		categoryName: {
	        validators: {
	            notEmpty: {
	                message: '分类名称不能是空 '
	            }
	        }
	    },    
	    agentId: {
	        validators: {
	            notEmpty: {
	                message: '代理商不能为空 '
	            }
	        }
	    },    
	    type: {
	        validators: {
	            notEmpty: {
	                message: '字段类型不能是空'
	            },	           
	        }
	    },	 
	    fieldName: {
	        validators: {
	            notEmpty: {
	                message: '字段名不能是空'
	            },	           
	        }
	    },	   
	}
};

/**
 * item获取新的id
 */
BizServiceFiledsInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 添加条目
 */
BizServiceFiledsInfoDlg.addItem = function () {
	var type = $("#type").val();
	//判断是否是单选
	if (type==2) {				
		$("#itemsArea").append(this.itemTemplate);
		$("#dictItem").attr("id", this.newId());
		return;		
	}
	//复选框多个值
	if(type==3){
		$("#itemsArea").append(this.itemTemplate);
		$("#dictItem").attr("id", this.newId());		
		return;	
	}else{		
		var info = $("#type option:selected").html()
		Feng.error("字段类型"+info+"不需要字段值");
		return false;
	}   
};

/**
 * 删除item
 */
BizServiceFiledsInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj.parent().parent().remove();
};

/**
 * 清除数据
 */
BizServiceFiledsInfoDlg.clearData = function() {
    this.bizServiceFiledsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceFiledsInfoDlg.set = function(key, val) {
    this.bizServiceFiledsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceFiledsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizServiceFiledsInfoDlg.close = function() {
    parent.layer.close(window.parent.BizServiceFileds.layerIndex);
}

/**
 * 收集数据
 */
BizServiceFiledsInfoDlg.collectData = function() {
    this 
    .set('filedId')
    .set('categoryId')
    .set('agentId')
    .set('fieldName')
    .set('type')
    .set('sort')
    .set('scale')
    .set('isMust')
    ;
}

/**
 * 点击代理商input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizServiceFiledsInfoDlg.onClickAgent = function (e, treeId, treeNode) {
	
	if (treeNode.isParent) {
		 Feng.error("请选择子分类");
       return false;
	};
	
	$("#agentId_name").attr("value", instance.getSelectedVal());
    $("#agentId").attr("value", treeNode.id);
};

/**
 * 显示代理商选择的树
 *
 * @returns
 */
BizServiceFiledsInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#agentId_name");
    var cityOffset = $("#agentId_name").offset();   
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏代理商选择的树
 */
BizServiceFiledsInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集添加字典的数据
 */
BizServiceFiledsInfoDlg.collectDataValue = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();       
        mutiString = mutiString + (num + ";");
    });
    this.dictName = $("#dictName").val();
    this.mutiString = mutiString;
};

/**
 * 清除为空的item Dom
 */
BizServiceFiledsInfoDlg.clearNullDom = function(){	
	 $("[name='dictItem']").each(function(){    	    		
		 var num = $(this).find("[name='itemNum']").val();  
	        console.log(name);
	        if(num == ''){
	            $(this).remove();
	        }
	  });
};

/**
 * 验证数据是否为空
 */
BizServiceFiledsInfoDlg.validate = function () {	
    $('#bizServiceFiledForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizServiceFiledForm').data("bootstrapValidator").isValid();   
    $('#bizServiceFiledForm').bootstrapValidator('validate');    
    var flag = $('#bizServiceFiledForm').data("bootstrapValidator").isValid();   
    return $('#bizServiceFiledForm').data("bootstrapValidator").isValid();
};

/**
 * 提交添加
 */
BizServiceFiledsInfoDlg.addSubmit = function() {
	
    this.clearData();
    this.collectData();
    this.collectDataValue();
    
    if($("#fieldName").val()==''){
    	 Feng.error("请输入字段名称!");
    	 return;
    }
    
  //验证表单
    if (!this.validate()) {
        return;
    }     

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizServiceFileds/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizServiceFileds.table.refresh();
        BizServiceFiledsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceFiledsInfoData);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
}

/**
 * 提交修改
 */
BizServiceFiledsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    this.collectDataValue();
    
    if($("#fieldName").val()==''){
   	 Feng.error("请输入字段名称!");
   	 return;
   }
    
  //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizServiceFileds/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizServiceFileds.table.refresh();
        BizServiceFiledsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceFiledsInfoData);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
BizServiceFiledsInfoDlg.onClickServiceCate = function (e, treeId, treeNode) {
	 if (treeNode.isParent) {
		 Feng.error("请选择子分类");
         return false;
     }
    $("#categoryName").attr("value", BizServiceFiledsInfoDlg.ztreeInstance.getSelectedVal());
    $("#categoryId").attr("value", treeNode.id);   
    $("#pcodeTreeDiv").hide();
};

/**
 * 显示父级菜单选择的树
 */
BizServiceFiledsInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("category_name", "pcodeTreeDiv", 15, 34);
};

//代理商划入结束
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	BizServiceFiledsInfoDlg.hideDeptSelectTree();
    }
}

$(function() {		
	Feng.initValidator("bizServiceFiledForm", BizServiceFiledsInfoDlg.validateFields);
	 var ztree = new $ZTree("pcodeTree", "/bizServiceCategory/selectServiceCateTreeList");
	    ztree.bindOnClick(BizServiceFiledsInfoDlg.onClickServiceCate);
	    ztree.init();
	    BizServiceFiledsInfoDlg.ztreeInstance = ztree;
	 var ztree2 = new $ZTree("treeDemo", "/bizPassportCoupon/tree");
	    ztree2.bindOnClick(BizServiceFiledsInfoDlg.onClickAgent);
	    ztree2.init();
	    instance = ztree2;
	    
	    //下拉框清空item
	    $("#type").bind("change",function () {  
	    	BizServiceFiledsInfoDlg.clearNullDom();
        })          
       
	    $("#isMust").val($("#isMustValue").val());
	    $("#type").val($("#typeValue").val());

});

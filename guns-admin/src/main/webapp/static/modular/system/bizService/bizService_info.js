/**
 * 初始化详情对话框
 */
var BizServiceInfoDlg = {
    bizServiceInfoData : {},  
    validateFields: {
    	parentName: {
	        validators: {
	            notEmpty: {
	                message: '分类不能为空 '
	            }
	        }
	    },    
	    name: {
	        validators: {
	            notEmpty: {
	                message: '名字不能是空 '
	            }
	        }
	    },    
	    shopPrice: {
	        validators: {
	        	numeric: {
	                message: '请填写正确的价格'
	            },	           
	        }
	    },	   	     	        
	    status: {
	        validators: {
	            notEmpty: {
	                message: '状态不能是空'
	            },	           
	        }
	    },	   	     
	    agentId: {
	        validators: {
	            notEmpty: {
	                message: '代理商不能是空'
	            },	           
	        }
	    },	
	    verify: {
	        validators: {
	            notEmpty: {
	                message: '是否通过审核'
	            },	           
	        }
	    },	   	    
	}
};

/**
 * 清除数据
 */
BizServiceInfoDlg.clearData = function() {
    this.bizServiceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceInfoDlg.set = function(key, val) {
    this.bizServiceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizServiceInfoDlg.close = function() {
    parent.layer.close(window.parent.BizService.layerIndex);
}

/**
 * 收集数据
 */
BizServiceInfoDlg.collectData = function() {
    this
    .set('serviceId')
    .set('agentId')
    .set('categoryId')
    .set('name')
    .set('shopPrice')
    .set('listPic')
    .set('detail')
    .set('status')
    .set('verify')
    ;
}

/**
 * 验证数据是否为空
 */
BizServiceInfoDlg.validate = function () {	
    $('#bizServiceForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizServiceForm').data("bootstrapValidator").isValid();   
    $('#bizServiceForm').bootstrapValidator('validate');    
    var flag = $('#bizServiceForm').data("bootstrapValidator").isValid();   
    return $('#bizServiceForm').data("bootstrapValidator").isValid();
};

/**
 * 点击代理商input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizServiceInfoDlg.onClickAgent = function (e, treeId, treeNode) {
	
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
BizServiceInfoDlg.showDeptSelectTree = function () {
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
BizServiceInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

//代理商划入结束
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	BizServiceInfoDlg.hideDeptSelectTree();
    }
}

/**
 * 提交添加
 */
BizServiceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizService/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizService.table.refresh();
        BizServiceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizServiceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizService/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizService.table.refresh();
        BizServiceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
BizServiceInfoDlg.onClickServiceCate = function (e, treeId, treeNode) {
	if (treeNode.isParent) {
		 Feng.error("请选择子分类");
        return false;
	};
    $("#parentName").attr("value", BizServiceInfoDlg.ztreeInstance.getSelectedVal());
    $("#categoryId").attr("value", treeNode.id);   
    $("#pcodeTreeDiv").hide();      
};

/**
 * 显示父级菜单选择的树
 */
BizServiceInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("category_name", "pcodeTreeDiv", 15, 34);
};

$(function() {
	Feng.initValidator("bizServiceForm", BizServiceInfoDlg.validateFields);
	
	 //服务分类的树
	 var ztree = new $ZTree("pcodeTree", "/bizServiceCategory/selectServiceCateTreeList");
	    ztree.bindOnClick(BizServiceInfoDlg.onClickServiceCate);
	    ztree.init();
	    BizServiceInfoDlg.ztreeInstance = ztree;
	
	 //代理商下拉树	    
	 var ztree2 = new $ZTree("treeDemo", "/bizPassportCoupon/tree");
		 ztree2.bindOnClick(BizServiceInfoDlg.onClickAgent);
		 ztree2.init();
		 instance = ztree2;   
	    
	    $("#status").val($("#statusValue").val());	
	    $("#verify").val($("#verifyValue").val());	
});

/**
 * 初始化详情对话框
 */
var BizServiceCategoryInfoDlg = {
    bizServiceCategoryInfoData : {},
    validateFields: {
    	categoryName: {
	        validators: {
	            notEmpty: {
	                message: '分类名称不能是空 '
	            }
	        }
	    },    
	    parentCategoryId: {
	        validators: {
	            notEmpty: {
	                message: '父类不能是空 '
	            }
	        }
	    },    
	  /*  agentId: {
	        validators: {
	            notEmpty: {
	                message: '代理商不能空'
	            },	           
	        }
	    },	   */
	    describe: {
	        validators: {
	            notEmpty: {
	                message: '请输入描述长度不要超过10个'
	            },	           
	        }
	    },	   
	    details: {
	        validators: {
	            notEmpty: {
	                message: '请输入详情'
	            },	           
	        }
	    },	 
	    categoryImg: {
	        validators: {
	            notEmpty: {
	                message: '请选择图片'
	            },	           
	        }
	    },	   
	}
};

/**
 * 清除数据
 */
BizServiceCategoryInfoDlg.clearData = function() {
    this.bizServiceCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceCategoryInfoDlg.set = function(key, val) {
    this.bizServiceCategoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizServiceCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizServiceCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.BizServiceCategory.layerIndex);
}

/**
 * 收集数据
 */
BizServiceCategoryInfoDlg.collectData = function() {
    this
    .set('categoryId')
    .set('agentId')
    .set('categoryName')
    .set('parentCategoryId')
    .set('status')
    .set('details')
    .set('describe')
    .set('categoryImg')
    ;
}

/**
 * 验证数据是否为空
 */
BizServiceCategoryInfoDlg.validate = function () {	
    $('#bizServiceCategoryForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizServiceCategoryForm').data("bootstrapValidator").isValid();  
    $('#bizServiceCategoryForm').bootstrapValidator('validate');    
    var flag = $('#bizServiceCategoryForm').data("bootstrapValidator").isValid();    
    return $('#bizServiceCategoryForm').data("bootstrapValidator").isValid();
};

/**
 * 点击代理商input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizServiceCategoryInfoDlg.onClickAgent = function (e, treeId, treeNode) {
	
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
BizServiceCategoryInfoDlg.showDeptSelectTree = function () {
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
BizServiceCategoryInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

//代理商划入结束
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	BizServiceCategoryInfoDlg.hideDeptSelectTree();
    }
}

/**
 * 提交添加
 */
BizServiceCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

  //验证是否上传图片   
    
  //验证表单
    if (!this.validate()) {
        return;
    }    
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizServiceCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizServiceCategory.table.refresh();
        BizServiceCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizServiceCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizServiceCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizServiceCategory.table.refresh();
        BizServiceCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizServiceCategoryInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
BizServiceCategoryInfoDlg.onClickServiceCate = function (e, treeId, treeNode) {
	/* if (treeNode.isParent) {
		 Feng.error("请选择子分类");
         return false;
     }*/
    $("#parentCategoryName").attr("value", BizServiceCategoryInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCategoryId").attr("value", treeNode.id);   
    $("#pcodeTreeDiv").hide();
};

/**
 * 显示父级菜单选择的树
 */
BizServiceCategoryInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("category_name", "pcodeTreeDiv", 15, 34);
};

$(function() {
	Feng.initValidator("bizServiceCategoryForm", BizServiceCategoryInfoDlg.validateFields);
	
	//分类树
	 var ztree = new $ZTree("pcodeTree", "/bizServiceCategory/selectServiceCateTreeList");
	    ztree.bindOnClick(BizServiceCategoryInfoDlg.onClickServiceCate);
	    ztree.init();
	    BizServiceCategoryInfoDlg.ztreeInstance = ztree;
	 
	 //代理商下拉树	    
	 /* var ztree2 = new $ZTree("treeDemo", "/bizPassportCoupon/tree");
	    ztree2.bindOnClick(BizServiceCategoryInfoDlg.onClickAgent);
	    ztree2.init();
	    instance = ztree2;
	    
	 $("#status").val($("#statusValue").val());	
	 */
	// 初始化头像上传
	    var avatarUp = new $WebUpload("categoryImg");
	    console.log(avatarUp);
	    avatarUp.setUploadBarId("progressBar");
	    avatarUp.init();
});

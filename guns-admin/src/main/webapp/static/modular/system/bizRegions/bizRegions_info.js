/**
 * 初始化地址管理详情对话框
 */
//var BizRegionsInfoDlg = {
//    bizRegionsInfoData : {},
/**
 * 初始化详情对话框
 */
var BizRegionsInfoDlg = {
	bizRegionsInfoData : {},
    validateFields: {  
	    name: {
	        validators: {
	        	notEmpty: {
	                message: '名称不能为空 '
	            },
	            regexp: {  
                    regexp: "^[\u4e00-\u9fa5]+$",  
                    message: '请输入要添加的省/市/区的名称，例如:XX市或XX区或XX县'  
                }        
	        }
	    },
	    level: {
	        validators: {
	            notEmpty: {
	            	message: '层级不能为空'
	            },
	            regexp: {  
                    regexp:"^[1-3]$", 
	                message: '请输入要添加的省市区的层级(只能为1,2,3)'
	            },	           
	        }
	    },	   
//	    cityCode: {
//	        validators: {
//	            notEmpty: {
//	            	message: '城市编码不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^\d{1,4}$", 
//	                message: '请输入城市编码(区号)'
//	            },	           
//	        }
//	    },	 
//	    zipCode: {
//	        validators: {
//	            notEmpty: {
//	            	message: '邮政编码不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^\d{6}$", 
//	                message: '请输入邮政编码'
//	            },	           
//	        }
//	    },	 
//	    mergerName: {
//	        validators: {
//	            notEmpty: {
//	            	message: '地址全称不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^([\u4e00-\u9fa5]{1,},){0,}([\u4e00-\u9fa5]{1,})$", 
//	                message: '请选择图片'
//	            },	           
//	        }
//	    },	  
//	    lng: {
//	        validators: {
//	            notEmpty: {
//	            	message: '纬度不能为空'
//	            },
//	            regexp: {  
//                    regexp:"^(?:[0-9]|[1-8][0-9]|90)\.([0-9]{6})$", 
//	                message: '请选择图片'
//	            },	           
//	        }
//	    },	  
//	    lat: {
//	        validators: {
//	            notEmpty: {
//	            	message: '经度不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^(?:[0-9]|[1-9][0-9]|1[0-7][0-9]|180)\.([0-9]{6})$", 
//	                message: '请选择图片'
//	            },	           
//	        }
//	    },	  
//	    fullPinyin: {
//	        validators: {
//	            notEmpty: {
//	            	message: '地址拼音不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^[A-Z][a-z]+$", 
//	                message: '请选择图片'
//	            },	           
//	        }
//	    },	 
//	    fullPinyin: {
//	        validators: {
//	            notEmpty: {
//	            	message: '地址简拼不能为空 '
//	            },
//	            regexp: {  
//                    regexp:"^[A-Z]+$", 
//	                message: '请选择图片'
//	            },	           
//	        }
//	    },	
	}
};


/**
 * 清除数据
 */
BizRegionsInfoDlg.clearData = function() {
    this.bizRegionsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizRegionsInfoDlg.set = function(key, val) {
    this.bizRegionsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizRegionsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizRegionsInfoDlg.close = function() {
    parent.layer.close(window.parent.BizRegions.layerIndex);
}

/**
 * 收集数据
 */
BizRegionsInfoDlg.collectData = function() {
    this     
    .set('id')
    .set('name')
    .set('parentId')
    .set('shortName')
    .set('level')
    .set('cityCode')
    .set('zipCode')
    .set('mergerName')
    .set('lng')
    .set('lat')
    .set('fullPinyin')
    ;
}

/**
 * 验证数据是否为空
 */
BizRegionsInfoDlg.validate = function () {	
    $('#bizRegionsForm').data("bootstrapValidator").resetForm();
    var flag1 = $('#bizRegionsForm').data("bootstrapValidator").isValid();   
    $('#bizRegionsForm').bootstrapValidator('validate');    
    var flag = $('#bizRegionsForm').data("bootstrapValidator").isValid();   
    return $('#bizRegionsForm').data("bootstrapValidator").isValid();
};

/**
 * 点击代理商input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BizRegionsInfoDlg.onClickProvince = function (e, treeId, treeNode) {
	
//	if (treeNode.isParent) {
//		 Feng.error("请选择上级单位");
//       return false;
//	};
	
	$("#bigname").attr("value", BizRegionsInfoDlg.provinceinstance.getSelectedVal());
    $("#parentId").attr("value", treeNode.id);
    $("#treeDemoDiv").hide();
//    var id = $("#id").val();
//    console.log(id);
//    var ztree2 = new $ZTree("treeDemo2", "/bizRegions/city?id="+id);
//    ztree2.bindOnClick(BizRegionsInfoDlg.onClickCity);
//    ztree2.init();
//    BizRegionsInfoDlg.cityinstance = ztree2;
};

/**
 * 显示代理商选择的树
 *
 * @returns
 */
BizRegionsInfoDlg.showDeptSelectTreeProvince = function () {
    var cityObj = $("#parentId");
    var cityOffset = $("#parentId").offset();   
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");
    
    Feng.showInputTree("parentId", "treeDemoDiv", 15, 34);
    
//    $("body").bind("mousedown", onBodyDownProvince);         
};

/**
 * 隐藏代理商选择的树
 */
BizRegionsInfoDlg.hideDeptSelectTreeProvince = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDownProvince);// mousedown当鼠标按下就可以触发，不用弹起
    
};

//代理商划入结束
//function onBodyDownProvince(event) {
//    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
//            event.target).parents("#menuContent").length > 0)) {
//    	BizRegionsInfoDlg.hideDeptSelectTreeProvince();
//    }
//}

//BizRegionsInfoDlg.onClickCity = function (e, treeId, treeNode) {
//	
//	if (treeNode.isParent) {
//		 Feng.error("请选择市");
//       return false;
//	};
//	
//	$("#city").attr("value", BizRegionsInfoDlg.cityinstance.getSelectedVal());
//    $("#id").attr("value", treeNode.id);
//    
//    var id = $("#id").val();
//    console.log(id);
//    var ztree3 = new $ZTree("treeDemo3", "/bizRegions/area?id="+id);
//    ztree3.bindOnClick(BizRegionsInfoDlg.onClickArea);
//    ztree3.init();
//    BizRegionsInfoDlg.areainstance = ztree3;
//};
//
///**
// * 显示代理商选择的树
// *
// * @returns
// */
//BizRegionsInfoDlg.showDeptSelectTreeCity = function () {
//    var cityObj = $("#city");
//    var cityOffset = $("#city").offset();   
//    $("#menuContent2").css({
//        left: cityOffset.left + "px",
//        top: cityOffset.top + cityObj.outerHeight() + "px"
//    }).slideDown("fast");
//
//    $("body").bind("mousedown", onBodyDownCity);
//};
//
///**
// * 隐藏代理商选择的树
// */
//BizRegionsInfoDlg.hideDeptSelectTreeCity = function () {
//    $("#menuContent2").fadeOut("fast");
//    $("body").unbind("mousedown", onBodyDownCity);// mousedown当鼠标按下就可以触发，不用弹起
//};
//
////代理商划入结束
//function onBodyDownCity(event) {
//    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent2" || $(
//            event.target).parents("#menuContent2").length > 0)) {
//    	BizRegionsInfoDlg.hideDeptSelectTreeCity();
//    }
//}
//
//BizRegionsInfoDlg.onClickArea = function (e, treeId, treeNode) {
//	
//	if (treeNode.isParent) {
//		 Feng.error("请选择区/县");
//       return false;
//	};
//	
//	$("#area").attr("value", BizRegionsInfoDlg.areainstance.getSelectedVal());
//    $("#id").attr("value", treeNode.id);
////    var id = $("#id").val();
////    console.log(id);
////    var ztree3 = new $ZTree("treeDemo3", "/bizRegions/area?id="+id);
////    ztree3.bindOnClick(BizRegionsInfoDlg.onClickArea);
////    ztree3.init();
////    BizRegionsInfoDlg.areainstance = ztree3;
//};
//
///**
// * 显示代理商选择的树
// *
// * @returns
// */
//BizRegionsInfoDlg.showDeptSelectTreeArea = function () {
//    var cityObj = $("#area");
//    var cityOffset = $("#area").offset();   
//    $("#menuContent3").css({
//        left: cityOffset.left + "px",
//        top: cityOffset.top + cityObj.outerHeight() + "px"
//    }).slideDown("fast");
//
//    $("body").bind("mousedown", onBodyDownArea);
//};
///**
// * 隐藏代理商选择的树
// */
//BizRegionsInfoDlg.hideDeptSelectTreeArea = function () {
//    $("#menuContent3").fadeOut("fast");
//    $("body").unbind("mousedown", onBodyDownArea);// mousedown当鼠标按下就可以触发，不用弹起
//};
//
////代理商划入结束
//function onBodyDownArea(event) {
//    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent3" || $(
//            event.target).parents("#menuContent3").length > 0)) {
//    	BizRegionsInfoDlg.hideDeptSelectTreeArea();
//    }
//}



/**
 * 提交添加
 */
BizRegionsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizRegions/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizRegions.table.refresh();
        BizRegionsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizRegionsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizRegionsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
  //验证表单
    if (!this.validate()) {
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizRegions/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizRegions.table.refresh();
        BizRegionsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizRegionsInfoData);
    ajax.start();
}


$(function() {
	Feng.initValidator("bizRegionsForm", BizRegionsInfoDlg.validateFields);
	
	 var ztree1 = new $ZTree("treeDemo", "/bizRegions/bizRegionsTreeList");
	 	ztree1.bindOnClick(BizRegionsInfoDlg.onClickProvince);
	 	ztree1.init();
	 	BizRegionsInfoDlg.provinceinstance = ztree1;
	/* var ztree2 = new $ZTree("treeDemo2", "/bizRegions/city");
	    ztree2.bindOnClick(BizRegionsInfoDlg.onClickCity);
	    ztree2.init();
	    BizRegionsInfoDlg.cityinstance = ztree2;
	 var ztree3 = new $ZTree("treeDemo3", "/bizRegions/area");
	    ztree3.bindOnClick(BizRegionsInfoDlg.onClickArea);
	    ztree3.init();
	    BizRegionsInfoDlg.areainstance = ztree3;*/
});

package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色相关的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface BizRegionsDao {


	/**
	 * @author denghsuang
     * 获取父级分类
     */
	List<ZTreeNode> RegionsTreeList();
	
	 /**
	   * 服务商首页的顶级分类
	   * data:2018年2月6日 14:28:09
	   * 分类名称  id
	   */
	List<Map<String, Object>> findTopCategoryList();

}

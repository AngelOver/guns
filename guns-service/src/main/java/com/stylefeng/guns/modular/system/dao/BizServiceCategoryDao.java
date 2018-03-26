package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.core.node.ZTreeNode;

/**
 * 分类中的dao
 *
 * @author dengshuang
 * @date 2018年1月19日 16:37:52
 */
public interface BizServiceCategoryDao {

	
	/**
     * 查询服务分类列表
     * 
     * @author denghsuang
     * @Date 2018年1月18日 11:31:07
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

	
	/**
	 * @author denghsuang
     * 获取父级分类
     */
	List<ZTreeNode> ServiceCateTreeList();


	 /**
	   * 服务商首页的顶级分类
	   * data:2018年2月6日 14:28:09
	   * 分类名称  id
	   */
	List<Map<String, Object>> findTopRegionsList();
	
	 /**
	   * 服务商首页的顶级分类
	   * data:2018年2月6日 14:28:09
	   * 分类名称  id
	   */
	List<Map<String, Object>> findTopCategoryList();

}

package com.stylefeng.guns.common.constant.factory;

import com.stylefeng.guns.common.persistence.model.Dict;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 获取用户状态
     */
	Object getStatus(Integer integer);

	/**
     * 判断用户是否认证
     */
	Object getVerify(Integer integer);

	/**
     * 判断角色类型
     */
	Object getRole(Integer integer);
    
	/**
     * 判断是否明星代理
     */
	String getIsStar(Integer is_star);
	
	/**
     * 判断代理商类型
     */
	String getAgentType(Integer agent_type);
	
	/**
    * 判断代理商状态
    */
	String getAgentStatus(Integer agent_status);
	
	/**
    * 判断审核状态
    */
	String getApplyStatus(Integer apply_status);

	/**
	 * 根据代理人主键获得代理人名称
	 */
	Object getAgent_id(Integer integer);

	
	/**
	 * 根据主键 获得父类名称
	 */
	Object getParentCategoryId(Integer integer);

	/**
	 * 获得服务规格类型
	 */
	Object getServiceType(Integer integer);
	
	/**
     * 获取新闻资讯分类状态
     */
	String getNewsStatus(Integer status);

	/**
     * 获取是否值
     */
	Object getWhether(Integer integer);
	/**
     * 获取是否首页展示
     */
	String getIndexShow(Integer index_show);
}

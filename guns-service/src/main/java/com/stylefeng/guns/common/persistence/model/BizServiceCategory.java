package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dengshuang
 * @since 2018-01-19
 */
@TableName("biz_service_category")
public class BizServiceCategory extends Model<BizServiceCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
	@TableId(value="category_id", type= IdType.AUTO)
	private Integer categoryId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 分类名称
     */
	@TableField("category_name")
	private String categoryName;
    /**
     * 父级分类
     */
	@TableField("parent_category_id")
	private Integer parentCategoryId;
    /**
     * 状态(0禁用1启用)
     */
	private Integer status;
    /**
     * 排序
     */
	private Integer sort;
	 /**
     * 详情
     */
	private String details;
	 /**
     * 描述
     */
	private String describe;
	/**
     * 分类图片
     */
	@TableField("category_img")
	private String categoryImg;


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
		
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCategoryImg() {
		return categoryImg;
	}

	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
	}

	@Override
	protected Serializable pkVal() {
		return this.categoryId;
	}

	@Override
	public String toString() {
		return "BizServiceCategory{" +
			"categoryId=" + categoryId +
			", agentId=" + agentId +
			", categoryName=" + categoryName +
			", parentCategoryId=" + parentCategoryId +
			", status=" + status +
			", sort=" + sort +
			", describe=" + describe +
			", details=" + details +
			", categoryImg=" + categoryImg +
			"}";
	}
}

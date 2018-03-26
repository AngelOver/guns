package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 需求分类
 * </p>
 *
 * @author stylefeng
 * @since 2018-02-03
 */
@TableName("biz_demands_cateory")
public class BizDemandsCateory extends Model<BizDemandsCateory> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
	private Integer id;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizDemandsCateory{" +
			"id=" + id +
			", categoryName=" + categoryName +
			", parentCategoryId=" + parentCategoryId +
			", status=" + status +
			", sort=" + sort +
			"}";
	}
}

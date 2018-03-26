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
 * @author stylefeng
 * @since 2018-01-22
 */
@TableName("biz_news_category")
public class BizNewsCategory extends Model<BizNewsCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
	@TableId(value="category_id", type= IdType.AUTO)
	private Integer categoryId;
    /**
     * 分类名称
     */
	@TableField("category_name")
	private String categoryName;
    /**
     * 分类父ID
     */
	@TableField("parent_category_id")
	private Integer parentCategoryId;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 状态:0禁用1启用
     */
	private Integer status;


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.categoryId;
	}

	@Override
	public String toString() {
		return "BizNewsCategory{" +
			"categoryId=" + categoryId +
			", categoryName=" + categoryName +
			", parentCategoryId=" + parentCategoryId +
			", sort=" + sort +
			", status=" + status +
			"}";
	}
}

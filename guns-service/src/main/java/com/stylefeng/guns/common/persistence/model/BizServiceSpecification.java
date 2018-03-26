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
 * @since 2018-03-14
 */
@TableName("biz_service_specification")
public class BizServiceSpecification extends Model<BizServiceSpecification> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 分类id
     */
	@TableField("category_id")
	private Integer categoryId;
    /**
     * 规格名称
     */
	@TableField("field_name")
	private String fieldName;
    /**
     * 规格类型(1文本2单选3复选4文件5时间)
     */
	private Integer type;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 范围
     */
	private String scale;
    /**
     * 是否必选
     */
	@TableField("is_must")
	private Integer isMust;
    /**
     * 代理商
     */
	@TableField("agent_id")
	private Integer agentId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizServiceSpecification{" +
			"id=" + id +
			", categoryId=" + categoryId +
			", fieldName=" + fieldName +
			", type=" + type +
			", sort=" + sort +
			", scale=" + scale +
			", isMust=" + isMust +
			", agentId=" + agentId +
			"}";
	}
}

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
@TableName("biz_service_fileds")
public class BizServiceFileds extends Model<BizServiceFileds> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务字段ID
     */
	@TableId(value="filed_id", type= IdType.AUTO)
	private Integer filedId;
    /**
     * 分类ID
     */
	@TableField("category_id")
	private Integer categoryId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 服务字段名
     */
	@TableField("field_name")
	private String fieldName;
    /**
     * 字段类型(1文本2单选3复选4文件5时间)
     */
	private Integer type;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 可选范围
     */
	private String scale;
    /**
     * 必填
     */
	@TableField("is_must")
	private Integer isMust;


	public Integer getFiledId() {
		return filedId;
	}

	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}

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

	@Override
	protected Serializable pkVal() {
		return this.filedId;
	}

	@Override
	public String toString() {
		return "BizServiceFileds{" +
			"filedId=" + filedId +
			", categoryId=" + categoryId +
			", agentId=" + agentId +
			", fieldName=" + fieldName +
			", type=" + type +
			", sort=" + sort +
			", scale=" + scale +
			", isMust=" + isMust +
			"}";
	}
}

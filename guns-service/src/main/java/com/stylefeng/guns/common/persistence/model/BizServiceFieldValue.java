package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("biz_service_field_value")
public class BizServiceFieldValue extends Model<BizServiceFieldValue> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 服务字段ID
     */
	@TableId(value = "filed_id")
	private Integer filedId;
    /**
     * 值
     */
	private String value;
    /**
     * 字段类型(1文本2单选3复选4文件5时间)
     */
	@TableField("field_type")
	private Integer fieldType;
    /**
     * 字段名
     */
	@TableField("field_name")
	private String fieldName;


	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getFiledId() {
		return filedId;
	}

	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	protected Serializable pkVal() {
		return "";
	}

	@Override
	public String toString() {
		return "BizServiceFieldValue{" +
			"serviceId=" + serviceId +
			", filedId=" + filedId +
			", value=" + value +
			", fieldType=" + fieldType +
			", fieldName=" + fieldName +
			"}";
	}
}

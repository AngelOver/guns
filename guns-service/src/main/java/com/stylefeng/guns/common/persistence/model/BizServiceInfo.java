package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

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
 * @since 2018-02-27
 */
@TableName("biz_service_info")
public class BizServiceInfo extends Model<BizServiceInfo> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 商品id
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 对应字段表主键
     */
	@TableField("service_fileds_id")
	private Integer serviceFiledsId;
    /**
     * 对应的值
     */
	@TableField("service_filed_value")
	private String serviceFiledValue;
    /**
     * 字段名称
     */
	@TableField("service_filed_name")
	private String serviceFiledName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceFiledsId() {
		return serviceFiledsId;
	}

	public void setServiceFiledsId(Integer serviceFiledsId) {
		this.serviceFiledsId = serviceFiledsId;
	}

	public String getServiceFiledValue() {
		return serviceFiledValue;
	}

	public void setServiceFiledValue(String serviceFiledValue) {
		this.serviceFiledValue = serviceFiledValue;
	}

	public String getServiceFiledName() {
		return serviceFiledName;
	}

	public void setServiceFiledName(String serviceFiledName) {
		this.serviceFiledName = serviceFiledName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizServiceInfo{" +
			"id=" + id +
			", serviceId=" + serviceId +
			", serviceFiledsId=" + serviceFiledsId +
			", serviceFiledValue=" + serviceFiledValue +
			", serviceFiledName=" + serviceFiledName +
			"}";
	}
}

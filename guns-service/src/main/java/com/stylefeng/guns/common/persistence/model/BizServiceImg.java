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
@TableName("biz_service_img")
public class BizServiceImg extends Model<BizServiceImg> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 对应商品id
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 是否默认(1是 2否)
     */
	@TableField("is_default")
	private Integer isDefault;
    /**
     * 内容
     */
	private String content;


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

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizServiceImg{" +
			"id=" + id +
			", serviceId=" + serviceId +
			", isDefault=" + isDefault +
			", content=" + content +
			"}";
	}
}

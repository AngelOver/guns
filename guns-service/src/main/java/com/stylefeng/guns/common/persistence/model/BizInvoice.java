package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
 * @since 2018-03-06
 */
@TableName("biz_invoice")
public class BizInvoice extends Model<BizInvoice> {

    private static final long serialVersionUID = 1L;

	@TableId(value="invoice_id", type= IdType.AUTO)
	private Integer invoiceId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
	private String name;
	private Integer type1;
	private Integer type2;
	private String num;
	private String address;
	private Integer type3;
	private String username;
	private String mobile;
    /**
     * 商品名称
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 创建时间
     */
	@TableField("order_create_time")
	private Date orderCreateTime;
    /**
     * 最后申请时间
     */
	@TableField("order_apply_time")
	private Date orderApplyTime;
    /**
     * 发票抬头
     */
	@TableField("invoice_head")
	private String invoiceHead;

	 /**
     * 订单编号
     */
	@TableField("order_num")
	private String orderNum;

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType1() {
		return type1;
	}

	public void setType1(Integer type1) {
		this.type1 = type1;
	}

	public Integer getType2() {
		return type2;
	}

	public void setType2(Integer type2) {
		this.type2 = type2;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType3() {
		return type3;
	}

	public void setType3(Integer type3) {
		this.type3 = type3;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Date getOrderApplyTime() {
		return orderApplyTime;
	}

	public void setOrderApplyTime(Date orderApplyTime) {
		this.orderApplyTime = orderApplyTime;
	}

	public String getInvoiceHead() {
		return invoiceHead;
	}

	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}
		
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.invoiceId;
	}

	@Override
	public String toString() {
		return "BizInvoice{" +
			"invoiceId=" + invoiceId +
			", passportId=" + passportId +
			", name=" + name +
			", type1=" + type1 +
			", type2=" + type2 +
			", num=" + num +
			", address=" + address +
			", type3=" + type3 +
			", username=" + username +
			", mobile=" + mobile +
			", serviceId=" + serviceId +
			", orderCreateTime=" + orderCreateTime +
			", orderApplyTime=" + orderApplyTime +
			", invoiceHead=" + invoiceHead +
			", orderNum=" + orderNum +
			"}";
	}
}

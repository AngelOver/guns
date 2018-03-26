package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2018-01-29
 */
@TableName("biz_coupons")
public class BizCoupons extends Model<BizCoupons> {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券ID
     */
	@TableId(value="coupon_id", type= IdType.AUTO)
	private Integer couponId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 优惠券名称
     */
	@TableField("coupon_name")
	private String couponName;
    /**
     * 最低消费
     */
	private BigDecimal min;
    /**
     * 减免金额
     */
	private BigDecimal cost;
    /**
     * 发行量
     */
	private Integer num;
    /**
     * 有效期
     */
	private String expires;
    /**
     * 发布时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 状态(0禁用1启用)
     */
	private Integer status;
	/**
     * 优惠券编号
     */
	@TableField("coupons_num")
	private String couponsNum;	
	/**
     * 分类id
     */
	@TableField("category_id")
	private Integer categoryId;

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCouponsNum() {
		return couponsNum;
	}

	public void setCouponsNum(String couponsNum) {
		this.couponsNum = couponsNum;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	protected Serializable pkVal() {
		return this.couponId;
	}

	@Override
	public String toString() {
		return "BizCoupons{" +
			"couponId=" + couponId +
			", agentId=" + agentId +
			", couponName=" + couponName +
			", min=" + min +
			", cost=" + cost +
			", num=" + num +
			", expires=" + expires +
			", createdAt=" + createdAt +
			", status=" + status +
			", couponsNum=" + couponsNum +
			", categoryId=" + categoryId +
			"}";
	}
}

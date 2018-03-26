package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单优惠券
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@TableName("biz_orders_coupon")
public class BizOrdersCoupon extends Model<BizOrdersCoupon> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 会员优惠券ID
     */
	@TableField("coupon_user_id")
	private Integer couponUserId;
    /**
     * 优惠券ID
     */
	@TableField("coupon_id")
	private Integer couponId;
    /**
     * 抵扣金额
     */
	private BigDecimal cost;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCouponUserId() {
		return couponUserId;
	}

	public void setCouponUserId(Integer couponUserId) {
		this.couponUserId = couponUserId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizOrdersCoupon{" +
			"id=" + id +
			", orderId=" + orderId +
			", couponUserId=" + couponUserId +
			", couponId=" + couponId +
			", cost=" + cost +
			"}";
	}
}

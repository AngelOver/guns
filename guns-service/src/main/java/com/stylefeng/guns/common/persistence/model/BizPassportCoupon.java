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
 * @since 2018-01-30
 */
@TableName("biz_passport_coupon")
public class BizPassportCoupon extends Model<BizPassportCoupon> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员优惠券ID
     */
	@TableId(value="coupon_user_id", type= IdType.AUTO)
	private Integer couponUserId;
    /**
     * 优惠券ID
     */
	@TableField("coupon_id")
	private Integer couponId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 最低消费
     */
	private BigDecimal min;
    /**
     * 减免金额
     */
	private BigDecimal cost;
    /**
     * 是否使用
     */
	@TableField("is_used")
	private Integer isUsed;
    /**
     * 获取时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 使用时间
     */
	@TableField("used_at")
	private Date usedAt;
    /**
     * 过期时间
     */
	@TableField("expired_at")
	private Date expiredAt;


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

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
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

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(Date usedAt) {
		this.usedAt = usedAt;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.couponUserId;
	}

	@Override
	public String toString() {
		return "BizPassportCoupon{" +
			"couponUserId=" + couponUserId +
			", couponId=" + couponId +
			", passportId=" + passportId +
			", min=" + min +
			", cost=" + cost +
			", isUsed=" + isUsed +
			", createdAt=" + createdAt +
			", usedAt=" + usedAt +
			", expiredAt=" + expiredAt +
			"}";
	}
}

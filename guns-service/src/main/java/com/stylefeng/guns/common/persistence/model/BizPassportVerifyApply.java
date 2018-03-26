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
 * ʵ
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-14
 */
@TableName("biz_passport_verify_apply")
public class BizPassportVerifyApply extends Model<BizPassportVerifyApply> {

    private static final long serialVersionUID = 1L;

    /**
     * 申请ID
     */
	@TableId(value="apply_id", type= IdType.AUTO)
	private Integer applyId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 申请单号
     */
	@TableField("apply_number")
	private String applyNumber;
    /**
     * 真实姓名
     */
	private String username;
    /**
     * 身份照号码
     */
	@TableField("id_card")
	private String idCard;
    /**
     * 身份证正面照
     */
	@TableField("id_card_positive")
	private String idCardPositive;
    /**
     * 身份证反面照
     */
	@TableField("id_card_negative")
	private String idCardNegative;
    /**
     * 手持身份证照
     */
	@TableField("id_card_hand")
	private String idCardHand;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 驳回理由
     */
	@TableField("refuse_reason")
	private String refuseReason;
    /**
     * 审核状态(0未审核1通过2驳回)
     */
	@TableField("apply_status")
	private Integer applyStatus;
    /**
     * 申请时间
     */
	@TableField("apply_time")
	private Date applyTime;
    /**
     * ͨ通过时间
     */
	@TableField("pass_time")
	private Date passTime;


	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardPositive() {
		return idCardPositive;
	}

	public void setIdCardPositive(String idCardPositive) {
		this.idCardPositive = idCardPositive;
	}

	public String getIdCardNegative() {
		return idCardNegative;
	}

	public void setIdCardNegative(String idCardNegative) {
		this.idCardNegative = idCardNegative;
	}

	public String getIdCardHand() {
		return idCardHand;
	}

	public void setIdCardHand(String idCardHand) {
		this.idCardHand = idCardHand;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.applyId;
	}

	@Override
	public String toString() {
		return "BizPassportVerifyApply{" +
			"applyId=" + applyId +
			", passportId=" + passportId +
			", applyNumber=" + applyNumber +
			", username=" + username +
			", idCard=" + idCard +
			", idCardPositive=" + idCardPositive +
			", idCardNegative=" + idCardNegative +
			", idCardHand=" + idCardHand +
			", mobile=" + mobile +
			", refuseReason=" + refuseReason +
			", applyStatus=" + applyStatus +
			", applyTime=" + applyTime +
			", passTime=" + passTime +
			"}";
	}
}

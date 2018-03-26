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
 * 需求
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-30
 */
@TableName("biz_demands")
public class BizDemands extends Model<BizDemands> {

    private static final long serialVersionUID = 1L;

    /**
     * 需求ID
     */
	@TableId(value="demand_id", type= IdType.AUTO)
	private Integer demandId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 行业1
     */
	private Integer industry1;
    /**
     * 行业2
     */
	private Integer industry2;
    /**
     * 行业3
     */
	private Integer industry3;
    /**
     * 标题
     */
	private String title;
    /**
     * 价格预算
     */
	@TableField("price_budget")
	private BigDecimal priceBudget;
    /**
     * 内容补充
     */
	@TableField("content_supplement")
	private String contentSupplement;
    /**
     * 图片
     */
	private String image;
    /**
     * 标签
     */
	private String label;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 微信号
     */
	private String wechat;
    /**
     * QQ
     */
	private String qq;
    /**
     * 邮箱
     */
	private String mail;
    /**
     * 发布时间
     */
	@TableField("release_time")
	private Date releaseTime;
    /**
     * 有效期
     */
	@TableField("expiry_date")
	private Integer expiryDate;
    /**
     * 状态(0关闭1开启)
     */
	private Integer status;


	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public Integer getIndustry1() {
		return industry1;
	}

	public void setIndustry1(Integer industry1) {
		this.industry1 = industry1;
	}

	public Integer getIndustry2() {
		return industry2;
	}

	public void setIndustry2(Integer industry2) {
		this.industry2 = industry2;
	}

	public Integer getIndustry3() {
		return industry3;
	}

	public void setIndustry3(Integer industry3) {
		this.industry3 = industry3;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPriceBudget() {
		return priceBudget;
	}

	public void setPriceBudget(BigDecimal priceBudget) {
		this.priceBudget = priceBudget;
	}

	public String getContentSupplement() {
		return contentSupplement;
	}

	public void setContentSupplement(String contentSupplement) {
		this.contentSupplement = contentSupplement;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Integer expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.demandId;
	}

	@Override
	public String toString() {
		return "BizDemands{" +
			"demandId=" + demandId +
			", passportId=" + passportId +
			", industry1=" + industry1 +
			", industry2=" + industry2 +
			", industry3=" + industry3 +
			", title=" + title +
			", priceBudget=" + priceBudget +
			", contentSupplement=" + contentSupplement +
			", image=" + image +
			", label=" + label +
			", contacts=" + contacts +
			", mobile=" + mobile +
			", wechat=" + wechat +
			", qq=" + qq +
			", mail=" + mail +
			", releaseTime=" + releaseTime +
			", expiryDate=" + expiryDate +
			", status=" + status +
			"}";
	}
}

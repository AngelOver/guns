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
 * @since 2018-01-17
 */
@TableName("biz_passport")
public class BizPassport extends Model<BizPassport> {

    private static final long serialVersionUID = 1L;

	@TableId(value="passport_id", type= IdType.AUTO)
	private Long passportId;
	@TableField("verify_apply_id")
	private Long verifyApplyId;
	@TableField("agent_id")
	private Long agentId;
	@TableField("industry_id")
	private Long industryId;
	private String account;
	private String password;
	private Integer role;
	private String nickname;
	private String headimg;
	private String mobile;
	private String email;
	private Integer sex;
	private Integer verify;
	private Long score;
	private BigDecimal balance;
	private Integer status;
	@TableField("created_at")
	private Date createdAt;
	@TableField("verify_tel")
	private Integer verifyTel;
	@TableField("verify_email")
	private Integer verifyEmail;
	@TableField("verify_password")
	private Integer verifyPassword;
	@TableField("address_id")
	private Integer addressId;
	private String address;
	public Long getPassportId() {
		return passportId;
	}

	public void setPassportId(Long passportId) {
		this.passportId = passportId;
	}

	public Long getVerifyApplyId() {
		return verifyApplyId;
	}

	public void setVerifyApplyId(Long verifyApplyId) {
		this.verifyApplyId = verifyApplyId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}		

	public Integer getVerifyTel() {
		return verifyTel;
	}

	public void setVerifyTel(Integer verifyTel) {
		this.verifyTel = verifyTel;
	}

	public Integer getVerifyEmail() {
		return verifyEmail;
	}

	public void setVerifyEmail(Integer verifyEmail) {
		this.verifyEmail = verifyEmail;
	}

	public Integer getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(Integer verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	@Override
	protected Serializable pkVal() {
		return this.passportId;
	}
		
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "BizPassport{" +
			"passportId=" + passportId +
			", verifyApplyId=" + verifyApplyId +
			", agentId=" + agentId +
			", industryId=" + industryId +
			", account=" + account +
			", password=" + password +
			", role=" + role +
			", nickname=" + nickname +
			", headimg=" + headimg +
			", mobile=" + mobile +
			", email=" + email +
			", sex=" + sex +
			", verify=" + verify +
			", score=" + score +
			", balance=" + balance +
			", status=" + status +
			", createdAt=" + createdAt +
			", verifyTel=" + verifyTel +
			", verifyEmail=" + verifyEmail +
			", verifyPassword=" + verifyPassword +
			", address=" + address +
			", addressId=" + addressId +
			"}";
	}
}

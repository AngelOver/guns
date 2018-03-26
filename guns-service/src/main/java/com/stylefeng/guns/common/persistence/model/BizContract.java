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
@TableName("biz_contract")
public class BizContract extends Model<BizContract> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 合同类型(1公司或者个体商户  2个人)
     */
	@TableField("contract_type")
	private Integer contractType;
    /**
     * 根据contract_type来决定企业名称和姓名
     */
	private String name;
    /**
     * 地址
     */
	private String address;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 联系人电话
     */
	@TableField("contacts_phone")
	private String contactsPhone;
    /**
     * 固定电话
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 代理商id
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 用户id
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizContract{" +
			"id=" + id +
			", contractType=" + contractType +
			", name=" + name +
			", address=" + address +
			", contacts=" + contacts +
			", contactsPhone=" + contactsPhone +
			", phone=" + phone +
			", email=" + email +
			", agentId=" + agentId +
			", passportId=" + passportId +
			", remark=" + remark +
			", createTime=" + createTime +
			"}";
	}
}

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
 * @since 2018-01-25
 */
@TableName("biz_agent_company")
public class BizAgentCompany extends Model<BizAgentCompany> {

    private static final long serialVersionUID = 1L;

	@TableId(value="agent_company_id", type= IdType.AUTO)
	private Integer agentCompanyId;
	@TableField("agent_id")
	private Integer agentId;
	@TableField("agent_company_name")
	private String agentCompanyName;
	@TableField("agent_company_contactor")
	private String agentCompanyContactor;
	@TableField("agent_company_mobile")
	private String agentCompanyMobile;
    /**
     * Ӫҵִ
     */
	@TableField("business_license_no")
	private String businessLicenseNo;
    /**
     * Ӫҵִ
     */
	@TableField("business_license")
	private String businessLicense;
	@TableField("organization_code_certificate")
	private String organizationCodeCertificate;
    /**
     * ˰
     */
	@TableField("tax_registration_certificate")
	private String taxRegistrationCertificate;
	@TableField("bank_company")
	private String bankCompany;
	@TableField("bank_card_num")
	private String bankCardNum;
    /**
     * ί
     */
	@TableField("power_of_attorney")
	private String powerOfAttorney;
    /**
     * ί
     */
	private String trustee;
	@TableField("agent_certificate1")
	private String agentCertificate1;
	@TableField("agent_certificate2")
	private String agentCertificate2;
	@TableField("company_introduce")
	private String companyIntroduce;
	@TableField("company_logo")
	private String companyLogo;
	@TableField("company_scale")
	private Integer companyScale;
	@TableField("company_build_time")
	private Date companyBuildTime;
	
	@TableField("index_show")
	private Integer indexShow;



	public Integer getAgentCompanyId() {
		return agentCompanyId;
	}

	public void setAgentCompanyId(Integer agentCompanyId) {
		this.agentCompanyId = agentCompanyId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getAgentCompanyName() {
		return agentCompanyName;
	}

	public void setAgentCompanyName(String agentCompanyName) {
		this.agentCompanyName = agentCompanyName;
	}

	public String getAgentCompanyContactor() {
		return agentCompanyContactor;
	}

	public void setAgentCompanyContactor(String agentCompanyContactor) {
		this.agentCompanyContactor = agentCompanyContactor;
	}

	public String getAgentCompanyMobile() {
		return agentCompanyMobile;
	}

	public void setAgentCompanyMobile(String agentCompanyMobile) {
		this.agentCompanyMobile = agentCompanyMobile;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getOrganizationCodeCertificate() {
		return organizationCodeCertificate;
	}

	public void setOrganizationCodeCertificate(String organizationCodeCertificate) {
		this.organizationCodeCertificate = organizationCodeCertificate;
	}

	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}

	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}

	public String getBankCompany() {
		return bankCompany;
	}

	public void setBankCompany(String bankCompany) {
		this.bankCompany = bankCompany;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getPowerOfAttorney() {
		return powerOfAttorney;
	}

	public void setPowerOfAttorney(String powerOfAttorney) {
		this.powerOfAttorney = powerOfAttorney;
	}

	public String getTrustee() {
		return trustee;
	}

	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}

	public String getAgentCertificate1() {
		return agentCertificate1;
	}

	public void setAgentCertificate1(String agentCertificate1) {
		this.agentCertificate1 = agentCertificate1;
	}

	public String getAgentCertificate2() {
		return agentCertificate2;
	}

	public void setAgentCertificate2(String agentCertificate2) {
		this.agentCertificate2 = agentCertificate2;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public Integer getCompanyScale() {
		return companyScale;
	}

	public void setCompanyScale(Integer companyScale) {
		this.companyScale = companyScale;
	}

	public Date getCompanyBuildTime() {
		return companyBuildTime;
	}

	public void setCompanyBuildTime(Date companyBuildTime) {
		this.companyBuildTime = companyBuildTime;
	}
	
	public Integer getIndexShow() {
		return indexShow;
	}

	public void setIndexShow(Integer indexShow) {
		this.indexShow = indexShow;
	}

	@Override
	protected Serializable pkVal() {
		return this.agentCompanyId;
	}

	@Override
	public String toString() {
		return "BizAgentCompany [agentCompanyId=" + agentCompanyId
				+ ", agentId=" + agentId + ", agentCompanyName="
				+ agentCompanyName + ", agentCompanyContactor="
				+ agentCompanyContactor + ", agentCompanyMobile="
				+ agentCompanyMobile + ", businessLicenseNo="
				+ businessLicenseNo + ", businessLicense=" + businessLicense
				+ ", organizationCodeCertificate="
				+ organizationCodeCertificate + ", taxRegistrationCertificate="
				+ taxRegistrationCertificate + ", bankCompany=" + bankCompany
				+ ", bankCardNum=" + bankCardNum + ", powerOfAttorney="
				+ powerOfAttorney + ", trustee=" + trustee
				+ ", agentCertificate1=" + agentCertificate1
				+ ", agentCertificate2=" + agentCertificate2
				+ ", companyIntroduce=" + companyIntroduce + ", companyLogo="
				+ companyLogo + ", companyScale=" + companyScale
				+ ", companyBuildTime=" + companyBuildTime + ", indexShow="
				+ indexShow + "]";
	}
}

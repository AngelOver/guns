package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 代理人列表
 * </p>
 *
 * @author liuqunnnn
 * @since 2018-03-03
 */
@TableName("biz_agent_person")
public class BizAgentPerson extends Model<BizAgentPerson> {

    private static final long serialVersionUID = 1L;

    /**
     * 代理人ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 代理人姓名
     */
	private String username;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 身份证号码
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
     * 资格证书1
     */
	@TableField("agent_certificate1")
	private String agentCertificate1;
    /**
     * 资格证书2
     */
	@TableField("agent_certificate2")
	private String agentCertificate2;
    /**
     * 业务介绍
     */
	@TableField("business_introduce")
	private String businessIntroduce;
    /**
     * 工作年限
     */
	@TableField("work_year")
	private Integer workYear;
    /**
     * 服务客户
     */
	@TableField("serve_cusomter")
	private String serveCusomter;
    /**
     * 擅长领域
     */
	@TableField("good_at")
	private String goodAt;
    /**
     * 展示照片
     */
	 /**
     * 测试
     */
	@TableField("show_picture")
	private String showPicture;
	
	 /**
     * 是否首页展示
     */
	@TableField("index_show")
	private Integer indexShow;

	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

	public String getBusinessIntroduce() {
		return businessIntroduce;
	}

	public void setBusinessIntroduce(String businessIntroduce) {
		this.businessIntroduce = businessIntroduce;
	}

	public Integer getWorkYear() {
		return workYear;
	}

	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	public String getServeCusomter() {
		return serveCusomter;
	}

	public void setServeCusomter(String serveCusomter) {
		this.serveCusomter = serveCusomter;
	}

	public String getGoodAt() {
		return goodAt;
	}

	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	public String getShowPicture() {
		return showPicture;
	}

	public void setShowPicture(String showPicture) {
		this.showPicture = showPicture;
	}
	
    
	public Integer getIndexShow() {
		return indexShow;
	}

	public void setIndexShow(Integer indexShow) {
		this.indexShow = indexShow;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizAgentPerson [id=" + id + ", agentId=" + agentId
				+ ", username=" + username + ", mobile=" + mobile + ", idCard="
				+ idCard + ", idCardPositive=" + idCardPositive
				+ ", idCardNegative=" + idCardNegative + ", agentCertificate1="
				+ agentCertificate1 + ", agentCertificate2="
				+ agentCertificate2 + ", businessIntroduce="
				+ businessIntroduce + ", workYear=" + workYear
				+ ", serveCusomter=" + serveCusomter + ", goodAt=" + goodAt
				+ ", showPicture=" + showPicture + ", indexShow=" + indexShow
				+ "]";
	}
}

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
 * @since 2018-01-19
 */
@TableName("biz_agent")
public class BizAgent extends Model<BizAgent> {

	private static final long serialVersionUID = 1L;

	/**
	 * 代理商ID
	 */
	@TableId(value = "agent_id", type = IdType.AUTO)
	private Integer agentId;
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
	 * 会员ID
	 */
	@TableField("passport_id")
	private Integer passportId;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String county;
	private String address;
	private String lat;
	/**
	 * γ
	 */
	private String lng;
	private Integer star;
	@TableField("order_total_count")
	private Integer orderTotalCount;
	@TableField("is_star")
	private Integer isStar;
	@TableField("agent_type")
	private Integer agentType;
	private String name;
	private Integer recommender;
	@TableField("agent_status")
	private Integer agentStatus;
	@TableField("apply_status")
	private Integer applyStatus;
	@TableField("created_at")
	private Date createdAt;

	private Integer recommend;
	private String authentication;
	private String position;
	private String imgUrl;
	private String business;
	private String experience;
	private String customer;
	private String expertise;
	private String example;
	private String example2;
	private String phone;
	private String describe;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getOrderTotalCount() {
		return orderTotalCount;
	}

	public void setOrderTotalCount(Integer orderTotalCount) {
		this.orderTotalCount = orderTotalCount;
	}

	public Integer getIsStar() {
		return isStar;
	}

	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}

	public Integer getAgentType() {
		return agentType;
	}

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRecommender() {
		return recommender;
	}

	public void setRecommender(Integer recommender) {
		this.recommender = recommender;
	}

	public Integer getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExample2() {
		return example2;
	}

	public void setExample2(String example2) {
		this.example2 = example2;
	}
		
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	protected Serializable pkVal() {
		return this.agentId;
	}
		
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "BizAgent{" + "agentId=" + agentId + ", industry1=" + industry1
				+ ", industry2=" + industry2 + ", industry3=" + industry3
				+ ", passportId=" + passportId + ", province=" + province
				+ ", city=" + city + ", county=" + county + ", address="
				+ address + ", lat=" + lat + ", lng=" + lng + ", star=" + star
				+ ", orderTotalCount=" + orderTotalCount + ", isStar=" + isStar
				+ ", agentType=" + agentType + ", name=" + name
				+ ", recommender=" + recommender + ", agentStatus="
				+ agentStatus 
				+ ", applyStatus=" + applyStatus 
				+ ", recommend=" + recommend 
				+ ", authentication=" + authentication 
				+ ", position=" + position 
				+ ", imgUrl=" + imgUrl 
				+ ", business=" + business 
				+ ", experience=" + experience 
				+ ", customer=" + customer 
				+ ", expertise=" + expertise 
				+ ", example=" + example 
				+ ", example2=" + example2 
				+ ", phone=" + phone 
				+ ", describe=" + describe 
				+ ", createdAt="
				+ createdAt + "}";
	}
}

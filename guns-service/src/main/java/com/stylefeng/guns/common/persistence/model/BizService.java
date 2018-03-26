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
 * @since 2018-01-20
 */
@TableName("biz_service")
public class BizService extends Model<BizService> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
	@TableId(value="service_id", type= IdType.AUTO)
	private Integer serviceId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 分类ID
     */
	@TableField("category_id")
	private Integer categoryId;
    /**
     * 服务名称
     */
	private String name;
    /**
     * 服务价格
     */
	@TableField("shop_price")
	private BigDecimal shopPrice;
    /**
     * 列表图
     */
	@TableField("list_pic")
	private String listPic;
    /**
     * 服务详情描述
     */
	private String detail;
    /**
     * 状态(0禁用1启用)
     */
	private Integer status;
	/**
     * 服务详情
     */
	@TableField("service_info")
	private String serviceInfo;
	/**
     * 服务售后
     */
	@TableField("service_customer")
	private String serviceCustomer;
	/**
     * 是否上架
     */ 
	private Integer release;
	/**
     * 发布时间
     */ 
	@TableField("created_at")
	private Date createdAt;
	/**
     * 修改时间
     */ 
	@TableField("update_time")
	private Date updateTime;
	/**
	 * 审核状态
     */ 
	private Integer verify;
	/**
	 * 是否是精选服务
     */ 
	private Integer selected;
	
	/**
	 * 合同主体
     */ 	
	@TableField("contract_select_id")
	private Integer contractSelectId; 
	
	/**
	 * 手机端详情
     */ 
	@TableField("service_info_tel")
	private String serviceInfoTel;

	/**
	 * 关键字
     */ 
	@TableField("goods_key_word")
	private String goodsKeyWord;
	
	/**
	 * 商品副标题
     */ 
	@TableField("goods_sec_title")
	private String goodsSecTitle;
	
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getListPic() {
		return listPic;
	}

	public void setListPic(String listPic) {
		this.listPic = listPic;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}	
	
	public String getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public String getServiceCustomer() {
		return serviceCustomer;
	}

	public void setServiceCustomer(String serviceCustomer) {
		this.serviceCustomer = serviceCustomer;
	}	
	
	public Integer getRelease() {
		return release;
	}

	public void setRelease(Integer release) {
		this.release = release;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	
	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}	

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	@Override
	protected Serializable pkVal() {
		return this.serviceId;
	}	

	public Integer getContractSelectId() {
		return contractSelectId;
	}

	public void setContractSelectId(Integer contractSelectId) {
		this.contractSelectId = contractSelectId;
	}

	public String getServiceInfoTel() {
		return serviceInfoTel;
	}

	public void setServiceInfoTel(String serviceInfoTel) {
		this.serviceInfoTel = serviceInfoTel;
	}
			
	public String getGoodsKeyWord() {
		return goodsKeyWord;
	}

	public void setGoodsKeyWord(String goodsKeyWord) {
		this.goodsKeyWord = goodsKeyWord;
	}
		
	public String getGoodsSecTitle() {
		return goodsSecTitle;
	}

	public void setGoodsSecTitle(String goodsSecTitle) {
		this.goodsSecTitle = goodsSecTitle;
	}

	@Override
	public String toString() {
		return "BizService{" +
			"serviceId=" + serviceId +
			", agentId=" + agentId +
			", categoryId=" + categoryId +
			", name=" + name +
			", shopPrice=" + shopPrice +
			", listPic=" + listPic +
			", detail=" + detail +
			", status=" + status +
			", serviceInfo=" + serviceInfo +
			", serviceCustomer=" + serviceCustomer +
			", release=" + release +
			", createdAt=" + createdAt +
			", updateTime=" + updateTime +
			", verify=" + verify +
			", selected=" + selected +
			", contractSelectId=" + contractSelectId +
			", serviceInfoTel=" + serviceInfoTel +
			", goodsKeyWord=" + goodsKeyWord +
			", goodsSecTitle=" + goodsSecTitle +
			"}";
	}
}

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
 * 需求报价
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-31
 */
@TableName("biz_demands_quote")
public class BizDemandsQuote extends Model<BizDemandsQuote> {

    private static final long serialVersionUID = 1L;

    /**
     * 报价主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 报价金额
     */
	private BigDecimal price;
    /**
     * 报价时间
     */
	@TableField("quote_time")
	private Date quoteTime;
    /**
     * 状态(0未选择1已选择2已作废3已过期)
     */
	private Integer status;
    /**
     * 需求ID
     */
	@TableField("demand_id")
	private Integer demandId;
	
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;

	
	/**
     * 代理商ID
     */
	@TableField("passport_id")
	private Integer passportId;
	
	
	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getQuoteTime() {
		return quoteTime;
	}

	public void setQuoteTime(Date quoteTime) {
		this.quoteTime = quoteTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizDemandsQuote{" +
			"id=" + id +
			", price=" + price +
			", quoteTime=" + quoteTime +
			", status=" + status +
			", demandId=" + demandId +
			", agentId=" + agentId +
			"}";
	}
}

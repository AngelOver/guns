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
 * @since 2018-02-08
 */
@TableName("biz_evaluate")
public class BizEvaluate extends Model<BizEvaluate> {

    private static final long serialVersionUID = 1L;

    /**
     * 评价ID
     */
	@TableId(value="evaluate_id", type= IdType.AUTO)
	private Integer evaluateId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 服务ID
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 星级
     */
	private Integer star;
    /**
     * 评价内容
     */
	private String content;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;
	/**
     * 被评价人id
     */
	@TableField("argued_id")
	private Integer arguedId;


	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}	

	public Integer getArguedId() {
		return arguedId;
	}

	public void setArguedId(Integer arguedId) {
		this.arguedId = arguedId;
	}

	@Override
	protected Serializable pkVal() {
		return this.evaluateId;
	}

	@Override
	public String toString() {
		return "BizEvaluate{" +
			"evaluateId=" + evaluateId +
			", passportId=" + passportId +
			", serviceId=" + serviceId +
			", agentId=" + agentId +
			", star=" + star +
			", content=" + content +
			", createdAt=" + createdAt +
			", arguedId=" + arguedId +
			"}";
	}
}

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
 * @since 2018-02-09
 */
@TableName("biz_agent_case")
public class BizAgentCase extends Model<BizAgentCase> {

    private static final long serialVersionUID = 1L;

	@TableId(value="agent_case_id", type= IdType.AUTO)
	private Integer agentCaseId;
    /**
     * 代理商Id
     */
	@TableField("agent_id")
	private Integer agentId;
    /**
     * 案例封面
     */
	private String cover;
    /**
     * 案例内容
     */
	private String content;
    /**
     * 案例标题
     */
	private String title;
    /**
     * 相关链接
     */
	@TableField("link_url")
	private String linkUrl;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;


	public Integer getAgentCaseId() {
		return agentCaseId;
	}

	public void setAgentCaseId(Integer agentCaseId) {
		this.agentCaseId = agentCaseId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.agentCaseId;
	}

	@Override
	public String toString() {
		return "BizAgentCase{" +
			"agentCaseId=" + agentCaseId +
			", agentId=" + agentId +
			", cover=" + cover +
			", content=" + content +
			", title=" + title +
			", linkUrl=" + linkUrl +
			", createdAt=" + createdAt +
			"}";
	}
}

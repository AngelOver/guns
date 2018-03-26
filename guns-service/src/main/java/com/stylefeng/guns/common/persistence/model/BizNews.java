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
 * @since 2018-01-22
 */
@TableName("biz_news")
public class BizNews extends Model<BizNews> {

    private static final long serialVersionUID = 1L;

	@TableId(value="news_id", type= IdType.AUTO)
	private Integer newsId;
	@TableField("category_id")
	private Integer categoryId;
	@TableField("agent_id")
	private Integer agentId;
	private String title;
	private String cover;
	private String content;
    /**
     * ״̬(0
     */
	private Integer status;
	@TableField("apply_status")
	private Integer applyStatus;
	@TableField("created_time")
	private Date createdTime;


	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.newsId;
	}

	@Override
	public String toString() {
		return "BizNews{" +
			"newsId=" + newsId +
			", categoryId=" + categoryId +
			", agentId=" + agentId +
			", title=" + title +
			", cover=" + cover +
			", content=" + content +
			", status=" + status +
			", applyStatus=" + applyStatus +
			", createdTime=" + createdTime +
			"}";
	}
}

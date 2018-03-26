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
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-29
 */
@TableName("biz_links")
public class BizLinks extends Model<BizLinks> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="links_id", type= IdType.AUTO)
	private Integer linksId;
    /**
     * 连接名称
     */
	@TableField("links_name")
	private String linksName;
    /**
     * 链接url
     */
	@TableField("links_url")
	private String linksUrl;


	public Integer getLinksId() {
		return linksId;
	}

	public void setLinksId(Integer linksId) {
		this.linksId = linksId;
	}

	public String getLinksName() {
		return linksName;
	}

	public void setLinksName(String linksName) {
		this.linksName = linksName;
	}

	public String getLinksUrl() {
		return linksUrl;
	}

	public void setLinksUrl(String linksUrl) {
		this.linksUrl = linksUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.linksId;
	}

	@Override
	public String toString() {
		return "BizLinks{" +
			"linksId=" + linksId +
			", linksName=" + linksName +
			", linksUrl=" + linksUrl +
			"}";
	}
}

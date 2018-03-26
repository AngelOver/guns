package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-26
 */
@TableName("biz_paterner")
public class BizPaterner extends Model<BizPaterner> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="paterner_id", type= IdType.AUTO)
	private Integer paternerId;
    /**
     * 公司logo
     */
	private String logo;
    /**
     * 公司名称
     */
	private String name;
    /**
     * 公司链接
     */
	private String url;


	public Integer getPaternerId() {
		return paternerId;
	}

	public void setPaternerId(Integer paternerId) {
		this.paternerId = paternerId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected Serializable pkVal() {
		return this.paternerId;
	}

	@Override
	public String toString() {
		return "BizPaterner{" +
			"paternerId=" + paternerId +
			", logo=" + logo +
			", name=" + name +
			", url=" + url +
			"}";
	}
}

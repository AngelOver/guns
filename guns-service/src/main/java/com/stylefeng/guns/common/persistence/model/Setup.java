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
 * 系统设置
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-29
 */
@TableName("sys_setup")
public class Setup extends Model<Setup> {

    private static final long serialVersionUID = 1L;

    /**
     * 网站名称
     */
	@TableField("website_name")
	private String websiteName;
    /**
     * LOGO
     */
	private String logo;
    /**
     * 关键字
     */
	private String keyword;
	 /**
     * 描述
     */
	private String describe;
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
     * 地址
     */
	private String address;
	/**
     * 邮箱
     */
	private String email;
	/**
     * 服务热线
     */
	private String hotline;

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}		

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}			

	@Override
	public String toString() {
		return "Setup{" +
			"websiteName=" + websiteName +
			", logo=" + logo +
			", keyword=" + keyword +
			", id=" + id +
			", describe=" + describe +
			", address=" + address +
			", email=" + email +
			", hotline=" + hotline +
			"}";
	}
}

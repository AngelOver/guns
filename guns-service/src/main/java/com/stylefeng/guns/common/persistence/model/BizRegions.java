package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 地址管理
 * </p>
 *
 * @author liuqunnnn
 * @since 2018-03-13
 */
@TableName("biz_regions")
public class BizRegions extends Model<BizRegions> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 父id
     */
	@TableField("parent_id")
	private Integer parentId;
    /**
     * 简称
     */
	@TableField("short_name")
	private String shortName;
    /**
     * 层级
     */
	private Integer level;
    /**
     * 城市编码
     */
	@TableField("city_code")
	private String cityCode;
    /**
     * 邮政编码
     */
	@TableField("zip_code")
	private String zipCode;
    /**
     * 地址全称
     */
	@TableField("merger_name")
	private String mergerName;
    /**
     * 纬度
     */
	private String lng;
    /**
     * 经度
     */
	private String lat;
    /**
     * 地址拼音
     */
	@TableField("full_pinyin")
	private String fullPinyin;
    /**
     * 地址简拼
     */
	private String pinyin;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizRegions{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + parentId +
			", shortName=" + shortName +
			", level=" + level +
			", cityCode=" + cityCode +
			", zipCode=" + zipCode +
			", mergerName=" + mergerName +
			", lng=" + lng +
			", lat=" + lat +
			", fullPinyin=" + fullPinyin +
			", pinyin=" + pinyin +
			"}";
	}
}

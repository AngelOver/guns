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
 * @since 2018-01-30
 */
@TableName("biz_branch")
public class BizBranch extends Model<BizBranch> {

    private static final long serialVersionUID = 1L;

    /**
     * 分公司主键
     */
	@TableId(value="branch_id", type= IdType.AUTO)
	private Integer branchId;
    /**
     * 分公司名称
     */
	private String name;
    /**
     * 分公司地址
     */
	private String address;
    /**
     * 电话号码
     */
	private String telephone;


	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	protected Serializable pkVal() {
		return this.branchId;
	}

	@Override
	public String toString() {
		return "BizBranch{" +
			"branchId=" + branchId +
			", name=" + name +
			", address=" + address +
			", telephone=" + telephone +
			"}";
	}
}

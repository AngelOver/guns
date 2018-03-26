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
 * 订单日志
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@TableName("biz_orders_log")
public class BizOrdersLog extends Model<BizOrdersLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
	@TableId(value="log_id", type= IdType.AUTO)
	private Integer logId;
    /**
     * 订单ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 会员ID
     */
	@TableField("passport_id")
	private Integer passportId;
    /**
     * 操作类型
     */
	private Integer type;
    /**
     * 备注
     */
	private String note;
    /**
     * 操作时间
     */
	@TableField("created_at")
	private Date createdAt;


	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.logId;
	}

	@Override
	public String toString() {
		return "BizOrdersLog{" +
			"logId=" + logId +
			", orderId=" + orderId +
			", passportId=" + passportId +
			", type=" + type +
			", note=" + note +
			", createdAt=" + createdAt +
			"}";
	}
}

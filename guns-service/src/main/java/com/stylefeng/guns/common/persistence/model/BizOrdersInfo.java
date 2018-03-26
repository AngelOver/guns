package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单详情
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@TableName("biz_orders_info")
public class BizOrdersInfo extends Model<BizOrdersInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 服务ID
     */
	@TableField("service_id")
	private Integer serviceId;
    /**
     * 服务名称
     */
	@TableField("service_name")
	private String serviceName;
    /**
     * 服务数量
     */
	@TableField("service_num")
	private Integer serviceNum;
    /**
     * 服务价格
     */
	@TableField("service_price")
	private BigDecimal servicePrice;
    /**
     * 服务小计
     */
	@TableField("service_count")
	private BigDecimal serviceCount;
    /**
     * 当前状态(0未开始1已完成)
     */
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public BigDecimal getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(BigDecimal serviceCount) {
		this.serviceCount = serviceCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BizOrdersInfo{" +
			"id=" + id +
			", orderId=" + orderId +
			", serviceId=" + serviceId +
			", serviceName=" + serviceName +
			", serviceNum=" + serviceNum +
			", servicePrice=" + servicePrice +
			", serviceCount=" + serviceCount +
			", status=" + status +
			"}";
	}
}

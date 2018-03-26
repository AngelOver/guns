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
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-02-08
 */
@TableName("biz_carts")
public class BizCarts extends Model<BizCarts> {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车ID
     */
	@TableId(value="cart_id", type= IdType.AUTO)
	private Integer cartId;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private Integer agentId;
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
     * 服务名称
     */
	@TableField("service_name")
	private String serviceName;
    /**
     * 服务价格
     */
	@TableField("shop_price")
	private BigDecimal shopPrice;
    /**
     * 服务数量
     */
	private Integer num;
    /**
     * 小计
     */
	@TableField("s_total")
	private BigDecimal sTotal;


	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getsTotal() {
		return sTotal;
	}

	public void setsTotal(BigDecimal sTotal) {
		this.sTotal = sTotal;
	}

	@Override
	protected Serializable pkVal() {
		return this.cartId;
	}

	@Override
	public String toString() {
		return "BizCarts{" +
			"cartId=" + cartId +
			", agentId=" + agentId +
			", passportId=" + passportId +
			", serviceId=" + serviceId +
			", serviceName=" + serviceName +
			", shopPrice=" + shopPrice +
			", num=" + num +
			", sTotal=" + sTotal +
			"}";
	}
}

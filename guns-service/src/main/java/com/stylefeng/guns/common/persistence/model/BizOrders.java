package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@TableName("biz_orders")
public class BizOrders extends Model<BizOrders> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
	@TableId(value="order_id", type= IdType.AUTO)
	private Integer orderId;
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
     * 订单编号
     */
	@TableField("order_number")
	private String orderNumber;
    /**
     * 订单金额
     */
	@TableField("total_price")
	private BigDecimal totalPrice;
    /**
     * 联系人
     */
	private String username;
    /**
     * 联系方式
     */
	private String mobile;
    /**
     * 省
     */
	private String province;
    /**
     * 市
     */
	private String city;
    /**
     * 区
     */
	private String county;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 固定电话
     */
	private String telphone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 主体名称(个人或者企业名)
     */
	private String subject;
    /**
     * 证件号码(营业执照或者身份证)
     */
	@TableField("certificate_num")
	private String certificateNum;
    /**
     * 推荐人
     */
	private Integer recommender;
    /**
     * 留言
     */
	private String note;
    /**
     * 是否开票
     */
	private Integer invoice;
    /**
     * 优惠1
     */
	private BigDecimal cost1;
    /**
     * 优惠2
     */
	private BigDecimal cost2;
    /**
     * 附加字段1
     */
	private String ext1;
    /**
     * 附加字段2
     */
	private String ext2;
    /**
     * 状态(0未支付1已取消2已支付)
     */
	private Integer status;
    /**
     * 下单时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 支付时间
     */
	@TableField("payed_at")
	private Date payedAt;
    /**
     * 完成时间
     */
	@TableField("completed_at")
	private Date completedAt;
    /**
     * 接单时间
     */
	@TableField("received_at")
	private Date receivedAt;


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Integer getRecommender() {
		return recommender;
	}

	public void setRecommender(Integer recommender) {
		this.recommender = recommender;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getCost1() {
		return cost1;
	}

	public void setCost1(BigDecimal cost1) {
		this.cost1 = cost1;
	}

	public BigDecimal getCost2() {
		return cost2;
	}

	public void setCost2(BigDecimal cost2) {
		this.cost2 = cost2;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getPayedAt() {
		return payedAt;
	}

	public void setPayedAt(Date payedAt) {
		this.payedAt = payedAt;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

	@Override
	public String toString() {
		return "BizOrders{" +
			"orderId=" + orderId +
			", agentId=" + agentId +
			", passportId=" + passportId +
			", orderNumber=" + orderNumber +
			", totalPrice=" + totalPrice +
			", username=" + username +
			", mobile=" + mobile +
			", province=" + province +
			", city=" + city +
			", county=" + county +
			", address=" + address +
			", telphone=" + telphone +
			", email=" + email +
			", subject=" + subject +
			", certificateNum=" + certificateNum +
			", recommender=" + recommender +
			", note=" + note +
			", invoice=" + invoice +
			", cost1=" + cost1 +
			", cost2=" + cost2 +
			", ext1=" + ext1 +
			", ext2=" + ext2 +
			", status=" + status +
			", createdAt=" + createdAt +
			", payedAt=" + payedAt +
			", completedAt=" + completedAt +
			", receivedAt=" + receivedAt +
			"}";
	}
}

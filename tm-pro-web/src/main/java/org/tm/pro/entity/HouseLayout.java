package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 房源户型信息
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_house_layout")
public class HouseLayout extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 房源ID
     */
	@TableField("house_id")
	private Integer houseId;
    /**
     * 户型名称
     */
	private String name;
    /**
     * 面积
     */
	private BigDecimal acreage;
    /**
     * 价格（总价）
     */
	private BigDecimal price;
    /**
     * 配套设施
     */
	@TableField("support_facility")
	private String supportFacility;
    /**
     * 居室
     */
	@TableField("living_amount")
	private Integer livingAmount;
    /**
     * 厅数
     */
	@TableField("hall_amount")
	private Integer hallAmount;
    /**
     * 厨房数量
     */
	@TableField("kitchen_amount")
	private Integer kitchenAmount;
    /**
     * 卫生间数量
     */
	@TableField("washroom_amount")
	private Integer washroomAmount;
    /**
     * 展示状态
     */
	@TableField("show_status")
	private String showStatus;
    /**
     * 销售状态
     */
	@TableField("sale_status")
	private String saleStatus;
    /**
     * 最后操作人
     */
	private String operator;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAcreage() {
		return acreage;
	}

	public void setAcreage(BigDecimal acreage) {
		this.acreage = acreage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSupportFacility() {
		return supportFacility;
	}

	public void setSupportFacility(String supportFacility) {
		this.supportFacility = supportFacility;
	}

	public Integer getLivingAmount() {
		return livingAmount;
	}

	public void setLivingAmount(Integer livingAmount) {
		this.livingAmount = livingAmount;
	}

	public Integer getHallAmount() {
		return hallAmount;
	}

	public void setHallAmount(Integer hallAmount) {
		this.hallAmount = hallAmount;
	}

	public Integer getKitchenAmount() {
		return kitchenAmount;
	}

	public void setKitchenAmount(Integer kitchenAmount) {
		this.kitchenAmount = kitchenAmount;
	}

	public Integer getWashroomAmount() {
		return washroomAmount;
	}

	public void setWashroomAmount(Integer washroomAmount) {
		this.washroomAmount = washroomAmount;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "HouseLayout{" +
			", id=" + id +
			", houseId=" + houseId +
			", name=" + name +
			", acreage=" + acreage +
			", price=" + price +
			", supportFacility=" + supportFacility +
			", livingAmount=" + livingAmount +
			", hallAmount=" + hallAmount +
			", kitchenAmount=" + kitchenAmount +
			", washroomAmount=" + washroomAmount +
			", showStatus=" + showStatus +
			", saleStatus=" + saleStatus +
			", operator=" + operator +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

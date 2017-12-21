package org.tm.pro.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 房源基础信息表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_house")
public class House extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 房源ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目拼音
	 */
	private String pinyin;
	/**
	 * 项目特色
	 */
	private String feature;
	/**
	 * 类别
	 */
	private String category;
	/**
	 * 物业类型
	 */
	@TableField("property_type")
	private String propertyType;
	/**
	 * 产权
	 */
	@TableField("property_right")
	private String propertyRight;
	/**
	 * （户型）面积范围
	 */
	private String acreage;
	/**
	 * 开盘日期
	 */
	@TableField("open_quotation_date")
	private Date openQuotationDate;
	/**
	 * 交房时间
	 */
	@TableField("handed_house_date")
	private Date handedHouseDate;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * （区）县
	 */
	private String county;
	/**
	 * 项目位置
	 */
	private String location;
	/**
	 * （地理位置）经度
	 */
	private String longitude;
	/**
	 * （地理位置）纬度
	 */
	private String latitude;
	/**
	 * 售楼处地址
	 */
	@TableField("sales_offices_location")
	private String salesOfficesLocation;
	/**
	 * 销售热线
	 */
	@TableField("sales_hotline")
	private String salesHotline;
	/**
	 * 房源均价（单位价格）
	 */
	@TableField("average_price")
	private BigDecimal averagePrice;
	/**
	 * 首付信息
	 */
	@TableField("down_payment")
	private String downPayment;
	/**
	 * 开发商
	 */
	private String developer;
	/**
	 * 施工单位
	 */
	private String builder;
	/**
	 * 物业公司
	 */
	@TableField("property_company")
	private String propertyCompany;
	/**
	 * 规划面积
	 */
	@TableField("planning_area")
	private String planningArea;
	/**
	 * 建筑面积
	 */
	@TableField("covered_area")
	private String coveredArea;
	/**
	 * 停车位信息
	 */
	private String parking;
	/**
	 * 栋数
	 */
	@TableField("building_amount")
	private Integer buildingAmount;
	/**
	 * 户数
	 */
	@TableField("house_amount")
	private Integer houseAmount;
	/**
	 * 装修状况
	 */
	private String decoration;
	/**
	 * 容积率
	 */
	@TableField("plot_ratio")
	private String plotRatio;
	/**
	 * 绿化率
	 */
	@TableField("greening_ratio")
	private String greeningRatio;
	/**
	 * 销售状态
	 */
	@TableField("sale_status")
	private String saleStatus;
	/**
	 * 展示状态
	 */
	@TableField("show_status")
	private String showStatus;

	@Version
	@TableField("version")
	private Integer version;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyRight() {
		return propertyRight;
	}

	public void setPropertyRight(String propertyRight) {
		this.propertyRight = propertyRight;
	}

	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	public Date getOpenQuotationDate() {
		return openQuotationDate;
	}

	public void setOpenQuotationDate(Date openQuotationDate) {
		this.openQuotationDate = openQuotationDate;
	}

	public Date getHandedHouseDate() {
		return handedHouseDate;
	}

	public void setHandedHouseDate(Date handedHouseDate) {
		this.handedHouseDate = handedHouseDate;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getSalesOfficesLocation() {
		return salesOfficesLocation;
	}

	public void setSalesOfficesLocation(String salesOfficesLocation) {
		this.salesOfficesLocation = salesOfficesLocation;
	}

	public String getSalesHotline() {
		return salesHotline;
	}

	public void setSalesHotline(String salesHotline) {
		this.salesHotline = salesHotline;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}

	public String getPlanningArea() {
		return planningArea;
	}

	public void setPlanningArea(String planningArea) {
		this.planningArea = planningArea;
	}

	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public Integer getBuildingAmount() {
		return buildingAmount;
	}

	public void setBuildingAmount(Integer buildingAmount) {
		this.buildingAmount = buildingAmount;
	}

	public Integer getHouseAmount() {
		return houseAmount;
	}

	public void setHouseAmount(Integer houseAmount) {
		this.houseAmount = houseAmount;
	}

	public String getDecoration() {
		return decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	public String getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}

	public String getGreeningRatio() {
		return greeningRatio;
	}

	public void setGreeningRatio(String greeningRatio) {
		this.greeningRatio = greeningRatio;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		return "House [id=" + id + ", name=" + name + ", pinyin=" + pinyin + ", feature=" + feature + ", category="
				+ category + ", propertyType=" + propertyType + ", propertyRight=" + propertyRight + ", acreage="
				+ acreage + ", openQuotationDate=" + openQuotationDate + ", handedHouseDate=" + handedHouseDate
				+ ", province=" + province + ", city=" + city + ", county=" + county + ", location=" + location
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", salesOfficesLocation="
				+ salesOfficesLocation + ", salesHotline=" + salesHotline + ", averagePrice=" + averagePrice
				+ ", downPayment=" + downPayment + ", developer=" + developer + ", builder=" + builder
				+ ", propertyCompany=" + propertyCompany + ", planningArea=" + planningArea + ", coveredArea="
				+ coveredArea + ", parking=" + parking + ", buildingAmount=" + buildingAmount + ", houseAmount="
				+ houseAmount + ", decoration=" + decoration + ", plotRatio=" + plotRatio + ", greeningRatio="
				+ greeningRatio + ", saleStatus=" + saleStatus + ", showStatus=" + showStatus + ", version=" + version
				+ ", operator=" + operator + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

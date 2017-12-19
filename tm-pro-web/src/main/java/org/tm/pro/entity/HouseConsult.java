package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 房源预约咨询信息表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_house_consult")
public class HouseConsult extends BaseEntity {

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
     * 用户名称
     */
	@TableField("user_name")
	private String userName;
    /**
     * 手机号
     */
	@TableField("phone_no")
	private String phoneNo;
    /**
     * 回访状态
     */
	@TableField("return_visit_status")
	private String returnVisitStatus;
    /**
     * 回访结果
     */
	@TableField("return_visit_result")
	private String returnVisitResult;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getReturnVisitStatus() {
		return returnVisitStatus;
	}

	public void setReturnVisitStatus(String returnVisitStatus) {
		this.returnVisitStatus = returnVisitStatus;
	}

	public String getReturnVisitResult() {
		return returnVisitResult;
	}

	public void setReturnVisitResult(String returnVisitResult) {
		this.returnVisitResult = returnVisitResult;
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
		return "HouseConsult{" +
			", id=" + id +
			", houseId=" + houseId +
			", userName=" + userName +
			", phoneNo=" + phoneNo +
			", returnVisitStatus=" + returnVisitStatus +
			", returnVisitResult=" + returnVisitResult +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

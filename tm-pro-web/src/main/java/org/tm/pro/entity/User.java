package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 登陆名称
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 登陆密码
     */
	@TableField("login_pass")
	private String loginPass;
    /**
     * 用户名称
     */
	private String username;
    /**
     * 组织ID
     */
	@TableField("organization_id")
	private Integer organizationId;
    /**
     * 用户性别 F(Female,女),M(Male,男)
     */
	private String gender;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 手机
     */
	private String phone;
    /**
     * 电话
     */
	private String telephone;
    /**
     * 账号锁定状态(L:锁定,N:正常)
     */
	@TableField("locked_state")
	private String lockedState;
    /**
     * 账号禁用状态(D:禁用,N:正常)
     */
	@TableField("disabled_state")
	private String disabledState;
    /**
     * 用户账号过期日期(0:永不过期)
     */
	@TableField("expired_time")
	private Long expiredTime;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Long createdTime;
    /**
     * 最后更新时间
     */
	@TableField("updated_time")
	private Long updatedTime;
    /**
     * 最后登陆时间
     */
	@TableField("last_login_time")
	private Long lastLoginTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLockedState() {
		return lockedState;
	}

	public void setLockedState(String lockedState) {
		this.lockedState = lockedState;
	}

	public String getDisabledState() {
		return disabledState;
	}

	public void setDisabledState(String disabledState) {
		this.disabledState = disabledState;
	}

	public Long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "User{" +
			", id=" + id +
			", loginName=" + loginName +
			", loginPass=" + loginPass +
			", username=" + username +
			", organizationId=" + organizationId +
			", gender=" + gender +
			", email=" + email +
			", phone=" + phone +
			", telephone=" + telephone +
			", lockedState=" + lockedState +
			", disabledState=" + disabledState +
			", expiredTime=" + expiredTime +
			", createdTime=" + createdTime +
			", updatedTime=" + updatedTime +
			", lastLoginTime=" + lastLoginTime +
			"}";
	}
}

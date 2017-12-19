package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 登录日志记录表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_login_log")
public class LoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 登录名称
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 登录密码
     */
	@TableField("login_pass")
	private String loginPass;
    /**
     * 操作系统
     */
	@TableField("operating_system")
	private String operatingSystem;
    /**
     * 浏览器
     */
	private String browser;
    /**
     * 浏览器版本
     */
	@TableField("browser_version")
	private String browserVersion;
    /**
     * 登录结果
     */
	@TableField("login_result")
	private String loginResult;
    /**
     * 登录时间
     */
	@TableField("login_time")
	private Date loginTime;


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

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "LoginLog{" +
			", id=" + id +
			", loginName=" + loginName +
			", loginPass=" + loginPass +
			", operatingSystem=" + operatingSystem +
			", browser=" + browser +
			", browserVersion=" + browserVersion +
			", loginResult=" + loginResult +
			", loginTime=" + loginTime +
			"}";
	}
}

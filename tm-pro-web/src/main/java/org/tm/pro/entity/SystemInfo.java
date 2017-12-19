package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统信息表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_system_info")
public class SystemInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 系统标题
     */
	@TableField("system_title")
	private String systemTitle;
    /**
     * 系统描述
     */
	@TableField("system_descript")
	private String systemDescript;
    /**
     * 是否启动登录限制
     */
	@TableField("login_fail_limit")
	private String loginFailLimit;
    /**
     * 允许同一账号错误登录次数
     */
	@TableField("login_fail_count")
	private Integer loginFailCount;
    /**
     * 超过登录次数限制时间（单位：秒）
     */
	@TableField("login_fail_expired")
	private Integer loginFailExpired;
    /**
     * 是否只允许Chrome浏览器登录
     */
	@TableField("only_chrome")
	private String onlyChrome;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSystemTitle() {
		return systemTitle;
	}

	public void setSystemTitle(String systemTitle) {
		this.systemTitle = systemTitle;
	}

	public String getSystemDescript() {
		return systemDescript;
	}

	public void setSystemDescript(String systemDescript) {
		this.systemDescript = systemDescript;
	}

	public String getLoginFailLimit() {
		return loginFailLimit;
	}

	public void setLoginFailLimit(String loginFailLimit) {
		this.loginFailLimit = loginFailLimit;
	}

	public Integer getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(Integer loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public Integer getLoginFailExpired() {
		return loginFailExpired;
	}

	public void setLoginFailExpired(Integer loginFailExpired) {
		this.loginFailExpired = loginFailExpired;
	}

	public String getOnlyChrome() {
		return onlyChrome;
	}

	public void setOnlyChrome(String onlyChrome) {
		this.onlyChrome = onlyChrome;
	}

	@Override
	public String toString() {
		return "SystemInfo{" +
			", id=" + id +
			", systemTitle=" + systemTitle +
			", systemDescript=" + systemDescript +
			", loginFailLimit=" + loginFailLimit +
			", loginFailCount=" + loginFailCount +
			", loginFailExpired=" + loginFailExpired +
			", onlyChrome=" + onlyChrome +
			"}";
	}
}

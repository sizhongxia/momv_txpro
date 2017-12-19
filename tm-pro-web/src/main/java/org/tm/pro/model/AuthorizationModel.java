package org.tm.pro.model;

import java.io.Serializable;

public class AuthorizationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;

	private Integer pid;

	private String moduleName;

	private String moduleIntroduce;

	private String authorizationCode;

	private boolean checked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleIntroduce() {
		return moduleIntroduce;
	}

	public void setModuleIntroduce(String moduleIntroduce) {
		this.moduleIntroduce = moduleIntroduce;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}

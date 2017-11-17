package org.tm.pro.entity;

import java.io.Serializable;

public class Authorization implements Serializable {
    private Integer id;

    private Integer pid;

    private String moduleName;

    private String moduleIntroduce;

    private String authorizationCode;

    private static final long serialVersionUID = 1L;

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
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleIntroduce() {
        return moduleIntroduce;
    }

    public void setModuleIntroduce(String moduleIntroduce) {
        this.moduleIntroduce = moduleIntroduce == null ? null : moduleIntroduce.trim();
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode == null ? null : authorizationCode.trim();
    }
}
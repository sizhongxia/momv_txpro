package org.tm.pro.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String loginName;

    private String loginPass;

    private String username;

    private String gender;

    private String lockedState;

    private String disabledState;

    private Long expiredTime;

    private Long createdTime;

    private Long updatedTime;

    private Long lastLoginTime;

    private static final long serialVersionUID = 1L;

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
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass == null ? null : loginPass.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getLockedState() {
        return lockedState;
    }

    public void setLockedState(String lockedState) {
        this.lockedState = lockedState == null ? null : lockedState.trim();
    }

    public String getDisabledState() {
        return disabledState;
    }

    public void setDisabledState(String disabledState) {
        this.disabledState = disabledState == null ? null : disabledState.trim();
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
}
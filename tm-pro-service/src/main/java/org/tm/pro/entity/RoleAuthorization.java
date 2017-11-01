package org.tm.pro.entity;

import java.io.Serializable;

public class RoleAuthorization implements Serializable {
    private Integer id;

    private Integer roleId;

    private String premissionWord;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPremissionWord() {
        return premissionWord;
    }

    public void setPremissionWord(String premissionWord) {
        this.premissionWord = premissionWord == null ? null : premissionWord.trim();
    }
}
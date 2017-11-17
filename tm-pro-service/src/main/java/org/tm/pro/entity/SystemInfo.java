package org.tm.pro.entity;

import java.io.Serializable;

public class SystemInfo implements Serializable {
    private Integer id;

    private String systemTitle;

    private String systemDescript;

    private String loginFailLimit;

    private Integer loginFailCount;

    private Integer loginFailExpired;

    private String onlyChrome;

    private static final long serialVersionUID = 1L;

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
        this.systemTitle = systemTitle == null ? null : systemTitle.trim();
    }

    public String getSystemDescript() {
        return systemDescript;
    }

    public void setSystemDescript(String systemDescript) {
        this.systemDescript = systemDescript == null ? null : systemDescript.trim();
    }

    public String getLoginFailLimit() {
        return loginFailLimit;
    }

    public void setLoginFailLimit(String loginFailLimit) {
        this.loginFailLimit = loginFailLimit == null ? null : loginFailLimit.trim();
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
        this.onlyChrome = onlyChrome == null ? null : onlyChrome.trim();
    }
}
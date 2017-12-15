package org.tm.pro.entity;

import java.io.Serializable;

public class Dictionary implements Serializable {
    private Integer id;

    private String name;

    private String visitCode;

    private String remarks;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getVisitCode() {
        return visitCode;
    }

    public void setVisitCode(String visitCode) {
        this.visitCode = visitCode == null ? null : visitCode.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}
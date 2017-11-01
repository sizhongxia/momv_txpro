package org.tm.pro.entity;

import java.io.Serializable;

public class UserAuthorization implements Serializable {
    private Integer id;

    private Integer userId;

    private String premissionWord;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPremissionWord() {
        return premissionWord;
    }

    public void setPremissionWord(String premissionWord) {
        this.premissionWord = premissionWord == null ? null : premissionWord.trim();
    }
}
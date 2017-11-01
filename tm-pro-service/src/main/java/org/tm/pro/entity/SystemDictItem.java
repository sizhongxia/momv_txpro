package org.tm.pro.entity;

import java.io.Serializable;

public class SystemDictItem implements Serializable {
    private Integer id;

    private Integer dictId;

    private String itemName;

    private String itemValue;

    private Integer itemSort;

    private String usingState;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public Integer getItemSort() {
        return itemSort;
    }

    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    public String getUsingState() {
        return usingState;
    }

    public void setUsingState(String usingState) {
        this.usingState = usingState == null ? null : usingState.trim();
    }
}
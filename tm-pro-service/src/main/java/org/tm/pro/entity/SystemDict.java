package org.tm.pro.entity;

import java.io.Serializable;

public class SystemDict implements Serializable {
    private Integer id;

    private Integer dictSort;

    private String dictCode;

    private String dictExplain;

    private Integer itemLength;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictExplain() {
        return dictExplain;
    }

    public void setDictExplain(String dictExplain) {
        this.dictExplain = dictExplain == null ? null : dictExplain.trim();
    }

    public Integer getItemLength() {
        return itemLength;
    }

    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }
}
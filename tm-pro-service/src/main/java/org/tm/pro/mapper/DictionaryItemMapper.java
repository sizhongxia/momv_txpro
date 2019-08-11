package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.DictionaryItem;
import org.tm.pro.entity.DictionaryItemExample;

public interface DictionaryItemMapper {
    long countByExample(DictionaryItemExample example);

    int deleteByExample(DictionaryItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictionaryItem record);

    int insertSelective(DictionaryItem record);

    List<DictionaryItem> selectByExample(DictionaryItemExample example);

    DictionaryItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictionaryItem record, @Param("example") DictionaryItemExample example);

    int updateByExample(@Param("record") DictionaryItem record, @Param("example") DictionaryItemExample example);

    int updateByPrimaryKeySelective(DictionaryItem record);

    int updateByPrimaryKey(DictionaryItem record);
}
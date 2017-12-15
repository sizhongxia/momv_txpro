package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryExample;

public interface DictionaryMapper {
    long countByExample(DictionaryExample example);

    int deleteByExample(DictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    List<Dictionary> selectByExample(DictionaryExample example);

    Dictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dictionary record, @Param("example") DictionaryExample example);

    int updateByExample(@Param("record") Dictionary record, @Param("example") DictionaryExample example);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}
package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.SystemDict;
import org.tm.pro.entity.SystemDictExample;

public interface SystemDictMapper {
    long countByExample(SystemDictExample example);

    int deleteByExample(SystemDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemDict record);

    int insertSelective(SystemDict record);

    List<SystemDict> selectByExample(SystemDictExample example);

    SystemDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

    int updateByExample(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

    int updateByPrimaryKeySelective(SystemDict record);

    int updateByPrimaryKey(SystemDict record);
}
package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.SystemDictItem;
import org.tm.pro.entity.SystemDictItemExample;

public interface SystemDictItemMapper {
    long countByExample(SystemDictItemExample example);

    int deleteByExample(SystemDictItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemDictItem record);

    int insertSelective(SystemDictItem record);

    List<SystemDictItem> selectByExample(SystemDictItemExample example);

    SystemDictItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemDictItem record, @Param("example") SystemDictItemExample example);

    int updateByExample(@Param("record") SystemDictItem record, @Param("example") SystemDictItemExample example);

    int updateByPrimaryKeySelective(SystemDictItem record);

    int updateByPrimaryKey(SystemDictItem record);
}
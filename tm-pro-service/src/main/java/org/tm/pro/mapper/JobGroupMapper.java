package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.JobGroup;
import org.tm.pro.entity.JobGroupExample;

public interface JobGroupMapper {
    long countByExample(JobGroupExample example);

    int deleteByExample(JobGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobGroup record);

    int insertSelective(JobGroup record);

    List<JobGroup> selectByExample(JobGroupExample example);

    JobGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobGroup record, @Param("example") JobGroupExample example);

    int updateByExample(@Param("record") JobGroup record, @Param("example") JobGroupExample example);

    int updateByPrimaryKeySelective(JobGroup record);

    int updateByPrimaryKey(JobGroup record);
}
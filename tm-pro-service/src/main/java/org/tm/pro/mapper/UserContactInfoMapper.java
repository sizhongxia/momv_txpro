package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.UserContactInfo;
import org.tm.pro.entity.UserContactInfoExample;

public interface UserContactInfoMapper {
    long countByExample(UserContactInfoExample example);

    int deleteByExample(UserContactInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserContactInfo record);

    int insertSelective(UserContactInfo record);

    List<UserContactInfo> selectByExample(UserContactInfoExample example);

    UserContactInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserContactInfo record, @Param("example") UserContactInfoExample example);

    int updateByExample(@Param("record") UserContactInfo record, @Param("example") UserContactInfoExample example);

    int updateByPrimaryKeySelective(UserContactInfo record);

    int updateByPrimaryKey(UserContactInfo record);
}
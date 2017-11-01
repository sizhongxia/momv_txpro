package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.UserAuthorization;
import org.tm.pro.entity.UserAuthorizationExample;

public interface UserAuthorizationMapper {
    long countByExample(UserAuthorizationExample example);

    int deleteByExample(UserAuthorizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthorization record);

    int insertSelective(UserAuthorization record);

    List<UserAuthorization> selectByExample(UserAuthorizationExample example);

    UserAuthorization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAuthorization record, @Param("example") UserAuthorizationExample example);

    int updateByExample(@Param("record") UserAuthorization record, @Param("example") UserAuthorizationExample example);

    int updateByPrimaryKeySelective(UserAuthorization record);

    int updateByPrimaryKey(UserAuthorization record);
}
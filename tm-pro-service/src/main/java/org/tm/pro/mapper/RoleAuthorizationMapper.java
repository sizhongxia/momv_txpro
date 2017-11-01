package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.RoleAuthorization;
import org.tm.pro.entity.RoleAuthorizationExample;

public interface RoleAuthorizationMapper {
    long countByExample(RoleAuthorizationExample example);

    int deleteByExample(RoleAuthorizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthorization record);

    int insertSelective(RoleAuthorization record);

    List<RoleAuthorization> selectByExample(RoleAuthorizationExample example);

    RoleAuthorization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAuthorization record, @Param("example") RoleAuthorizationExample example);

    int updateByExample(@Param("record") RoleAuthorization record, @Param("example") RoleAuthorizationExample example);

    int updateByPrimaryKeySelective(RoleAuthorization record);

    int updateByPrimaryKey(RoleAuthorization record);
}
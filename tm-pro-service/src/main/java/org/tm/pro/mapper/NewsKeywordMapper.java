package org.tm.pro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tm.pro.entity.NewsKeyword;
import org.tm.pro.entity.NewsKeywordExample;

public interface NewsKeywordMapper {
    long countByExample(NewsKeywordExample example);

    int deleteByExample(NewsKeywordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsKeyword record);

    int insertSelective(NewsKeyword record);

    List<NewsKeyword> selectByExample(NewsKeywordExample example);

    NewsKeyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsKeyword record, @Param("example") NewsKeywordExample example);

    int updateByExample(@Param("record") NewsKeyword record, @Param("example") NewsKeywordExample example);

    int updateByPrimaryKeySelective(NewsKeyword record);

    int updateByPrimaryKey(NewsKeyword record);
}
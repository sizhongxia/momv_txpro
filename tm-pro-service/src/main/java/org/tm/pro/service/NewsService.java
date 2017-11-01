package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.News;
import org.tm.pro.entity.NewsKeyword;

public interface NewsService {

	News getById(Integer id);

	int insert(News news, List<String> keywords);

	long getCount(String title);

	List<News> getListData(String title, int page, int offsize);

	List<NewsKeyword> getNewsKeywords(Integer id);

	int update(News news);

	int update(NewsKeyword newsKeyword);

	boolean releaseNews(Integer id);
}

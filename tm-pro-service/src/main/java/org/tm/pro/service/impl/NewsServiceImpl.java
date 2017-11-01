package org.tm.pro.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tm.pro.entity.News;
import org.tm.pro.entity.NewsExample;
import org.tm.pro.entity.NewsExample.Criteria;
import org.tm.pro.entity.NewsKeyword;
import org.tm.pro.entity.NewsKeywordExample;
import org.tm.pro.mapper.NewsKeywordMapper;
import org.tm.pro.mapper.NewsMapper;
import org.tm.pro.service.NewsService;

import com.github.pagehelper.PageHelper;

@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsMapper newsMapper;
	@Autowired
	NewsKeywordMapper newsKeywordMapper;

	@Override
	public News getById(Integer id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(News news, List<String> keywords) {
		int newsId = newsMapper.insert(news);
		for (String keyword : keywords) {
			NewsKeyword newsKeyword = new NewsKeyword();
			newsKeyword.setNewsId(newsId);
			newsKeyword.setKeyword(keyword);
			newsKeywordMapper.insert(newsKeyword);
		}
		return newsId;
	}

	@Override
	public long getCount(String title) {

		NewsExample example = new NewsExample();
		Criteria criteria = example.createCriteria();

		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title.trim() + "%");
		}
		return newsMapper.countByExample(example);
	}

	@Override
	public List<News> getListData(String title, int page, int offsize) {
		PageHelper.startPage(page, offsize);
		NewsExample example = new NewsExample();
		Criteria criteria = example.createCriteria();

		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title.trim() + "%");
		}
		return newsMapper.selectByExample(example);
	}

	@Override
	public List<NewsKeyword> getNewsKeywords(Integer id) {
		NewsKeywordExample example = new NewsKeywordExample();
		example.createCriteria().andNewsIdEqualTo(id);
		return newsKeywordMapper.selectByExample(example);
	}

	@Override
	public int update(News news) {
		return newsMapper.updateByPrimaryKey(news);
	}

	@Override
	public int update(NewsKeyword newsKeyword) {
		return newsKeywordMapper.updateByPrimaryKey(newsKeyword);
	}

	@Override
	public boolean releaseNews(Integer id) {
		News news = getById(id);
		if (news == null) {
			return false;
		}
		List<NewsKeyword> keywords = getNewsKeywords(id);
		if (keywords == null) {
			return false;
		}
		return false;
	}
	
	

}

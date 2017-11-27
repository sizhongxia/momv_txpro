package org.tm.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.anno.DataSource;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryExample;
import org.tm.pro.entity.DictionaryItem;
import org.tm.pro.entity.DictionaryItemExample;
import org.tm.pro.mapper.DictionaryItemMapper;
import org.tm.pro.mapper.DictionaryMapper;
import org.tm.pro.service.DictionaryService;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryMapper dictionaryMapper;
	@Autowired
	DictionaryItemMapper dictionaryItemMapper;

	@Override
	@DataSource("slave")
	public Dictionary getById(Integer id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	@DataSource("slave")
	public Dictionary getByVisitCode(String visitCode) {
		DictionaryExample example = new DictionaryExample();
		example.createCriteria().andVisitCodeEqualTo(visitCode);
		List<Dictionary> dicts = dictionaryMapper.selectByExample(example);
		if (dicts != null && !dicts.isEmpty()) {
			return dicts.get(0);
		}
		return null;
	}

	@Override
	@DataSource("master")
	public int saveDictionary(Dictionary dictionary) {
		return dictionaryMapper.insert(dictionary);
	}

	@Override
	@DataSource("master")
	public int updateDictionary(Dictionary dictionary) {
		return dictionaryMapper.updateByPrimaryKey(dictionary);
	}

	@Override
	@DataSource("master")
	public int deleteDictionary(Dictionary dictionary) {
		if (dictionary == null || dictionary.getId() == null) {
			return -1;
		}
		return dictionaryMapper.deleteByPrimaryKey(dictionary.getId());
	}

	@Override
	@DataSource("slave")
	public List<Dictionary> getAllDictionarys() {
		DictionaryExample example = new DictionaryExample();
		example.setOrderByClause("id asc");
		return dictionaryMapper.selectByExample(example);
	}

	@Override
	@DataSource("slave")
	public DictionaryItem getItemById(Integer id) {
		return dictionaryItemMapper.selectByPrimaryKey(id);
	}

	@Override
	@DataSource("master")
	public int saveDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.insert(dictionaryItem);
	}

	@Override
	@DataSource("slave")
	public int updateDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
	}

	@Override
	@DataSource("master")
	public int deleteDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.deleteByPrimaryKey(dictionaryItem.getId());
	}

	@Override
	@DataSource("slave")
	public List<DictionaryItem> getAllDictionaryItems(Integer dictId) {
		DictionaryItemExample example = new DictionaryItemExample();
		example.createCriteria().andDictIdEqualTo(dictId);
		example.setOrderByClause("id asc");
		return dictionaryItemMapper.selectByExample(example);
	}

	@Override
	@DataSource("slave")
	public List<DictionaryItem> getItemsByVCode(String visitCode) {
		Dictionary dictionary = getByVisitCode(visitCode);
		if (dictionary == null) {
			return new ArrayList<>();
		}
		List<DictionaryItem> items = getAllDictionaryItems(dictionary.getId());
		if (items == null) {
			return new ArrayList<>();
		}
		return items;
	}
}

package org.tm.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public Dictionary getById(Integer id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
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
	public int saveDictionary(Dictionary dictionary) {
		return dictionaryMapper.insert(dictionary);
	}

	@Override
	public int updateDictionary(Dictionary dictionary) {
		return dictionaryMapper.updateByPrimaryKey(dictionary);
	}

	@Override
	public int deleteDictionary(Dictionary dictionary) {
		if (dictionary == null || dictionary.getId() == null) {
			return -1;
		}
		return dictionaryMapper.deleteByPrimaryKey(dictionary.getId());
	}

	@Override
	public List<Dictionary> getAllDictionarys() {
		DictionaryExample example = new DictionaryExample();
		example.setOrderByClause("id asc");
		return dictionaryMapper.selectByExample(example);
	}

	@Override
	public DictionaryItem getItemById(Integer id) {
		return dictionaryItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int saveDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.insert(dictionaryItem);
	}

	@Override
	public int updateDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
	}

	@Override
	public int deleteDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.deleteByPrimaryKey(dictionaryItem.getId());
	}

	@Override
	public List<DictionaryItem> getAllDictionaryItems(Integer dictId) {
		DictionaryItemExample example = new DictionaryItemExample();
		example.createCriteria().andDictIdEqualTo(dictId);
		example.setOrderByClause("id asc");
		return dictionaryItemMapper.selectByExample(example);
	}

	@Override
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

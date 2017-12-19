package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryItem;
import org.tm.pro.mapper.DictionaryItemDao;
import org.tm.pro.service.DictionaryItemService;
import org.tm.pro.service.DictionaryService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao, DictionaryItem>
		implements DictionaryItemService {

	@Autowired
	DictionaryService dictionaryService;

	@Override
	public DictionaryItem getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	public int saveDictionaryItem(DictionaryItem dictionaryItem) {
		return baseMapper.insert(dictionaryItem);
	}

	@Override
	public int updateDictionaryItem(DictionaryItem dictionaryItem) {
		return baseMapper.updateById(dictionaryItem);
	}

	@Override
	public int deleteDictionaryItem(DictionaryItem dictionaryItem) {
		return baseMapper.deleteById(dictionaryItem.getId());
	}

	@Override
	public List<DictionaryItem> getAllDictionaryItems(Integer dictId) {
		DictionaryItem entity = new DictionaryItem();
		entity.setDictId(dictId);
		Wrapper<DictionaryItem> wrapper = new EntityWrapper<DictionaryItem>(entity);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<DictionaryItem> getItemsByVCode(String visitCode) {
		Dictionary dictionary = dictionaryService.getByVisitCode(visitCode);
		if (dictionary == null) {
			return null;
		}
		DictionaryItem entity = new DictionaryItem();
		entity.setDictId(dictionary.getId());
		Wrapper<DictionaryItem> wrapper = new EntityWrapper<DictionaryItem>(entity);
		return baseMapper.selectList(wrapper);
	}
}

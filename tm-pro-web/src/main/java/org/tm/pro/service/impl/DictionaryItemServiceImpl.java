package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryItem;
import org.tm.pro.mapper.DictionaryItemDao;
import org.tm.pro.service.DictionaryItemService;
import org.tm.pro.service.DictionaryService;
import org.tm.pro.web.anno.DataSource;

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
@DataSource("master")
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao, DictionaryItem>
		implements DictionaryItemService {

	@Autowired
	DictionaryService dictionaryService;

	@Override
	@DataSource("slave")
	public List<DictionaryItem> getItemsByVCode(String visitCode) {
		Dictionary _entity = new Dictionary();
		_entity.setVisitCode(visitCode);
		Dictionary dictionary = dictionaryService.selectOne(new EntityWrapper<Dictionary>(_entity));
		if (dictionary == null) {
			return null;
		}
		DictionaryItem entity = new DictionaryItem();
		entity.setDictId(dictionary.getId());
		Wrapper<DictionaryItem> wrapper = new EntityWrapper<DictionaryItem>(entity);
		return baseMapper.selectList(wrapper);
	}
}

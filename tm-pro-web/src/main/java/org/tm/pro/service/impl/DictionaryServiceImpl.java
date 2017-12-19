package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.mapper.DictionaryDao;
import org.tm.pro.service.DictionaryService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {

	@Override
	public Dictionary getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Override
	public Dictionary getByVisitCode(String visitCode) {
		Dictionary entity = new Dictionary();
		entity.setVisitCode(visitCode);
		return baseMapper.selectOne(entity);
	}

	@Override
	public int saveDictionary(Dictionary dictionary) {
		return baseMapper.insert(dictionary);
	}

	@Override
	public int updateDictionary(Dictionary dictionary) {
		return baseMapper.updateById(dictionary);
	}

	@Override
	public int deleteDictionary(Dictionary dictionary) {
		return baseMapper.deleteById(dictionary.getId());
	}

	@Override
	public List<Dictionary> getAllDictionarys() {
		Wrapper<Dictionary> wrapper = new EntityWrapper<>();
		return baseMapper.selectList(wrapper);
	}

}

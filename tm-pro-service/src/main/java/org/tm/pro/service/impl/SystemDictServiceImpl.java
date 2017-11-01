package org.tm.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tm.pro.entity.SystemDict;
import org.tm.pro.entity.SystemDictExample;
import org.tm.pro.entity.SystemDictItem;
import org.tm.pro.entity.SystemDictItemExample;
import org.tm.pro.mapper.SystemDictItemMapper;
import org.tm.pro.mapper.SystemDictMapper;
import org.tm.pro.service.SystemDictService;

@Service(value = "systemDictService")
public class SystemDictServiceImpl implements SystemDictService {

	@Autowired
	SystemDictMapper systemDictMapper;
	@Autowired
	SystemDictItemMapper systemDictItemMapper;

	@Override
	public SystemDict getById(Integer id) {
		return systemDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SystemDict> getAllDicts() {
		SystemDictExample example = new SystemDictExample();
		example.setOrderByClause("dict_sort asc");
		return systemDictMapper.selectByExample(example);
	}

	@Override
	public List<SystemDictItem> getAllDictItems(Integer id) {
		SystemDictItemExample example = new SystemDictItemExample();
		example.createCriteria().andDictIdEqualTo(id).andUsingStateEqualTo("Y");
		return systemDictItemMapper.selectByExample(example);
	}

}

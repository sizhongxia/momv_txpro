package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.SystemDict;
import org.tm.pro.entity.SystemDictItem;

public interface SystemDictService {

	SystemDict getById(Integer id);

	List<SystemDict> getAllDicts();

	List<SystemDictItem> getAllDictItems(Integer id);

}

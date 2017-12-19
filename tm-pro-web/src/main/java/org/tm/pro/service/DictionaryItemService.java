package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.DictionaryItem;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 字典项 服务类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
public interface DictionaryItemService extends IService<DictionaryItem> {

	DictionaryItem getById(Integer id);

	int saveDictionaryItem(DictionaryItem dictionaryItem);

	int updateDictionaryItem(DictionaryItem dictionaryItem);

	int deleteDictionaryItem(DictionaryItem dictionaryItem);

	List<DictionaryItem> getAllDictionaryItems(Integer dictId);

	List<DictionaryItem> getItemsByVCode(String visitCode);
}

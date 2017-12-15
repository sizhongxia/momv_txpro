package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryItem;

public interface DictionaryService {

	Dictionary getById(Integer id);

	Dictionary getByVisitCode(String visitCode);

	int saveDictionary(Dictionary dictionary);

	int updateDictionary(Dictionary dictionary);

	int deleteDictionary(Dictionary dictionary);

	List<Dictionary> getAllDictionarys();

	DictionaryItem getItemById(Integer id);

	int saveDictionaryItem(DictionaryItem dictionaryItem);

	int updateDictionaryItem(DictionaryItem dictionaryItem);

	int deleteDictionaryItem(DictionaryItem dictionaryItem);

	List<DictionaryItem> getAllDictionaryItems(Integer dictId);

	List<DictionaryItem> getItemsByVCode(String visitCode);

}

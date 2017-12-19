package org.tm.pro.service;

import java.util.List;

import org.tm.pro.entity.Dictionary;

public interface DictionaryService {

	Dictionary getById(Integer id);

	Dictionary getByVisitCode(String visitCode);

	int saveDictionary(Dictionary dictionary);

	int updateDictionary(Dictionary dictionary);

	int deleteDictionary(Dictionary dictionary);

	List<Dictionary> getAllDictionarys();

}

package org.tm.pro.web.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.tm.pro.entity.SystemDict;
import org.tm.pro.entity.SystemDictItem;
import org.tm.pro.service.SystemDictService;

public class SystemDictCacheUtil implements InitializingBean {

	@Autowired
	SystemDictService systemDictService;

	public static ConcurrentHashMap<String, List<Map<String, String>>> dictInfo = new ConcurrentHashMap<>();

	/**
	 * 初始化系统字典数据
	 */
	private void initSystemDict() {
		if (!dictInfo.isEmpty()) {
			dictInfo.clear();
		}
		List<SystemDict> dicts = systemDictService.getAllDicts();
		if (dicts != null && !dicts.isEmpty()) {
			List<Map<String, String>> items = null;
			Map<String, String> item = null;
			for (SystemDict sd : dicts) {
				List<SystemDictItem> dictItems = systemDictService.getAllDictItems(sd.getId());
				if (dictItems != null && !dictItems.isEmpty()) {
					items = new ArrayList<>();
					String key = sd.getDictCode();
					for (SystemDictItem sdi : dictItems) {
						item = new HashMap<>();
						item.put("name", sdi.getItemName());
						item.put("value", sdi.getItemValue());
						items.add(item);
					}
					dictInfo.put(key, items);
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSystemDict();
	}

}

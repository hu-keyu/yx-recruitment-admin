package org.jypj.dev.cache;

import java.util.ArrayList;
import java.util.List;

import org.jypj.dev.entity.Dictionary;

/**
 * 字典缓存调用类
 * 
 * @author ChenYu
 *
 */
public class DictionaryCache {

	private static List<Dictionary> dicCache = new ArrayList<Dictionary>();

	public static List<Dictionary> getDicCache() {
		return DictionaryCache.dicCache;
	}

	public static void setDicCache(List<Dictionary> dicCache) {
		DictionaryCache.dicCache = dicCache;
	}

	/**
	 * 根据code查询list字典
	 * 
	 * @param code
	 * @return
	 */
	public static List<Dictionary> getDictionaryByCode(String code) {
		List<Dictionary> dics = new ArrayList<Dictionary>();
		if (dicCache != null && dicCache.size() > 0) {
			for (Dictionary dictionary : dicCache) {
				if (dictionary.getCode().equals(code)) {
					dics.add(dictionary);
				}
			}
		}
		return dics;
	}

}

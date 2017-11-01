package org.tm.pro.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TmCollectionUtil {

	public static boolean isEmpty(Collection<?> ls) {
		if (ls == null || ls.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Set<String> asSet(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return null;
		}
		Set<String> st = new HashSet<>();
		for (String str : strArr) {
			st.add(str);
		}
		return st;
	}

}

package org.tm.pro.utils;

import com.xiaoleilu.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.xiaoleilu.hutool.crypto.symmetric.SymmetricCrypto;
import com.xiaoleilu.hutool.util.CharsetUtil;

public class TmDesUtil {
	private static SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, "103-29-11637-11321-14-23".getBytes());
	/***
	 * 加密
	 */
	public static String encrypt(String str) {
		return aes.encryptHex(str);
	}

	/***
	 * 解密
	 */
	public static String decrypt(String str) {
		return aes.decryptStr(str, CharsetUtil.CHARSET_UTF_8);
	}
}

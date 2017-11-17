//package org.tm.pro.picture.util;
//
//import org.junit.Test;
//
//public class UploadFileUtilTest {
//
//	@Test
//	public void UploadTest() {
//		String[] arrs = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
//				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
//		int l = arrs.length;
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < l; i++) {
//			int strILength = sb.length();
//			if (strILength > 0) {
//				sb.delete(0, strILength);
//			}
//			sb.append(arrs[i]);
//			for (int j = 0; j < l; j++) {
//				int strJLength = sb.length();
//				if (strJLength > 1) {
//					sb.delete(1, strJLength);
//				}
//				sb.append(arrs[j]);
//				for (int k = 0; k < l; k++) {
//					int strKLength = sb.length();
//					if (strKLength > 2) {
//						sb.delete(2, strKLength);
//					}
//					sb.append(arrs[k]);
//					for (int m = 0; m < l; m++) {
//						int strMLength = sb.length();
//						if (strMLength > 3) {
//							sb.delete(3, strMLength);
//						}
//						sb.append(arrs[m]);
//						for (int n = 0; n < l; n++) {
//							int strNLength = sb.length();
//							if (strNLength > 4) {
//								sb.delete(4, strNLength);
//							}
//							sb.append(arrs[n]);
//							if (!isExist(sb.toString())) {
//								System.out.println();
//								System.out.println(sb.toString());
//								System.out.println();
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	private boolean isExist(String domain) {
//		String res = HttpClientUtil.checkDomain(domain + ".com");
//		if (res == null) {
//			System.out.println();
//			System.err.println("请求出现异常：" + domain);
//			System.out.println();
//			return true;
//		}
//		return res.contains("211");
//	}
//
//}

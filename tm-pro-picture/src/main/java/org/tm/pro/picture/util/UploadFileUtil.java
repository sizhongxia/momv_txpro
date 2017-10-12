package org.tm.pro.picture.util;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.jetbrains.annotations.NotNull;

public class UploadFileUtil {

	public static boolean isImage(File tempFile) throws Exception {
		ImageInputStream is = ImageIO.createImageInputStream(tempFile);
		return is != null;
	}

	public static StringBuilder createMd5(@NotNull final InputStream inputStream) throws Exception {
		StringBuilder sb = new StringBuilder();
		// 生成MD5实例
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		int available = inputStream.available();
		byte[] bytes = new byte[available];
		md5.update(bytes);
		for (byte by : md5.digest()) {
			// 将生成的字节MD5值转换成字符串
			sb.append(String.format("%02X", by));
		}
		return sb;
	}

}

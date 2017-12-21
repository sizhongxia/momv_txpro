package org.tm.pro.picture.zimg.service;

import org.tm.pro.picture.PictureUploadService;
import org.tm.pro.picture.result.PictureUploadRet;
import org.tm.pro.picture.util.HttpClientUtil;
import org.tm.pro.picture.zimg.config.ZimgConfig;

import com.google.gson.Gson;

public class ZimgPictureUploadService implements PictureUploadService {

	private String serverAddr = ZimgConfig.getConfigProperty("zimg.server_addr");

	@Override
	public PictureUploadRet upload(byte[] bytes, String fileName) {

		String result = HttpClientUtil.upload(serverAddr + "upload", bytes, fileName.substring(fileName.lastIndexOf(".") + 1));
		if (result == null) {
			return null;
		}
		return new Gson().fromJson(result, PictureUploadRet.class);
	}
}
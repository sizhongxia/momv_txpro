package org.tm.pro.picture.zimg.service;

import java.io.File;

import org.tm.pro.picture.PictureUploadService;
import org.tm.pro.picture.util.HttpClientUtil;
import org.tm.pro.picture.zimg.config.ZimgConfig;
import org.tm.pro.picture.zimg.model.ZimgUploadRet;

import com.google.gson.Gson;

public class ZimgPictureUploadService implements PictureUploadService {

	private String serverAddr = ZimgConfig.getConfigProperty("zimg.server_addr");

	@Override
	public ZimgUploadRet upload(File file) {
		String result = HttpClientUtil.upload(serverAddr + "/upload", file);
		if (result == null) {
			return null;
		}
		return new Gson().fromJson(result, ZimgUploadRet.class);
	}

}

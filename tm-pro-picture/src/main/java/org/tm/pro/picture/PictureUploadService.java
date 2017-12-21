package org.tm.pro.picture;

import org.tm.pro.picture.result.PictureUploadRet;

public interface PictureUploadService {
	public PictureUploadRet upload(byte[] bytes, String fileName);
}
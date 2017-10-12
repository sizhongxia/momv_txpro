package org.tm.pro.picture.util;

import java.io.File;

import org.junit.Test;

public class UploadFileUtilTest {

	@Test
	public void TestIsImage() {
		try {
			System.out.println(UploadFileUtil.isImage(new File("E:\\doc\\模块.txt")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

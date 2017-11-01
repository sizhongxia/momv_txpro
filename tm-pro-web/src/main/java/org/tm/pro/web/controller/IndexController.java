package org.tm.pro.web.controller;

import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.picture.PictureUploadService;
import org.tm.pro.picture.zimg.model.ZimgFailInfo;
import org.tm.pro.picture.zimg.model.ZimgUploadRet;
import org.tm.pro.service.UserService;
import org.tm.pro.web.cache.SystemBaseInfoCacheUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.google.code.kaptcha.Producer;

@Controller
public class IndexController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	Producer captchaProducer;
	@Autowired
	PictureUploadService pictureUploadService;

	public IndexController() {
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title", "Tm Sys");
		mv.addObject("time", "Time: " + SystemBaseInfoCacheUtil.systemInfo.get("update_time"));

		// Map<String, List<Map<String, String>>> dicts = SystemCacheUtil.dictInfo;
		//
		// for (String key : dicts.keySet()) {
		// Console.log("key:{} ========== ", key);
		// for (Map<String, String> item : dicts.get(key)) {
		// Console.log("name:{}， value:{}", item.get("name"), item.get("value"));
		// }
		// }

		return mv;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginBgPic", SystemBaseInfoCacheUtil.systemInfo.get("login_bg_pic"));
		return mav;
	}

	@RequestMapping("image-verify")
	public void imageVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(captchaProducer.createImage(captchaProducer.createText()), "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/upload-image")
	public ApiResultMap uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		File imgFile = ((DiskFileItem) cf.getFileItem()).getStoreLocation();
		Object obj = pictureUploadService.upload(imgFile);
		if (obj == null) {
			return new ApiResultMap(301, "error");
		}
		ZimgUploadRet ret = (ZimgUploadRet) obj;
		if (ret.isRet()) {
			return new ApiResultMap(ret.getInfo());
		} else {
			ZimgFailInfo failInfo = ret.getError();
			return new ApiResultMap(failInfo.getCode(), failInfo.getMessage());
		}
	}
}

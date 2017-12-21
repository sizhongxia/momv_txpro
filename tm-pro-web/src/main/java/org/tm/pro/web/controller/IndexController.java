package org.tm.pro.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tm.pro.entity.Picture;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.picture.PictureUploadService;
import org.tm.pro.picture.result.FailInfo;
import org.tm.pro.picture.result.PictureUploadRet;
import org.tm.pro.picture.result.SucInfo;
import org.tm.pro.service.PictureService;
import org.tm.pro.utils.TmMd5Util;
import org.tm.pro.web.anno.UserAgentRecord;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.sms.SendSmsEvent;
import org.tm.pro.web.sms.SendSmsEventObj;
import org.tm.pro.web.sms.SendSmsWorker;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.code.kaptcha.Producer;

@Controller
public class IndexController extends BaseController implements ApplicationEventPublisherAware {

	@Autowired
	Producer captchaProducer;
	@Autowired
	PictureUploadService pictureUploadService;
	@Autowired
	PictureService pictureService;
	@Autowired
	SendSmsWorker sendSmsUtil;

	ApplicationEventPublisher applicationEventPublisher;

	public IndexController() {
	}

	@UserAgentRecord
	@RequiresAuthentication
	@RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		SystemInfo systemInfo = SystemInfoCacheUtil.systemInfo;
		mv.addObject("systemTitle", systemInfo.getSystemTitle());
		mv.addObject("systemDescript", systemInfo.getSystemDescript());

		mv.addObject("currentTime", System.currentTimeMillis());

		// 发送短信验证码
		SendSmsEventObj source = new SendSmsEventObj();
		source.setMobile("18518436862");
		source.setType(SendSmsWorker.JUHE_TPL_ID_VERIFICATION_CODE);
		SendSmsEvent event = new SendSmsEvent(source);
		applicationEventPublisher.publishEvent(event);

		if ("Y".equalsIgnoreCase(systemInfo.getOnlyChrome())) {
			if (!"CHROME".equalsIgnoreCase(request.getAttribute("browser").toString())) {
				mv.setViewName("ie");
				return mv;
			}
		}

		return mv;
	}

	/***
	 * 后台主页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/welcome")
	public ModelAndView welcome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}

	/***
	 * 不兼容提示
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ie")
	public ModelAndView ie(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("ie");
		return mav;
	}

	/***
	 * 登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView("login");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			mav.setViewName("redirect:/index.do");
			return mav;
		}
		Session session = subject.getSession(true);
		Object referer = session.getAttribute("referer");
		mav.addObject("referer", referer);
		Object msg = session.getAttribute("login_fail_msg");
		mav.addObject("msg", msg);
		// 清除
		session.removeAttribute("referer");
		session.removeAttribute("login_fail_msg");
		return mav;
	}

	/***
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
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

	@RequestMapping(value = "/un_authorized")
	public ModelAndView unAuthorized(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("un_authorized");
		return view;
	}

	@RequestMapping(value = "/page_not_found")
	public ModelAndView pageNotFound(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		if (request.getHeader("Referer") != null) {
			view.setViewName("page_not_found");
		} else {
			view.setViewName("to_skip_index");
		}
		return view;
	}

	@RequestMapping(value = "/server_busy")
	public ModelAndView serverBusy(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("server_busy");
		return view;
	}

	@RequestMapping(value = "/operation_timeout")
	public ModelAndView operationTimeout(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("operation_timeout");
		return view;
	}

	@RequestMapping(value = "/socket_io")
	public ModelAndView socketIo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("socket_io");
		return view;
	}

	/***
	 * 上传图片文件
	 *
	 * @param request
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/upload-image")
	public ApiResultMap uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			return new ApiResultMap(301, "未上传图片信息");
		}
		String md5 = TmMd5Util.md5(bytes);
		Picture entity = new Picture();
		entity.setMd5(md5);
		Picture picture = pictureService.selectOne(new EntityWrapper<Picture>(entity));
		if (picture != null) {
			return new ApiResultMap(picture);
		}
		// 文件上传服务
		PictureUploadRet ret = pictureUploadService.upload(bytes, file.getOriginalFilename());
		if (ret == null) {
			return new ApiResultMap(302, "上传图片出错");
		}
		if (ret.isRet()) {
			SucInfo sucInfo = ret.getInfo();
			picture = new Picture();
			picture.setMd5(md5);
			picture.setName(file.getOriginalFilename());
			picture.setType(file.getContentType());
			picture.setSize(sucInfo.getSize());
			picture.setUrl(sucInfo.getMd5());
			picture.setUploadTime(new Date());
			if (pictureService.insert(picture)) {
				return new ApiResultMap(picture);
			} else {
				return new ApiResultMap(500, "保存文件出错");
			}
		} else {
			FailInfo failInfo = ret.getError();
			return new ApiResultMap(failInfo.getCode(), failInfo.getMessage());
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}

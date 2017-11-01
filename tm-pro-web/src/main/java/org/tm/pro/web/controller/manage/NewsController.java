package org.tm.pro.web.controller.manage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.entity.News;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.service.NewsService;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.utils.FreeMarkerUtil;

@Controller
@RequestMapping("manage")
public class NewsController extends BaseController {

	@Autowired
	NewsService newsService;

	public NewsController() {
	}

	@ResponseBody
	@RequestMapping(value = "/news/release", method = { RequestMethod.GET })
	public ApiResultMap release(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String newsId = request.getParameter("newsId");
		if (TmStringUtil.isBlank(newsId)) {
			return new ApiResultMap(301, "未传入的新闻ID");
		}

		News news = newsService.getById(new Integer(newsId));
		if (news == null) {
			return new ApiResultMap(301, "无效的新闻ID");
		}

		String realPath = request.getSession().getServletContext().getRealPath("");
		String ftlPath = realPath + "WEB-INF/templet/news_template.ftl";
		String fileName = UUID.randomUUID().toString().replaceAll("-", "");
		String fileFullName = "news/" + fileName + ".html";
		String filePath = realPath + fileFullName;

		Map<String, Object> result = new HashMap<>();
		result.put("title", news.getTitle());
		result.put("content", news.getContent());

		try {
			if (FreeMarkerUtil.createHTML(ftlPath, filePath, result)) {
				data.put("path", fileFullName);
			} else {
				return new ApiResultMap(302, "生成失败，未知原因");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResultMap(303, "生成失败：" + e.getMessage());
		}
		return new ApiResultMap("生成成功");
	}

}

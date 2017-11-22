//package org.tm.pro.web.controller.manage;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.shiro.authz.annotation.RequiresAuthentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tm.pro.es.ElasticUtil;
//import org.tm.pro.web.controller.base.BaseController;
//
//@Controller
//@RequestMapping(value = "/elastic")
//public class ElasticController extends BaseController {
//
//	@Autowired
//	ElasticUtil elasticUtil;
//
//	public ElasticController() {
//	}
//
//	/***
//	 * ES服务
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@ResponseBody
//	@RequiresAuthentication
//	@RequestMapping(value = "/index")
//	public Map<String, Object> index(HttpServletRequest request) {
//		Map<String, Object> data = new HashMap<>();
//		String jsonParam = "{" + "	\"from\": 0," + "	\"size\": 0," + "	\"aggregations\": {"
//				+ "		\"types\": {" + "			\"terms\": {" + "				\"field\": \"status_code\""
//				+ "			}" + "		}" + "	}" + "}";
//		try {
//			String jsonResult = elasticUtil.postBaseRequest("ppc_log_test-20170930", "access", jsonParam);
//			data.put("data", jsonResult);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return data;
//	}
//
//}

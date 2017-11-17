package org.tm.pro.web.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.es.ElasticsearchUtil;
import org.tm.pro.es.entity.SsxRequestLogEntity;
import org.tm.pro.es.model.ElasticHitsItem;
import org.tm.pro.es.model.ElasticResponseModel;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/elastic")
public class ElasticController extends BaseController {

	@Autowired
	ElasticsearchUtil elasticsearchUtil;

	public ElasticController() {
	}

	/***
	 * ES服务
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("elastic/index");
		List<SsxRequestLogEntity> list = new ArrayList<>();
		mav.addObject("json", "");

		String index = getParameter(request, "index", "");

		if (TmStringUtil.isBlank(index)) {
			mav.addObject("list", list);
			return mav;
		}
		String type = getParameter(request, "type", "");
		if (TmStringUtil.isBlank(index)) {
			mav.addObject("list", list);
			return mav;
		}
		BoolQueryBuilder builder = QueryBuilders.boolQuery();

		String fieldSort = getParameter(request, "fieldSort", "");
		SortBuilder<?> sortBuilder = null;
		if (TmStringUtil.isNotBlank(fieldSort)) {
			String sortOrder = getParameter(request, "sortOrder", "asc");
			SortOrder sortType = SortOrder.ASC;
			if ("desc".equals(sortOrder)) {
				sortType = SortOrder.DESC;
			}
			sortBuilder = SortBuilders.fieldSort(fieldSort).order(sortType);
		}

		Integer from = getParameter(request, "form", 0);
		Integer size = getParameter(request, "size", 10);
		SearchResponse searchResponse = elasticsearchUtil.search(index, type, builder, sortBuilder, null, from, size);

		String responseJsonString = searchResponse.toString();
		ElasticResponseModel<JSONObject> responseModel = new ElasticResponseModel<JSONObject>();
		try {
			responseModel = JSON.parseObject(responseJsonString, responseModel.getClass());
		} catch (Exception e) {
		}
		ElasticHitsItem<JSONObject>[] hits = responseModel.getHits().getHits();
		if (hits != null && hits.length > 0) {
			JSONObject item = null;
			for (ElasticHitsItem<JSONObject> hit : hits) {
				item = hit.get_source();
				list.add(transform(item));
			}
		}
		mav.addObject("json", responseJsonString);
		mav.addObject("list", list);
		return mav;
	}

	private SsxRequestLogEntity transform(JSONObject obj) {
		return JSON.toJavaObject(obj, SsxRequestLogEntity.class);
	}
}

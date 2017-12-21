package org.tm.pro.web.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.entity.DictionaryItem;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.service.DictionaryItemService;
import org.tm.pro.service.DictionaryService;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {

	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	DictionaryItemService dictionaryItemService;

	@RequiresAuthentication
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("dictionary/index");
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/list")
	public ApiResultMap list(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setList(dictionaryService.selectList(new EntityWrapper<>()));
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/items")
	public ModelAndView items(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView("dictionary/items");
		Dictionary dict = dictionaryService.selectById(id);
		mav.addObject("dict", dict);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/itemList")
	public ApiResultMap itemList(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		DictionaryItem entity = new DictionaryItem();
		entity.setDictId(id);
		Wrapper<DictionaryItem> wrapper = new EntityWrapper<DictionaryItem>(entity);
		List<DictionaryItem> items = dictionaryItemService.selectList(wrapper);
		if (items == null) {
			items = new ArrayList<>();
		}
		arm.setList(items);
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("dictionary/add");
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/save")
	public ApiResultMap save(HttpServletRequest request, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "visitCode", required = true) String visitCode,
			@RequestParam(value = "remarks", required = true) String remarks) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		if (TmStringUtil.isBlank(name)) {
			arm.setMsg("错误：请输入字典名称");
			return arm;
		}
		if (TmStringUtil.isBlank(visitCode)) {
			arm.setMsg("错误：请输入字典访问编码");
			return arm;
		}
		Dictionary entity = new Dictionary();
		entity.setVisitCode(visitCode);
		Dictionary dictionary = dictionaryService.selectOne(new EntityWrapper<Dictionary>(entity));
		if (dictionary != null) {
			arm.setMsg("错误：字典访问编码已存在");
			return arm;
		}
		dictionary = new Dictionary();

		dictionary.setName(name);
		dictionary.setVisitCode(visitCode);
		dictionary.setRemarks(remarks);
		if (dictionaryService.insert(dictionary)) {
			arm.setStatus(true);
			arm.setData(dictionary);
			arm.setMsg("保存成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView("dictionary/edit");
		Dictionary dictionary = dictionaryService.selectById(id);
		if (dictionary == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}
		mav.addObject("dictionary", dictionary);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/update")
	public ApiResultMap update(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "visitCode", required = true) String visitCode,
			@RequestParam(value = "remarks", required = true) String remarks) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Dictionary dictionary = dictionaryService.selectById(id);
		if (dictionary == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (TmStringUtil.isBlank(name)) {
			arm.setMsg("错误：请输入字典名称");
			return arm;
		}
		if (TmStringUtil.isBlank(visitCode)) {
			arm.setMsg("错误：请输入字典访问编码");
			return arm;
		}

		Dictionary entity = new Dictionary();
		entity.setVisitCode(visitCode);
		Dictionary _dictionary = dictionaryService.selectOne(new EntityWrapper<Dictionary>(entity));
		if (_dictionary != null && _dictionary.getId() != id) {
			arm.setMsg("错误：字典访问编码已存在");
			return arm;
		}

		dictionary.setName(name);
		dictionary.setVisitCode(visitCode);
		dictionary.setRemarks(remarks);

		if (dictionaryService.updateById(dictionary)) {
			arm.setStatus(true);
			arm.setData(dictionary);
			arm.setMsg("修改成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/delete")
	public ApiResultMap delete(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Dictionary dictionary = dictionaryService.selectById(id);
		if (dictionary == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		if (dictionaryService.deleteById(id)) {
			arm.setStatus(true);
			arm.setData(dictionary);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/saveItem")
	public ApiResultMap saveItem(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "value", required = true) String value) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Dictionary dictionary = dictionaryService.selectById(id);
		if (dictionary == null) {
			// 无权访问
			arm.setMsg("错误：无效的表单");
			return arm;
		}
		if (TmStringUtil.isBlank(name)) {
			arm.setMsg("错误：请输入字典项名称");
			return arm;
		}
		if (TmStringUtil.isBlank(value)) {
			arm.setMsg("错误：请输入字典项值");
			return arm;
		}
		DictionaryItem dictionaryItem = new DictionaryItem();
		dictionaryItem.setDictId(id);
		dictionaryItem.setName(name);
		dictionaryItem.setValue(value);
		if (dictionaryItemService.insert(dictionaryItem)) {
			arm.setStatus(true);
			arm.setData(dictionaryItem);
			arm.setMsg("保存成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/updateItem")
	public ApiResultMap updateItem(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "value", required = true) String value) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		DictionaryItem dictionaryItem = dictionaryItemService.selectById(id);
		if (dictionaryItem == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (TmStringUtil.isBlank(name)) {
			arm.setMsg("错误：请输入字典项名称");
			return arm;
		}
		if (TmStringUtil.isBlank(value)) {
			arm.setMsg("错误：请输入字典项值");
			return arm;
		}

		dictionaryItem.setName(name);
		dictionaryItem.setValue(value);

		if (dictionaryItemService.updateById(dictionaryItem)) {
			arm.setStatus(true);
			arm.setData(dictionaryItem);
			arm.setMsg("修改成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/deleteItem")
	public ApiResultMap deleteItem(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		DictionaryItem dictionaryItem = dictionaryItemService.selectById(id);
		if (dictionaryItem == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (dictionaryItemService.deleteById(id)) {
			arm.setStatus(true);
			arm.setData(dictionaryItem);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

}

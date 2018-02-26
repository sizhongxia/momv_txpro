package org.tm.pro.web.poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.view.PoiBaseView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tm.pro.web.poi.entity.UserInfo;

public class EasyPoiUtil {
	@RequestMapping("downExcel")
	public void downExcel(HttpServletResponse response) {
		Collection<UserInfo> list = new ArrayList<>();
		UserInfo u1 = new UserInfo();
		u1.setId("20110101");
		u1.setName("夏风飞舞");
		u1.setHeadPic("..\\statics\\images\\headpic.jpg");
		u1.setSex(1);
		u1.setBirthday(new Date());
		list.add(u1);

		UserInfo u2 = new UserInfo();
		u2.setId("20110102");
		u2.setName("sizhongxia");
		u2.setSex(2);
		u2.setBirthday(new Date());
		list.add(u2);

		// 指定列表标题 和 工作表名称
		ExportParams params = new ExportParams("用户信息表", "用户");
		Workbook workbook = ExcelExportUtil.exportExcel(params, UserInfo.class, list);

		response.setHeader("content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
		response.setCharacterEncoding("UTF-8");

		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("downExcelWithSpring")
	public void download(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		for (int i = 0; i < 10; i++) {
			UserInfo client = new UserInfo();
			client.setId("2011010" + i);
			client.setName("用户" + i);
			client.setSex(i % 2 == 0 ? 1 : 2);
			client.setBirthday(new Date());
			list.add(client);
		}
		ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
		params.setFreezeCol(2);
		map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
		map.put(NormalExcelConstants.CLASS, UserInfo.class);// 导出实体
		map.put(NormalExcelConstants.PARAMS, params);// 参数
		map.put(NormalExcelConstants.FILE_NAME, "12345.xls");// 文件名称
		//PoiBaseView.render(map, request, response, NormalExcelConstants.JEECG_EXCEL_VIEW);
	}
}

package org.tm.pro.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("deprecation")
public class FreeMarkerUtil {

	// public static void main(String[] args) throws IOException {
	// String title = "title";
	// String content = "content";
	// Map<String, Object> data = new HashMap<>();
	// data.put("title", title);
	// data.put("content", content);
	// System.out.println(createHTML(
	// "C:/Users/sizho/Documents/MyWorkSpace/tm-pro-root/tm-pro-web/src/main/webapp/news_template/news_template.ftl",
	// "D:/1.html",
	// data));
	// }

	/**
	 * 根据ftl模板文件,生成静态HTML文件
	 * 
	 * @param ftlPath
	 *            FTL模板文件路径,例如["c:/template.ftl"]
	 * @param filePath
	 *            生成HMTL文件路径，例如["d:/sizhongxia.html"]
	 * @param data
	 *            Map数据
	 * @return
	 */
	public static boolean createHTML(String ftlPath, String filePath, Map<String, Object> data) throws IOException {
		return createHTML(ftlPath, filePath, data, true);
	}

	/**
	 * 根据ftl模板文件,生成静态HTML文件
	 * 
	 * @param ftlPath
	 *            FTL模板文件路径,例如["c:/template.ftl"]
	 * @param filePath
	 *            生成HMTL文件路径，例如["d:/sizhongxia.html"]
	 * @param data
	 *            Map数据
	 * @param isCreate4NoExists
	 *            是否文件夹不存在的时候创建
	 * @return
	 */
	public static boolean createHTML(String ftlPath, String filePath, Map<String, Object> data,
			boolean isCreate4NoExists) throws IOException {
		String fileDir = StringUtils.substringBeforeLast(filePath, "/"); // 获取HMTL文件目录
		String ftlDir = StringUtils.substringBeforeLast(ftlPath, "/"); // 获取FTL文件目录
		String ftlName = StringUtils.substringAfterLast(ftlPath, "/"); // 获取FTL文件名

		// 文件递归创建生成文件目录
		if (isCreate4NoExists) {
			File realDirectory = new File(fileDir);
			if (!realDirectory.exists()) {
				realDirectory.mkdirs();
			}
		}

		// step1 获取freemarker的配置
		Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_23);
		// step2 设置freemarker模板所放置的位置(文件夹)
		freemarkerCfg.setDirectoryForTemplateLoading(new File(ftlDir));
		// step3 设置freemarker模板编码
		freemarkerCfg.setEncoding(Locale.getDefault(), CharEncoding.UTF_8);
		// step4 找到对应freemarker模板并实例化
		Template template = freemarkerCfg.getTemplate(ftlName, CharEncoding.UTF_8);
		// step5 初始化一个IO流
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(filePath)), CharEncoding.UTF_8))) {
			writer.flush();
			// step6 模板渲染出所要的内容
			template.process(data, writer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
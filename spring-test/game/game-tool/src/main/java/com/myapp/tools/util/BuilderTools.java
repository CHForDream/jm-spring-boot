package com.myapp.tools.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BuilderTools {
	// 日志
	private static Logger log = Logger.getLogger(BuilderTools.class);

	/**
	 * 自动生成文件
	 * 
	 * @param templatePath
	 *                     模板文件路径
	 * @param savePath
	 *                     自动生成的文件路径
	 * @param fileName
	 *                     自动生成的文件名字
	 * @param root
	 *                     数据
	 */
	public static void build(String templatePath, String savePath, String fileName, Map<String, Object> root) {
		String templatePath_tmp = templatePath.substring(0, templatePath.lastIndexOf(File.separator));
		String templatePath_name = templatePath.substring(templatePath.lastIndexOf(File.separator) + 1);

		// 如果路径不存在，就创建路径
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 创建一个合适的Configuration
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
		try {
			cfg.setDefaultEncoding("UTF-8");
			cfg.setDirectoryForTemplateLoading(new File(templatePath_tmp));
		} catch (IOException e) {
			log.error("创建Configuration失败！", e);
			System.exit(1);
		}
		DefaultObjectWrapperBuilder defaultObjectWrapperBuilder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_30);
		cfg.setObjectWrapper(defaultObjectWrapperBuilder.build());

		// 获取或创建一个模板
		Template template = null;
		try {
			template = cfg.getTemplate(templatePath_name);
		} catch (IOException e) {
			log.error("创建模板失败！", e);
			System.exit(1);
		}

		// 生成文件
		Writer writer;
		try {
			// 保存路径
			String fileSavePath = savePath + File.separator + fileName;
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileSavePath), "UTF-8"));

			// 保存数据
			template.process(root, writer);
			writer.flush();
			writer.close();
			// 提示信息
			log.info(String.format("生成文件：%s", file.getAbsolutePath() + File.separator + fileName));
		} catch (IOException e) {
			log.error("写文件失败！", e);
			System.exit(1);
		} catch (TemplateException e) {
			log.error("模板操作异常！", e);
			System.exit(1);
		}
	}

}

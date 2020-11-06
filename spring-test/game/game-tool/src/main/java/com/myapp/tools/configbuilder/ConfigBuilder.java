package com.myapp.tools.configbuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myapp.tools.bean.ToolsConfig;
import com.myapp.tools.configbuilder.base.FieldBean;
import com.myapp.tools.util.BuilderTools;

public class ConfigBuilder {

	private String sheetName = null;

	private String sheetNamePY = null;

	private String fileName = null;

	private List<FieldBean> fieldBeans = null;

	private String idColumn = null;

	private String toPinying(String name) {
		int length = sheetName.length();
		StringBuilder sb = new StringBuilder();
		char c;
		for (int i = 0; i < length; i++) {
			c = sheetName.charAt(i);
			if (c >= 'a' && c <= 'z') {
				sb.append(c);
				continue;
			}

			if (c >= 'A' && c <= 'Z') {
				sb.append(c);
				continue;
			}

			if (c >= '0' && c <= '9') {
				sb.append(c);
				continue;
			}
		}

		return sb.toString();
	}

	public void builderMsg(String fileName, String sheetName, Object[][] data, String target) {
		this.fileName = fileName;
		this.sheetName = sheetName;
		this.sheetNamePY = this.toPinying(sheetName);

		readHeaderInfo(data);

		// 构建服务器配置文件
		builderBaseBean(target);
		builderBean(target);
	}

	private void readHeaderInfo(Object[][] data) {
		// 组织数据
		// field英文名字
		String[] enName = (String[]) data[0];
		// field类型
		String[] fieldType = (String[]) data[1];
		// field中文名字
		String[] znName = (String[]) data[2];
		// 是否注释列
		String[] isComment = (String[]) data[3];

		int length = enName.length;
		fieldBeans = new ArrayList<FieldBean>();
		for (int i = 0; i < length; i++) {
			FieldBean tmp = new FieldBean();
			tmp.setEnName(enName[i].trim());// field英文名字
			tmp.setIsComment(isComment[i]);// 是否注释列
			// 注释只有0和2需要服务器解析
			if (!"0".equals(tmp.getIsComment()) && !"2".equals(tmp.getIsComment())) {
				continue;
			}

			tmp.setFieldType(fieldType[i]);// field类型
			// 除了这4种类型, 其他类型暂时不解析
			if (!"int".equals(tmp.getFieldType()) && !"long".equals(tmp.getFieldType()) && !"String".equals(tmp.getFieldType())
					&& !"float".equals(tmp.getFieldType())) {
				continue;
			}

			String nameZn = znName[i].trim().replaceAll("\\s+", " ");
			if (nameZn.length() > 140) {
				nameZn = nameZn.substring(0, 140) + "...";
			}
			tmp.setZnName(nameZn);// field中文名字

			// 第一列默认为id
			if (i == 0) {
				this.idColumn = enName[i];
			}

			fieldBeans.add(tmp);
		}
	}

	private void builderBaseBean(String target) {
		String templatePath = ToolsConfig.appToolsConfigBean.getBase_Path() + File.separator + ToolsConfig.appToolsConfigBean.getConfig_BaseBeanTemplate();

		String savePath = "export" + File.separator + target + File.separator + "src" + File.separator
				+ ToolsConfig.appToolsConfigBean.getConfig_BaseBeanPackage().replaceAll("\\.", "\\" + File.separator);

		String tmp = (String.valueOf(this.sheetNamePY.charAt(0))).toUpperCase();
		String fileName = tmp + this.sheetNamePY.substring(1) + "BaseBean.java";

		// 准备数据
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("ConfigPackage", ToolsConfig.appToolsConfigBean.getConfig_BaseBeanPackage());
		root.put("FileName", this.fileName);
		root.put("IdColunm", this.idColumn);
		root.put("Name", this.sheetNamePY);
		root.put("SheetName", this.sheetName);
		root.put("FieldBeans", this.fieldBeans);

		// 生成文件
		BuilderTools.build(templatePath, savePath, fileName, root);
	}

	private void builderBean(String target) {
		String templatePath = ToolsConfig.appToolsConfigBean.getBase_Path() + File.separator + ToolsConfig.appToolsConfigBean.getConfig_BeanTemplate();

		String savePath = "export" + File.separator + target + File.separator + "src" + File.separator
				+ ToolsConfig.appToolsConfigBean.getConfig_BeanPackage().replaceAll("\\.", "\\" + File.separator);

		String tmp = (String.valueOf(this.sheetNamePY.charAt(0))).toUpperCase();
		String fileName = tmp + this.sheetNamePY.substring(1) + "Bean.java";

		// 准备数据
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("ConfigPackage", ToolsConfig.appToolsConfigBean.getConfig_BeanPackage());
		root.put("FileName", this.fileName);
		root.put("IdColunm", this.idColumn);
		root.put("Name", this.sheetNamePY);
		root.put("SheetName", this.sheetName);
		root.put("FieldBeans", this.fieldBeans);

		// 生成文件
		BuilderTools.build(templatePath, savePath, fileName, root);
	}
}

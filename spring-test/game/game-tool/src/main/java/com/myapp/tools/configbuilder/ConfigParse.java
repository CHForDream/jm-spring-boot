package com.myapp.tools.configbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.myapp.tools.util.ExcelUtils;

@SuppressWarnings("resource")
public class ConfigParse {

	// 日志
	private Logger log = Logger.getLogger(ConfigParse.class);

	public void parsePath(File filePath, String target) {
		if (filePath.isDirectory()) {
			File[] files = filePath.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					this.parsePath(file, target);
				} else {
					String name = file.getName();
					if (name.endsWith(".xls")) {
						this.parseFile(file.getAbsolutePath(), target);
					}
				}
			}
		}
	}

	private void parseFile(String path, String target) {
		Object[][] data = null;
		String sheetName = null;
		ConfigBuilder configBuilder = new ConfigBuilder();
		String fileName = null;
		try {
			InputStream is = new FileInputStream(path);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			int count = workbook.getNumberOfSheets();
			for (int i = 0; i < count; i++) {
				sheetName = workbook.getSheetName(i);
				data = ExcelUtils.loadConfig(path, sheetName);
				if (data.length > 0) {
					// 生成配置文件bean
					File tmp = new File(path);
					fileName = tmp.getName();
					configBuilder.builderMsg(fileName, sheetName, data, target);
				}
			}
		} catch (IOException e) {
			log.error(String.format("Read Excel error - File[%s]", path), e);
//			System.exit(1);
		} catch (Exception e) {
			log.error(String.format("Parse error - File[%s] - SheetName[%s]", path, sheetName), e);
//			System.exit(1);
		}
	}
}

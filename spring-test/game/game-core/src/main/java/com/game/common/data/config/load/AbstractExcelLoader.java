package com.game.common.data.config.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.game.common.data.define.DataConfigManager;

@SuppressWarnings("resource")
public abstract class AbstractExcelLoader implements ILoader<String, HSSFWorkbook> {

	// public abstract HSSFWorkbook loadFile(String sheetName);

	public HSSFSheet getFile(String filename, String sheetname) throws IOException {
		Map<String, HSSFSheet> xlsMap = DataConfigManager.getInstance().getXlsMap();
		Sheet sheet = xlsMap.get(sheetname);
		if (sheet != null) {
			return xlsMap.get(sheetname);
		}

		FileInputStream fis = null;
		try {
			String filePath = System.getProperty("user.dir") + File.separator + "excel" + File.separator + filename;
			File file = new File(filePath);
			fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			return workbook.getSheetAt(workbook.getSheetIndex(sheetname));
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
}

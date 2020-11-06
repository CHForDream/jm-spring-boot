package com.myapp.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("resource")
public class ExcelDelLineTool {

	public static void main(String[] args) {
		File fileFolder = new File("D:/svn_home/client/doc/serverConfig");
		for (File file : fileFolder.listFiles()) {
			deleteRow(file, 5, 3);
		}

//		File file = new File("D:\\svn_home\\client\\doc\\serverConfig\\achieve.xls");
//		deleteRow(file, 5, 3);
	}

	private static void deleteRow(File file, int start, int lineNum) {
		try {
			FileInputStream is = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0; i < lineNum; i++) {
				sheet.shiftRows(start, sheet.getLastRowNum(), -1);
			}
			FileOutputStream os = new FileOutputStream(file);
			workbook.write(os);
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

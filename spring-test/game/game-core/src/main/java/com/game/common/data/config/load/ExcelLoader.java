package com.game.common.data.config.load;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelLoader extends AbstractExcelLoader {

	@Override
	public String[][] loadConfig(String configPath, String sheetName) throws Exception {
//		Loggers.serverLogger.info(String.format("Load Excel[%s] sheet[%s]", configPath, sheetName));

//		InputStream is = this.getClass().getClassLoader().getResourceAsStream(configPath);
//		HSSFWorkbook workbook = new HSSFWorkbook(is);
		HSSFSheet sheet = getFile(configPath, sheetName);
		if (sheet == null) {
		}
		int rowCount = sheet.getPhysicalNumberOfRows();
		if (rowCount == 0) {
			return new String[0][0];
		}
		HSSFRow row = sheet.getRow(0);
		// cell[0][0]为空，此sheet跳过
		if (row.getCell(0) == null || row.getCell(0).getCellTypeEnum() == CellType.BLANK) {
			return new String[0][0];
		}

		int colunmCount = row.getLastCellNum();
		int rowNum_legal = rowCount;
		// 判断实际列，第一列出现第一个空白时，认为列结束
		Object tmp;
		for (int i = 0; i < colunmCount; i++) {
			tmp = row.getCell(i);
			if (tmp == null || "".equals(tmp.toString())) {
				colunmCount = i;
			}
		}

		String[][] data = new String[rowCount][colunmCount];
		boolean end = false;
		for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {
			if (end) {// 到达底端
				break;
			}

			row = sheet.getRow(rowIdx);
			data[rowIdx] = new String[colunmCount];
			if (row == null) {// 碰到整行为空时，直接退出
				rowNum_legal = rowIdx;
				end = true;
				break;
			}

			tmp = row.getCell(0);
			if (tmp == null || "".equals(tmp.toString())) {// 判断实际行，第一行出现第一个空白时，认为行结束
				rowNum_legal = rowIdx;
				end = true;
				break;
			}

			// 获取数据
			for (int columnIdx = 0; columnIdx < colunmCount; columnIdx++) {
				data[rowIdx][columnIdx] = this.getCellValue(row.getCell(columnIdx));
			}

		}

		// 整理数据
		if (rowNum_legal < rowCount) {
			String[][] back = new String[rowNum_legal][colunmCount];
			System.arraycopy(data, 0, back, 0, rowNum_legal);
			return back;
		} else {
			return data;
		}
	}

	private String getCellValue(HSSFCell cell) throws Exception {
		if (cell == null) {
			return "";
		}
		CellType cellTypeEnum = cell.getCellTypeEnum();
		switch (cellTypeEnum) {
		case BLANK:
			return "";
		case STRING:
			return cell.toString();
		case NUMERIC:
			return NumberToTextConverter.toText(cell.getNumericCellValue());
		case FORMULA:
		case BOOLEAN:
		case ERROR:
		default:
			throw new IllegalAccessException(String.format("cell [sheet:%s][colunm:%d][row:%d] type " + cellTypeEnum, cell.getSheet().getSheetName(),
					cell.getColumnIndex(), cell.getRowIndex()));
		}
	}
}

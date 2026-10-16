package com.blazedemo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

			
		public static Object[][] getExcelData(String filePath, String sheetName) throws IOException
		{
			File file=new File(filePath);
			FileInputStream fis=new FileInputStream(file);
			
			 XSSFWorkbook workbook=new  XSSFWorkbook(fis);
			 XSSFSheet sheet=workbook.getSheet(sheetName);
			 
			 int totalRow = sheet.getLastRowNum();             // Counts total data rows
		     int totalCol = sheet.getRow(0).getLastCellNum(); 
			 
			 Object[][] data = new Object[totalRow][totalCol];
		        DataFormatter formatter = new DataFormatter();	
		        
		        for (int i = 1; i <= totalRow; i++) { // Start at index 1 to skip the header row
		            XSSFRow row = sheet.getRow(i);
		            for (int j = 0; j < totalCol; j++) {
		                if (row == null) {
		                    data[i - 1][j] = "";
		                } else {
		                    XSSFCell cell = row.getCell(j);
		                    // Formats cell contents exactly as displayed in Excel
		                    data[i - 1][j] = formatter.formatCellValue(cell); 
		                }
		            }
		        }
		        workbook.close();
		        fis.close();
		        return data;
		}

	}



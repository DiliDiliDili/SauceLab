package Resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class excelFunction {

    public String readExcel(String cell) throws IOException{

	    FileInputStream inputStream = new FileInputStream("Book1.xlsx");
	
	    Workbook wb = null;
	
	    wb = new XSSFWorkbook(inputStream);
	   
	    String a = null;
	
	    Sheet sheet = wb.getSheet("Demo Sheet");
	
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	
	    for (int i = 0; i < rowCount+1; i++) {
	
	        Row row = sheet.getRow(i);
	
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	
	        	if(row.getCell(j) != null) {
	        		if(row.getCell(j).getCellType()==CellType.STRING) {
	        			if(row.getCell(j).getAddress().toString().equalsIgnoreCase(cell)) {
			            	
//			            	System.out.println(cell+": "+row.getCell(j).getStringCellValue());
			            	a=row.getCell(j).getStringCellValue();
			            }
	        		}else if(row.getCell(j).getAddress().toString().equalsIgnoreCase(cell)) {
        				
//	        			System.out.println(cell+": "+NumberToTextConverter.toText(row.getCell(j).getNumericCellValue()));
        				a=NumberToTextConverter.toText(row.getCell(j).getNumericCellValue());
	        		}
	        	
	        	}
	
	        }
	
	    } 
	    wb.close();
	    return a;
   
    }
  }


package org.jypj.dev.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenyu.sql.model.Column;
import com.chenyu.sql.model.Table;
import com.chenyu.sql.util.SqlUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/spring*.xml")
public class TestInitData {
	
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
    public static final String POINT = ".";
	
	
	/**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Column> readExcel(String path) throws IOException {
        if (path == null || "".equals(path)) {
            return null;
        } else {
            String postfix = this.getPostfix(path);
            if (!EMPTY.equals(postfix)) {
                if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            }
        }
        return null;
    }
	
    private List<Column> readXlsx(String path) throws IOException {
    	List<Column> cols=new ArrayList<>();
    	InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        // Read the Row
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
        	XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                XSSFCell field = xssfRow.getCell(0);
                XSSFCell type = xssfRow.getCell(1);
                XSSFCell comment = xssfRow.getCell(2);
                cols.add(new Column(getValue(field),getValue(comment),getValue(type),""));
            }
        }
        return cols;
    }

	private List<Column> readXls(String path) throws IOException {
		List<Column> cols=new ArrayList<>();
		InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        //Read the Sheet
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        // Read the Row
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
            	HSSFCell field = hssfRow.getCell(0);
            	HSSFCell type = hssfRow.getCell(1);
            	HSSFCell comment = hssfRow.getCell(2);
            	cols.add(new Column(getValue(field),getValue(comment),getValue(type),""));
            }
        }
        return cols;
	}
    
	/**
     * get postfix of the path
     * @param path
     * @return
     */
	private String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }
    
	private String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}
	
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	@Test
	public void initDate() throws IOException{
		List<Column> cols=readExcel("D:\\exportData.xls");
		Table table =new Table("eims","DGZP","RECRUIT_THEME", "招聘主题", cols);
		String sql = SqlUtil.createSqlByTable(table);
		System.out.println(sql);
	}
	
}

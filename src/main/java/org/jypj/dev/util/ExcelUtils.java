package org.jypj.dev.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 开源组件POI动态导出EXCEL文档
 * @author QiCai
 * @param  <T>泛型
 * 注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 * byte[]表jpg格式的图片数据
 */
public class ExcelUtils<T> {
	
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
    public static final String POINT = ".";
	
	public void exportExcel(String sheetName,String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel(sheetName, headers, dataset, out, "yyyy-MM-dd");
	}

   /**
    * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
    * @param  sheetName 表格标题名
    * @param  headers 表格属性列名数组
    * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
    *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
    * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
    * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
    */
	@SuppressWarnings("unchecked")
	public void exportExcel(String sheetName, String[] headers,Collection<T> dataset, 
			OutputStream out, String pattern) {
		//声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		//生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		//设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		
		//生成标题样式
		HSSFCellStyle style = setTitleStyle(workbook,null);
		//生成内容样式
		HSSFCellStyle style2 = setContentStyle(workbook);
		//声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		
		//产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		//遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			//利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					//判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					}  else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else{
						//其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					//如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if(textValue!=null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						}else{
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} 
			}
		}
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置标题头样式
	 * @param workbook
	 * @param fontSize
	 * @return
	 */
	public static HSSFCellStyle setTitleStyle(HSSFWorkbook workbook,Integer fontSize){
		//生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		//设置这些样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//生成一个字体
		HSSFFont font = workbook.createFont();
		short fsize=12;//默认字体大小
		if(fontSize != null){
			fsize=Short.valueOf(fontSize.toString()).shortValue();
		}
		font.setFontHeightInPoints(fsize);//字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//把字体应用到当前的样式
		style.setFont(font);
		style.setWrapText(true);//自动换行
		return style;
	}
	
	/**
	 * 设置内容样式
	 * @param workbook
	 * @param fontSize
	 * @return
	 */
	public static HSSFCellStyle setContentStyle(HSSFWorkbook workbook){
		//生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);//自动换行
		return style;
	}
	
	/** 
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @param sheet 要设置的sheet
     * @param textlist 下拉框显示的内容 
     * @param firstRow 开始行 
     * @param endRow 结束行 
     * @param firstCol 开始列 
     * @param endCol 结束列 
     * @return 设置好的sheet
     */  
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,  
            String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {  
        //加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        //设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        //数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
        sheet.addValidationData(data_validation_list);  
        return sheet;  
    }
    
    /**
     * 设置标题头内容
     * @param wb
     * @param sheet
     * @param title
     * @param headers
     */
    public static HSSFRow setHSSFHeader(HSSFWorkbook wb,HSSFSheet sheet,String title,String[] headers){
    	//设置表格默认列宽、行高
    	sheet.setDefaultColumnWidth(15);
    	sheet.setDefaultRowHeightInPoints(15);
    	//合并单元格 四个参数分别为：开始行开始列，结束行结束列 
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length-1));
		HSSFCellStyle titleStyle = ExcelUtils.setTitleStyle(wb,15);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (25 * 25));
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(title);  
        cell.setCellStyle(titleStyle);
        HSSFCellStyle headerStyle = ExcelUtils.setTitleStyle(wb,12);
        row = sheet.createRow(1);
        for(int i = 0; i < headers.length; i++){
        	HSSFCell hc = row.createCell(i);
        	hc.setCellStyle(headerStyle);
        	hc.setCellValue(headers[i]);
        }
        return row;
    }
    
    
    /**
     * 设置标题头内容，添加创建行参数
     * @param wb
     * @param sheet
     * @param title
     * @param name
     * @param headers
     * @param num
     * @return
     */
    public static HSSFRow setHSSFHeaderRow(HSSFWorkbook wb,HSSFSheet sheet, HSSFCellStyle nameStyle,String[] headers,Integer num,Integer count){
    	//设置表格默认列宽、行高
    	sheet.setDefaultColumnWidth(15);
    	sheet.setDefaultRowHeightInPoints(15);
    	//合并单元格 四个参数分别为：开始行开始列，结束行结束列 
    	sheet.addMergedRegion(new CellRangeAddress(num, num, 0, count));
    	HSSFRow row =null;
        row = sheet.createRow(num);
        for(int i = 0; i < headers.length; i++){
        	HSSFCell hc = row.createCell(i);
        	hc.setCellStyle(nameStyle);
        	hc.setCellValue(headers[i]);
        }
        
        return row;
    }
    
    /**
     * read the Excel file
     * @param fileName 上传文件名（包括后缀名）
     * @param is 上传文件输入流
     * @return List<Map<String, String>>
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel(String fileName,InputStream is) throws IOException {
    	List<Map<String, String>> excelData=new ArrayList<Map<String, String>>();
    	if (StringUtils.isBlank(fileName) && is ==null) {
            return excelData;
        } else {
            String postfix = getPostfix(fileName);
            if (!EMPTY.equals(postfix)) {
                if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(is);
                } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(is);
                }
            }
        }
        return excelData;
    }
	
    private static List<Map<String,String>> readXlsx(InputStream is) throws IOException {
    	List<Map<String, String>> excelData=new ArrayList<Map<String, String>>();
    	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);//Read the Sheet
        //excel格式 第一行标题行、第二行表头、第三行内容...
        XSSFRow xssfRowHeader = xssfSheet.getRow(1);//表头
        if(xssfRowHeader != null){
        	int totleRank=xssfRowHeader.getPhysicalNumberOfCells();//总列数
            Map<String ,String> data = null;
        	for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
        		data=new HashMap<String, String>();
        		XSSFRow xssfRow = xssfSheet.getRow(rowNum);
        		if (xssfRow != null) {
        			for(int i=0;i<totleRank;i++){//遍历列数
        				data.put(getValue(xssfRowHeader.getCell(i)), getValue(xssfRow.getCell(i)));
        			}
        			excelData.add(data);
        		}
        	}
        }
        return excelData;
    }
    
    private static List<Map<String,String>> readXls(InputStream is) throws IOException {
		List<Map<String, String>> excelData=new ArrayList<Map<String, String>>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);//Read the Sheet
        //excel格式 第一行标题行、第二行表头、第三行内容...
        HSSFRow hssfRowHeader = hssfSheet.getRow(1);//表头
        if(hssfRowHeader != null ){
        	int totleRank=hssfRowHeader.getPhysicalNumberOfCells();//总列数
            Map<String ,String> data = null;
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {//遍历内容行数
            	data=new HashMap<String, String>();
            	HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    for(int i=0;i<totleRank;i++){//遍历列数
                    	data.put(getValue(hssfRowHeader.getCell(i)), getValue(hssfRow.getCell(i)));
                    }
                    excelData.add(data);
                }
            }
        }
        return excelData;
	}
    
	/**
     * @param fileName
     * @return
     */
	public static String getPostfix(String fileName) {
        if (fileName == null || EMPTY.equals(fileName.trim())) {
            return EMPTY;
        }
        if (fileName.contains(POINT)) {
            return fileName.substring(fileName.lastIndexOf(POINT) + 1, fileName.length());
        }
        return EMPTY;
    }
    
	private static String getValue(XSSFCell xssfRow) {
		if(xssfRow !=null ){
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}
		}
		return "";
	}
	
	private static String getValue(HSSFCell hssfCell) {
		if(hssfCell !=null ){
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
		return "";
	}
	
	/**
	 * 设置标题头样式
	 * @param workbook
	 * @param fontSize
	 * @return
	 */
	public static HSSFCellStyle setThStyle(HSSFWorkbook workbook,Integer fontSize){
		//生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		//设置这些样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		//生成一个字体
		HSSFFont font = workbook.createFont();
		short fsize=12;//默认字体大小
		if(fontSize != null){
			fsize=Short.valueOf(fontSize.toString()).shortValue();
		}
		font.setFontHeightInPoints(fsize);//字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//把字体应用到当前的样式
		style.setFont(font);
		style.setWrapText(true);//自动换行
		return style;
	}
	
	public static void setResponseHeader(HttpServletRequest request,HttpServletResponse response, String fileName) {  
		try {  
			 String userAgent = request.getHeader("User-Agent").toLowerCase();
			 fileName = userAgent.indexOf("firefox")> 0 ? new String(fileName.getBytes(),"ISO8859-1") : URLEncoder.encode(fileName, "UTF8");
			 String charset=userAgent.indexOf("firefox")> 0 ? "ISO8859-1":"UTF-8";
			 response.setContentType("application/octet-stream;charset="+charset);  
		     response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
		     response.addHeader("Pargam", "no-cache");  
		     response.addHeader("Cache-Control", "no-cache");  
		} catch (UnsupportedEncodingException e) {  
		     e.printStackTrace();  
		}
	}
}
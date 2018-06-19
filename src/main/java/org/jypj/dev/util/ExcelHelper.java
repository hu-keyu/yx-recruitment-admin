package org.jypj.dev.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * Excel导入导出帮助类
 */
public class ExcelHelper {

	protected static Log logger = LogFactory.getLog(ExcelHelper.class);
	
	public static Map tNames = new HashMap(); 
	
	public ExcelHelper(){
		
	}
	
	/**
	 * 
	 * 用于导出保存excel方法
	 * @param response
	 * @param excelName：文件名称，可为中文
	 * @param filePath：从服务器获取文件全路径
	 */
	public static void outputExcel(HttpServletResponse response, String excelName,
			String filePath) {
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			
			//Word对应的就是 application/msword
			String fileName = new String(excelName.getBytes("gb2312"), "ISO-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(filePath));
				bos = new BufferedOutputStream(response.getOutputStream());
				
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bos.flush();
				
			} catch (final IOException e) {
				e.printStackTrace();
				logger.debug("出现IOException." + e.getMessage());
			} finally {
				if (bis != null){
					bis.close();
					bis = null;
				}
				if (bos != null){
					bos.close();
					bos = null;
				}
			}
			return;
		} catch (Exception e) {
			logger.debug(e);
		}
	}
	public static void outputExcelToLocal(String excelName,
			String filePath) {
		try {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			java.io.FileOutputStream fos=null;
			try {
				bis = new BufferedInputStream(new FileInputStream(filePath));
				fos= new java.io.FileOutputStream("c:\\"+excelName+".xls");
				//bos = new BufferedOutputStream(response.getOutputStream());
				
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					fos.write(buff, 0, bytesRead);
				}
				fos.flush();
				
			} catch (final IOException e) {
				e.printStackTrace();
				logger.debug("出现IOException." + e.getMessage());
			} finally {
				if (bis != null){
					bis.close();
					bis = null;
				}
				if (fos != null){
					fos.close();
					fos = null;
				}
			}
			return;
		} catch (Exception e) {
			logger.debug(e);
		}
	}
	/**
	 * 
	 * 导入指定excel文件的数据，必须按照规定模板进行导入
	 * @param fileName：excel导入的文件名称类型，如：用户信息等
	 * @return List<Map>：每行数据为一个Map<表头名称,对应值>
	 */
	public static List<Map<String,String>> inputExcel(HttpServletRequest request,String savePath){
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		Workbook rwb = null; 
		InputStream is = null;
		try {
			//构建Workbook对象 只读Workbook对象 
			//直接从本地文件创建Workbook 
			//从输入流创建Workbook 
			is = new FileInputStream(savePath); 
			WorkbookSettings workbooksetting = new WorkbookSettings();
			workbooksetting .setCellValidationDisabled(true);
			rwb = Workbook.getWorkbook(is,workbooksetting); 
			//Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中 
			//Sheet的下标是从0开始的 
			//获取第一张Sheet表 
			Sheet rs = rwb.getSheet(0); 
			//获取Sheet表中所包含的总列数 
			int rsColumns = rs.getColumns(); 
			//获取Sheet表中所包含的总行数 
			int rsRows = rs.getRows(); 

			//excel列模板标准
			//根据列名将数据存放到map中
			Map<String,String> datas = null;
			//第二行，初始化map
			Cell keyCell = null; 
			//从第三行开始(即销售计划数据开始)
			if(rsRows < 3){
				logger.error("inputExcel Fail:input File is can not analysis !");
				return null;
			}
			for(int i = 3;i < rsRows;i++){ 
				datas = new HashMap<String,String>();
				for(int j = 0;j < rsColumns;j++){ 
					Cell cell = rs.getCell(j,i);
					keyCell = rs.getCell(j,2);
					String key = keyCell.getContents();
					if(key == null || key.trim().equals("")) continue;
					Object value = cell.getContents();
					if(value == null){
						value = "";
					}
					//根据列名将数据存放到map中
					datas.put(key, value.toString().trim());
				} 
				if(!datas.isEmpty()){
					results.add(datas);
				}
			}
		} catch (Exception e) {
			//  handle exception
			e.printStackTrace();
		}finally{
			if(rwb != null){
				  rwb.close();
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return results;
	}
	
	public static Map<String,List<Map<String,String>>> parseExcel(String savePath,int titleRowNum,int dataRowNum){
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		Map sheetsData=new HashMap();
		Workbook rwb = null;
		InputStream is = null;
		try {
			//构建Workbook对象 只读Workbook对象 
			//直接从本地文件创建Workbook 
			//从输入流创建Workbook 
			is = new FileInputStream(savePath); 
			rwb = Workbook.getWorkbook(is); 
			//Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中 
			//Sheet的下标是从0开始的 
			//获取第一张Sheet表 
			int sheetsNum=rwb.getSheets().length;
			for(int k=0;k<sheetsNum;k++){
				Sheet rs = rwb.getSheet(k); 
				//获取Sheet表中所包含的总列数 
				int rsColumns = rs.getColumns(); 
				//获取Sheet表中所包含的总行数 
				int rsRows = rs.getRows(); 
	
				//excel列模板标准
				//根据列名将数据存放到map中
				Map<String,String> datas = null;
				//第二行，初始化map
				Cell keyCell = null; 
				//从第三行开始(即销售计划数据开始)
				if(rsRows < 3){
					logger.error("inputExcel Fail:input File is can not analysis !");
					return null;
				}
				for(int i = dataRowNum;i < rsRows;i++){ 
					datas = new HashMap<String,String>();
					for(int j = 0;j < rsColumns;j++){ 
						Cell cell = rs.getCell(j,i);
						keyCell = rs.getCell(j,titleRowNum);
						String key = keyCell.getContents();
						if(key == null || key.trim().equals("")) continue;
						Object value = cell.getContents();
						if(value == null){
							value = "";
						}
						//根据列名将数据存放到map中
						datas.put(key, value.toString().trim());
					} 
					if(!datas.isEmpty()){
						results.add(datas);
					}
				 }
				sheetsData.put(rs.getName(), results);
			}
			
		} catch (Exception e) {
			//  handle exception
			e.printStackTrace();
		}finally{
			if(rwb != null){
				  rwb.close();
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sheetsData;
	}
	
	
	/**
	 * 单元格数据样式
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getWfTable2(){
		WritableCellFormat wfTable2 = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 9,
						WritableFont.NO_BOLD, false));
		try {
			wfTable2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wfTable2.setAlignment(Alignment.LEFT);
			wfTable2.setWrap(true);
			
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return wfTable2;
	}
	
	/**
	 * 居中显示
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getWfTable3(){
		WritableCellFormat wfTable3 = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 12,
						WritableFont.NO_BOLD, false));
		try {
			wfTable3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wfTable3.setAlignment(jxl.format.Alignment.CENTRE);
			
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return wfTable3;
	}
	
	public static WritableCellFormat getWfTableTen(){
		WritableCellFormat wfTable2 = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 10,
						WritableFont.NO_BOLD, false));
		try {
			wfTable2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wfTable2.setAlignment(Alignment.LEFT);
			
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return wfTable2;
	}
	
	/**
	 * 返回表头样式
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getTitleTable(){
		WritableCellFormat titleTable = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 14,
						WritableFont.BOLD, false));
		try {
			titleTable.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			titleTable.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return titleTable;
	}
	
	public static WritableCellFormat getTitleTable1(){
		WritableCellFormat titleTable = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 11,
						WritableFont.BOLD, false));
		try {
			titleTable.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			titleTable.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return titleTable;
	}
	
	/**
	 * 合计行的样式
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getHeJiTable(){
		WritableCellFormat titleTable = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 12,
						WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE,Colour.RED));
		try {
			titleTable.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			//titleTable.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return titleTable;
	}
	
	
	/**
	 * 居中显示
	 * row 第几行,cols 第几列,str 要显示的数据
	 * 行和列都是从0开始的，如果需要往第二行添加数据是，row应该等于1
	 * 
	 * @param row
	 * @param cols
	 * @param str
	 * @return: 
	 */
	public static Label setCenterXs(Label label){
//		单元格居中
	    WritableCellFormat cellFormat=new WritableCellFormat(
	    		new WritableFont(WritableFont.createFont("宋体"), 14,
						WritableFont.BOLD, false));
	    try {
			cellFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
		    label.setCellFormat(cellFormat);
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	    return label;
	}
	
	public static Label setCenterTitle(Label label){
//		单元格居中
	    WritableCellFormat cellFormat=new WritableCellFormat(
	    		new WritableFont(WritableFont.createFont("宋体"), 18,
						WritableFont.BOLD, false));
	    try {
			cellFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		    label.setCellFormat(cellFormat);
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	    return label;
	}

	public static Label setCenterSmallTitle(Label label){
//		单元格居中
	    WritableCellFormat cellFormat=new WritableCellFormat(
	    		new WritableFont(WritableFont.createFont("宋体"), 14,
						WritableFont.BOLD, false));
	    try {
			cellFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		    label.setCellFormat(cellFormat);
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	    return label;
	}
	
	/**
	 * 单元格换行并居中显示
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getWfTableHh(){
			
		WritableCellFormat wfTable3 = new WritableCellFormat(
					new WritableFont(WritableFont.createFont("宋体"), 12,
							WritableFont.NO_BOLD, false));
		try {
			wfTable3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wfTable3.setAlignment(jxl.format.Alignment.CENTRE);
			wfTable3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wfTable3.setWrap(true);// 换行的lebel样式
			
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return wfTable3;
	}
	
	/**
	 * 表头样式，居中显示
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getTitleTable2(){
		WritableCellFormat titleTable = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 14,
						WritableFont.BOLD, false));
		try {
			titleTable.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			titleTable.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
			titleTable.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return titleTable;
	}
	
	/**
	 * 标题样式，无背景，加粗字体
	 * 
	 * @return: 
	 */
	public static WritableCellFormat getTitleTable3(){
		WritableCellFormat titleTable = new WritableCellFormat(
				new WritableFont(WritableFont.createFont("宋体"), 18,
						WritableFont.BOLD, false));
		try {
			titleTable.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			//titleTable.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
			titleTable.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return titleTable;
	}
	
	/**
	 * 表头样式 加粗居中
	 * @return: 
	 */
	public static WritableCellFormat getTitleTable4(){
			
		WritableCellFormat wfTable3 = new WritableCellFormat(
					new WritableFont(WritableFont.createFont("宋体"), 10,
							WritableFont.BOLD, false));
		try {
			wfTable3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wfTable3.setAlignment(jxl.format.Alignment.CENTRE);
			wfTable3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wfTable3.setWrap(true);// 换行的lebel样式
			
		} catch (WriteException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		return wfTable3;
	}
}

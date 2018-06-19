package org.jypj.dev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jypj.dev.dao.ApplicantsDao;
import org.jypj.dev.service.ApplicantsService;
import org.jypj.dev.util.ExcelUtils;
import org.jypj.dev.vo.ApplicantsVo;
import org.springframework.stereotype.Service;

@Service("applicantsService")
public class ApplicantsServiceImpl implements ApplicantsService {
	@Resource
	private ApplicantsDao applicantsDao;

	@Override
	public List<ApplicantsVo> selectAllByApplicantsVo(ApplicantsVo applicantsVo) {
		return applicantsDao.selectAllByApplicantsVo(applicantsVo);
	}

	@Override
	public List<ApplicantsVo> selectAllByItemsId(ApplicantsVo applicantsVo) {
		return applicantsDao.selectAllByItemsId(applicantsVo);
	}

	@Override
	public List<ApplicantsVo> selectPeopleCountByItemsId(ApplicantsVo applicantsVo) {
		return applicantsDao.selectPeopleCountByItemsId(applicantsVo);
	}
	
	/**
	 * 设置招聘比例统计表表头
	 * @param wb
	 * @param sheet
	 * @param title
	 * @param headers
	 */
	public HSSFRow setRationHeader(HSSFWorkbook wb, HSSFSheet sheet, String title) {
		// 设置表格默认列宽、行高
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(15);
		// 合并单元格 四个参数分别为：开始行开始列，结束行结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 14));
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 5));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 13));
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 14, 14));
		HSSFCellStyle titleStyle = ExcelUtils.setTitleStyle(wb, 15);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (25 * 25));
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(titleStyle);

		HSSFCellStyle headerStyle = ExcelUtils.setThStyle(wb, 12);
		row = sheet.createRow(3);
		HSSFCell hc = row.createCell(0);
		hc.setCellValue("招聘项目");
		hc.setCellStyle(headerStyle);
		
		hc = row.createCell(1);
		hc.setCellValue("总人数");
		hc.setCellStyle(headerStyle);
		
		hc = row.createCell(2);
		hc.setCellValue("性别");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(6);
		hc.setCellValue("学历");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(14);
		hc.setCellValue("备注");
		hc.setCellStyle(headerStyle);
		
		row = sheet.createRow(4);
		hc = row.createCell(2);
		hc.setCellValue("男");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(3);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(4);
		hc.setCellValue("女");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(5);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(6);
		hc.setCellValue("研究生及硕士");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(7);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(8);
		hc.setCellValue("本科");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(9);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(10);
		hc.setCellValue("大专");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(11);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(12);
		hc.setCellValue("中师");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(13);
		hc.setCellValue("占总人数%");
		hc.setCellStyle(headerStyle);

		hc = row.createCell(14);
		hc.setCellValue("备注");
		hc.setCellStyle(headerStyle);
		return row;
	}
}

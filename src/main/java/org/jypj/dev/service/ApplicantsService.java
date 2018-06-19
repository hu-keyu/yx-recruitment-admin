package org.jypj.dev.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jypj.dev.vo.ApplicantsVo;

public interface ApplicantsService {

	public List<ApplicantsVo> selectAllByApplicantsVo(ApplicantsVo applicantsVo);

	public List<ApplicantsVo> selectAllByItemsId(ApplicantsVo applicantsVo);

	public List<ApplicantsVo> selectPeopleCountByItemsId(ApplicantsVo applicantsVo);

	public HSSFRow setRationHeader(HSSFWorkbook wb, HSSFSheet sheet, String title);
}

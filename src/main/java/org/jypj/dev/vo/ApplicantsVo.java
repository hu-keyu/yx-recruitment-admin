package org.jypj.dev.vo;

import java.math.BigDecimal;

/**
 * 报考人数统计
 * 
 * @author qi_ma
 *
 */
public class ApplicantsVo {
	/**
	 * 主题ID
	 */
	private String itemsId;
	/**
	 * 主题名称
	 */
	private String itemsName;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 主题对应年份
	 */
	private String year;
	/**
	 * 岗位报考人数(人数)
	 */
	private Integer totalCount;

	/**
	 * 男(人数)
	 */
	private Integer menCount;

	/**
	 * 男(比例)
	 */
	private BigDecimal menScale;
	/**
	 * 女(人数)
	 */
	private Integer womenCount;
	/**
	 * 女(比例)
	 */
	private BigDecimal womenScale;
	/**
	 * 研究生(人数)
	 */
	private Integer graduateCount;
	/**
	 * 研究生(比例)
	 */
	private BigDecimal graduateScale;
	/**
	 * 本科(人数)
	 */
	private Integer bachelorCount;
	/**
	 * 本科(比例)
	 */
	private BigDecimal bachelorScale;
	/**
	 * 大专(人数)
	 */
	private Integer collegeCount;
	/**
	 * 大专(比例)
	 */
	private BigDecimal collegeScale;
	/**
	 * 中师(人数)
	 */
	private Integer secondaryCount;
	/**
	 * 中师(比例)
	 */
	private BigDecimal secondaryScale;
	/**
	 * 岗位数
	 */
	private Integer jobCount;
	/**
	 * 报考人数
	 */
	private Integer stuCount;
	/**
	 * 面试人数
	 */
	private Integer interviewCount;
	/**
	 * 进入统一笔试人数
	 */
	private Integer writtenCount;
	/**
	 * 进入统一试讲人数
	 */
	private Integer trialCount;

	public Integer getBachelorCount() {
		return bachelorCount;
	}

	public BigDecimal getBachelorScale() {
		return bachelorScale;
	}

	public Integer getCollegeCount() {
		return collegeCount;
	}

	public BigDecimal getCollegeScale() {
		return collegeScale;
	}

	public Integer getGraduateCount() {
		return graduateCount;
	}

	public BigDecimal getGraduateScale() {
		return graduateScale;
	}

	public Integer getInterviewCount() {
		return interviewCount;
	}

	public String getItemsId() {
		return itemsId;
	}

	public String getItemsName() {
		return itemsName;
	}

	public Integer getJobCount() {
		return jobCount;
	}

	public Integer getMenCount() {
		return menCount;
	}

	public BigDecimal getMenScale() {
		return menScale;
	}

	public String getPostName() {
		return postName;
	}

	public Integer getSecondaryCount() {
		return secondaryCount;
	}

	public BigDecimal getSecondaryScale() {
		return secondaryScale;
	}

	public Integer getStuCount() {
		return stuCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getTrialCount() {
		return trialCount;
	}

	public Integer getWomenCount() {
		return womenCount;
	}

	public BigDecimal getWomenScale() {
		return womenScale;
	}

	public Integer getWrittenCount() {
		return writtenCount;
	}

	public String getYear() {
		return year;
	}

	public void setBachelorCount(Integer bachelorCount) {
		this.bachelorCount = bachelorCount;
	}

	public void setBachelorScale(BigDecimal bachelorScale) {
		this.bachelorScale = bachelorScale;
	}

	public void setCollegeCount(Integer collegeCount) {
		this.collegeCount = collegeCount;
	}

	public void setCollegeScale(BigDecimal collegeScale) {
		this.collegeScale = collegeScale;
	}

	public void setGraduateCount(Integer graduateCount) {
		this.graduateCount = graduateCount;
	}

	public void setGraduateScale(BigDecimal graduateScale) {
		this.graduateScale = graduateScale;
	}

	public void setInterviewCount(Integer interviewCount) {
		this.interviewCount = interviewCount;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public void setJobCount(Integer jobCount) {
		this.jobCount = jobCount;
	}

	public void setMenCount(Integer menCount) {
		this.menCount = menCount;
	}

	public void setMenScale(BigDecimal menScale) {
		this.menScale = menScale;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public void setSecondaryCount(Integer secondaryCount) {
		this.secondaryCount = secondaryCount;
	}

	public void setSecondaryScale(BigDecimal secondaryScale) {
		this.secondaryScale = secondaryScale;
	}

	public void setStuCount(Integer stuCount) {
		this.stuCount = stuCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public void setTrialCount(Integer trialCount) {
		this.trialCount = trialCount;
	}

	public void setWomenCount(Integer womenCount) {
		this.womenCount = womenCount;
	}

	public void setWomenScale(BigDecimal womenScale) {
		this.womenScale = womenScale;
	}

	public void setWrittenCount(Integer writtenCount) {
		this.writtenCount = writtenCount;
	}

	public void setYear(String year) {
		this.year = year;
	}

}

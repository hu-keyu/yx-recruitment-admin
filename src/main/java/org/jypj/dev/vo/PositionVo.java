package org.jypj.dev.vo;

import java.util.List;

import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.entity.Specialty;

public class PositionVo {
	
	private String id;
	private String schoolName;//单位名称
	private String postName;//岗位名称
	private String subject;//学科代码
	private String approveCount;//招聘人员
	private String limitRecruit;//招聘对象
	private String age;//年龄
	private String education;//学历
	private String degree;//学位
	private String otherLimit;//其他条件限制
	private String category;//岗位类别
	private String remark;//备注
	private List<PositionDomain> positionDomains;//学校岗位专业限制
	private List<Specialty> specialtys;//教育局岗位专业限制
	private String graduate;//招聘对象毕业生
	private String social;//招聘对象社会人员
	private String profession;//岗位限制的专业
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getApproveCount() {
		return approveCount;
	}
	public void setApproveCount(String approveCount) {
		this.approveCount = approveCount;
	}
	public String getLimitRecruit() {
		return limitRecruit;
	}
	public void setLimitRecruit(String limitRecruit) {
		this.limitRecruit = limitRecruit;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getOtherLimit() {
		return otherLimit;
	}
	public void setOtherLimit(String otherLimit) {
		this.otherLimit = otherLimit;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public List<PositionDomain> getPositionDomains() {
		return positionDomains;
	}
	public void setPositionDomains(List<PositionDomain> positionDomains) {
		this.positionDomains = positionDomains;
	}
	public List<Specialty> getSpecialtys() {
		return specialtys;
	}
	public void setSpecialtys(List<Specialty> specialtys) {
		this.specialtys = specialtys;
	}
	public String getGraduate() {
		return graduate;
	}
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	public String getSocial() {
		return social;
	}
	public void setSocial(String social) {
		this.social = social;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}

package org.jypj.dev.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.util.StringUtil;

/**
 * 学校招聘岗位信息表
 * @author
 */
public class Position implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
    private String id; //主键ID
    private String projectId; //招聘主题ID
    private String planApplyId; //招聘计划申报表ID
    private String stationId; //岗位Id
    private String status; //岗位状态（默认1启动 2取消）
    private BigDecimal subjectHour; //科目课时
    private BigDecimal subjectCount; //岗位应设人数
    private BigDecimal subjectCurrentCount; //岗位现有人数
    private BigDecimal subjectVacancyCount; //岗位空缺人数
    private Integer subjectPlanCount; //岗位计划招聘人数
    private Integer subjectApproveCount; //岗位审批招聘人数(对应的就是教育局的审批招聘人数)
    private String isLimitAge; //是否有年龄限制（1有限制 0无限制）
    private BigDecimal limitAge; //限制的年龄
    private String limitAgeConditition; //年龄限制条件（0及以上 1及以下）
    private String isLimitEducation; //是否有学历限制（1有限制 0无限制）
    private String limitEducation; //限制的学历
    private String limitEducationConditition; //学历限制条件（0及以上 1及以下）
    private String isLimitEdu; //是否教育类型限制（1有限制 0无限制）
    private String limitEdu; //限制的教育类型
    private String limitEduConditition; //教育类型限制条件
    private String isLimitDegree; //是否学位限制（1有限制 0无限制）
    private String limitDegree; //限制的学位
    private String limitDegreeConditition; //学位限制条件
    private String isLimitRecruit; //是否招聘对象限制（1有限制 0无限制）
    private String limitRecruit; //招聘对象
    private String isEducationStudent; //是否东莞生源免费师范生（1是 0否）
    private String remark; //岗位备注说明
    private String otherLimit; //学校其他限制条件
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String schoolId; //学校ID
    private String isLimitProfession;//是否专业要求限制
    
    //非数据库映射字段
    private String limitEducationText;//学历
    private String limitEduText;//教育类型
    private String limitDegreeText;//学位
    private String limitRecruitText;//招聘对象
    private String postName;//岗位名称
    private String postSummary;//岗位简介
    private String subjectText;//科目名称
    private String limitProfession;//岗位限制的专业Code
    private String limitProfessionText;//岗位限制的专业名称
    private List<PositionDomain> positionDomains;//岗位对应的专业
    private String enterCondition;//招聘公告中的录取形式 1比例和分数 2比例 3分数
    private String interviewEnterpPropo;//面试录取比例
    private String applyCount;//岗位报名人数
    private String schoolName;//学校名称
    private String posCondition;//岗位限制字符串
    
    /**
     * 招聘主题名称 扩展字段
     */
    private String themeName;

    private String postsetName ; //额外添加的字段，岗位名称
    
    /**
     * 单位面试新和现场资料确认信息
     */
    private String profileSubmitTime;
    
    private String profileSubmitSite;
    
    private String interviewTime;
    
    private String interviewSite;
    
    public String getProfileSubmitTime() {
        return profileSubmitTime;
    }

    public void setProfileSubmitTime(String profileSubmitTime) {
        this.profileSubmitTime = profileSubmitTime;
    }
    
    
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getPostSummary() {
        return postSummary;
    }

    public void setPostSummary(String postSummary) {
        this.postSummary = postSummary;
    }

    public String getProfileSubmitSite() {
        return profileSubmitSite;
    }

    public void setProfileSubmitSite(String profileSubmitSite) {
        this.profileSubmitSite = profileSubmitSite;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewSite() {
        return interviewSite;
    }

    public void setInterviewSite(String interviewSite) {
        this.interviewSite = interviewSite;
    }

    public String getPosCondition() {
        return posCondition;
    }

    public void setPosCondition(String posCondition) {
        this.posCondition = posCondition;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getProjectId(){
        return this.projectId;
    }
    
    public void setProjectId(String projectId){
        this.projectId = projectId;
    }
    public String getPlanApplyId(){
        return this.planApplyId;
    }
    
    public void setPlanApplyId(String planApplyId){
        this.planApplyId = planApplyId;
    }
    public String getStationId(){
        return this.stationId;
    }
    
    public void setStationId(String stationId){
        this.stationId = stationId;
    }
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    public BigDecimal getSubjectHour(){
        return this.subjectHour;
    }
    
    public void setSubjectHour(BigDecimal subjectHour){
        this.subjectHour = subjectHour;
    }
    public BigDecimal getSubjectCount(){
        return this.subjectCount;
    }
    
    public void setSubjectCount(BigDecimal subjectCount){
        this.subjectCount = subjectCount;
    }
    public BigDecimal getSubjectCurrentCount(){
        return this.subjectCurrentCount;
    }
    
    public void setSubjectCurrentCount(BigDecimal subjectCurrentCount){
        this.subjectCurrentCount = subjectCurrentCount;
    }
    public BigDecimal getSubjectVacancyCount(){
        return this.subjectVacancyCount;
    }
    
    public void setSubjectVacancyCount(BigDecimal subjectVacancyCount){
        this.subjectVacancyCount = subjectVacancyCount;
    }
    public Integer getSubjectPlanCount(){
        return this.subjectPlanCount;
    }
    
    public void setSubjectPlanCount(Integer subjectPlanCount){
        this.subjectPlanCount = subjectPlanCount;
    }
    public Integer getSubjectApproveCount(){
        return this.subjectApproveCount;
    }
    
    public void setSubjectApproveCount(Integer subjectApproveCount){
        this.subjectApproveCount = subjectApproveCount;
    }
    public String getIsLimitAge(){
        return this.isLimitAge;
    }
    
    public void setIsLimitAge(String isLimitAge){
        this.isLimitAge = isLimitAge;
    }
    public BigDecimal getLimitAge(){
        return this.limitAge;
    }
    
    public void setLimitAge(BigDecimal limitAge){
        this.limitAge = limitAge;
    }
    public String getLimitAgeConditition(){
        return this.limitAgeConditition;
    }
    
    public void setLimitAgeConditition(String limitAgeConditition){
        this.limitAgeConditition = limitAgeConditition;
    }
    public String getIsLimitEducation(){
        return this.isLimitEducation;
    }
    
    public void setIsLimitEducation(String isLimitEducation){
        this.isLimitEducation = isLimitEducation;
    }
    public String getLimitEducation(){
        return this.limitEducation;
    }
    
    public void setLimitEducation(String limitEducation){
        this.limitEducation = limitEducation;
    }
    public String getLimitEducationConditition(){
        return this.limitEducationConditition;
    }
    
    public void setLimitEducationConditition(String limitEducationConditition){
        this.limitEducationConditition = limitEducationConditition;
    }
    public String getIsLimitEdu(){
        return this.isLimitEdu;
    }
    
    public void setIsLimitEdu(String isLimitEdu){
        this.isLimitEdu = isLimitEdu;
    }
    public String getLimitEdu(){
        return this.limitEdu;
    }
    
    public void setLimitEdu(String limitEdu){
        this.limitEdu = limitEdu;
    }
    public String getLimitEduConditition(){
        return this.limitEduConditition;
    }
    
    public void setLimitEduConditition(String limitEduConditition){
        this.limitEduConditition = limitEduConditition;
    }
    public String getIsLimitDegree(){
        return this.isLimitDegree;
    }
    
    public void setIsLimitDegree(String isLimitDegree){
        this.isLimitDegree = isLimitDegree;
    }
    public String getLimitDegree(){
        return this.limitDegree;
    }
    
    public void setLimitDegree(String limitDegree){
        this.limitDegree = limitDegree;
    }
    public String getLimitDegreeConditition(){
        return this.limitDegreeConditition;
    }
    
    public void setLimitDegreeConditition(String limitDegreeConditition){
        this.limitDegreeConditition = limitDegreeConditition;
    }
    public String getIsLimitRecruit(){
        return this.isLimitRecruit;
    }
    
    public void setIsLimitRecruit(String isLimitRecruit){
        this.isLimitRecruit = isLimitRecruit;
    }
    public String getLimitRecruit(){
        return this.limitRecruit;
    }
    
    public void setLimitRecruit(String limitRecruit){
        this.limitRecruit = limitRecruit;
    }
    public String getIsEducationStudent(){
        return this.isEducationStudent;
    }
    
    public void setIsEducationStudent(String isEducationStudent){
        this.isEducationStudent = isEducationStudent;
    }
    public String getRemark(){
        return this.remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getOtherLimit(){
        return this.otherLimit;
    }
    
    public void setOtherLimit(String otherLimit){
        this.otherLimit = otherLimit;
    }
    public Date getCreatetime(){
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }
    public Date getModifytime(){
        return this.modifytime;
    }
    
    public void setModifytime(Date modifytime){
        this.modifytime = modifytime;
    }
    public String getCreateuser(){
        return this.createuser;
    }
    
    public void setCreateuser(String createuser){
        this.createuser = createuser;
    }
    public String getModifyuser(){
        return this.modifyuser;
    }
    
    public void setModifyuser(String modifyuser){
        this.modifyuser = modifyuser;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }

	public String getLimitEducationText() {
		return limitEducationText;
	}

	public void setLimitEducationText(String limitEducationText) {
		this.limitEducationText = limitEducationText;
	}

	public String getLimitEduText() {
		return limitEduText;
	}

	public void setLimitEduText(String limitEduText) {
		this.limitEduText = limitEduText;
	}

	public String getLimitDegreeText() {
		return limitDegreeText;
	}

	public void setLimitDegreeText(String limitDegreeText) {
		this.limitDegreeText = limitDegreeText;
	}

	public String getLimitRecruitText() {
		return limitRecruitText;
	}

	public void setLimitRecruitText(String limitRecruitText) {
		this.limitRecruitText = limitRecruitText;
	}

	public String getIsLimitProfession() {
		return isLimitProfession;
	}

	public void setIsLimitProfession(String isLimitProfession) {
		this.isLimitProfession = isLimitProfession;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	public String getLimitProfession() {
		return limitProfession;
	}

	public void setLimitProfession(String limitProfession) {
		this.limitProfession = limitProfession;
	}

	public String getLimitProfessionText() {
		return limitProfessionText;
	}

	public void setLimitProfessionText(String limitProfessionText) {
		this.limitProfessionText = limitProfessionText;
	}

	public List<PositionDomain> getPositionDomains() {
		return positionDomains;
	}

	public void setPositionDomains(List<PositionDomain> positionDomains) {
		this.positionDomains = positionDomains;
	}

	public String getEnterCondition() {
		return enterCondition;
	}

	public void setEnterCondition(String enterCondition) {
		this.enterCondition = enterCondition;
	}

	public String getInterviewEnterpPropo() {
		return interviewEnterpPropo;
	}

	public void setInterviewEnterpPropo(String interviewEnterpPropo) {
		this.interviewEnterpPropo = interviewEnterpPropo;
	}

	public String getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}
	

    public String generateCondition() {
        StringBuilder sb = new StringBuilder();
        String temp ="";
        if (StringUtil.isNotEmpty(this.isLimitAge) && this.isLimitAge.equals("1")) {
            sb.append("年龄").append(this.limitAgeConditition.equals(">=") ? "大于或等于" : "小于或等于").append(this.limitAge).append("；");
        }
        
        if (StringUtil.isNotEmpty(this.limitEducation) && this.isLimitEducation.equals("1")) {
            List<Dictionary> dicList = DictionaryCache.getDictionaryByCode("xllx");
            temp = "";
            for (Dictionary dic : dicList) {
                if (dic.getValue().equals(this.limitEducation)) {
                    temp = dic.getText();
                }
            }
            sb.append("学历").append(this.limitEducationConditition.equals(">=") ? "大于或等于" : "小于或等于").append(temp).append("；");
        }

        if (StringUtil.isNotEmpty(this.limitEdu) && this.isLimitEdu.equals("1")) {
            List<Dictionary> dicList = DictionaryCache.getDictionaryByCode("jylx");
            temp = "";
            for (Dictionary dic : dicList) {
                if (dic.getValue().equals(this.limitEdu)) {
                    temp = dic.getText();
                }
            }
            sb.append("教育类型").append(this.limitEduConditition.equals(">=") ? "大于或等于" : "小于或等于")
                    .append(temp).append("；");
        }
        
        
        if (StringUtil.isNotEmpty(this.limitDegree) && this.isLimitDegree.equals("1")) {
            List<Dictionary> dicList = DictionaryCache.getDictionaryByCode("xwlx");
            temp = "";
            for (Dictionary dic : dicList) {
                if (dic.getValue().equals(this.limitDegree)) {
                    temp = dic.getText();
                }
            }
            sb.append("学位").append(this.limitDegreeConditition.equals(">=") ? "大于或等于" : "小于或等于")
                    .append(temp).append("；");
        }
        
        if (StringUtil.isNotEmpty(this.getIsLimitRecruit()) && this.getIsLimitRecruit().equals("1")) {
            List<Dictionary> dicList = DictionaryCache.getDictionaryByCode("bylx");
            temp = "";
            for (Dictionary dic : dicList) {
                if (dic.getValue().equals(this.limitRecruit)) {
                    temp = dic.getText();
                }
            }
            if ("".equals(temp)) {
                temp = "全部";
            }
            sb.append("招聘对象：").append(temp).append("；");
        }
        
        if (StringUtil.isNotEmpty(this.isEducationStudent) && this.isEducationStudent.equals("1")) {
            sb.append("要求东莞生源免费师范生；");
        }
        
        //学校其他限制条件
        if (StringUtil.isNotEmpty(this.otherLimit)) {
            sb.append("学校其他限制条件：");
            sb.append(this.otherLimit).append("；");
        }
        
        //岗位备注说明remark
        if (StringUtil.isNotEmpty(this.remark)) {
            sb.append("岗位备注说明：");
            sb.append(this.remark).append("；");
        }
        
        if (StringUtil.isNotEmpty(this.getIsLimitProfession()) && this.getIsLimitProfession().equals("1")) {
            sb.append("专业包括：");
            for (PositionDomain pd : this.positionDomains) {
                sb.append(pd.getDomainName()).append("、");
            }
        }
        
        return sb.substring(0, sb.length() - 1);
        
    }

    public String getPostsetName() {
        return postsetName;
    }

    public void setPostsetName(String postsetName) {
        this.postsetName = postsetName;
    }
}
package org.jypj.dev.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 招聘计划申报表
 * @author
 */
public class PlanApply implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String projectId; //招聘主题ID
    private String status; //申报状态（默认0未上报 1待审批 2已审批）
    private String projectPublishWork; //招聘主题发布单位ID
    private String schoolId; //申报学校ID
    private String year; //招聘年份
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark;//备注
    
    private Theme themeApply;//一个学校的招聘计划对应一个招聘主题
    private String schoolCode ; //额外添加的属性，学校代码对应学校字典里面的code
    private String schoolName ;//额外添加的属性，学校名称
    private int isNoticePub ;//额外添加的字段，对应招聘公告是否发布，因为招聘公告发布之后就不能修改审批
    private List<Position> positionList = new ArrayList<Position>();//额外添加的属性
    private String projectPublishName;//招聘主题发布名称
    private List<Grade> grades=new ArrayList<Grade>();//学生成绩
    private Date interviewStartDate;//面试开始时间
    
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
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    public String getProjectPublishWork(){
        return this.projectPublishWork;
    }
    
    public void setProjectPublishWork(String projectPublishWork){
        this.projectPublishWork = projectPublishWork;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
    public String getYear(){
        return this.year;
    }
    
    public void setYear(String year){
        this.year = year;
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
    public String getRemark(){
        return this.remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }

	public Theme getThemeApply() {
		return themeApply;
	}

	public void setThemeApply(Theme themeApply) {
		this.themeApply = themeApply;
	}

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getIsNoticePub() {
        return isNoticePub;
    }

    public void setIsNoticePub(int isNoticePub) {
        this.isNoticePub = isNoticePub;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
    
	public String getProjectPublishName() {
		return projectPublishName;
	}

	public void setProjectPublishName(String projectPublishName) {
		this.projectPublishName = projectPublishName;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Date getInterviewStartDate() {
		return interviewStartDate;
	}

	public void setInterviewStartDate(Date interviewStartDate) {
		this.interviewStartDate = interviewStartDate;
	}
    
	
}
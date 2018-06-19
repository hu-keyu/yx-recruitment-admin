package org.jypj.dev.entity;
import java.util.Date;

/**
 * 招聘成绩表
 * @author
 */
public class Grade implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String studentId; //学生ID
    private String grade; //学校面试、统一笔试、统一试讲成绩
    private String isEmploy; //学校面试是否录用（1是 0否）
    private String result; //体检、考察结果（1通过 2不通过）
    private String year; //年份
    private String projectId; //招聘主题ID
    private String positionId; //学校招聘岗位ID
    private String schoolId; //招聘单位ID
    private String scorePublishStatus; //成绩发布状态（1是 0否）
    private String listPublishStatus; //名单发布状态（1是 0否）
    private String type; //类型（1单位面试 2统一笔试 3统一试讲 4体检 5考察 6公式）
    private String subjectType; //学科类别（1普通科 2艺术科）
    private String isReset; //是否调整名单（1是 0否）
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注
    
    private StudentInfo studentInfo;
    
    private String ticketnum;//准考证号 祁才加误删
    private String name;//考生姓名 祁才加误删
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getGrade(){
        return this.grade;
    }
    
    public void setGrade(String grade){
        this.grade = grade;
    }
    public String getIsEmploy(){
        return this.isEmploy;
    }
    
    public void setIsEmploy(String isEmploy){
        this.isEmploy = isEmploy;
    }
    public String getResult(){
        return this.result;
    }
    
    public void setResult(String result){
        this.result = result;
    }
    public String getYear(){
        return this.year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    public String getProjectId(){
        return this.projectId;
    }
    
    public void setProjectId(String projectId){
        this.projectId = projectId;
    }
    public String getPositionId(){
        return this.positionId;
    }
    
    public void setPositionId(String positionId){
        this.positionId = positionId;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
    public String getScorePublishStatus(){
        return this.scorePublishStatus;
    }
    
    public void setScorePublishStatus(String scorePublishStatus){
        this.scorePublishStatus = scorePublishStatus;
    }
    public String getListPublishStatus(){
        return this.listPublishStatus;
    }
    
    public void setListPublishStatus(String listPublishStatus){
        this.listPublishStatus = listPublishStatus;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getSubjectType(){
        return this.subjectType;
    }
    
    public void setSubjectType(String subjectType){
        this.subjectType = subjectType;
    }
    public String getIsReset(){
        return this.isReset;
    }
    
    public void setIsReset(String isReset){
        this.isReset = isReset;
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

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public String getTicketnum() {
		return ticketnum;
	}

	public void setTicketnum(String ticketnum) {
		this.ticketnum = ticketnum;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Grade() {
		super();
	}
	
	public Grade(String id, String grade, String isEmploy) {
		super();
		this.id = id;
		this.grade = grade;
		this.isEmploy = isEmploy;
	}
	
	public Grade(String grade, String projectId, String schoolId, String modifyuser,String ticketnum,String name) {
		super();
		this.grade = grade;
		this.projectId = projectId;
		this.schoolId = schoolId;
		this.modifyuser = modifyuser;
		this.ticketnum = ticketnum;
		this.name=name;
	}

	public Grade(String id, String studentId,String grade, String isEmploy, String projectId, String positionId, String schoolId) {
		super();
		this.id = id;
		this.studentId=studentId;
		this.grade = grade;
		this.isEmploy = isEmploy;
		this.projectId = projectId;
		this.positionId = positionId;
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "Grade [studentId=" + studentId + ", grade=" + grade + ", positionId=" + positionId + "]";
	}
	
}
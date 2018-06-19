package org.jypj.dev.entity;
import java.util.Date;

/**
 * 招聘成绩调整日志表
 * @author
 */
public class GradeAdjustLog implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String projectId; //招聘主题ID
    private String positionId; //学校招聘岗位ID
    private String studentId; //学生ID
    private String year; //年份
    private String type; //类型（1单位面试 2统一笔试 3统一试讲 4体检 5考察 6公式）
    private String initGrade; //调整前的成绩及是否录用（存json串）
    private String gradeAfter; //调整前的成绩及是否录用（存json串）
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注
    private String schoolId;//学校ID

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
    public String getPositionId(){
        return this.positionId;
    }
    
    public void setPositionId(String positionId){
        this.positionId = positionId;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getYear(){
        return this.year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getInitGrade(){
        return this.initGrade;
    }
    
    public void setInitGrade(String initGrade){
        this.initGrade = initGrade;
    }
    public String getGradeAfter(){
        return this.gradeAfter;
    }
    
    public void setGradeAfter(String gradeAfter){
        this.gradeAfter = gradeAfter;
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
    
}
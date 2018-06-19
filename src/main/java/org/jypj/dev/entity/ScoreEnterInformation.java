package org.jypj.dev.entity;
import java.util.Date;

/**
 * 教育局发布入围名单
 * @author
 */
public class ScoreEnterInformation implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //
    private String itemsId; //
    private String postId; //
    private String studentId; //
    private String type; //
    private String isEnter; //1、是 2、否
    private String subjectType; //
    private String scorePublishStatus; //
    private String listPublishStatus; //
    private String createUser; //
    private Date ctime; //
    private String modifyUser; //
    private Date mtime; //
    private StudentInfo studentInfo;
    private String schoolId;//学校ID
    
    private String score; //分数：不与数据库关联
    private String school; //招聘单位：不与数据库关联
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getItemsId(){
        return this.itemsId;
    }
    
    public void setItemsId(String itemsId){
        this.itemsId = itemsId;
    }
    public String getPostId(){
        return this.postId;
    }
    
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getIsEnter(){
        return this.isEnter;
    }
    
    public void setIsEnter(String isEnter){
        this.isEnter = isEnter;
    }
    public String getSubjectType(){
        return this.subjectType;
    }
    
    public void setSubjectType(String subjectType){
        this.subjectType = subjectType;
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
    public String getCreateUser(){
        return this.createUser;
    }
    
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public Date getCtime(){
        return this.ctime;
    }
    
    public void setCtime(Date ctime){
        this.ctime = ctime;
    }
    public String getModifyUser(){
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }
    public Date getMtime(){
        return this.mtime;
    }
    
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
    
    
}
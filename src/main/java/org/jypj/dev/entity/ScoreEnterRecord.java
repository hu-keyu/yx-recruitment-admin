package org.jypj.dev.entity;
import java.util.Date;

/**
 * 入围情况记录表
 * @author
 */
public class ScoreEnterRecord implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String empItemsId; //招聘项目id
    private String postId; //岗位id
    private String studentId; //考生id
    private String type; //考试类型 1、单位面试 2、统一笔试 3、统一试讲  4、体检 5、考察 6、公示 

    private String isEnter; //是否入围
    private String subjectType; //学科类别
    private String updateReason; //修改原因 1、删除2、调整（添加到入围名单中）

    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String schoolId; //学校id

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getEmpItemsId(){
        return this.empItemsId;
    }
    
    public void setEmpItemsId(String empItemsId){
        this.empItemsId = empItemsId;
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
    public String getUpdateReason(){
        return this.updateReason;
    }
    
    public void setUpdateReason(String updateReason){
        this.updateReason = updateReason;
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
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
}
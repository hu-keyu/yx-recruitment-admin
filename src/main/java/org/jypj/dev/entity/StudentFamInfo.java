package org.jypj.dev.entity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 学生家庭情况信息
 * @author
 */
public class StudentFamInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String employItemsId; //招聘项目id
    private String studentId; //考生id
    private String studentName; //考生姓名
    private String memberRelation; //关系，读取字典
    private String memberName; //姓名
    private Integer memberAge; //成员年龄
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String memberwork; //工作单位以及职务

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getEmployItemsId(){
        return this.employItemsId;
    }
    
    public void setEmployItemsId(String employItemsId){
        this.employItemsId = employItemsId;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getStudentName(){
        return this.studentName;
    }
    
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public String getMemberRelation(){
        return this.memberRelation;
    }
    
    public void setMemberRelation(String memberRelation){
        this.memberRelation = memberRelation;
    }
    public String getMemberName(){
        return this.memberName;
    }
    
    public void setMemberName(String memberName){
        this.memberName = memberName;
    }
    public Integer getMemberAge(){
        return this.memberAge;
    }
    
    public void setMemberAge(Integer memberAge){
        this.memberAge = memberAge;
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
    public String getMemberwork(){
        return this.memberwork;
    }
    
    public void setMemberwork(String memberwork){
        this.memberwork = memberwork;
    }
}
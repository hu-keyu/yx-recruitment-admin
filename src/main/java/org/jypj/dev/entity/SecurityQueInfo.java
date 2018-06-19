package org.jypj.dev.entity;
import java.util.Date;

/**
 * 密保问题信息
 * @author
 */
public class SecurityQueInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String studentId; //考生id
    private String questionCode; //问题编号，读取字典
    private String questions; //问题
    private String answer; //答案
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间

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
    public String getQuestionCode(){
        return this.questionCode;
    }
    
    public void setQuestionCode(String questionCode){
        this.questionCode = questionCode;
    }
    public String getQuestions(){
        return this.questions;
    }
    
    public void setQuestions(String questions){
        this.questions = questions;
    }
    public String getAnswer(){
        return this.answer;
    }
    
    public void setAnswer(String answer){
        this.answer = answer;
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
}
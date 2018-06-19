package org.jypj.dev.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 考生学历信息表
 * @author
 */
public class StudentEduInfo implements java.io.Serializable, Comparable<StudentEduInfo>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
    private String id; 
    
    /**
     * 招聘主题ID
     */
    private String employItemsId;
    
    /**
     * 考生id
     */
    private String studentId;
    
    /**
     * 学历类别码
     */
    private String eduCode; 
    
    /**
     * 学历类别名称
     */
    private String eduName; 
    
    /**
     * 毕业学校代码
     */
    private String eduSchoolCode; 
    
    /**
     * 毕业学校名称
     */
    private String eduSchoolName; 
    
    /**
     * 毕业时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eduGraduateTime; 
    
    private String eduGraduateTimeStr; 
    /**
     * '专业代码 所学专业代码，如果专业代码为空的，1、    手动输入框输入；2、  相近专业
     */
    private String eduProfessionCode;
    
    /**
     * 专业名称
     */
    private String eduProfessionName;
    
    /**
     * 是否有相近专业
     */
    private String isSimilarTerm;
    
    /**
     * 相近专业代码
     */
    private String similarTermCode; 
    
    /**
     * 学位代码
     */
    private String eduBachelorCode; 
    
    /**
     * 教育类型
     */
    private String eduType; 
    
    /**
     * 证书附件id
     */
    private String eduCertificate; 
    
    /**
     * 数据创建人员
     */
    private String createUser;
    
    /**
     * 数据创建时间
     */
    private Date ctime; 
    
    /**
     * 数据修改人员
     */
    private String modifyUser;
    
    /**
     * 数据修改时间
     */
    private Date mtime; 
    
    /**
     * 专业真实名称
     */
    private String realSpecialtyName;
    
    public String getRealSpecialtyName() {
		return realSpecialtyName;
	}

	public void setRealSpecialtyName(String realSpecialtyName) {
		this.realSpecialtyName = realSpecialtyName;
	}

	public String getEduGraduateTimeStr() {
        return eduGraduateTimeStr;
    }

    public void setEduGraduateTimeStr(String eduGraduateTimeStr) {
        this.eduGraduateTimeStr = eduGraduateTimeStr;
    }

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
    public String getEduCode(){
        return this.eduCode;
    }
    
    public void setEduCode(String eduCode){
        this.eduCode = eduCode;
    }
    public String getEduName(){
        return this.eduName;
    }
    
    public void setEduName(String eduName){
        this.eduName = eduName;
    }
    public String getEduSchoolCode(){
        return this.eduSchoolCode;
    }
    
    public void setEduSchoolCode(String eduSchoolCode){
        this.eduSchoolCode = eduSchoolCode;
    }
    public String getEduSchoolName(){
        return this.eduSchoolName;
    }
    
    public void setEduSchoolName(String eduSchoolName){
        this.eduSchoolName = eduSchoolName;
    }
    public Date getEduGraduateTime(){
        return this.eduGraduateTime;
    }
    
    public void setEduGraduateTime(Date eduGraduateTime){
        this.eduGraduateTime = eduGraduateTime;
    }
    public String getEduProfessionCode(){
        return this.eduProfessionCode;
    }
    
    public void setEduProfessionCode(String eduProfessionCode){
        this.eduProfessionCode = eduProfessionCode;
    }
    public String getEduProfessionName(){
        return this.eduProfessionName;
    }
    
    public void setEduProfessionName(String eduProfessionName){
        this.eduProfessionName = eduProfessionName;
    }
    public String getIsSimilarTerm(){
        return this.isSimilarTerm;
    }
    
    public void setIsSimilarTerm(String isSimilarTerm){
        this.isSimilarTerm = isSimilarTerm;
    }
    public String getSimilarTermCode(){
        return this.similarTermCode;
    }
    
    public void setSimilarTermCode(String similarTermCode){
        this.similarTermCode = similarTermCode;
    }
    public String getEduBachelorCode(){
        return this.eduBachelorCode;
    }
    
    public void setEduBachelorCode(String eduBachelorCode){
        this.eduBachelorCode = eduBachelorCode;
    }
    public String getEduType(){
        return this.eduType;
    }
    
    public void setEduType(String eduType){
        this.eduType = eduType;
    }
    public String getEduCertificate(){
        return this.eduCertificate;
    }
    
    public void setEduCertificate(String eduCertificate){
        this.eduCertificate = eduCertificate;
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

    @Override
    public int compareTo(StudentEduInfo o) {
        int temp1 = Integer.parseInt(this.getEduCode());
        int temp2 = Integer.parseInt(o.getEduCode());
        return (new Integer(temp2)).compareTo((new Integer(temp1)));
    }
}
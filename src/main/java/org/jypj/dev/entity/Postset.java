package org.jypj.dev.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 招聘岗位
 * @author
 */
public class Postset implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //索引
    private String postCode; //岗位代码
    private String postName; //岗位名称
    private String subject; //学科（字典）
    private BigDecimal isArt; //是否艺术类（0否1是）
    private String postCategory; //岗位类别（字典）
    private BigDecimal seq; //排序（正整数）
    private String summary; //简介
    private String isLimitAge="0"; //是否有年龄限制（1有限制 0无限制）
    private BigDecimal limitAge; //限制的年龄
    private String limitAgeConditition; //年龄限制条件（0及以上 1及以下）
    private String isLimitEducation="0"; //是否有学历限制（1有限制 0无限制）
    private String limitEducation; //限制的学历
    private String limitEducationConditition; //学历限制条件（0及以上 1及以下）
    private String isLimitEdu="0"; //是否教育类型限制（1有限制 0无限制）
    private String limitEdu; //限制的教育类型
    private String limitEduConditition; //教育类型限制条件
    private String isLimitDegree="0"; //是否学位限制（1有限制 0无限制）
    private String limitDegree; //限制的学位
    private String limitDegreeConditition; //学位限制条件
    private String isLimitProfession="0" ;//是否专业要求
    private String isLimitRecruit; //是否招聘对象限制（1有限制 0无限制）
    private String limitRecruit; //招聘对象（字典）
    private String isEducationStudent="0"; //是否要求东莞生源免费师范生（1是 0否）
    private String otherLimit; //学校其他限制条件
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注
    private String iszz ;//是否中职专业课
    
    //非数据库映射字段
    private String subjectText;//学科名字
    private List<Specialty> specialtys;//岗位对应的限制专业
    private String limitProfession;//岗位限制的专业Code
    private String limitProfessionText;//岗位限制的专业名称
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getPostCode(){
        return this.postCode;
    }
    
    public void setPostCode(String postCode){
        this.postCode = postCode;
    }
    public String getPostName(){
        return this.postName;
    }
    
    public void setPostName(String postName){
        this.postName = postName;
    }
    public String getSubject(){
        return this.subject;
    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    public BigDecimal getIsArt(){
        return this.isArt;
    }
    
    public void setIsArt(BigDecimal isArt){
        this.isArt = isArt;
    }
    public String getPostCategory(){
        return this.postCategory;
    }
    
    public void setPostCategory(String postCategory){
        this.postCategory = postCategory;
    }
    public BigDecimal getSeq(){
        return this.seq;
    }
    
    public void setSeq(BigDecimal seq){
        this.seq = seq;
    }
    public String getSummary(){
        return this.summary;
    }
    
    public void setSummary(String summary){
        this.summary = summary;
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

    public String getIsLimitProfession() {
        return isLimitProfession;
    }

    public void setIsLimitProfession(String isLimitProfession) {
        this.isLimitProfession = isLimitProfession;
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
    public String getRemark(){
        return this.remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }

	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	public List<Specialty> getSpecialtys() {
		return specialtys;
	}

	public void setSpecialtys(List<Specialty> specialtys) {
		this.specialtys = specialtys;
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
	
	public String getIszz() {
		return iszz;
	}

	public void setIszz(String iszz) {
		this.iszz = iszz;
	}

	public Postset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Postset(String postCode, String postName) {
		super();
		this.postCode = postCode;
		this.postName = postName;
	}

	
	
}
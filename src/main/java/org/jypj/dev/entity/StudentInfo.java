package org.jypj.dev.entity;
import java.util.Date;
import java.util.List;

/**
 * 考生基本信息表
 * @author
 */
public class StudentInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //考生id
    private String employItemsId; //招聘项目ID
    private String name; //考生姓名
    private String sex; //性别
    private String identityCard; //身份证号码
    private Date birthday; //出生日期
    private String birthdayStr; //出生日期
    private String password; //密码密文
    private String nationCode; //民族代码，读取字典
    private String nativePlace; //籍贯代码，读取字典
    private String originPlace; //生源地，读取字典
    private String familyRegister; //户籍地址
    private String birthControl; //是否违反计划生育  1、是  0、否
    private String healthy; //是否健康  1、是  0、否
    private String politicalStatus; //政治面貌代码，读取字典
    private String photoAttId; //照片附件id
    private String certiType; //资格证种类，读取字典
    private String expertiseCode; //专业技术资格代码，读取字典
    private String honorCode; //获得荣誉称号，读取字典
    private String[] honorCodes; //获得荣誉称号，读取字典
    private String teachingSubject; //专业技术资格证书的任教学科

    private String publicOffice; //是否公职人员  1、是  0、否  （社会人员专用）

    private String contractTeacher; //是否东莞市聘用合同制教师  1、是  0、否（社会人员专用）
    private String attachedUnits; //现人事档案挂靠单位（社会人员专用）
    private String freeStudent; //是否东莞生源免费师范生  1、是  0、否 
    private String professionalCourse; //是否报考中职专业课的岗位  1、是  0、否
    private String mandarinScore; //普通话成绩
    private String educationScore; //教育学成绩
    private String psychologyScore; //心理学成绩
    private String teachingAbilityScore;//教育教学能力测试
    private String internshipCertification;//教育实习证明  1、有  0、无 
    
	private String phoneNumber; //手机号码
	private String contactPhone; //联系电话
    
    private String address; //联系地址
    private String resume; //学习简历
    private String workExperience; //工作经历
    private String studentType; //考生类型，读取字典
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String ticketnum;//准考证号
    private List<Grade> grades;
    private List<ScoreGradeWriten> gradeWritens;//统一笔试成绩
    
    private List<ScoreGradeTrial> gradeTrials;//统一试讲成绩
    private List<ScoreGradePhysical> gradePhysicals;//体检成绩
    private List<ScoreGradeStudy> gradeStudies;//考察成绩
    /**
     * 考生的学历信息
     */
    private List<StudentEduInfo> studentEduInfoList;
    /**
     * 考生的家庭信息
     */
    private List<StudentFamInfo> studentFamInfoList;
    /**
     * 验证码
     */
    private String verifyCode;
    
    /**
     * 是否有相近专业  0 无  1 有
     */
    private String hasSimilar;
    
    /**
     * 拆分荣誉称号
     */
    private String rych0;
    
    private String rych1;
    
    private String rych2;
    private String rych3;
    private String rych4;
    private String rych5;
    private String rych6;
    /** add by QiCai start**/
    //非数据库映射字段
    private String postName;//岗位名称
    private String applyStatus;//考生状态
    
    private String statustext;//考生状态中文
    /** add by QiCai end**/

    private String photoStatus ;  //照片状态，额外添加的字段
    /**
     * 照片实际地址
     */
    private String photoUrlPath;
    /**
     * 图片的真实文件名
     */
    private String photoRealName;
    private String schoolName ;//学校名称，非数据库映射字段
    private String professionName ;//专业名称，非数据库映射字段
    
    private String viewGrade ;//非数据库映射字段，面试分数
    

    private String age ;//年龄，非数据库映射字段
    public String getAddress(){
        return this.address;
    }
    public String getAge() {
        return age;
    }
    
    public String getApplyStatus() {
		return applyStatus;
	}

    public String getAttachedUnits(){
        return this.attachedUnits;
    }
    
    public String getBirthControl(){
        return this.birthControl;
    }

    public Date getBirthday(){
        return this.birthday;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public String getCertiType(){
        return this.certiType;
    }

    public String getContactPhone(){
        return this.contactPhone;
    }

    public String getContractTeacher(){
        return this.contractTeacher;
    }

    public String getCreateUser(){
        return this.createUser;
    }

    public Date getCtime(){
        return this.ctime;
    }

    public String getEducationScore(){
        return this.educationScore;
    }

    public String getEmployItemsId(){
        return this.employItemsId;
    }

    public String getExpertiseCode(){
        return this.expertiseCode;
    }

    public String getFamilyRegister(){
        return this.familyRegister;
    }

    public String getFreeStudent(){
        return this.freeStudent;
    }

    public List<ScoreGradePhysical> getGradePhysicals() {
		return gradePhysicals;
	}

    public List<Grade> getGrades() {
		return grades;
	}

    public List<ScoreGradeStudy> getGradeStudies() {
		return gradeStudies;
	}

    public List<ScoreGradeTrial> getGradeTrials() {
		return gradeTrials;
	}

    public List<ScoreGradeWriten> getGradeWritens() {
		return gradeWritens;
	}

    public String getHasSimilar() {
        return hasSimilar;
    }

    public String getHealthy(){
        return this.healthy;
    }

    public String getHonorCode(){
        return this.honorCode;
    }

    public String[] getHonorCodes() {
        return honorCodes;
    }

    public String getId(){
        return this.id;
    }

    public String getIdentityCard(){
        return this.identityCard;
    }

    public String getInternshipCertification() {
		return internshipCertification;
	}

    public String getMandarinScore(){
        return this.mandarinScore;
    }

    public String getModifyUser(){
        return this.modifyUser;
    }

    public Date getMtime(){
        return this.mtime;
    }

    public String getName(){
        return this.name;
    }
    
    public String getNationCode(){
        return this.nationCode;
    }
    public String getNativePlace(){
        return this.nativePlace;
    }
    
    public String getOriginPlace(){
        return this.originPlace;
    }
    public String getPassword(){
        return this.password;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getPhotoAttId(){
        return this.photoAttId;
    }
    
    public String getPhotoRealName() {
        return photoRealName;
    }
    public String getPhotoStatus() {
        return photoStatus;
    }
    
    public String getPhotoUrlPath() {
        return photoUrlPath;
    }
    public String getPoliticalStatus(){
        return this.politicalStatus;
    }
    
    public String getPostName() {
		return postName;
	}
    public String getProfessionalCourse(){
        return this.professionalCourse;
    }
    
    public String getProfessionName() {
        return professionName;
    }
    public String getPsychologyScore(){
        return this.psychologyScore;
    }
    
    public String getPublicOffice(){
        return this.publicOffice;
    }
    public String getResume(){
        return this.resume;
    }
    
    public String getRych0() {
        return rych0;
    }
    public String getRych1() {
        return rych1;
    }
    
    public String getRych2() {
        return rych2;
    }
    public String getRych3() {
        return rych3;
    }
    
    public String getRych4() {
        return rych4;
    }
    public String getRych5() {
        return rych5;
    }
    
    public String getRych6() {
        return rych6;
    }
    public String getSchoolName() {
        return schoolName;
    }
    
    public String getSex(){
        return this.sex;
    }
    public String getStatustext() {
		return statustext;
	}
    
    public List<StudentEduInfo> getStudentEduInfoList() {
        return studentEduInfoList;
    }
    public List<StudentFamInfo> getStudentFamInfoList() {
        return studentFamInfoList;
    }
    
    public String getStudentType(){
        return this.studentType;
    }
    public String getTeachingAbilityScore() {
		return teachingAbilityScore;
	}
    
    public String getTeachingSubject(){
        return this.teachingSubject;
    }
    public String getTicketnum() {
        return ticketnum;
    }
    
    public String getVerifyCode() {
        return verifyCode;
    }
    public String getWorkExperience(){
        return this.workExperience;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
    public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
    public void setAttachedUnits(String attachedUnits){
        this.attachedUnits = attachedUnits;
    }
    
    public void setBirthControl(String birthControl){
        this.birthControl = birthControl;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    
    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }
    public void setCertiType(String certiType){
        this.certiType = certiType;
    }
    
    public void setContactPhone(String contactPhone){
        this.contactPhone = contactPhone;
    }
    public void setContractTeacher(String contractTeacher){
        this.contractTeacher = contractTeacher;
    }
    
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public void setCtime(Date ctime){
        this.ctime = ctime;
    }
    
    public void setEducationScore(String educationScore){
        this.educationScore = educationScore;
    }
    public void setEmployItemsId(String employItemsId){
        this.employItemsId = employItemsId;
    }
    
    public void setExpertiseCode(String expertiseCode){
        this.expertiseCode = expertiseCode;
    }
    public void setFamilyRegister(String familyRegister){
        this.familyRegister = familyRegister;
    }
    
    public void setFreeStudent(String freeStudent){
        this.freeStudent = freeStudent;
    }
    public void setGradePhysicals(List<ScoreGradePhysical> gradePhysicals) {
		this.gradePhysicals = gradePhysicals;
	}
    
    public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
    public void setGradeStudies(List<ScoreGradeStudy> gradeStudies) {
		this.gradeStudies = gradeStudies;
	}
    
    public void setGradeTrials(List<ScoreGradeTrial> gradeTrials) {
		this.gradeTrials = gradeTrials;
	}
    public void setGradeWritens(List<ScoreGradeWriten> gradeWritens) {
		this.gradeWritens = gradeWritens;
	}
    
    public void setHasSimilar(String hasSimilar) {
        this.hasSimilar = hasSimilar;
    }
    public void setHealthy(String healthy){
        this.healthy = healthy;
    }
    
    public void setHonorCode(String honorCode){
        this.honorCode = honorCode;
    }
    public void setHonorCodes(String[] honorCodes) {
        this.honorCodes = honorCodes;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public void setIdentityCard(String identityCard){
        this.identityCard = identityCard;
    }
    
    public void setInternshipCertification(String internshipCertification) {
		this.internshipCertification = internshipCertification;
	}
    public void setMandarinScore(String mandarinScore){
        this.mandarinScore = mandarinScore;
    }
    
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setNationCode(String nationCode){
        this.nationCode = nationCode;
    }
    
    public void setNativePlace(String nativePlace){
        this.nativePlace = nativePlace;
    }
    public void setOriginPlace(String originPlace){
        this.originPlace = originPlace;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setPhotoAttId(String photoAttId){
        this.photoAttId = photoAttId;
    }

	public void setPhotoRealName(String photoRealName) {
        this.photoRealName = photoRealName;
    }

	public void setPhotoStatus(String photoStatus) {
        this.photoStatus = photoStatus;
    }
	
	public void setPhotoUrlPath(String photoUrlPath) {
        this.photoUrlPath = photoUrlPath;
    }

	public void setPoliticalStatus(String politicalStatus){
        this.politicalStatus = politicalStatus;
    }

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public void setProfessionalCourse(String professionalCourse){
        this.professionalCourse = professionalCourse;
    }

	public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

	public void setPsychologyScore(String psychologyScore){
        this.psychologyScore = psychologyScore;
    }

	public void setPublicOffice(String publicOffice){
        this.publicOffice = publicOffice;
    }

	public void setResume(String resume){
        this.resume = resume;
    }

	public void setRych0(String rych0) {
        this.rych0 = rych0;
    }

	public void setRych1(String rych1) {
        this.rych1 = rych1;
    }

	public void setRych2(String rych2) {
        this.rych2 = rych2;
    }

	public void setRych3(String rych3) {
        this.rych3 = rych3;
    }

	public void setRych4(String rych4) {
        this.rych4 = rych4;
    }

	public void setRych5(String rych5) {
        this.rych5 = rych5;
    }

    public void setRych6(String rych6) {
        this.rych6 = rych6;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public void setStatustext(String statustext) {
		this.statustext = statustext;
	}

    public void setStudentEduInfoList(List<StudentEduInfo> studentEduInfoList) {
        this.studentEduInfoList = studentEduInfoList;
    }

    public void setStudentFamInfoList(List<StudentFamInfo> studentFamInfoList) {
        this.studentFamInfoList = studentFamInfoList;
    }

    public void setStudentType(String studentType){
        this.studentType = studentType;
    }

    public void setTeachingAbilityScore(String teachingAbilityScore) {
		this.teachingAbilityScore = teachingAbilityScore;
	}
    public void setTeachingSubject(String teachingSubject){
        this.teachingSubject = teachingSubject;
    }

	public void setTicketnum(String ticketnum) {
        this.ticketnum = ticketnum;
    }

	public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

	public void setWorkExperience(String workExperience){
        this.workExperience = workExperience;
    }
	public String getViewGrade() {
		return viewGrade;
	}
	public void setViewGrade(String viewGrade) {
		this.viewGrade = viewGrade;
	}
	
	
}
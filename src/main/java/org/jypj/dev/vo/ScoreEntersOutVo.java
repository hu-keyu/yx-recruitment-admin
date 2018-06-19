package org.jypj.dev.vo;

public class ScoreEntersOutVo {
	
	private String id; //考生ID
	
	private String studentId; //考生ID
	
	private String projectId; //项目ID
	
	private String postId; //岗位ID
	
	private String postName; //岗位名
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postName == null) ? 0 : postName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreEntersOutVo other = (ScoreEntersOutVo) obj;
		if (postName == null) {
			if (other.postName != null)
				return false;
		} else if (!postName.equals(other.postName))
			return false;
		return true;
	}

	private String ticketNum; //准考证号
	
	private String idCard; //身份证号
	
	private String studentName;//学生姓名
	
	private String sex;//学生性别
	
	private String score;//成绩
	 
	private String isPass;//是否通过
	
	private String type;//考生类型
	
	private String school;//报考单位
	
	private String applyId;//报考信息ID
	
	private String offer;//是否录用 祁才加误删
	
	private String gradeId;//成绩表ID

	private String schoolId;//学校ID
	
	private String scyscore;//综合成绩
	
	private String writtenTime ;//笔试时间
	
	private String lectureTime ;//试讲时间
	
	private String phone ;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

	public String getScyscore() {
		return scyscore;
	}

	public void setScyscore(String scyscore) {
		this.scyscore = scyscore;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getWrittenTime() {
		return writtenTime;
	}

	public void setWrittenTime(String writtenTime) {
		this.writtenTime = writtenTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLectureTime() {
		return lectureTime;
	}

	public void setLectureTime(String lectureTime) {
		this.lectureTime = lectureTime;
	}
    
	
}


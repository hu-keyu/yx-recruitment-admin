package org.jypj.dev.vo;

public class InterviewNoticeVo {
    
    private String studentApplyId;
    
    private String recruitId;

	private String postName;
	
	private String studentName;
	
	private String identityCard;
	
	private String admissionTicket;
	
	private String subject;
	
	private String interviewTime;
	
	private String interviewSite;
	
	private String interviewNotice;
	
	private String noticeItem = "一、考生须携带身份证（军人证）和准考证原件依规定时间到达考场进行考试。本通知书使用于本次招聘面试环节考试，请妥善保管。";

	
    public String getStudentApplyId() {
        return studentApplyId;
    }

    public void setStudentApplyId(String studentApplyId) {
        this.studentApplyId = studentApplyId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
    
    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruitId) {
        this.recruitId = recruitId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getAdmissionTicket() {
        return admissionTicket;
    }

    public void setAdmissionTicket(String admissionTicket) {
        this.admissionTicket = admissionTicket;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewSite() {
        return interviewSite;
    }

    public void setInterviewSite(String interviewSite) {
        this.interviewSite = interviewSite;
    }

    public String getInterviewNotice() {
        return interviewNotice;
    }

    public void setInterviewNotice(String interviewNotice) {
        this.interviewNotice = interviewNotice;
    }

    public String getNoticeItem() {
        return noticeItem;
    }

    public void setNoticeItem(String noticeItem) {
        this.noticeItem = noticeItem;
    }
}

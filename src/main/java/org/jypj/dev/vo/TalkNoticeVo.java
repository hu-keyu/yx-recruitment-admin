package org.jypj.dev.vo;

public class TalkNoticeVo {
    
    private String studentApplyId;
    
    private String recruitId;
    
	private String postName;
	
	private String studentName;
	
	private String identityCard;
	
	private String admissionTicket;
	
	private String subject;
	
	/**
	 * 考场名称
	 */
	private String talkExam;

	/**
	 * 组号
	 */
	private String talkGroup;
	
	/**
	 * 考场地址
	 */
	private String talkAddr;
	
	/**
	 * 试讲时间
	 */
	private String talkTime;
	
	/**
	 * 交通环境
	 */
	private String talkEnviron;
	
	private String taklArriveTime;
	
	private String noticeItem = "一、考场规则。（请考生自行打印）<br/>1、考生须持身份证和准考证按规定时间进入考试组对应的候考室（例如：考生考试组号为：小学语文1组，对应的候考室（组）为：小学语文1组候考室；到达学校却未进入相应候考室者不符合报到要求）。迟到者一律取消考试资格。 <br/>2、考生不得穿制服或明显文字或图案标识的服装参加考试，严禁携带电子通讯工具等禁止物品进入候考室，须自觉接受金属探测仪检查(怀孕期内的除外)，已携带的需关机存放至指定地点。考生只能携带身份证和准考证进入备考区，其余物品放置在指定地点。音乐学科考场提供钢琴和简易音响，其他乐器由考生自备，允许由其本人带入考试区，不得其他无关人员入场搬抬。<br/>3、考生必须自觉服从考试工作人员管理，遵守考试纪律，不得以任何理由妨碍考试工作人员履行职责，不得扰乱考场及其他考试工作地点的秩序，保持考场安静，候考、备考期间不得随意交谈和离开候考、备考室，考生需要上洗手间的，须经工作人员同意，并由工作人员陪同前往，无关人员不得进入考场。考试完毕签领成绩后应立即离开考场，不得在考场附近逗留。<br/>4、考试过程中按照试题要求在规定时间内进行考试，需用普通话回答评委提问，不能以任何方式向评委透露个人资料，不得向评委询问对课题的解释，对评委的提问听不清楚的，可要求评委重新念题。<br/>二、除美术外其他学科的考试流程。<br/>1、报到时间：早上：2016年12月4日7:30前，或下午：2016年12月4日12:45前。<br/>2、考生签到后抽签决定考试顺序，考生佩戴抽签号码牌并由工作人员按抽签号顺序引导进入备考室备课。<br/>3、每位考生备课时间约为40-45分钟，备课结束后由工作人员引导进入考试室进行考试。<br/>4、正式考试时间为15分钟，其中10分钟为片段教学环节，5分钟为问答环节。<br/>5、考生考试结束后到候分室等候并签领成绩单后离开。<br/>三、美术科考试流程。<br/>（一）上午美术科才艺展示考试<br/>1、报到时间：早上：2016年12月4日7:30前，考试时间：8:00-11:00。考试内容为：命题作品一幅，自创作品一幅，考场提供画纸和画架，其余工具、物料均由考生自带。（全部考生均需参加上午的术科考试和下午的片段教学考试）<br/>2、考生直接进入考试室报到，考生签到后抽签决定考试座位，考生佩戴抽签号码牌进行考试。<br/>3、考生除了携带必备的考试用文具外不能携带任何其他物品进入考试区，否则作违规处理，取消考试资格。<br/>4、考试结束后，考生离场，分数待统一评分后在考场公布栏公布。<br/>（二）下午片段教学考试（与其他科的考试流程相同，考试时间为10分钟，不设问答环节）<br/>违反上述要求的，视为放弃考核资格。";

    public String getPostName() {
        return postName;
    }
    
    public String getTaklArriveTime() {
        return taklArriveTime;
    }



    public void setTaklArriveTime(String taklArriveTime) {
        this.taklArriveTime = taklArriveTime;
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

    public String getStudentApplyId() {
        return studentApplyId;
    }

    public void setStudentApplyId(String studentApplyId) {
        this.studentApplyId = studentApplyId;
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

    public String getTalkExam() {
        return talkExam;
    }

    public void setTalkExam(String talkExam) {
        this.talkExam = talkExam;
    }

    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    public String getTalkEnviron() {
        return talkEnviron;
    }

    public void setTalkEnviron(String talkEnviron) {
        this.talkEnviron = talkEnviron;
    }

    public String getNoticeItem() {
        return noticeItem;
    }

    public void setNoticeItem(String noticeItem) {
        this.noticeItem = noticeItem;
    }

    public String getTalkGroup() {
        return talkGroup;
    }

    public void setTalkGroup(String talkGroup) {
        this.talkGroup = talkGroup;
    }

    public String getTalkAddr() {
        return talkAddr;
    }

    public void setTalkAddr(String talkAddr) {
        this.talkAddr = talkAddr;
    }

}

package org.jypj.dev.entity;
import java.util.Date;

/**
 * 考生报考信息表
 * @author
 */
public class StudentApplyInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 报考ID
	 */
    private String id;
    
    /**
     * 招聘项目id
     */
    private String employItemsId;
    
    /**
     * 考生ID
     */
    private String studentId;
    
    /**
     * 岗位信息表id
     */
    private String applyJobId; 
    
    /**
     * 岗位名称
     */
    private String applyJobName;
    
    /**
     * 岗代码
     */
    private String applyJobCode;
    
    /**
     * 招聘单位id
     */
    private String applyDepId; 
    
    /**
     * 身份证附件
     */
    private String idcardAttId; 
    
    /**
     * 身份证附件
     */
    private String idcardRealName; 
    
    /**
     * 身份证附件
     */
    private String idcardUrlPath; 
    
    /**
     * 教师资格证
     */
    private String cerAchAttId; 
    
    /**
     * 教师资格证
     */
    private String cerAchRealName; 
    
    /**
     * 教师资格证
     */
    private String cerAchUrlPath; 
    
    /**
     * 普通话成绩单
     */
    private String cerManAttId; 
    
    /**
     * 普通话成绩单
     */
    private String cerManRealName; 
    
    /**
     * 普通话成绩单
     */
    private String cerManUrlPath; 
    /**
     * 心理学成绩单
     */
    private String cerPsyAttId; 
    
    /**
     * 心理学成绩单
     */
    private String cerPsyRealName; 
    
    /**
     * 心理学成绩单
     */
    private String cerPsyUrlPath; 
    /**
     * 教育学成绩单
     */
    private String cerPedAttId; 
    
    /**
     * 教育学成绩单
     */
    private String cerPedRealName; 
    
    /**
     * 教育学成绩单
     */
    private String cerPedUrlPath; 
    /**
     * 教育教学能力测试
     */
    private String cerAbiAttId; 
    
    /**
     * 教育教学能力测试
     */
    private String cerAbiRealName; 
    
    /**
     * 教育教学能力测试
     */
    private String cerAbiUrlPath; 
    /**
     * 教育实习证明
     */
    private String cerShipAttId; 
    
    /**
     * 教育实习证明
     */
    private String cerShipRealName; 
    
    /**
     * 教育实习证明
     */
    private String cerShipUrlPath; 
    
    /**
     * 毕业证书或者就业推荐表
     */
    private String graRecomAttId;
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String graRecomRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String graRecomUrlPath; 
    
    /**
     * 学历鉴定证明
     */
    private String acaQuaAttId;
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String acaQuaRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String acaQuaUrlPath; 
    
    
    /**
     * 学位证书
     */
    private String bacAttId; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String bacRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String bacUrlPath; 
    
    /**
     * 学位鉴定证明
     */
    private String bacQuaAttId; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String bacQuaRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String bacQuaUrlPath; 
    
    /**
     * 计划生育证明
     */
    private String famPlanAttId; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String famPlanRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String famPlanUrlPath; 
    
    /**
     * 个人学习和工作情况总结
     */
    private String studyWorkAttId;
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String studyWorkRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String studyWorkUrlPath; 
    
    
    /**
     * 注意：请上传5-8分钟与申请岗位相关的教学视频，支持mp4,flv格式，100MB以内,如果格式不对或者播放不了请使用转换软件转码后上传，转换软件下载
     */
    private String teaVideoAttId; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String teaVideoRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String teaVideoUrlPath; 

    /**
     * 暂缓就业协议书（已办理暂缓就业手续的毕业生必须提供）
     */
    private String suspendEmpAttId; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String suspendEmpRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String suspendEmpUrlPath; 
    
    /**
     * 暂缓就业协议书（已办理暂缓就业手续的毕业生必须提供）
     */
    private String abroadStudyAttId;
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String abroadStudyRealName; 
    
    /**
     * 教师资格证或教育学、心理学、普通话成绩单
     */
    private String abroadStudyUrlPath; 
    
    /**
     * 毕业成绩单附件id
     */
    private String transcriptAttId; 
    
    /**
     * 毕业成绩单真实文件名
     */
    private String transcriptRealName; 
    
    /**
     * 毕业成绩单的文件服务器地址
     */
    private String transcriptUrlPath; 
    
    /**
     * 申请理由
     */
    private String applyReason;
    
    /**
     *  1、完成填写基本资料，开始报考；
		2、教育局资料待审；
		3、有相近专业，教育局审核通过，学校端资料待审；无相近专业，教育局默认审核通过，学校端待审；
		4、审核不通过，照片退回；
		5、审核不通过，资料审核不通过，非照片退回
		6、简历审核通过；
		7、简历审核通过，教育局面试名单发布，进入面试
		8、发布笔试名单和考场信息，进入笔试环节；
		9、发布试讲名单和考场信息，进入试讲环节；
		10、发布体检名单，并进入体检环节；
		11、发布考察名单，并进入考察阶段；
		12、发布公示名单；
		13、招聘单位发放介绍信，考生持介绍信报到；
    */
    private String applyStatus; 

    /**
     * 审核过程中的审核意见编码，读取字典
     */
    private String applyAuditCode;
    
    /**
     * 审核意见
     */
    private String applyAuditName;
    
    /**
     * 由于面试岗位被取消导致或者考生主动选择补报    是否补报：0、  不是 1、是
     */
    private String isRepay; 
    
    /**
     * 数据创建人员
     */
    private String createUser;
    
    /**
     * 数据创建时间
     */
    private Date ctime; 
    
    /**
     * 申请提交时间
     */
    private Date submitTime; 
    
    /**
     * 数据修改人员
     */
    private String modifyUser;
    
    /**
     * 数据修改时间
     */
    private Date mtime; 
    
    /**
     * 应聘的部门
     */
    private String applyDeptName;
    
    /**
     * 岗位限制条件
     * @return
     */
    private String posCondition;

    /**
     * 操作者，1教育局，2学校端
     */
    private String audittype ;
    
    /**
     * 岗位集合
     */
    private String postSetStr;
    
    /**
     * 准考证注意事项
     * @return
     */
    private String ticketNoticeItem = "<h3>考生须知</h3><p>一、考生须携带身份证（军人证）和准考证原件依规定时间到达考场进行考试。</p><p>二、笔试环节：考生在考前20分钟进入试室候考，考试开始30分钟后，迟到考生不得进入试室，考试开始60分钟后考生方可交卷离开。试讲环节：考生在考前30分钟到达候考室，考完领取成绩后离开，不得和未考人员进行交流。</p><p>三、参加考试时除携带考试必须的文具用品外一律不得携带教辅用书及其他物品（含手机等通讯工具）。</p><p>四、本次招聘各环节考试无关人员均不得进入考场，考试的其他要求详见考试通知单。</p><p>五、本准考证适用于本次招聘各环节考试，请妥善保管。</p>";
    
    public String getPosCondition() {
        return posCondition;
    }

    public void setPosCondition(String posCondition) {
        this.posCondition = posCondition;
    }
    
    
    public String getCerManAttId() {
        return cerManAttId;
    }

    public void setCerManAttId(String cerManAttId) {
        this.cerManAttId = cerManAttId;
    }

    public String getCerManRealName() {
        return cerManRealName;
    }

    public void setCerManRealName(String cerManRealName) {
        this.cerManRealName = cerManRealName;
    }
    
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getCerManUrlPath() {
        return cerManUrlPath;
    }

    public void setCerManUrlPath(String cerManUrlPath) {
        this.cerManUrlPath = cerManUrlPath;
    }

    public String getCerPsyAttId() {
        return cerPsyAttId;
    }

    public void setCerPsyAttId(String cerPsyAttId) {
        this.cerPsyAttId = cerPsyAttId;
    }

    public String getCerPsyRealName() {
        return cerPsyRealName;
    }

    public void setCerPsyRealName(String cerPsyRealName) {
        this.cerPsyRealName = cerPsyRealName;
    }

    public String getCerPsyUrlPath() {
        return cerPsyUrlPath;
    }

    public void setCerPsyUrlPath(String cerPsyUrlPath) {
        this.cerPsyUrlPath = cerPsyUrlPath;
    }

    public String getCerPedAttId() {
        return cerPedAttId;
    }

    public void setCerPedAttId(String cerPedAttId) {
        this.cerPedAttId = cerPedAttId;
    }

    public String getCerPedRealName() {
        return cerPedRealName;
    }

    public void setCerPedRealName(String cerPedRealName) {
        this.cerPedRealName = cerPedRealName;
    }

    public String getCerPedUrlPath() {
        return cerPedUrlPath;
    }

    public void setCerPedUrlPath(String cerPedUrlPath) {
        this.cerPedUrlPath = cerPedUrlPath;
    }

    public String getCerAbiAttId() {
        return cerAbiAttId;
    }

    public void setCerAbiAttId(String cerAbiAttId) {
        this.cerAbiAttId = cerAbiAttId;
    }

    public String getCerAbiRealName() {
        return cerAbiRealName;
    }

    public void setCerAbiRealName(String cerAbiRealName) {
        this.cerAbiRealName = cerAbiRealName;
    }

    public String getCerAbiUrlPath() {
        return cerAbiUrlPath;
    }

    public void setCerAbiUrlPath(String cerAbiUrlPath) {
        this.cerAbiUrlPath = cerAbiUrlPath;
    }

    public String getCerShipAttId() {
        return cerShipAttId;
    }

    public void setCerShipAttId(String cerShipAttId) {
        this.cerShipAttId = cerShipAttId;
    }

    public String getCerShipRealName() {
        return cerShipRealName;
    }

    public void setCerShipRealName(String cerShipRealName) {
        this.cerShipRealName = cerShipRealName;
    }

    public String getCerShipUrlPath() {
        return cerShipUrlPath;
    }

    public void setCerShipUrlPath(String cerShipUrlPath) {
        this.cerShipUrlPath = cerShipUrlPath;
    }

    public String getTicketNoticeItem() {
        return ticketNoticeItem;
    }

    public void setTicketNoticeItem(String ticketNoticeItem) {
        this.ticketNoticeItem = ticketNoticeItem;
    }

    public String getApplyDeptName() {
        return applyDeptName;
    }

    public void setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
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
    
    
    public String getIdcardRealName() {
        return idcardRealName;
    }

    public void setIdcardRealName(String idcardRealName) {
        this.idcardRealName = idcardRealName;
    }

    public String getIdcardUrlPath() {
        return idcardUrlPath;
    }

    public void setIdcardUrlPath(String idcardUrlPath) {
        this.idcardUrlPath = idcardUrlPath;
    }

    public String getCerAchRealName() {
        return cerAchRealName;
    }

    public void setCerAchRealName(String cerAchRealName) {
        this.cerAchRealName = cerAchRealName;
    }

    public String getCerAchUrlPath() {
        return cerAchUrlPath;
    }

    public void setCerAchUrlPath(String cerAchUrlPath) {
        this.cerAchUrlPath = cerAchUrlPath;
    }

    public String getGraRecomRealName() {
        return graRecomRealName;
    }

    public void setGraRecomRealName(String graRecomRealName) {
        this.graRecomRealName = graRecomRealName;
    }

    public String getApplyJobCode() {
        return applyJobCode;
    }

    public void setApplyJobCode(String applyJobCode) {
        this.applyJobCode = applyJobCode;
    }

    public String getGraRecomUrlPath() {
        return graRecomUrlPath;
    }

    public void setGraRecomUrlPath(String graRecomUrlPath) {
        this.graRecomUrlPath = graRecomUrlPath;
    }

    public String getAcaQuaRealName() {
        return acaQuaRealName;
    }

    public void setAcaQuaRealName(String acaQuaRealName) {
        this.acaQuaRealName = acaQuaRealName;
    }

    public String getAcaQuaUrlPath() {
        return acaQuaUrlPath;
    }

    public void setAcaQuaUrlPath(String acaQuaUrlPath) {
        this.acaQuaUrlPath = acaQuaUrlPath;
    }

    public String getBacRealName() {
        return bacRealName;
    }

    public void setBacRealName(String bacRealName) {
        this.bacRealName = bacRealName;
    }

    public String getBacUrlPath() {
        return bacUrlPath;
    }

    public void setBacUrlPath(String bacUrlPath) {
        this.bacUrlPath = bacUrlPath;
    }

    public String getBacQuaRealName() {
        return bacQuaRealName;
    }

    public void setBacQuaRealName(String bacQuaRealName) {
        this.bacQuaRealName = bacQuaRealName;
    }

    public String getBacQuaUrlPath() {
        return bacQuaUrlPath;
    }

    public void setBacQuaUrlPath(String bacQuaUrlPath) {
        this.bacQuaUrlPath = bacQuaUrlPath;
    }

    public String getFamPlanRealName() {
        return famPlanRealName;
    }

    public void setFamPlanRealName(String famPlanRealName) {
        this.famPlanRealName = famPlanRealName;
    }

    public String getFamPlanUrlPath() {
        return famPlanUrlPath;
    }

    public void setFamPlanUrlPath(String famPlanUrlPath) {
        this.famPlanUrlPath = famPlanUrlPath;
    }

    public String getStudyWorkRealName() {
        return studyWorkRealName;
    }

    public void setStudyWorkRealName(String studyWorkRealName) {
        this.studyWorkRealName = studyWorkRealName;
    }

    public String getStudyWorkUrlPath() {
        return studyWorkUrlPath;
    }

    public void setStudyWorkUrlPath(String studyWorkUrlPath) {
        this.studyWorkUrlPath = studyWorkUrlPath;
    }

    public String getTeaVideoRealName() {
        return teaVideoRealName;
    }

    public void setTeaVideoRealName(String teaVideoRealName) {
        this.teaVideoRealName = teaVideoRealName;
    }

    public String getTeaVideoUrlPath() {
        return teaVideoUrlPath;
    }

    public void setTeaVideoUrlPath(String teaVideoUrlPath) {
        this.teaVideoUrlPath = teaVideoUrlPath;
    }

    public String getSuspendEmpRealName() {
        return suspendEmpRealName;
    }

    public void setSuspendEmpRealName(String suspendEmpRealName) {
        this.suspendEmpRealName = suspendEmpRealName;
    }

    public String getSuspendEmpUrlPath() {
        return suspendEmpUrlPath;
    }

    public void setSuspendEmpUrlPath(String suspendEmpUrlPath) {
        this.suspendEmpUrlPath = suspendEmpUrlPath;
    }

    public String getAbroadStudyRealName() {
        return abroadStudyRealName;
    }

    public void setAbroadStudyRealName(String abroadStudyRealName) {
        this.abroadStudyRealName = abroadStudyRealName;
    }

    public String getAbroadStudyUrlPath() {
        return abroadStudyUrlPath;
    }

    public void setAbroadStudyUrlPath(String abroadStudyUrlPath) {
        this.abroadStudyUrlPath = abroadStudyUrlPath;
    }

    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getApplyJobId(){
        return this.applyJobId;
    }
    
    public void setApplyJobId(String applyJobId){
        this.applyJobId = applyJobId;
    }
    public String getApplyJobName(){
        return this.applyJobName;
    }
    
    public void setApplyJobName(String applyJobName){
        this.applyJobName = applyJobName;
    }
    public String getApplyDepId(){
        return this.applyDepId;
    }
    
    public void setApplyDepId(String applyDepId){
        this.applyDepId = applyDepId;
    }
    public String getIdcardAttId(){
        return this.idcardAttId;
    }
    
    public void setIdcardAttId(String idcardAttId){
        this.idcardAttId = idcardAttId;
    }
    public String getCerAchAttId(){
        return this.cerAchAttId;
    }
    
    public void setCerAchAttId(String cerAchAttId){
        this.cerAchAttId = cerAchAttId;
    }
    public String getGraRecomAttId(){
        return this.graRecomAttId;
    }
    
    public void setGraRecomAttId(String graRecomAttId){
        this.graRecomAttId = graRecomAttId;
    }
    public String getAcaQuaAttId(){
        return this.acaQuaAttId;
    }
    
    public void setAcaQuaAttId(String acaQuaAttId){
        this.acaQuaAttId = acaQuaAttId;
    }
    public String getBacAttId(){
        return this.bacAttId;
    }
    
    public void setBacAttId(String bacAttId){
        this.bacAttId = bacAttId;
    }
    public String getBacQuaAttId(){
        return this.bacQuaAttId;
    }
    
    public void setBacQuaAttId(String bacQuaAttId){
        this.bacQuaAttId = bacQuaAttId;
    }
    public String getFamPlanAttId(){
        return this.famPlanAttId;
    }
    
    public void setFamPlanAttId(String famPlanAttId){
        this.famPlanAttId = famPlanAttId;
    }
    public String getStudyWorkAttId(){
        return this.studyWorkAttId;
    }
    
    public void setStudyWorkAttId(String studyWorkAttId){
        this.studyWorkAttId = studyWorkAttId;
    }
    public String getTeaVideoAttId(){
        return this.teaVideoAttId;
    }
    
    public void setTeaVideoAttId(String teaVideoAttId){
        this.teaVideoAttId = teaVideoAttId;
    }
    public String getSuspendEmpAttId(){
        return this.suspendEmpAttId;
    }
    
    public void setSuspendEmpAttId(String suspendEmpAttId){
        this.suspendEmpAttId = suspendEmpAttId;
    }
    public String getAbroadStudyAttId(){
        return this.abroadStudyAttId;
    }
    
    public void setAbroadStudyAttId(String abroadStudyAttId){
        this.abroadStudyAttId = abroadStudyAttId;
    }
    public String getApplyReason(){
        return this.applyReason;
    }
    
    public void setApplyReason(String applyReason){
        this.applyReason = applyReason;
    }
    public String getApplyStatus(){
        return this.applyStatus;
    }
    
    public void setApplyStatus(String applyStatus){
        this.applyStatus = applyStatus;
    }
    public String getApplyAuditCode(){
        return this.applyAuditCode;
    }
    
    public void setApplyAuditCode(String applyAuditCode){
        this.applyAuditCode = applyAuditCode;
    }
    public String getApplyAuditName(){
        return this.applyAuditName;
    }
    
    public void setApplyAuditName(String applyAuditName){
        this.applyAuditName = applyAuditName;
    }
    public String getIsRepay(){
        return this.isRepay;
    }
    
    public void setIsRepay(String isRepay){
        this.isRepay = isRepay;
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

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype;
    }

    public String getTranscriptAttId() {
        return transcriptAttId;
    }

    public void setTranscriptAttId(String transcriptAttId) {
        this.transcriptAttId = transcriptAttId;
    }

    public String getTranscriptRealName() {
        return transcriptRealName;
    }

    public void setTranscriptRealName(String transcriptRealName) {
        this.transcriptRealName = transcriptRealName;
    }

    public String getTranscriptUrlPath() {
        return transcriptUrlPath;
    }

    public void setTranscriptUrlPath(String transcriptUrlPath) {
        this.transcriptUrlPath = transcriptUrlPath;
    }

    
    
    public String getPostSetStr() {
		return postSetStr;
	}

	public void setPostSetStr(String postSetStr) {
		this.postSetStr = postSetStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyJobId == null) ? 0 : applyJobId.hashCode());
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
		StudentApplyInfo other = (StudentApplyInfo) obj;
		if (applyJobId == null) {
			if (other.applyJobId != null)
				return false;
		} else if (!applyJobId.equals(other.applyJobId))
			return false;
		return true;
	}

	public StudentApplyInfo(String id, String studentId, String applyJobId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.applyJobId = applyJobId;
	}

	 
	public StudentApplyInfo() {
		super();
	}
    
    
}
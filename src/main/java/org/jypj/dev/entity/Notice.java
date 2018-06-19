package org.jypj.dev.entity;
import java.util.Date;

/**
 * 招聘公告
 * @author
 */
public class Notice implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

    private String id; //索引
    private String themeId; //项目Id
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private int enterCondition; //录取形式（1.比分2比，3分）
    private int isPublish; //是否发布（0未，1已）
    private Date reportStart; //补报开始时间
    private Date reportEnd; //补报结束时间
    private Date registerStart; //注册报名开始时间
    private Date registerEnd; //注册报名结束时间
    private Date interviewStart; //面试开始时间
    private Date interviewEnd; //面试结束时间
    private int interviewListPublish; //面试名单是否发布
    private int interviewEnterPropo; //面试录取比例
    private int interviewEnterLine; //面试录取分数线
    private Date writtenStart; //笔试开始时间
    private Date writtenEnd; //笔试结束时间
    private int writtenListPublish; //笔试名单是否发布
    private int writtenScorePublish; //笔试成绩是否发布
    private int writtenEnterPropo; //笔试录取比例
    private int writtenEnterLine; //笔试录取分数线
    private Date lectureStart; //统一试讲开始时间
    private Date lectureEnd; //统一试讲结束时间
    private int lectureListPublishNor; //统一试讲是否公开普通考生名单
    private int lectureListPublishArt; //统一试讲是否公开艺术考生名单
    private int lectureScorePublishNor; //统一试讲是否公开普通考生的成绩
    private int lectureScorePublishArt; //统一试讲是否公开艺术考生的成绩
    private int lectureEnterPropo; //统一试讲录取比例
    private int lectureEnterLine; //统一试讲录取分数线
    private Date bodyexamStart; //体检开始时间
    private Date bodyexamEnd; //体检结束时间
    private int bodyexamListPublish; //体检是否公布名单
    private int bodyexamScorePublish; //体检是否公布分数线
    private Date lookStart; //考察开始时间
    private Date lookEnd; //考察结束时间
    private int lookListPublish; //考察名单是否公布
    private int lookScorePublish; //考察分数是否公布
    private Date showStart; //公示开始时间
    private Date showEnd; //公示结束时间
    private int showListPublish; //公示是否公布名单
    private int showScorePublish; //公示是否公布分数线
    private String content; //项目公告正文
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注

    // 注册报名开始结束时间
    private String registerTimeStr;

    // 单位面试开始结束时间
    private String interviewTimeStr;

    // 统一笔试开始结束时间
    private String writtenTimeStr;

    // 统一试讲开始结束时间
    private String talkTimeStr;
    // 体检开始结束时间
    private String bodyexamTimeStr;

    // 考察开始结束时间
    private String lookTimeStr;
    // 公示开始结束时间
    private String showTimeStr;

    private int stop ; //公告是否停止
    private String stopTitle ;  //暂停标题
    private String stopContent ;  //暂停内容
    
    
    public String getRegisterTimeStr() {
        return registerTimeStr;
    }

    public void setRegisterTimeStr(String registerTimeStr) {
        this.registerTimeStr = registerTimeStr;
    }

    public String getInterviewTimeStr() {
        return interviewTimeStr;
    }

    public void setInterviewTimeStr(String interviewTimeStr) {
        this.interviewTimeStr = interviewTimeStr;
    }

    public String getWrittenTimeStr() {
        return writtenTimeStr;
    }

    public void setWrittenTimeStr(String writtenTimeStr) {
        this.writtenTimeStr = writtenTimeStr;
    }

    public String getTalkTimeStr() {
        return talkTimeStr;
    }

    public void setTalkTimeStr(String talkTimeStr) {
        this.talkTimeStr = talkTimeStr;
    }

    public String getBodyexamTimeStr() {
        return bodyexamTimeStr;
    }

    public void setBodyexamTimeStr(String bodyexamTimeStr) {
        this.bodyexamTimeStr = bodyexamTimeStr;
    }

    public String getLookTimeStr() {
        return lookTimeStr;
    }

    public void setLookTimeStr(String lookTimeStr) {
        this.lookTimeStr = lookTimeStr;
    }

    public String getShowTimeStr() {
        return showTimeStr;
    }

    public void setShowTimeStr(String showTimeStr) {
        this.showTimeStr = showTimeStr;
    }

    private String themeName; //扩展  招聘名称

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeId(){
        return this.themeId;
    }

    public void setThemeId(String themeId){
        this.themeId = themeId;
    }
    public Date getStartTime(){
        return this.startTime;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    public Date getEndTime(){
        return this.endTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public int getEnterCondition(){
        return this.enterCondition;
    }

    public void setEnterCondition(int enterCondition){
        this.enterCondition = enterCondition;
    }
    public int getIsPublish(){
        return this.isPublish;
    }

    public void setIsPublish(int isPublish){
        this.isPublish = isPublish;
    }
    public Date getReportStart(){
        return this.reportStart;
    }

    public void setReportStart(Date reportStart){
        this.reportStart = reportStart;
    }
    public Date getReportEnd(){
        return this.reportEnd;
    }

    public void setReportEnd(Date reportEnd){
        this.reportEnd = reportEnd;
    }
    public Date getRegisterStart(){
        return this.registerStart;
    }

    public void setRegisterStart(Date registerStart){
        this.registerStart = registerStart;
    }
    public Date getRegisterEnd(){
        return this.registerEnd;
    }

    public void setRegisterEnd(Date registerEnd){
        this.registerEnd = registerEnd;
    }
    public Date getInterviewStart(){
        return this.interviewStart;
    }

    public void setInterviewStart(Date interviewStart){
        this.interviewStart = interviewStart;
    }
    public Date getInterviewEnd(){
        return this.interviewEnd;
    }

    public void setInterviewEnd(Date interviewEnd){
        this.interviewEnd = interviewEnd;
    }
    public int getInterviewListPublish(){
        return this.interviewListPublish;
    }

    public void setInterviewListPublish(int interviewListPublish){
        this.interviewListPublish = interviewListPublish;
    }
    public int getInterviewEnterPropo(){
        return this.interviewEnterPropo;
    }

    public void setInterviewEnterPropo(int interviewEnterPropo){
        this.interviewEnterPropo = interviewEnterPropo;
    }
    public int getInterviewEnterLine(){
        return this.interviewEnterLine;
    }

    public void setInterviewEnterLine(int interviewEnterLine){
        this.interviewEnterLine = interviewEnterLine;
    }
    public Date getWrittenStart(){
        return this.writtenStart;
    }

    public void setWrittenStart(Date writtenStart){
        this.writtenStart = writtenStart;
    }
    public Date getWrittenEnd(){
        return this.writtenEnd;
    }

    public void setWrittenEnd(Date writtenEnd){
        this.writtenEnd = writtenEnd;
    }
    public int getWrittenListPublish(){
        return this.writtenListPublish;
    }

    public void setWrittenListPublish(int writtenListPublish){
        this.writtenListPublish = writtenListPublish;
    }
    public int getWrittenScorePublish(){
        return this.writtenScorePublish;
    }

    public void setWrittenScorePublish(int writtenScorePublish){
        this.writtenScorePublish = writtenScorePublish;
    }
    public int getWrittenEnterPropo(){
        return this.writtenEnterPropo;
    }

    public void setWrittenEnterPropo(int writtenEnterPropo){
        this.writtenEnterPropo = writtenEnterPropo;
    }
    public int getWrittenEnterLine(){
        return this.writtenEnterLine;
    }

    public void setWrittenEnterLine(int writtenEnterLine){
        this.writtenEnterLine = writtenEnterLine;
    }
    public Date getLectureStart(){
        return this.lectureStart;
    }

    public void setLectureStart(Date lectureStart){
        this.lectureStart = lectureStart;
    }
    public Date getLectureEnd(){
        return this.lectureEnd;
    }

    public void setLectureEnd(Date lectureEnd){
        this.lectureEnd = lectureEnd;
    }
    public int getLectureListPublishNor(){
        return this.lectureListPublishNor;
    }

    public void setLectureListPublishNor(int lectureListPublishNor){
        this.lectureListPublishNor = lectureListPublishNor;
    }
    public int getLectureListPublishArt(){
        return this.lectureListPublishArt;
    }

    public void setLectureListPublishArt(int lectureListPublishArt){
        this.lectureListPublishArt = lectureListPublishArt;
    }
    public int getLectureScorePublishNor(){
        return this.lectureScorePublishNor;
    }

    public void setLectureScorePublishNor(int lectureScorePublishNor){
        this.lectureScorePublishNor = lectureScorePublishNor;
    }
    public int getLectureScorePublishArt(){
        return this.lectureScorePublishArt;
    }

    public void setLectureScorePublishArt(int lectureScorePublishArt){
        this.lectureScorePublishArt = lectureScorePublishArt;
    }
    public int getLectureEnterPropo(){
        return this.lectureEnterPropo;
    }

    public void setLectureEnterPropo(int lectureEnterPropo){
        this.lectureEnterPropo = lectureEnterPropo;
    }
    public int getLectureEnterLine(){
        return this.lectureEnterLine;
    }

    public void setLectureEnterLine(int lectureEnterLine){
        this.lectureEnterLine = lectureEnterLine;
    }
    public Date getBodyexamStart(){
        return this.bodyexamStart;
    }

    public void setBodyexamStart(Date bodyexamStart){
        this.bodyexamStart = bodyexamStart;
    }
    public Date getBodyexamEnd(){
        return this.bodyexamEnd;
    }

    public void setBodyexamEnd(Date bodyexamEnd){
        this.bodyexamEnd = bodyexamEnd;
    }
    public int getBodyexamListPublish(){
        return this.bodyexamListPublish;
    }

    public void setBodyexamListPublish(int bodyexamListPublish){
        this.bodyexamListPublish = bodyexamListPublish;
    }
    public int getBodyexamScorePublish(){
        return this.bodyexamScorePublish;
    }

    public void setBodyexamScorePublish(int bodyexamScorePublish){
        this.bodyexamScorePublish = bodyexamScorePublish;
    }
    public Date getLookStart(){
        return this.lookStart;
    }

    public void setLookStart(Date lookStart){
        this.lookStart = lookStart;
    }
    public Date getLookEnd(){
        return this.lookEnd;
    }

    public void setLookEnd(Date lookEnd){
        this.lookEnd = lookEnd;
    }
    public int getLookListPublish(){
        return this.lookListPublish;
    }

    public void setLookListPublish(int lookListPublish){
        this.lookListPublish = lookListPublish;
    }
    public int getLookScorePublish(){
        return this.lookScorePublish;
    }

    public void setLookScorePublish(int lookScorePublish){
        this.lookScorePublish = lookScorePublish;
    }
    public Date getShowStart(){
        return this.showStart;
    }

    public void setShowStart(Date showStart){
        this.showStart = showStart;
    }
    public Date getShowEnd(){
        return this.showEnd;
    }

    public void setShowEnd(Date showEnd){
        this.showEnd = showEnd;
    }
    public int getShowListPublish(){
        return this.showListPublish;
    }

    public void setShowListPublish(int showListPublish){
        this.showListPublish = showListPublish;
    }
    public int getShowScorePublish(){
        return this.showScorePublish;
    }

    public void setShowScorePublish(int showScorePublish){
        this.showScorePublish = showScorePublish;
    }
    public String getContent(){
        return this.content;
    }
    
    public void setContent(String content){
        this.content = content;
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

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public String getStopTitle() {
        return stopTitle;
    }

    public void setStopTitle(String stopTitle) {
        this.stopTitle = stopTitle;
    }

    public String getStopContent() {
        return stopContent;
    }

    public void setStopContent(String stopContent) {
        this.stopContent = stopContent;
    }
}
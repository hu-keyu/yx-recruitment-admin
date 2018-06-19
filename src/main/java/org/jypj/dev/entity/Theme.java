package org.jypj.dev.entity;

import java.util.Date;

/**
 * 招聘主题
 * @author
 */
public class Theme implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //索引
    private String theme; //主题
    private Date reportEndTime; //上报截止时间
    private String content; //详细内容
    private int status; //0未发布，1已发布
    private int step; //记录流程状态
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注

    private String eduIds;//额外添加的字段，计划上报单位的ids
    private String eduNames;//额外添加的字段
    private String noticeSubstr;//字段过长时，对文本进行截取再显示，前端用title显示全部
    
    //非数据库映射字段 add by QiCai
    private String schoolCount;//学校是否发布面试成绩 >1 则发布了面试成绩
    private String publishSchoolCount;//共有多少个学校发布了面试成绩
    private String schooTotleCount;//共有多少个学校 
   
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getTheme(){
        return this.theme;
    }
    
    public String getNoticeSubstr() {
        if (this.theme != null) {
            if (this.theme.length() > 30) {
                return this.theme.substring(0, 30) + "...";
            }
            return this.theme;
        }
        return "";
    }

    public void setNoticeSubstr(String noticeSubstr) {
        this.noticeSubstr = noticeSubstr;
    }

    public void setTheme(String theme){
        this.theme = theme;
    }
    public Date getReportEndTime(){
        return this.reportEndTime;
    }
    
    public void setReportEndTime(Date reportEndTime){
        this.reportEndTime = reportEndTime;
    }
    public String getContent(){
        return this.content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    public int getStatus(){
        return this.status;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    public int getStep(){
        return this.step;
    }
    
    public void setStep(int step){
        this.step = step;
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

    public String getEduIds() {
        return eduIds;
    }

    public void setEduIds(String eduIds) {
        this.eduIds = eduIds;
    }

    public String getEduNames() {
        return eduNames;
    }

    public void setEduNames(String eduNames) {
        this.eduNames = eduNames;
    }

	public String getSchoolCount() {
		return schoolCount;
	}

	public void setSchoolCount(String schoolCount) {
		this.schoolCount = schoolCount;
	}

	public String getPublishSchoolCount() {
		return publishSchoolCount;
	}

	public void setPublishSchoolCount(String publishSchoolCount) {
		this.publishSchoolCount = publishSchoolCount;
	}

	public String getSchooTotleCount() {
		return schooTotleCount;
	}

	public void setSchooTotleCount(String schooTotleCount) {
		this.schooTotleCount = schooTotleCount;
	}
    
}
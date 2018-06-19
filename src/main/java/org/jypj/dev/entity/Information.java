package org.jypj.dev.entity;
import java.util.Date;

/**
 * 学校资料信息表
 * @author
 */
public class Information implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String projectId; //招聘主题ID
    private String positionId; //学校招聘岗位信息ID
    private String schoolId; //学校ID
    private Date startDate; //开始时间
    private Date endDate; //结束时间
    private String site; //地点
    private String year; //年份
    private String status; //状态（0未发布 1已发布）
    private String type; //类型 （1招聘单位面试信息 2现场资料信息）
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注
    
    //非数据库映射字段
    private String postName;//岗位名称
    private String theme;//招聘主题
    private String schoolName;//学校名称
    
    private String startDateStr; //开始时间
    private String endDateStr; //开始时间

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProjectId(){
        return this.projectId;
    }
    
    public void setProjectId(String projectId){
        this.projectId = projectId;
    }
    public String getPositionId(){
        return this.positionId;
    }
    
    public void setPositionId(String positionId){
        this.positionId = positionId;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
    public Date getStartDate(){
        return this.startDate;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public Date getEndDate(){
        return this.endDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public String getSite(){
        return this.site;
    }
    
    public void setSite(String site){
        this.site = site;
    }
    public String getYear(){
        return this.year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
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

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
    
}
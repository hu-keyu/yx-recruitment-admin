package org.jypj.dev.entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author generaterCode
 */
public class ExamLectureGroup implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //
    private String ganweiid; //岗位id
    private String subjectId; //主题id
    private String period; //时间段 1代表上午  2代表下午
    private Date startDate; //具体日期
    private BigDecimal groupNumber; //组号
    private String isShow; //是否发布
    private String groupName; //主名
    private String kaodian;//考点id
    private Date modifyDate; //
    private String modifyUser; //
    private String createUser; //
    private Date createDate; //

    
    private String amDate;
    private String pmDate;
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getGanweiid(){
        return this.ganweiid;
    }
    
    public void setGanweiid(String ganweiid){
        this.ganweiid = ganweiid;
    }
    public String getSubjectId(){
        return this.subjectId;
    }
    
    public void setSubjectId(String subjectId){
        this.subjectId = subjectId;
    }
    public String getPeriod(){
        return this.period;
    }
    
    public void setPeriod(String period){
        this.period = period;
    }
    public Date getStartDate(){
        return this.startDate;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public BigDecimal getGroupNumber(){
        return this.groupNumber;
    }
    
    public void setGroupNumber(BigDecimal groupNumber){
        this.groupNumber = groupNumber;
    }
    public String getIsShow(){
        return this.isShow;
    }
    
    public void setIsShow(String isShow){
        this.isShow = isShow;
    }
    public String getGroupName(){
        return this.groupName;
    }
    
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public Date getModifyDate(){
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }
    public String getModifyUser(){
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }
    public String getCreateUser(){
        return this.createUser;
    }
    
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public Date getCreateDate(){
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

	public String getKaodian() {
		return kaodian;
	}

	public void setKaodian(String kaodian) {
		this.kaodian = kaodian;
	}

	public String getAmDate() {
		return amDate;
	}

	public void setAmDate(String amDate) {
		this.amDate = amDate;
	}

	public String getPmDate() {
		return pmDate;
	}

	public void setPmDate(String pmDate) {
		this.pmDate = pmDate;
	}
    
}
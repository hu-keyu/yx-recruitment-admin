package org.jypj.dev.entity;
import java.util.Date;

/**
 * 
 * @author generaterCode
 */
public class ExamItemsInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    private String id; //考点信息表
    private String itemsId; //招聘项目ID
    private String testNum; //考点编号
    private String type; //考试类型：1、统一笔试2、统一试讲
    private String testName; //考点名称
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private String testAddr; //考试地点
    private String sign; //交通环境
    private String publishStatus; //发布状态：1、保存未发布2、已发布
    private String createUser; //数据库创建人员
    private Date ctime; //数据库创建时间
    private String modifyUser; //数据库修改人员
    private Date mtime; //数据库修改时间
    private String permisGw; //限制岗位
    private String deleteStatus; //删除状态
    //不与数据库关联
    private Integer kaodianNum; //试室数量
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getItemsId(){
        return this.itemsId;
    }
    
    public void setItemsId(String itemsId){
        this.itemsId = itemsId;
    }
    public String getTestNum(){
        return this.testNum;
    }
    
    public void setTestNum(String testNum){
        this.testNum = testNum;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getTestName(){
        return this.testName;
    }
    
    public void setTestName(String testName){
        this.testName = testName;
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
    public String getTestAddr(){
        return this.testAddr;
    }
    
    public void setTestAddr(String testAddr){
        this.testAddr = testAddr;
    }
    public String getSign(){
        return this.sign;
    }
    
    public void setSign(String sign){
        this.sign = sign;
    }
    public String getPublishStatus(){
        return this.publishStatus;
    }
    
    public void setPublishStatus(String publishStatus){
        this.publishStatus = publishStatus;
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
    public String getPermisGw(){
        return this.permisGw;
    }
    
    public void setPermisGw(String permisGw){
        this.permisGw = permisGw;
    }

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getKaodianNum() {
		return kaodianNum;
	}

	public void setKaodianNum(Integer kaodianNum) {
		this.kaodianNum = kaodianNum;
	}
    
    
}
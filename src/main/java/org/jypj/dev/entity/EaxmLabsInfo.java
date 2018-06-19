package org.jypj.dev.entity;
import java.util.Date;

/**
 * 试室信息表
 * @author
 */
public class EaxmLabsInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //试室ID
    private String empItemsId; //招聘项目id
    private String testId; //ITEMS_ID
    private String postId; //岗位id
    private String subjectId; //学科id
    private String labsName; //试室名称   （在统一试讲中相当于组号）
    private String labsNum; //试室号   （在统一试讲中相当于组号）
    private String labsAddr; //试室地点
    private String labsTotal; //试室总人数
    private String labsRealnum; //试室实际人数
    private String isAdd; //是否附加试室 1、是2、否
    private String labsType; //试室类型 1、统一笔试.2、统一试讲
    private String mangerPerson;//监考人


    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    
    private Date  startTime;//开始时间
    
    private Date  endTime; //结束时间
    
    private String nyr;//日月年
    
    private String subjectItmeId;//外键
    

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getEmpItemsId(){
        return this.empItemsId;
    }
    
    public void setEmpItemsId(String empItemsId){
        this.empItemsId = empItemsId;
    }
    public String getTestId(){
        return this.testId;
    }
    
    public void setTestId(String testId){
        this.testId = testId;
    }
    public String getPostId(){
        return this.postId;
    }
    
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getSubjectId(){
        return this.subjectId;
    }
    
    public void setSubjectId(String subjectId){
        this.subjectId = subjectId;
    }
    public String getLabsName(){
        return this.labsName;
    }
    
    public void setLabsName(String labsName){
        this.labsName = labsName;
    }
    public String getLabsNum(){
        return this.labsNum;
    }
    
    public void setLabsNum(String labsNum){
        this.labsNum = labsNum;
    }
    public String getLabsAddr(){
        return this.labsAddr;
    }
    
    public void setLabsAddr(String labsAddr){
        this.labsAddr = labsAddr;
    }
    public String getLabsTotal(){
        return this.labsTotal;
    }
    
    public void setLabsTotal(String labsTotal){
        this.labsTotal = labsTotal;
    }
    public String getLabsRealnum(){
        return this.labsRealnum;
    }
    
    public void setLabsRealnum(String labsRealnum){
        this.labsRealnum = labsRealnum;
    }
    public String getIsAdd(){
        return this.isAdd;
    }
    
    public void setIsAdd(String isAdd){
        this.isAdd = isAdd;
    }
    public String getLabsType(){
        return this.labsType;
    }
    
    public void setLabsType(String labsType){
        this.labsType = labsType;
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

	public String getNyr() {
		return nyr;
	}

	public void setNyr(String nyr) {
		this.nyr = nyr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EaxmLabsInfo other = (EaxmLabsInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMangerPerson() {
		return mangerPerson;
	}

	public void setMangerPerson(String mangerPerson) {
		this.mangerPerson = mangerPerson;
	}

	public String getSubjectItmeId() {
		return subjectItmeId;
	}

	public void setSubjectItmeId(String subjectItmeId) {
		this.subjectItmeId = subjectItmeId;
	}

    
    
}
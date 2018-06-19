package org.jypj.dev.entity;
import java.util.Date;

/**
 * 报考的学科相关信息表
 * @author generaterCode
 */
public class ExamSubjectInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String empItemsId; //招聘项目id
    private String testId; //考点id
    private String subjectNum; //岗位总人数
    private String labsAmount; //试室数(按岗位分)
    private String postId; //岗位id
    private String subjectId; //学科id
    private String testAnmount; //试卷袋数


    private String isAdd; //是否是附加试室记录
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String addroomdes; //附加室岗位与人数对应情况
    
    //数据库不对应字段
    private String  nyr;
    

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
    public String getSubjectNum(){
        return this.subjectNum;
    }
    
    public void setSubjectNum(String subjectNum){
        this.subjectNum = subjectNum;
    }
    public String getLabsAmount(){
        return this.labsAmount;
    }
    
    public void setLabsAmount(String labsAmount){
        this.labsAmount = labsAmount;
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
    public String getTestAnmount(){
        return this.testAnmount;
    }
    
    public void setTestAnmount(String testAnmount){
        this.testAnmount = testAnmount;
    }
    public String getIsAdd(){
        return this.isAdd;
    }
    
    public void setIsAdd(String isAdd){
        this.isAdd = isAdd;
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
    public String getAddroomdes(){
        return this.addroomdes;
    }
    
    public void setAddroomdes(String addroomdes){
        this.addroomdes = addroomdes;
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
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		ExamSubjectInfo other = (ExamSubjectInfo) obj;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}
    
	
}
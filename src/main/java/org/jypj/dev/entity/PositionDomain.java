package org.jypj.dev.entity;
import java.util.Date;

/**
 * 学校岗位专业要求表
 * @author
 */
public class PositionDomain implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String schoolId; //学校ID
    private String planApplyId; //学校招聘计划申报信息ID
    private String positionId; //学校招聘岗位ID
    private String domainId; //专业ID
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注
    private String domainName;//专业名称

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
    public String getPlanApplyId(){
        return this.planApplyId;
    }
    
    public void setPlanApplyId(String planApplyId){
        this.planApplyId = planApplyId;
    }
    public String getPositionId(){
        return this.positionId;
    }
    
    public void setPositionId(String positionId){
        this.positionId = positionId;
    }
    public String getDomainId(){
        return this.domainId;
    }
    
    public void setDomainId(String domainId){
        this.domainId = domainId;
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

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
    
}
package org.jypj.dev.entity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 审核原因表
 * @author
 */
public class AuditReason implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String reason; //原因
    private BigDecimal order; //排序
    private String ownerid; //所属拥有者（学校ID或教育局ID）
    private String type; //类型（1学校端面试人员审核 2教育局端招聘人员审核...）
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //备注

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getReason(){
        return this.reason;
    }
    
    public void setReason(String reason){
        this.reason = reason;
    }
    public BigDecimal getOrder(){
        return this.order;
    }
    
    public void setOrder(BigDecimal order){
        this.order = order;
    }
    public String getOwnerid(){
        return this.ownerid;
    }
    
    public void setOwnerid(String ownerid){
        this.ownerid = ownerid;
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
}
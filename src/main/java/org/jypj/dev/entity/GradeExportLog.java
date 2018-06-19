package org.jypj.dev.entity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 成绩导入日志表
 * @author
 */
public class GradeExportLog implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键ID
    private String projectId; //招聘主题ID
    private String type; //类型（1单位面试 2统一笔试 3统一试讲 4体检 5考察 6公式）
    private String fileName; //导入的文件名称
    private String status; //导入状态（导入结束）
    private String result; //导入结果（更新成绩信息：1 条。）
    private BigDecimal orderNumber; //序号
    private Date createtime; //创建时间
    private Date modifytime; //更新时间
    private String createuser; //创建人
    private String modifyuser; //更新人
    private String remark; //关联附件表的ID （附件表 UPLOAD_ATTACHMENT_INFO）
    private String schoolId;//学校ID
    
    //非数据库映射字段
    private String userName;//姓名

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getProjectId(){
        return this.projectId;
    }
    
    public void setProjectId(String projectId){
        this.projectId = projectId;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getFileName(){
        return this.fileName;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    public String getResult(){
        return this.result;
    }
    
    public void setResult(String result){
        this.result = result;
    }
    public BigDecimal getOrderNumber(){
        return this.orderNumber;
    }
    
    public void setOrderNumber(BigDecimal orderNumber){
        this.orderNumber = orderNumber;
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
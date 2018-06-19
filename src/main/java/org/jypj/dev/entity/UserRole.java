package org.jypj.dev.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 * @author generaterCode
 */
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
	 * 主键
	 */
    private String id; 
    /**
	 * 用户登录信息表ID
	 */
    private String userId; 
    /**
	 * 角色ID
	 */
    private String roleId; 
    /**
	 * 创建时间
	 */
    private Date createDate; 
    /**
	 * 修改时间
	 */
    private Date modifyDate; 
    /**
	 * 创建人
	 */
    private String createUser; 
    /**
	 * 修改人
	 */
    private String modifyUser; 

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
  
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    public String getRoleId(){
        return this.roleId;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }

    public Date getModifyDate(){
        return this.modifyDate;
    }
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }

    public String getCreateUser(){
        return this.createUser;
    }
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }

    public String getModifyUser(){
        return this.modifyUser;
    }
}
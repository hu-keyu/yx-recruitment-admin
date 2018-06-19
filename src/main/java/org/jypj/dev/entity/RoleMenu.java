package org.jypj.dev.entity;
import java.util.Date;

/**
 * 角色菜单表
 * @author
 */
public class RoleMenu implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //主键
    private String roleId; //角色ID
    private String menuId; //菜单ID
    private Date createDate; //创建时间
    private Date modifyDate; //修改时间
    private String createUser; //创建人
    private String modifyUser; //修改人

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getRoleId(){
        return this.roleId;
    }
    
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    public String getMenuId(){
        return this.menuId;
    }
    
    public void setMenuId(String menuId){
        this.menuId = menuId;
    }
    public Date getCreateDate(){
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    public Date getModifyDate(){
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }
    public String getCreateUser(){
        return this.createUser;
    }
    
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public String getModifyUser(){
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }
}
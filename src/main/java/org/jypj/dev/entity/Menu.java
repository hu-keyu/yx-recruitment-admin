package org.jypj.dev.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单表
 * @author generaterCode
 */
public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
	 * 主键
	 */
    private String id; 
    /**
	 * 菜单名称
	 */
    private String menuName; 
    /**
	 * logo图片路径
	 */
    private String imageUrl; 
    /**
	 * 显示顺序
	 */
    private BigDecimal sortOrder; 
    /**
	 * 访问地址
	 */
    private String url; 
    /**
	 * 菜单描述
	 */
    private String describe; 
    /**
	 * 父菜单ID
	 */
    private String parentId; 
    /**
	 * 创建时间
	 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate; 
    /**
	 * 修改时间
	 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifyDate; 
    /**
	 * 创建人
	 */
    private String createUser; 
    /**
	 * 修改人
	 */
    private String modifyUser; 
    
    //------------------不与数据库对应
    /**
     * 上级菜单的名称
     */
    private String parentName;
    
    /**
     * 角色ID
     */
    private String roleId;
    
    /**
     * 角色名称
     */
    private String roleName;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    public String getMenuName(){
        return this.menuName;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
    public void setSortOrder(BigDecimal sortOrder){
        this.sortOrder = sortOrder;
    }

    public BigDecimal getSortOrder(){
        return this.sortOrder;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }

    public String getDescribe(){
        return this.describe;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
    }

    public String getParentId(){
        return this.parentId;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
}
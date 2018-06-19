package org.jypj.dev.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 * @author generaterCode
 */
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
	 * 主键
	 */
    private String id; 
    /**
	 * 角色名称
	 */
    private String roleName; 
    /**
	 * 角色编码
	 */
    private String roleCode; 
    /**
	 * 角色类型
	 */
    private String roleType; 
    /**
	 * 描述
	 */
    private String describe; 
    /**
	 * 显示顺序
	 */
    private BigDecimal sortOrder; 
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
    /**
     * 角色类型名称
     */
    private String roleTypeName;
    /**
     * 角色拥有的菜单权限
     */
    private String[] menuIds;
    
    private List<Menu> menus;
    
    public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

	public String getRoleTypeName() {
		return roleTypeName;
	}

	public void setRoleTypeName(String roleTypeName) {
		this.roleTypeName = roleTypeName;
	}

	public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return this.roleName;
    }
    public void setRoleCode(String roleCode){
        this.roleCode = roleCode;
    }

    public String getRoleCode(){
        return this.roleCode;
    }
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType(){
        return this.roleType;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }

    public String getDescribe(){
        return this.describe;
    }
    public void setSortOrder(BigDecimal sortOrder){
        this.sortOrder = sortOrder;
    }

    public BigDecimal getSortOrder(){
        return this.sortOrder;
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
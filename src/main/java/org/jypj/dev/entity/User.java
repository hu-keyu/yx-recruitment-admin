package org.jypj.dev.entity;
import java.io.Serializable;
import java.util.Date;
/**
 * 用户表
 * @author generaterCode
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
    private String id; 
    /**
	 * 登录名称
	 */
    private String loginName; 
    /**
	 * 加密密码
	 */
    private String password; 
    /**
	 * 无密密码
	 */
    private String passwordReal; 
    /**
	 * 用户姓名
	 */
    private String userName; 
    /**
	 * 电子邮件
	 */
    private String email; 
    /**
	 * 用户类型
	 */
    private String userType; 
    /**
     * 第三方系统的用户类型
     */
    private String categorys;
    /**
	 * 用户基本信息表id
	 */
    private String userId; 
    /**
	 * 创建时间
	 */
    private Date createDate; 
    /**
	 * 修改时间
	 */
    private Date modifyDate; 
    /**
	 * 用户昵称
	 */
    private String nickName; 
    /**
     * 用户角色id数组
     */
    private String[] roleIds;
    /**
     * 用户角色显示名称
     */
    private String roleNames;
    
    /**
     * 用户当前角色
     */
    private Role role;
    
    /**
     * 用户类型显示名称
     */
    private String userTypeName;
    
    /**
     * 学校ID
     */
    private String schoolId;
    
    /**
     * 机构ID
     */
    private String orginId;
    
    /**
     * 皮肤
     */
    private String skin;

    /**
     * 学校名称
     */
    private  String schoolName;
    
    /**
     * 单位名称
     */
    private String orginName;
    
	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public User() {
		super();
	}

	public User(String loginName, String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}

	public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }

    public String getLoginName(){
        return this.loginName;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPasswordReal(String passwordReal){
        this.passwordReal = passwordReal;
    }

    public String getPasswordReal(){
        return this.passwordReal;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return this.userType;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
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
    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getNickName(){
        return this.nickName;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getOrginId() {
		return orginId;
	}

	public void setOrginId(String orginId) {
		this.orginId = orginId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getOrginName() {
		return orginName;
	}

	public void setOrginName(String orginName) {
		this.orginName = orginName;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}
	
    
}
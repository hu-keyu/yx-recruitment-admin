package org.jypj.dev.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 字典表
 * @author generaterCode
 */
public class Dictionary implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
	 * 修改人
	 */
    private String modifyUser; 
    /**
	 * 修改时间
	 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifyDate; 
    /**
	 * 描述
	 */
    private String describe; 
    /**
	 * 排序
	 */
    private BigDecimal sortOrder; 
    /**
	 * 显示文本
	 */
    private String text; 
    /**
	 * 主键
	 */
    private String id; 
    /**
	 * 代号名称
	 */
    private String codeName; 
    /**
	 * 代号
	 */
    private String code; 
    /**
	 * 显示值
	 */
    private String value; 
    /**
	 * 是否默认值
	 */
    private String isDefault; 
    /**
	 * 创建时间
	 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate; 
    /**
	 * 创建人
	 */
    private String createUser;

    private boolean checked = false ; //给前端使用的数据

    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }

    public String getModifyUser(){
        return this.modifyUser;
    }
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }

    public Date getModifyDate(){
        return this.modifyDate;
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
    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setCodeName(String codeName){
        this.codeName = codeName;
    }

    public String getCodeName(){
        return this.codeName;
    }
    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
    public void setIsDefault(String isDefault){
        this.isDefault = isDefault;
    }

    public String getIsDefault(){
        return this.isDefault;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }

    public String getCreateUser(){
        return this.createUser;
    }

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Dictionary other = (Dictionary) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
    
    
}
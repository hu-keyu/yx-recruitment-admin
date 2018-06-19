package org.jypj.dev.entity;

/**
 * 招聘主题上报单位
 * @author
 */
public class ThemeUnit implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //索引
    private String themeId; //主题id
    private String unitId; //单位id

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getThemeId(){
        return this.themeId;
    }
    
    public void setThemeId(String themeId){
        this.themeId = themeId;
    }
    public String getUnitId(){
        return this.unitId;
    }
    
    public void setUnitId(String unitId){
        this.unitId = unitId;
    }
}
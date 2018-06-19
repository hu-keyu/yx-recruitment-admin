package org.jypj.dev.entity;

import org.jypj.dev.util.StringUtil;

/**
 * 招聘专业
 * @author
 */
public class Specialty implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //索引
    private String code; //代码
    private String name; //名称
    private String type; //类型（1.研究生，2本科，3高职）
    private int storey; //类型1.代表一级学科，2.代表二级学科3.代表专业
    private String pid; //父节点

    private String prefix ; //额外添加字段前缀
    private String nid ;//额外添加的字段，修改的时候使用新id
    
    private String suffix ;//后缀，额外添加的字段
    
    //扩展用于表单提交查询
    private String firstSbjid ;  //第一学科id
    
    private String firstSbjCode ; //第一学科代码
    
    private String firstSbjName ; //第一学科名称
    
    private String secondSbjid ; //第二学科id
    
    private String secondSbjCode ; //第二学科代码
    
    private String secondSbjName ; //第二学科名称
    
    
    //扩展用于表单提交查询
    private String fProfess;
    private String fProfessName ;
    private String sProfess;
    private String sProfessName ;
    private String zylx;
    private String keyProfess;
    
    private String name1 ;
    private String name2 ;
    private String name3;
    
    private String code1 ;
    private String code2 ;
    private String code3 ;

    private String newId ;//新的id

    /**
     * 前端字符长度截取 一级学科
     */
    private String fProfessTitle;

    /**
     * 二级学科截取
     */
    private String sProfessTitle;

    public String getsProfessTitle() {
        return sProfessTitle;
    }

    public void setsProfessTitle(String sProfessTitle) {
        if (StringUtil.isNotEmpty(this.name)) {
            this.sProfessTitle = this.name.length() > 5 ? this.name.substring(0,5) + "..." : this.name;
        } else {
            this.sProfessTitle = "";
        }
    }

    public String getfProfessTitle() {
        return fProfessTitle;
    }

    public void setfProfessTitle(String fProfessTitle) {
        if (StringUtil.isNotEmpty(this.name)) {
            this.fProfessTitle = this.name.length() > 5 ? this.name.substring(0,5) + "..." : this.name;
        } else {
            this.fProfessTitle = "";
        }

    }

    public String getfProfess() {
        return fProfess;
    }

    public void setfProfess(String fProfess) {
        this.fProfess = fProfess;
    }

    public String getsProfess() {
        return sProfess;
    }

    public void setsProfess(String sProfess) {
        this.sProfess = sProfess;
    }

    public String getZylx() {
        return zylx;
    }

    public void setZylx(String zylx) {
        this.zylx = zylx;
    }

    public String getKeyProfess() {
        return keyProfess;
    }

    public void setKeyProfess(String keyProfess) {
        this.keyProfess = keyProfess;
    }

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public int getStorey(){
        return this.storey;
    }
    
    public void setStorey(int storey){
        this.storey = storey;
    }
    public String getPid(){
        return this.pid;
    }
    
    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getfProfessName() {
        return fProfessName;
    }

    public void setfProfessName(String fProfessName) {
        this.fProfessName = fProfessName;
    }

    public String getsProfessName() {
        return sProfessName;
    }

    public void setsProfessName(String sProfessName) {
        this.sProfessName = sProfessName;
    }

	public String getFirstSbjid() {
		return firstSbjid;
	}

	public void setFirstSbjid(String firstSbjid) {
		this.firstSbjid = firstSbjid;
	}

	public String getFirstSbjCode() {
		return firstSbjCode;
	}

	public void setFirstSbjCode(String firstSbjCode) {
		this.firstSbjCode = firstSbjCode;
	}

	public String getFirstSbjName() {
		return firstSbjName;
	}

	public void setFirstSbjName(String firstSbjName) {
		this.firstSbjName = firstSbjName;
	}

	public String getSecondSbjid() {
		return secondSbjid;
	}

	public void setSecondSbjid(String secondSbjid) {
		this.secondSbjid = secondSbjid;
	}

	public String getSecondSbjCode() {
		return secondSbjCode;
	}

	public void setSecondSbjCode(String secondSbjCode) {
		this.secondSbjCode = secondSbjCode;
	}

	public String getSecondSbjName() {
		return secondSbjName;
	}

	public void setSecondSbjName(String secondSbjName) {
		this.secondSbjName = secondSbjName;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }
}
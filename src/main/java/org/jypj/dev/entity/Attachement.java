package org.jypj.dev.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jypj.dev.util.StringUtil;

/**
 * 上传附件信息表
 * @author
 */
public class Attachement implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	 /**
     * 图片文件类型         
     */
    private List<String> imgExts = new ArrayList<String>() {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        {
            add("png");
            add("gif");
            add("jpg");
            add("jpeg");
        }
    };
    
    
    /**
     * 非图片文件类型
     */
    private List<String> notImgExts = new ArrayList<String>() {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        {
            add("doc");
            add("docx");
            add("pdf");
            add("rar");
        }
    };
	
    private String id; //附件ID
    private String employItemsId; //招聘项目id
    private String studentId; //考生id
    private String path; //上传路径
    private Date uploadDate; //上传时间
    private String realName; //真实文件名
    private String fileType; //文件类型，读取字典
    private String uploadObject; //上传对象，考生类型
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String moduleType;//模块类型（导入成绩时专用字段 1学校面试 2统一笔试 3统一试讲 4... ）
    
    /*
     * 扩展属性
     */
    //图片名
    private String alt;
    //图片id
    private String pid;
    //原图地址
    private String src;
    //缩略图地址
    private String thumb = "";
    
    private String isImg;
    
    public void setIsImg(String isImg) {
        this.isImg = isImg;
    }
    

    public String getIsImg() {
        return isImg;
    }


    /**
     * 1  图片  2 非图片  0 其他
     * @return
     */
    public String isImgOrFile() {
        if (StringUtil.isNotEmpty(this.realName)) {
            String fileExt = this.realName.substring(this.realName.lastIndexOf(".") + 1).toLowerCase();
            if (this.imgExts.contains(fileExt)) {
                return "1";
            } else if (this.notImgExts.contains(fileExt)){
                return "2";
            }
        } 
        return "0";
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getEmployItemsId(){
        return this.employItemsId;
    }
    
    public void setEmployItemsId(String employItemsId){
        this.employItemsId = employItemsId;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getPath(){
        return this.path;
    }
    
    public void setPath(String path){
        this.path = path;
    }
    public Date getUploadDate(){
        return this.uploadDate;
    }
    
    public void setUploadDate(Date uploadDate){
        this.uploadDate = uploadDate;
    }
    public String getRealName(){
        return this.realName;
    }
    
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getFileType(){
        return this.fileType;
    }
    
    public void setFileType(String fileType){
        this.fileType = fileType;
    }
    public String getUploadObject(){
        return this.uploadObject;
    }
    
    public void setUploadObject(String uploadObject){
        this.uploadObject = uploadObject;
    }
    public String getCreateUser(){
        return this.createUser;
    }
    
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public Date getCtime(){
        return this.ctime;
    }
    
    public void setCtime(Date ctime){
        this.ctime = ctime;
    }
    public String getModifyUser(){
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }
    public Date getMtime(){
        return this.mtime;
    }
    
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
    
}
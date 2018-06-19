package org.jypj.dev.entity;
import java.util.Date;

/**
 * 统一试讲入围表
 * @author
 */
public class ScoreEnterTrial implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //
    private String itemsId; //招聘项目id
    private String postId; //岗位id
    private String studentId; //考生id
    private String type; // 1、单位面试2、统一笔试3、统一试讲4、体检5、考察6、公示
    private String isEnter; //是否入围：1、是 2、否
    private String subjectType; //学科类别：0、普通科1、艺术科
    private String scorePublishStatus; //成绩发布状态1、未发布 2已发布
    private String listPublishStatus; //名单发布状态1、未发布2、已发布
    private String createUser; //数据创建人员
    private Date ctime; //数据创建时间
    private String modifyUser; //数据修改人员
    private Date mtime; //数据修改时间
    private String schoolId; //学校id
    private String groupId;//组ID

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getItemsId(){
        return this.itemsId;
    }
    
    public void setItemsId(String itemsId){
        this.itemsId = itemsId;
    }
    public String getPostId(){
        return this.postId;
    }
    
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getStudentId(){
        return this.studentId;
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getIsEnter(){
        return this.isEnter;
    }
    
    public void setIsEnter(String isEnter){
        this.isEnter = isEnter;
    }
    public String getSubjectType(){
        return this.subjectType;
    }
    
    public void setSubjectType(String subjectType){
        this.subjectType = subjectType;
    }
    public String getScorePublishStatus(){
        return this.scorePublishStatus;
    }
    
    public void setScorePublishStatus(String scorePublishStatus){
        this.scorePublishStatus = scorePublishStatus;
    }
    public String getListPublishStatus(){
        return this.listPublishStatus;
    }
    
    public void setListPublishStatus(String listPublishStatus){
        this.listPublishStatus = listPublishStatus;
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
    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
    
    
}
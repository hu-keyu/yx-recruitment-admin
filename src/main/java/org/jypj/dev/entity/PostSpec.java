package org.jypj.dev.entity;

/**
 * 岗位专业
 * @author
 */
public class PostSpec implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id; //索引
    private String postId; //岗位id
    private String specialtyId; //专业id

    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    public String getPostId(){
        return this.postId;
    }
    
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getSpecialtyId(){
        return this.specialtyId;
    }
    
    public void setSpecialtyId(String specialtyId){
        this.specialtyId = specialtyId;
    }
}
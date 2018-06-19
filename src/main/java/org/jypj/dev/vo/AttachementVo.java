package org.jypj.dev.vo;

import java.util.List;

import org.jypj.dev.entity.Attachement;

public class AttachementVo {
    /**
     * 相册
     */
    private String title = "";
    
	private Integer id = 0;
	
	private String attId;
	
	private Integer start = 0;
	
	private List<Attachement> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
    
    public String getAttId() {
        return attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public List<Attachement> getData() {
        return data;
    }

    public void setData(List<Attachement> data) {
        this.data = data;
    }
    
}

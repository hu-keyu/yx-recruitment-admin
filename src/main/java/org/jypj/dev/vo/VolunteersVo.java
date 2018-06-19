package org.jypj.dev.vo;
/**
 * 填报志愿统计
 * 
 * @author hao_hu
 *
 */
public class VolunteersVo {
	/**
	 * 招聘单位id
	 */
	private String applyDepId;
	
	   /**
     * 招聘岗位id
     */
    private String applyJobId;
    
    /**
     * 招聘单位名称
     */
    private String applyDepName;
    
	/**
	 * 岗位名称
	 */
	private String postName;
	
	/**
	 * 志愿填报人数
	 */
	private Integer hasSubmit;
	
	/**
	 * 教育局审核未通过人数
	 */
	private Integer notPassByJYJ;
	
	
	/**
	 * 教育局待审核人数
	 */
	private Integer waitAuditByJYJ;
	
	/**
	 * 审核通过人数
	 */
	private Integer hasPass;

    public String getApplyDepId() {
        return applyDepId;
    }

    public void setApplyDepId(String applyDepId) {
        this.applyDepId = applyDepId;
    }

    public String getApplyJobId() {
        return applyJobId;
    }

    public void setApplyJobId(String applyJobId) {
        this.applyJobId = applyJobId;
    }

    public String getApplyDepName() {
        return applyDepName;
    }

    public void setApplyDepName(String applyDepName) {
        this.applyDepName = applyDepName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getHasSubmit() {
        return hasSubmit;
    }

    public void setHasSubmit(Integer hasSubmit) {
        this.hasSubmit = hasSubmit;
    }

    public Integer getNotPassByJYJ() {
        return notPassByJYJ;
    }

    public void setNotPassByJYJ(Integer notPassByJYJ) {
        this.notPassByJYJ = notPassByJYJ;
    }

    public Integer getWaitAuditByJYJ() {
        return waitAuditByJYJ;
    }

    public void setWaitAuditByJYJ(Integer waitAuditByJYJ) {
        this.waitAuditByJYJ = waitAuditByJYJ;
    }

    public Integer getHasPass() {
        return hasPass;
    }

    public void setHasPass(Integer hasPass) {
        this.hasPass = hasPass;
    }
	
}

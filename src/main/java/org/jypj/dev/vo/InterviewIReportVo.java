package org.jypj.dev.vo;

/**
 * 面试通知书打印实体VO
 * @author QiCai
 *
 */
public class InterviewIReportVo {
	
	private String postName;//岗位名称
	private String name;//姓名
	private String idCard;//身份证
	private String ticketnum;//准考证号
	private String subjectText;//科目名称
	private String startDate;//面试开始时间
	private String endDate;//面试结束时间
	private String address;//面试地址
	private String remark;//考生注意事项
	
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(String ticketnum) {
		this.ticketnum = ticketnum;
	}
	public String getSubjectText() {
		return subjectText;
	}
	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public InterviewIReportVo() {
		super();
	}
	
	public InterviewIReportVo(String postName, String name, String idCard, String ticketnum, String subjectText,
			String startDate, String endDate, String address, String remark) {
		super();
		this.postName = postName;
		this.name = name;
		this.idCard = idCard;
		this.ticketnum = ticketnum;
		this.subjectText = subjectText;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.remark = remark;
	}
	
}

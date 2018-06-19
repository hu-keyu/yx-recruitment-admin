package org.jypj.dev.vo;

public class RoomLayoutVo {

	private String ganWeiId; //岗位Id
	
	private String xkId;//学科Id
	
	private String ganWeiName;//岗位名称
	
	private String xkName;//学科名称
	
	private int ganWeiNum;//报考岗位数量
	
	private int roomNum;//室室数量
	
	private int restNum;//岗位剩余人数

	public String getGanWeiId() {
		return ganWeiId;
	}

	public void setGanWeiId(String ganWeiId) {
		this.ganWeiId = ganWeiId;
	}

	public String getXkId() {
		return xkId;
	}

	public void setXkId(String xkId) {
		this.xkId = xkId;
	}

	
	public void setXkName(String xkName) {
		this.xkName = xkName;
	}

	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getGanWeiName() {
		return ganWeiName;
	}

	public void setGanWeiName(String ganWeiName) {
		this.ganWeiName = ganWeiName;
	}

	public int getGanWeiNum() {
		return ganWeiNum;
	}

	public void setGanWeiNum(int ganWeiNum) {
		this.ganWeiNum = ganWeiNum;
	}

	public String getXkName() {
		return xkName;
	}

	public int getRestNum() {
		return restNum;
	}

	public void setRestNum(int restNum) {
		this.restNum = restNum;
	}

    
}

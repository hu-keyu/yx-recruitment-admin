package org.jypj.dev.vo;

public class SecondLayOutRoomVo {

	private String xkid;    //学科id
	private String ganweiId; //岗位id
	private int num;   //岗位对应的人数
	private String ganweiName;
	private String listStr;
	private String addRooms;//存储岗位对应的id
	
	
	public String getGanweiName() {
		return ganweiName;
	}
	public void setGanweiName(String ganweiName) {
		this.ganweiName = ganweiName;
	}
	public String getXkid() {
		return xkid;
	}
	public void setXkid(String xkid) {
		this.xkid = xkid;
	}
	public String getGanweiId() {
		return ganweiId;
	}
	public void setGanweiId(String ganweiId) {
		this.ganweiId = ganweiId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xkid == null) ? 0 : xkid.hashCode());
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
		SecondLayOutRoomVo other = (SecondLayOutRoomVo) obj;
		if (xkid == null) {
			if (other.xkid != null)
				return false;
		} else if (!xkid.equals(other.xkid))
			return false;
		return true;
	}
	public String getListStr() {
		return listStr;
	}
	public void setListStr(String listStr) {
		this.listStr = listStr;
	}
	public String getAddRooms() {
		return addRooms;
	}
	public void setAddRooms(String addRooms) {
		this.addRooms = addRooms;
	}
	
	
    
}

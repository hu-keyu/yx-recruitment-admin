package org.jypj.dev.vo;

public class RoomGanWeiVo {

	private String ganweiId;
	
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getGanweiId() {
		return ganweiId;
	}

	public void setGanweiId(String ganweiId) {
		this.ganweiId = ganweiId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ganweiId == null) ? 0 : ganweiId.hashCode());
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
		RoomGanWeiVo other = (RoomGanWeiVo) obj;
		if (ganweiId == null) {
			if (other.ganweiId != null)
				return false;
		} else if (!ganweiId.equals(other.ganweiId))
			return false;
		return true;
	}
    
	
}

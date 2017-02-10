/**
 * 
 */
package com.platform.admin.pojo.site;

/**
 * @author gxd
 *
 * 2015-2-9
 */
public class GameTypeInfoPOJO {
	private Integer gameTypeId; 
	private String gameTypeName; 
	private String typeDescribe;  
	private Integer gameNumbers;  
	private Integer typeOrder;
	public Integer getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(Integer gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public String getGameTypeName() {
		return gameTypeName;
	}
	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}
	public String getTypeDescribe() {
		return typeDescribe;
	}
	public void setTypeDescribe(String typeDescribe) {
		this.typeDescribe = typeDescribe;
	}
	public Integer getGameNumbers() {
		return gameNumbers;
	}
	public void setGameNumbers(Integer gameNumbers) {
		this.gameNumbers = gameNumbers;
	}
	public Integer getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(Integer typeOrder) {
		this.typeOrder = typeOrder;
	}
	@Override
	public String toString() {
		return "GameTypeInfoPOJO [gameNumbers=" + gameNumbers + ", gameTypeId="
				+ gameTypeId + ", gameTypeName=" + gameTypeName
				+ ", typeDescribe=" + typeDescribe + ", typeOrder=" + typeOrder
				+ "]";
	} 
}

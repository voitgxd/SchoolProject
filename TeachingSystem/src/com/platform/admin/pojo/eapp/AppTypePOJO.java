/**
 * 
 */
package com.platform.admin.pojo.eapp;

/**
 * @author gxd
 *
 * 2015-3-5
 */
public class AppTypePOJO {
	private Integer flag;
	private Integer typeId;
	private String typeName;
	private Integer platformType;
	private String addTime;
	private String typePic;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPlatformType() {
		return platformType;
	}
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getTypePic() {
		return typePic;
	}
	public void setTypePic(String typePic) {
		this.typePic = typePic;
	}
	@Override
	public String toString() {
		return "AppTypePOJO [addTime=" + addTime + ", flag=" + flag
				+ ", platformType=" + platformType + ", typeId=" + typeId
				+ ", typeName=" + typeName + ", typePic=" + typePic + "]";
	}
}

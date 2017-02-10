package com.platform.admin.pojo.common;


public class RolePOJO {

	//表字段
	private long roleId;
	private String roleName;
	private String createTime;
	
	//非表字段
	private String[] resIds;
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String[] getResIds() {
		return resIds;
	}
	public void setResIds(String[] resIds) {
		this.resIds = resIds;
	}

}

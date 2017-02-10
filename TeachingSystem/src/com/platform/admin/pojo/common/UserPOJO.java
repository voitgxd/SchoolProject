package com.platform.admin.pojo.common;

/**
 * 用户账号信息
 * 
 * @author chentao
 * 
 */
public class UserPOJO {
	private long userId;// 通行证id
	private String userName;// 通行证账号
	private String realName;// 用户真实名字
	private int userType; // 用户类型
	private int roleId; // 角色ID
	private String roleName;// 非表字段
	private String createTime;// 创建时间
	private String lastLoginTime;// 最后登录时间
	private long loginCount;// 登录次数
	private long userState;// 用户状态：1正常，2冻结
	private String lastLoginIp; // 登录Ip
	private String iconUrl;
	private int state;

	private String queryParam;

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
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

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {

		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(long loginCount) {
		this.loginCount = loginCount;
	}

	public long getUserState() {
		return userState;
	}

	public void setUserState(long userState) {
		this.userState = userState;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}

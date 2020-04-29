package com.dashboard.adp.api.model;



public class SysRoleUser extends BaseEntity<Long> {

	private String userId;
	private Integer roleId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}

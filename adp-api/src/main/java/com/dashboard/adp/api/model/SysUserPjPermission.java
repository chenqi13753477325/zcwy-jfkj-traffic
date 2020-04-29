package com.dashboard.adp.api.model;



public class SysUserPjPermission extends BaseEntity<String> {

	private String userId;
	private String pjPermissionId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPjPermissionId() {
		return pjPermissionId;
	}
	public void setPjPermissionId(String pjPermissionId) {
		this.pjPermissionId = pjPermissionId;
	}

}

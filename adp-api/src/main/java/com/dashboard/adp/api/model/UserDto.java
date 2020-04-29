package com.dashboard.adp.api.model;

import java.util.List;

public class UserDto extends SysUser {

	private static final long serialVersionUID = -184009306207076712L;

	private List<Long> roleIds;

	private List<String> departmentIds;

	private List<String> pjIds;

	private List<String> customerIds;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public List<String> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<String> departmentIds) {
		this.departmentIds = departmentIds;
	}

	public List<String> getPjIds() {
		return pjIds;
	}

	public void setPjIds(List<String> pjIds) {
		this.pjIds = pjIds;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}
}

package com.dashboard.adp.api.model;

public class SysRole extends BaseEntity<Long> {

	private static final long serialVersionUID = -3802292814767103648L;


	private String name;

	private String description;

	private Integer level;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}

package com.dashboard.adp.api.model;



public class AdpBaseServiceDepartment extends BaseEntity<Long> {

	private String serviceDepartmentCode;
	private String serviceDepartmentName;
	private String serviceDepartmentScope;
	private String northAndSouth;
	private String lng;
	private String lat;

	public String getServiceDepartmentCode() {
		return serviceDepartmentCode;
	}

	public void setServiceDepartmentCode(String serviceDepartmentCode) {
		this.serviceDepartmentCode = serviceDepartmentCode;
	}

	public String getServiceDepartmentName() {
		return serviceDepartmentName;
	}

	public void setServiceDepartmentName(String serviceDepartmentName) {
		this.serviceDepartmentName = serviceDepartmentName;
	}

	public String getServiceDepartmentScope() {
		return serviceDepartmentScope;
	}

	public void setServiceDepartmentScope(String serviceDepartmentScope) {
		this.serviceDepartmentScope = serviceDepartmentScope;
	}

	public String getNorthAndSouth() {
		return northAndSouth;
	}

	public void setNorthAndSouth(String northAndSouth) {
		this.northAndSouth = northAndSouth;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}

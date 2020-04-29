package com.dashboard.adp.api.bean;
import java.util.List;

/**
 * @Title 天气实体类
 * @Desc: some
 * @Author: zhoubo
 * @CreateDate: 2018/11/10 10:05 AM
 */
public class AdJinFengWeather {

    private List<Double> t_2;
    private List<String> datetime;
    private List<Double> wspd_70;
    
    private String id;				//ID
    private String pj_code;			//项目编号
    private String pj_longitude;	//项目坐标经度
    private String pj_latitude;		//项目坐标维度
   
    private String date;			//日期
    private String year;			//年
    private String month;			//月
    private String day;				//日
    private String windtime;		//时分
    private double predictwind;		//风力
    private double Temperature;		//温度
    
    public List<Double> getT_2() {
        return t_2;
    }

    public void setT_2(List<Double> t_2) {
        this.t_2 = t_2;
    }
    public List<String> getDatetime() {
        return datetime;
    }

    public void setDatetime(List<String> datetime) {
        this.datetime = datetime;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPj_code() {
		return pj_code;
	}

	public void setPj_code(String pj_code) {
		this.pj_code = pj_code;
	}

	public String getPj_longitude() {
		return pj_longitude;
	}

	public void setPj_longitude(String pj_longitude) {
		this.pj_longitude = pj_longitude;
	}

	public String getPj_latitude() {
		return pj_latitude;
	}

	public void setPj_latitude(String pj_latitude) {
		this.pj_latitude = pj_latitude;
	}

	public List<Double> getWspd_70() {
		return wspd_70;
	}

	public void setWspd_70(List<Double> wspd_70) {
		this.wspd_70 = wspd_70;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWindtime() {
		return windtime;
	}

	public void setWindtime(String windtime) {
		this.windtime = windtime;
	}

	public Double getPredictwind() {
		return predictwind;
	}

	public void setPredictwind(Double predictwind) {
		this.predictwind = predictwind;
	}

	public Double getTemperature() {
		return Temperature;
	}

	public void setTemperature(Double temperature) {
		Temperature = temperature;
	}

	

	

	
	
	

	
}

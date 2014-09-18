package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentEntity {
	
	public StudentEntity (){}
	
	public StudentEntity (Integer studentId,String studentName, Integer collegeId,String collegeName){
		this.collegeId = collegeId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.collegeName = collegeName;
	}
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	private Integer studentId;
	private String studentName;
	private Integer collegeId;
	private String collegeName;
	
	// Added property to solve 400 Bad request error comming in REST call from MyRESTDemoTwo.html
	private String cityName;	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}

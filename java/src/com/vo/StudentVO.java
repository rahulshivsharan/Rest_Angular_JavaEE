package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentVO {
	private String studentName;
	private Integer age;
	private Integer marksScored;
	
	
	public String getStudentName() {
		return studentName;
	}
	public StudentVO setStudentName(String studentName) {
		this.studentName = studentName;
		return this;
	}
	public Integer getAge() {
		return age;
	}
	public StudentVO setAge(Integer age) {
		this.age = age;
		return this;
	}
	public Integer getMarksScored() {
		return marksScored;
	}
	public StudentVO setMarksScored(Integer marksScored) {
		this.marksScored = marksScored;
		return this;
	}
	
}

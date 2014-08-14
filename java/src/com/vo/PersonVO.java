package com.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonVO implements Serializable{
	
	public PersonVO(){}
	
	public PersonVO(String personName, Integer personId){
		this.personId = personId;
		this.personName = personName;
		
	}
	
	private String personName;
	private Integer personId;
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
}

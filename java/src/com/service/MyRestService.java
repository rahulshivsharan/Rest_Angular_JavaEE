package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.vo.PersonVO;
import com.vo.StudentVO;

@Path("/ser")
public class MyRestService {
	
	@GET
	@Path("/hi")
	@Produces({MediaType.TEXT_HTML})
	public String sayHi(){
		return "Hi from REST of us";		
	}
	
	@GET
	@Path("/person/list")
	@Produces({MediaType.APPLICATION_JSON})
	public List<PersonVO> getPersonList(){
		List<PersonVO> list = new ArrayList<PersonVO>();
		list.add(new PersonVO("Rahul Shivsharan", 123));
		list.add(new PersonVO("Mehul Sarnikar", 34));
		list.add(new PersonVO("Praful Patel", 343));
		list.add(new PersonVO("Sohel Mallik", 634));
		list.add(new PersonVO("Jignesh Patel", 734));
		return list;
	}
	
	@GET
	@Path("/student/list")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentVO> getStudets(){
		
		List<StudentVO> studentList = new ArrayList<StudentVO>(){{
			add(new StudentVO().setAge(23).setMarksScored(90).setStudentName("Aslam Khan"));
			add(new StudentVO().setAge(13).setMarksScored(60).setStudentName("Nasim Shaikh"));
			add(new StudentVO().setAge(19).setMarksScored(56).setStudentName("Akbar Mohammad"));
			add(new StudentVO().setAge(24).setMarksScored(78).setStudentName("Sohail Qureshi"));
			add(new StudentVO().setAge(15).setMarksScored(46).setStudentName("Yaqub Ansari"));
			add(new StudentVO().setAge(20).setMarksScored(76).setStudentName("Dilavar Ahmad"));
		}};
		
		return studentList;
	}
}

package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vo.CollegeEntity;
import com.vo.PersonVO;
import com.vo.StudentEntity;
import com.vo.StudentVO;

@Path("/ser")
public class MyRestService {
	
	@GET
	@Path("/hi")
	@Produces({MediaType.TEXT_HTML})
	public String sayHi(){
		return "Hi from REST is it OK";		
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
	
	@GET
	@Path("/student/list/two")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentVO> getStudetsTwo(){
		
		List<StudentVO> studentList = new ArrayList<StudentVO>(){{
			add(new StudentVO().setAge(23).setMarksScored(90).setStudentName("Yuvaraj Singh"));
			add(new StudentVO().setAge(13).setMarksScored(60).setStudentName("Aashish Nehra"));
			add(new StudentVO().setAge(19).setMarksScored(56).setStudentName("Kapil Dev"));
			add(new StudentVO().setAge(24).setMarksScored(78).setStudentName("Ajay Jadeja"));
			add(new StudentVO().setAge(15).setMarksScored(46).setStudentName("Ravi Shastri"));
			add(new StudentVO().setAge(20).setMarksScored(76).setStudentName("Sunil Gavaskar"));
		}};
		
		return studentList;
	}
	
	@GET
	@Path("/college/list/One")
	@Produces({MediaType.APPLICATION_JSON})
	public List<CollegeEntity> getColleges(){
		List<CollegeEntity> collegeList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			ps = con.prepareStatement("select college_id as collegeId,college_name as collegeName from CollegeTBL");
			rs = ps.executeQuery();
			collegeList = new ArrayList<CollegeEntity>();
			while(rs.next()){
				collegeList.add(new CollegeEntity(rs.getString("collegeName"), rs.getInt("collegeId")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return collegeList;
	}
	
	@GET
	@Path("/studentEntity/list/One")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentEntity> listStudent(){
		List<StudentEntity> studentList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer  studentQuery = null;
		try{
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			studentList = new ArrayList<StudentEntity>(); 
			studentQuery = new StringBuffer();
			studentQuery.append("SELECT a.st_id        AS StudentId, ");
			studentQuery.append("       a.st_name      AS StudentName, ");
			studentQuery.append("       b.college_name AS CollegeName, ");
			studentQuery.append("       b.college_id   AS collegeId ");
			studentQuery.append("FROM   studenttbl a, ");
			studentQuery.append("       collegetbl b ");
			studentQuery.append("WHERE  a.st_college = b.college_id ");
			
			ps = con.prepareStatement(studentQuery.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				studentList.add(new StudentEntity(rs.getInt("StudentId"),rs.getString("StudentName"),rs.getInt("collegeId"),rs.getString("CollegeName")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return studentList;
	}
	
	@POST
	@Path("/studentEntity/edit")
	@Produces({MediaType.APPLICATION_JSON})	
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public List<StudentEntity> updateAndFetchStudent(@FormParam("studentName") String studentName,@FormParam("studentId") int studentId,@FormParam("collegeId") int collegeId){
		List<StudentEntity> studentList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer  studentQuery = null;
		try{
			//System.out.println(" Student-Name : "+studentName+"\n Student-Id : "+studentId+"\n CollegeId : "+collegeId);
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			ps = con.prepareStatement("update studenttbl set st_name = ?, st_college = ? where st_id = ?");
			ps.setString(1, studentName);
			ps.setInt(2, collegeId);
			ps.setInt(3, studentId);			
			ps.executeUpdate();
			
			studentList = new ArrayList<StudentEntity>(); 
			studentQuery = new StringBuffer();
			studentQuery.append("SELECT a.st_id        AS StudentId, ");
			studentQuery.append("       a.st_name      AS StudentName, ");
			studentQuery.append("       b.college_name AS CollegeName, ");
			studentQuery.append("       b.college_id   AS collegeId ");
			studentQuery.append("FROM   studenttbl a, ");
			studentQuery.append("       collegetbl b ");
			studentQuery.append("WHERE  a.st_college = b.college_id ");
			
			ps = con.prepareStatement(studentQuery.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				studentList.add(new StudentEntity(rs.getInt("StudentId"),rs.getString("StudentName"),rs.getInt("collegeId"),rs.getString("CollegeName")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return studentList; 
	}
	
	@POST
	@Path("/studentEntity/add")
	@Produces({MediaType.APPLICATION_JSON})	
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public List<StudentEntity> andNewStudent(@FormParam("studentName") String studentName,@FormParam("collegeId") int collegeId){
		List<StudentEntity> studentList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer  studentQuery = null;
		try{
			//System.out.println(" Student-Name : "+studentName+"\n Student-Id : "+studentId+"\n CollegeId : "+collegeId);
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			ps = con.prepareStatement("insert into STUDENTTBL values ((select max(st_id)+1 from STUDENTTBL),?,?)");
			ps.setString(1, studentName);
			ps.setInt(2, collegeId);
						
			ps.executeUpdate();
			
			studentList = new ArrayList<StudentEntity>(); 
			studentQuery = new StringBuffer();
			studentQuery.append("SELECT a.st_id        AS StudentId, ");
			studentQuery.append("       a.st_name      AS StudentName, ");
			studentQuery.append("       b.college_name AS CollegeName, ");
			studentQuery.append("       b.college_id   AS collegeId ");
			studentQuery.append("FROM   studenttbl a, ");
			studentQuery.append("       collegetbl b ");
			studentQuery.append("WHERE  a.st_college = b.college_id ");
			
			ps = con.prepareStatement(studentQuery.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				studentList.add(new StudentEntity(rs.getInt("StudentId"),rs.getString("StudentName"),rs.getInt("collegeId"),rs.getString("CollegeName")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null && !con.isClosed()){
					con.close();
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return studentList; 
	}
}

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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vo.StudentEntity;
import com.vo.StudentVO;

@Path("/student")
public class StudentService {
	
	@GET
	@Path("/hi")
	@Produces({MediaType.TEXT_HTML})
	public String sayHi(){
		return "Hi from REST-Student is it OK";		
	}
	
	@GET
	@Path("/hi/{myName}")
	@Produces({MediaType.TEXT_HTML})
	public String sayHi(@PathParam("myName") String name){
		return "Hi from REST-Student "+name;		
	}
	
	@GET
	@Path("/demo/list")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentEntity> printReq(){
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
	@Path("/demo/save")
	@Consumes({MediaType.APPLICATION_JSON})
	public void conStudent(StudentEntity entity){
		System.out.println("Student Name : "+entity.getStudentName());
		System.out.println("Student Id : "+entity.getStudentId());
		System.out.println("College Name : "+entity.getCollegeName());
		System.out.println("College Id : "+entity.getCollegeId());
	}
	
	@GET
	@Path("/demo/list/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentEntity> getStudentsOne(@PathParam("id") int id){
		System.out.println("STUDENT-ID : "+id);
		List<StudentEntity> list = new ArrayList<StudentEntity>(){{
			add(new StudentEntity(123, "Mohammad Ali", 12, "Khasi College"));
			add(new StudentEntity(123, "Atul Ratnani", 12, "Juneja College"));
			add(new StudentEntity(123, "Hemant Sakhardande", 12, "Kukarni College"));
		}};
		return list;
	}
	
}

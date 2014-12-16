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

@Path("/studentTwo")
public class MyStudentRestService {

	
	
	@GET	
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
	
	@GET	
	@Path("/{collegeId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<StudentEntity> listStudentByCollege(@PathParam("collegeId") int collegeId){
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
			studentQuery.append("AND 	b.college_id = ? ");
			
			ps = con.prepareStatement(studentQuery.toString());
			ps.setInt(1, collegeId);
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
	@Path("/saveOne")
	@Consumes({MediaType.APPLICATION_JSON})
	public void conStudent(StudentEntity entity){
		System.out.println("Student Name : "+entity.getStudentName());
		System.out.println("Student Id : "+entity.getStudentId());
		System.out.println("College Name : "+entity.getCollegeName());
		System.out.println("College Id : "+entity.getCollegeId());
	}
	
	@POST
	@Path("/saveOne/{city}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void conStudentTwo(@PathParam("city") String city , StudentEntity entity){
		System.out.println("Student Name --> "+entity.getStudentName());
		System.out.println("Student Id --> "+entity.getStudentId());
		System.out.println("College Name --> "+entity.getCollegeName());
		System.out.println("College Id --> "+entity.getCollegeId());
		System.out.println("City Name --> "+city);
	}
	
	@GET
	@Path("/colleges")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> getColleges() {
		List<String> collegeList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			collegeList = new ArrayList<String>();
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			ps = con.prepareStatement("select distinct city from COLLEGETBL");
			rs = ps.executeQuery();
			while (rs.next()) {
				collegeList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}


		return collegeList;
	}

	@GET
	@Path("/colleges/{cityName}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CollegeEntity> getColleges(
			@PathParam("cityName") String cityName) {
		List<CollegeEntity> collegeList = null;
		Context envCtx = null;
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			collegeList = new ArrayList<CollegeEntity>();
			envCtx = new InitialContext();
			ctx = (Context) envCtx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("java/mydb");
			con = ds.getConnection();
			ps = con.prepareStatement("select college_Id, college_name from COLLEGETBL where city = ?");
			ps.setString(1, cityName);
			rs = ps.executeQuery();
			while (rs.next()) {
				collegeList.add(new CollegeEntity(rs.getString("college_name"),rs.getInt("college_Id"),collegeName));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}

		return collegeList;
	}

	
}

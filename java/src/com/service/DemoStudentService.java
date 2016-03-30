package com.service;


import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import com.vo.PersonVO;

@Path("/studentdemo")
public class DemoStudentService {
	
	@GET
	@Path("getList")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPersonList() {
		List<PersonVO> personList = null;
		ObjectMapper mapper = null;
		String json = null;
		Response response = null;
		try{
			Thread.sleep(4000);
			personList = Arrays.asList(
					new PersonVO("Faiyaz Abdulla",123),
					new PersonVO("Rahim Sulaiman",223),
					new PersonVO("Hafiz Saed",235),
					new PersonVO("Abbas Khaiyam",237),
					new PersonVO("Salman Rushdie",121),
					new PersonVO("Sajid Khan",133));
			mapper = new ObjectMapper();
			json = mapper.writeValueAsString(personList);
			response = Response.ok(json).build();
			//throw new Exception("Some Exception baba");			
		}catch(Exception e){
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Person services is down").build();
		}
		
		return response;
	}
}

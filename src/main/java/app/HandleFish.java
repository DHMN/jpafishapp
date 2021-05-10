package app;

import java.io.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.Fish;

@WebServlet(urlPatterns = {"/addfish", "/deletefish","/updatefish","/readfish","/readtoupdatefish"})
public class HandleFish extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Fish> list=null;
	  switch (action) {
	  case "/addfish":
		  list=addfish(request);break;
	  case "/deletefish":
		  String id=request.getParameter("id");
		  list=deletefish(request);break;
	  case "/updatefish":
		  list=updatefish(request);break;
	  case "/readfish":
		  list=readfish(request);break;
	  case "/readtoupdatefish":
		  Fish f=readtoupdatefish(request);
		  request.setAttribute("fish", f);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/fishtoupdateform.jsp");
		  rd.forward(request, response);
		  return;
	  }
	  request.setAttribute("fishlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/fishform.jsp");
	  rd.forward(request, response);
  }

	private Fish readtoupdatefish(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/fishservice/readtoupdatefish/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Fish fish=b.get(Fish.class);
		return fish;
	}

	private List<Fish> addfish(HttpServletRequest request) {
		//A Fish object to send to our web-service 
		Fish f=new Fish(request.getParameter("breed"), request.getParameter("weight"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/fishservice/addfish";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Fish object as JSON string format
		Entity<Fish> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Fish>> genericList = new GenericType<List<Fish>>() {};
		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Fish> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Fish> readfish(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/fishservice/readfish";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Fish>> genericList = new GenericType<List<Fish>>() {};
		
		List<Fish> returnedList=b.get(genericList);
		return returnedList;
	}
	
	private List<Fish> updatefish(HttpServletRequest request) {
		//A Fish object to send to our web-service 
		Fish f=new Fish(request.getParameter("id"), request.getParameter("breed"), request.getParameter("weight"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/fishservice/updatefish";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Fish object as JSON string format
		Entity<Fish> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Fish>> genericList = new GenericType<List<Fish>>() {};
		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Fish> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Fish> deletefish(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/fishservice/deletefish/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Fish>> genericList = new GenericType<List<Fish>>() {};
		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Fish> returnedList=b.delete(genericList);
		return returnedList;
	}
}
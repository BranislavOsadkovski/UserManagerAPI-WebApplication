package com.servletstudy.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.servletstudy.dbUtil.UserDAO;
import com.servletstudy.dbUtil.UserDAOManager;
import com.servletstudy.objects.User;
	
@Path(value = "/userservice")
public class UserService {
	
	private UserDAOManager daoManager = new UserDAOManager();
	private UserDAO userDAO = daoManager.getUserDAOInstance(); 
	private List<User> users= userDAO.getUsers();
	private User user = null;
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getAllUsers() {
	
		users = userDAO.getUsers();
		return users;
	}
	
	@POST
	@Path("/createuser")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createUser(
			@FormParam("name") String name,
			@FormParam("lastname") String lastname,
			@FormParam("email") String email,
			@FormParam("profession") String profession,
			@Context HttpServletResponse response) {
		
			userDAO.createUser(name,lastname,email,profession);
		
	}
	
	@PUT
	@Path("/edituser")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void editUser(
			@FormParam("nameOfUser") String nameOfUser,
			@FormParam("lastNameOfUser") String lastNameOfUser,
			@FormParam("name") String name,
			@FormParam("lastname") String lastname,
			@FormParam("email") String email,
			@FormParam("profession") String profession,
			@Context HttpServletResponse response) {
		
			userDAO.editUser(nameOfUser,lastNameOfUser,name,lastname,email,profession);
		
	}
}

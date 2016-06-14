package com.google.loanapproval.webapp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.loanapproval.webapp.model.Account;
import com.google.loanapproval.webapp.services.AccManagerService;

@Path("/accountmanager")
public class AccountManager {
	
	
	@GET
	@Path("/account/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountByName(@PathParam("name") String name)
	{
		AccManagerService accManager = new AccManagerService();
		Account acc = new Account();
		acc.setName(name);
		
		Account accountResp = accManager.read(acc);
		
		if(accountResp == null)
			return Response.ok("Account not found").status(400).build();
		
		return Response.ok(accountResp).status(200).build();	
	}
	
	@GET
	@Path("/accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts()
	{
		AccManagerService accManager = new AccManagerService();
		List<Account> lstAccount = accManager.getAllAccounts();
		
		return Response.ok(lstAccount).status(201).build();
		
	}
	
	@DELETE
	@Path("/account/{name}")
	public Response deleteAccount(@PathParam("name") String name)
	{
		AccManagerService accManager = new AccManagerService();
		Account accountDeleted = new Account();
		accountDeleted.setName(name);
		
		accManager.delete(accountDeleted);
		
		return Response.ok("Account deleted").status(202).build();
	}
	
	@POST
	@Path("/account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUser(Account newAccount)
	{
		AccManagerService accManager = new AccManagerService();
		accManager.create(newAccount);
		
		return Response.ok(newAccount).status(201).build();
	}
	
	
}


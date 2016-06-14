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

@Path("/checkaccount")
public class CheckAccount {
	
	@GET
	@Path("/account/{name}")
	public Response getRisk(@PathParam("name") String name)
	{
		AccManagerService accService = new AccManagerService();
		Account accSearch = new Account();
		accSearch.setName(name);
		
		Account accResult = accService.read(accSearch);
		if(accResult==null)
			return Response.ok("No Elements").status(205).build();
		
		return Response.ok(accResult).status(202).build();
		
	}

}

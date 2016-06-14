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

import com.google.loanapproval.webapp.model.Approval;
import com.google.loanapproval.webapp.services.AppManagerService;

@Path("/appmanager")
public class ApprovalManager {
	
	@GET
	@Path("/approval/{name}")
	public Response getApprovalByName(@PathParam("name") String name)
	{
		AppManagerService appService = new AppManagerService();
		Approval appFind = new Approval();
		appFind.setName(name);
		Approval appReq = appService.read(appFind);
		
		return Response.ok(appReq).status(201).build();
		
	}
	
	@GET
	@Path("/approvals")
	public Response getApprovals()
	{
		AppManagerService appService = new AppManagerService();
		List<Approval> lstApproval = appService.getAllApproval();
		
		if(lstApproval==null)
			return Response.ok("No elements").build();
		
		return Response.ok(lstApproval).status(201).build();
	}
	
	@POST
	@Path("/approval")
	public Response postApproval(Approval approvalReq)
	{
		AppManagerService appService = new AppManagerService();
		appService.create(approvalReq);
		
		return Response.ok("Approval Created").status(201).build();
	}
	
	@DELETE
	@Path("/approval/{name}")
	public Response deleteApproval(@PathParam("name") String name)
	{
		AppManagerService appService = new AppManagerService();
		Approval app = new Approval();
		app.setName(name);
		
		appService.delete(app);
		return Response.ok("Approval Deleted").status(201).build();
	}
}

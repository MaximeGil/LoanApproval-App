package com.heroku.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;

import com.heroku.pojo.Account;
import com.heroku.pojo.Approval;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("loanapproval")
public class LoanApproval {
    
    @POST
    @Path("approval/{name}/{amount}")
    public Response postApproval(@PathParam("name") String name, @PathParam("amount") int amount)
    {
    	String responseS =""; 
    	Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
    	WebTarget webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("checkaccount/account/" + name);
    	 
    	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	 
    	Account accountResponse = response.readEntity(Account.class);

    	if(accountResponse==null)
    		return Response.ok("Not Found").status(404).build();
    	
    	if(amount < 10000)
    	{
    		if(accountResponse.getRisk().equals("high"))
    		{
    			Approval app = new Approval(name, "refused", amount);
    			webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("appmanager/approval/");
    			invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    			response = invocationBuilder.post(Entity.entity(app, MediaType.APPLICATION_JSON));
    			responseS = "refused";
    			
    		}
    		else
    		{
    			accountResponse.setAccount(accountResponse.getAccount()+amount);
    			webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("accountmanager/account/");
    			invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    			response = invocationBuilder.post(Entity.entity(accountResponse, MediaType.APPLICATION_JSON));    			
    			responseS = "accepted";
    		}
    	}
    	
    	if(amount >= 10000)
    	{
    		if(accountResponse.getRisk().equals("high"))
    		{
    			Approval app = new Approval(name, "refused", amount);
    			webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("appmanager/approval/");
    			invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    			response = invocationBuilder.post(Entity.entity(app, MediaType.APPLICATION_JSON));
    			responseS = "refused";
    		}
    		else
    		{
    			Approval app = new Approval(name, "accepted", amount);
    			webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("appmanager/approval/");
    			invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    			response = invocationBuilder.post(Entity.entity(app, MediaType.APPLICATION_JSON));
    		
    			accountResponse.setAccount(accountResponse.getAccount()+amount);
    			webTarget = client.target("http://1-dot-loanapprovalkhettabgil.appspot.com/api/").path("accountmanager/account/");
    			invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    			response = invocationBuilder.post(Entity.entity(accountResponse, MediaType.APPLICATION_JSON));    			
    			responseS = "accepted";
    		}
    	}
    	
    	
		return Response.ok(responseS).build();
    }
}

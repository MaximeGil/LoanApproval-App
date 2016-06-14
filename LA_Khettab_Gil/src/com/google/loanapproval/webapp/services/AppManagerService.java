package com.google.loanapproval.webapp.services;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.loanapproval.webapp.model.Account;
import com.google.loanapproval.webapp.model.Approval;

public class AppManagerService implements CRUDService<Approval> {

	public List<Approval> getAllApproval()
	{
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Query reqAll = new Query("Approval");
		List<Entity> results = datastoreGoogle.prepare(reqAll).asList(FetchOptions.Builder.withDefaults());
		List<Approval> lstApprovals = new ArrayList<Approval>(); 
		Approval appReturn = new Approval(); 
		
		for (Entity resultQ : results) {
			if(resultQ != null)
			{
				appReturn.setName((String)resultQ.getProperty("name"));
				appReturn.setResponse((String)resultQ.getProperty("response"));
				Integer newInt = new Integer(resultQ.getProperty("amount").toString());
				appReturn.setAmount(newInt);
				lstApprovals.add(appReturn);
				appReturn = new Approval(); 
			}
		}
		
		return lstApprovals;
	}
	
	@Override
	public void create(Approval object) {
		
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
        
		Entity newApproval = new Entity("Approval", object.getName());

		newApproval.setProperty("name", object.getName());
		newApproval.setProperty("response", object.getResponse());
		newApproval.setProperty("amount", object.getAmount());
		
		datastoreGoogle.put(newApproval);
		
	}

	@Override
	public Approval read(Approval object) {
		
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Entity appFind = null; 
		Approval appReturn = null;
		
		Key keyApproval = KeyFactory.createKey("Approval", object.getName());
		
		try {
			appFind = datastoreGoogle.get(keyApproval);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(appFind != null)
		{
			appReturn = new Approval();
			appReturn.setName((String)appFind.getProperty("name"));
			appReturn.setResponse((String)appFind.getProperty("response"));
			Integer newInt = new Integer(appFind.getProperty("amount").toString());
			appReturn.setAmount(newInt);
		}
		
		return appReturn; 
		
	}

	@Override
	public void update(Approval object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Approval object) {
		
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Key keyApproval = KeyFactory.createKey("Approval", object.getName());

		datastoreGoogle.delete(keyApproval);
		
	}

}

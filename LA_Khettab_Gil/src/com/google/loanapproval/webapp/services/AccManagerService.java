package com.google.loanapproval.webapp.services;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.loanapproval.webapp.model.Account;

public class AccManagerService implements CRUDService<Account> {

	public List<Account> getAllAccounts()
	{
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Query reqAll = new Query("Account");
		List<Entity> results = datastoreGoogle.prepare(reqAll).asList(FetchOptions.Builder.withDefaults());
		List<Account> lstAccounts = new ArrayList<Account>(); 
		Account accReturn = new Account(); 
		
		for (Entity resultQ : results) {
			if(resultQ != null)
			{
				accReturn.setName((String)resultQ.getProperty("name"));
				accReturn.setSurname((String)resultQ.getProperty("surname"));
				accReturn.setRisk((String)resultQ.getProperty("risk"));
				Integer newInt = new Integer(resultQ.getProperty("account").toString());
				accReturn.setAccount(newInt);
				lstAccounts.add(accReturn);
				accReturn = new Account(); 
			}
		}
		
		return lstAccounts; 
	}
	
	@Override
	public void create(Account object) {
		
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
        
		Entity newAccount = new Entity("Account", object.getName());

		newAccount.setProperty("name", object.getName());
		newAccount.setProperty("surname", object.getSurname());
		newAccount.setProperty("account", object.getAccount());
		newAccount.setProperty("risk", object.getRisk().toString());
		
		datastoreGoogle.put(newAccount);
		
	}

	@Override
	public Account read(Account object) {
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Entity accountFind = null; 
		Account accountReturn = null;
		
		Key keyAccount = KeyFactory.createKey("Account", object.getName());
		
		try {
			accountFind = datastoreGoogle.get(keyAccount);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(accountFind != null)
		{
			accountReturn = new Account();
			accountReturn.setName((String)accountFind.getProperty("name"));
			accountReturn.setSurname((String)accountFind.getProperty("surname"));
			accountReturn.setRisk((String)accountFind.getProperty("risk"));
			Integer newInt = new Integer(accountFind.getProperty("account").toString());
			accountReturn.setAccount(newInt);
		}
		
		return accountReturn; 
		
	}

	@Override
	public void update(Account object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Account object) {
		
		DatastoreService datastoreGoogle = DatastoreServiceFactory.getDatastoreService();
		Key keyAccount = KeyFactory.createKey("Account", object.getName());

		datastoreGoogle.delete(keyAccount);
	}

}

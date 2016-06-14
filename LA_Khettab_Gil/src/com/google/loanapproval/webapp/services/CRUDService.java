package com.google.loanapproval.webapp.services;

public interface CRUDService<T> {
	
	public void create(T object);
	public T read(T object); 
	public void update(T object);
	public void delete(T object);

}

package com.google.loanapproval.webapp.model;

public class Approval {
	
	private String name;
	private String response;
	private int amount;
	
	public Approval(String name, String response, int amount)
	{
		this.setName(name);
		this.setResponse(response);
		this.setAmount(amount);
	}
	
	public Approval()
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}

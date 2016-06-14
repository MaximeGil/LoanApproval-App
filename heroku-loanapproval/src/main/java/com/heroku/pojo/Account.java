package com.heroku.pojo;

public class Account {

	private String name;
	private String surname;
	private int account;
	private String risk;
	
	public Account(String name, String surname, int account, String risk)
	{
		this.setName(name);
		this.setSurname(surname);
		this.setAccount(account);
		this.setRisk(risk);
	}
	
	public Account()
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}
	

	
}

package com.pojo.classes;

public class EmailAndPhone {
	private String id;
	private String email;
	private String number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public EmailAndPhone(String id, String email, String number) {
		super();
		this.id = id;
		this.email = email;
		this.number = number;
	}

}

package com.in28minutes.rest.web.services.restful_web_services.versioning;

public class PersonV2 {

	private String firstname;
	private String middlename;
	private String lastname;

	public PersonV2(String firstname, String middlename, String lastname) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "NameVersion2 [firstname=" + firstname + ", middlename=" + middlename + ", lastname=" + lastname + "]";
	}

}

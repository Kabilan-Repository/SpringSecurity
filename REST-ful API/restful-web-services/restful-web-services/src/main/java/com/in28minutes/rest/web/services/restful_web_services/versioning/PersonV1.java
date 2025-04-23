package com.in28minutes.rest.web.services.restful_web_services.versioning;

public class PersonV1 {

	private String fullName;

	public PersonV1(String fullName) {
		super();
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return "NameVersion1 [fullName=" + fullName + "]";
	}

}

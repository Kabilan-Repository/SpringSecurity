package com.in28minutes.rest.web.services.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	// URL versioning of REST API

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPersn() {
		return new PersonV1("AndrewTate");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPersn() {
		return new PersonV2("Tristin", "", "Tate");
	}

	// Request Parameter Versioning

	@GetMapping(path = "/person", params = "version=v1")
	public PersonV1 getFirstVersionOfPersnRequestParam() {
		return new PersonV1("AndrewTate");
	}

	@GetMapping(path = "/person", params = "version=v2")
	public PersonV2 getSecondVersionOfPersnRequestParam() {
		return new PersonV2("Tristin", "", "Tate");
	}
	
	// Request Parameter Versioning

	@GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersnHeader() {
		return new PersonV1("AndrewTate");
	}

	@GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersnHeader() {
		return new PersonV2("Tristin", "", "Tate");
	}
	
	//Media Type versioning 
	
	@GetMapping(path = "/person/accept",produces = "application/v1+json")
	public PersonV1 getFirstVersionOfPersnMedia() {
		return new PersonV1("AndrewTate");
	}

	@GetMapping(path = "/person/accept",produces = "application/v2+json")
	public PersonV2 getSecondVersionOfPersnMedia() {
		return new PersonV2("Tristin", "", "Tate");
	}
	
}

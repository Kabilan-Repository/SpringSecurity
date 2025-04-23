package com.in28minutes.rest.web.services.restful_web_services.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "User_details")
public class User {

	@Id
	@GeneratedValue()
	private int id;

	@Size(min = 2, message = "Name should have atlease 2 characters")
	private String description;

	@Past(message = "Birthdate should be in the past")
	private LocalDate birthDate;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Posts> post;
	
	public User() {

	}

	public User(int id, String description, LocalDate birthDate) {
		super();
		this.id = id;
		this.description = description;
		this.birthDate = birthDate;
	}
	
	public User( String description, LocalDate birthDate) {
		super();
		this.description = description;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Posts> getPost() {
		return post;
	}

	public void setPost(List<Posts> post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", description=" + description + ", birthDate=" + birthDate + "]";
	}

}

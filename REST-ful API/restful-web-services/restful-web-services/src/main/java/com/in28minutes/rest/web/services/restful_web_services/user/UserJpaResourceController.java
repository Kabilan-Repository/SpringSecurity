package com.in28minutes.rest.web.services.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.web.services.restful_web_services.exception.NoUserFoundException;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
public class UserJpaResourceController {

	MessageSource messageSource;

	@Autowired
	RESTRepository repository;
	@Autowired
	PostRESTRepository postrepository;

	public UserJpaResourceController() {

	}

	public UserJpaResourceController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@PostConstruct
	public void loader() {
		repository.save(new User("Kabilan", LocalDate.now().minusYears(26)));
		repository.save(new User("Sriram", LocalDate.now().minusYears(26)));
		repository.save(new User("Saarathi", LocalDate.now().minusYears(26)));
	}

	@GetMapping("jpa/users")
	public List<User> retrieveAllUsers() {

		return repository.findAll();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = repository.findById(id).orElseThrow(() -> new NoUserFoundException("id:" + id + " Not Found"));

		EntityModel<User> entity = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entity.add(link.withRel("all-Users"));

		return entity;
	}

	@DeleteMapping("/jpa/users/{id}/delete")
	public EntityModel<DeleteResponse> deteleUser(@PathVariable int id) {

		if (!repository.existsById(id)) {
			throw new NoUserFoundException("id:" + id + " Not Found");
		}

		repository.deleteById(id);

		DeleteResponse response = new DeleteResponse("User with id : " + id + " - Deleted");

		EntityModel<DeleteResponse> entity = EntityModel.of(response);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entity.add(link.withRel("all-Users"));

		return entity;
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Posts> retrieveAllPostofUser(@PathVariable int id) {

		User user = repository.findById(id).orElseThrow(() -> new NoUserFoundException("id:" + id + " Not Found"));

		return user.getPost();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Posts post) {

		User user = repository.findById(id).orElseThrow(() -> new NoUserFoundException("id:" + id + " Not Found"));

		post.setUser(user);

		Posts savedPost = postrepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/jpa/users/{id}/posts/{id2}")
	public EntityModel<Posts> getPost(@PathVariable int id, @PathVariable int id2) {
		User user = repository.findById(id).orElseThrow(() -> new NoUserFoundException("id:" + id + " Not Found"));

		Predicate<? super Posts> predicate = (post) -> post.getId() == id2;

		Posts post = user.getPost().stream().filter(predicate).findFirst()
				.orElseThrow(() -> new NoUserFoundException("Post id: " + id2 + " not found"));

		EntityModel<Posts> entity = EntityModel.of(post);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPostofUser(id));
		entity.add(link.withRel("all-UserPosts"));

		return entity;
	}

	@DeleteMapping("/jpa/users/{user}/posts/{id}/delete")
	public EntityModel<DeleteResponse> deteleUserPost(@PathVariable int user,@PathVariable int id) {

		if (!repository.existsById(id)) {
			throw new NoUserFoundException("id:" + id + " Not Found");
		}

		postrepository.deleteById(id);

		DeleteResponse response = new DeleteResponse("Post with id : " + id + " - Deleted");

		EntityModel<DeleteResponse> entity = EntityModel.of(response);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPostofUser(user));
		entity.add(link.withRel("all-UserPosts"));

		return entity;
	}

}

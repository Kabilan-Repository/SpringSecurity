package com.in28minutes.rest.web.services.restful_web_services.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RESTRepository extends JpaRepository<User,Integer> {

}

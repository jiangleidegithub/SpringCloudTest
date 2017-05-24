package org.ms.consumer.movie.controller;

import org.ms.consumer.movie.entity.User;
import org.ms.consumer.movie.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
	private static final Logger log=LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserFeignClient feignClient;
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
	}
	@GetMapping("/user/{id}")
	public User findByIdFeign(@PathVariable Long id) {
		log.debug(this.feignClient.findById(id).toString());
		return this.feignClient.findById(id) ;
	}
}

package org.ms.consumer.movie.controller;

import java.util.HashMap;

import org.ms.consumer.movie.entity.User;
import org.ms.consumer.movie.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

@RestController
public class MovieController {
	private static final Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserFeignClient feignClient;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		// 使用ribbon之后，feign接口上的name设置以后，就可以通过服务名字来访问接口，ribbon和erueka配合使用时，
		// 会自动将虚拟主机名字映射成微服务的网络地址，
		return this.restTemplate.getForObject("http://ms-provider-user/" + id, User.class);
	}

	@GetMapping("/user/{id}")
	public User findByIdFeign(@PathVariable Long id) {
		log.debug(this.feignClient.findById(id).toString());
		return this.feignClient.findById(id);
	}

	@GetMapping("/user/map/{id}")
	public User findByIdFeignMap(@PathVariable Long id) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("id", id);
		User user = this.feignClient.getUser(map);
		log.debug(user.toString());
		return user;
	}

	@GetMapping("/lognstance")
	public void logInstance() {
		// 使用ribbon和Eureka之后，就可以用loadbalancerClient的api来更加直观的获取当前的用户服务节点
		ServiceInstance instance = this.loadBalancerClient.choose("ms-provider-user");
		this.log.info("{}:{}:{}", instance.getServiceId(), instance.getHost(), instance.getPort());
	}

	/**
	 * 通过FeignClient调用User服务 
	 * @param user
	 */
	@PostMapping("/post")
	public void postUser(@RequestBody User user) {
		this.feignClient.postUser(user);
	}

}

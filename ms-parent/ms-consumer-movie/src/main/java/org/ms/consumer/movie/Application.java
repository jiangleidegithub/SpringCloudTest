package org.ms.consumer.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * 电影微服务
 * @author jianglei
 *
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Application {
	//	客户端的负载均衡
	/**
	 * ribbon和eureka配合使用时，ribbon可以自动从eureka获取服务提供者列表，并基于负载均衡算法，
	 * 请求其中的一个服务提供者实例，请求时，请求用户微服务的虚拟主机名字
	 * 
	 */
	@LoadBalanced //这一条注解就能让RestTemplate整合Ribbon，使其具有负载均衡的能力，
	@Bean	// 必须采用这种方式进行Bean的注入，不然注入不到
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

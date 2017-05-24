package org.ms.consumer.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
	
//	@LoadBalanced //这一条注解就能让RestTemplate整合Ribbon，使其具有负载均衡的能力
	@Bean	// 必须采用这种方式进行Bean的注入，不然注入不到
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

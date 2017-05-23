package org.ms.provider.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * 提供用户查询的微服务
 * @author jianglei
 *
 */
@EnableDiscoveryClient//声明是Eureka的Client，也可以用@EnableEurekaClient
@SpringBootApplication
public class ProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderUserApplication.class, args);
	}

}

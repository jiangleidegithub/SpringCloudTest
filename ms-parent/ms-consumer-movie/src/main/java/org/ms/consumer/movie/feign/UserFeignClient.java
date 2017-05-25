package org.ms.consumer.movie.feign;

import java.util.Map;

import org.ms.consumer.movie.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 声明式Feign接口,简化调用服务的过程
 * @author Administrator
 *
 */
/**
 * 此name为一个任意的客户端名称， 用来创建Ribbon负载均衡器， 因为使用了Eureka
 * ,所以Ribbon会把ms-provider-user解析成注册在Eureka服务注册表中的服务
 * 还可以使用url属性，指定请求的url（url可以是完整的url或者主机名）
 *
 */

@FeignClient(name = "ms-provider-user")
public interface UserFeignClient {
	// 单个参数写法
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);

	// 多个参数写法，有几个写几个
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User getUser(@RequestParam("id") Long id, @RequestParam("username") String username);

	// map写法
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public User getUser(@RequestParam Map<String, Object> map);

	// 调用post请求
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public User postUser(@RequestBody User user);
}

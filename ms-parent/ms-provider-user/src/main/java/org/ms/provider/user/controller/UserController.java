package org.ms.provider.user.controller;

import org.ms.provider.user.entity.User;
import org.ms.provider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	/**
	 * 1.（包裹请求）使HystrixCommand 包裹对依赖的调用逻辑，每个命令在独立的线程中执行，
	 * 		使用了设计模式中的命令模式；
	 * 2.（跳闸机制）当某服务的错误率超过一定阈值时，Hystrix可以被自动或者手动跳闸，
	 * 		停止请求该服务一段时间；
	 * 3.（资源隔离）Hystrix为每个依赖都维护了一个小型的线程池（或信号量），如果该线程池已满，
	 * 		发往该依赖的的请求就立刻被拒绝，而不是排队等待，从而加速失败判定；
	 * 4.（监控）Hystrix可以近乎实时的监控运行指标和配置的变化，例如成功，失败，超时，以及被拒绝
	 * 		的请求等；
	 * 5.（回退机制）当请求失败、超时、被拒绝，或当断路器打开时，执行回退逻辑，回退逻辑可由开发
	 * 		人员自行提供，例如返回一个缺省值；
	 * 6.（自我修复）当断路器打开一段时间以后，会自动进入“半开" 状态。进入转换状态的逻辑；
	 * @param id
	 * @return
	 */
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		User findOne = this.userRepository.findOne(id);
		return findOne;
	}
	
	@PostMapping("/post")
	public User postUser(@RequestBody User user) {
		System.out.println(user);
		return user;
	}
}

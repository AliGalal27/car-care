package com.example.demo;

import com.example.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // http://localhost:8090/users
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register") // http://localhost:8090/users/register
	public User register(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/login") // http://localhost:8090/users/login
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request.getEmail(), request.getPassword());
	}
}

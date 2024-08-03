package org.example.carcare.user;


import org.example.carcare.Login.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup") // http://localhost:8080/users/signup
	public User register(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/login") // http://localhost:8080/users/login
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request.getEmail(), request.getPassword());
	}
}

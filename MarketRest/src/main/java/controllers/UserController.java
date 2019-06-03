package controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.User;


@RestController
@RequestMapping("/user")
public class UserController{
	@PostMapping("/login")
	public @ResponseBody boolean login(@RequestParam("username") String username) {
		return true;
	}
	@PostMapping("/signup")
	public @ResponseBody User register(@RequestBody User user) {
		return null;
	}
}

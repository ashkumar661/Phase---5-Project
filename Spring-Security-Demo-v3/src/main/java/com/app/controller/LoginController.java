package com.app.controller;

import java.util.HashMap;

import com.app.model.User;
import com.app.repository.UserRepository;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:8080/", "http://localhost:4200/" })
@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("login")
	public ResponseEntity<?> loginCheck(@RequestBody User user) throws JSONException {
		System.out.println("User email: " + user.getEmail() + " User password: " + user.getPassword());
		User userCheck = userRepository.findUserByEmailId(user.getEmail());
		if (userCheck == null) {
			return ResponseEntity.ok("User does not exist");
		} else if (userCheck.getEmail().equals(user.getEmail()) && userCheck.getPassword().equals(user.getPassword())) {
			JSONObject obj = new JSONObject();
			HashMap<String, String> response = new HashMap<String, String>();
			response.put("user_id", "" + userCheck.getUserId());
			response.put("email", userCheck.getEmail());
			response.put("name", userCheck.getName());
			response.put("address", userCheck.getAddress());
			obj.put("user_profile_details", response);
			return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		} else
			return ResponseEntity.ok("Email or password is incorrect");
	}

	@RequestMapping("register-user")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		return ResponseEntity.ok(userRepository.save(user));
	}

}

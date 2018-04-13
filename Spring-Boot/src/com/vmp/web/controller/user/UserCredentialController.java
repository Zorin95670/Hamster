package com.vmp.web.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmp.model.user.UserCredential;

@RestController
public class UserCredentialController {

	@PostMapping("/API/User/Add")
	public UserCredential add(@RequestBody UserCredential user) {
		
		return user;
	}
	
	@PostMapping("/API/User/Connect")
	@ResponseBody
	public UserCredential connect(@RequestBody UserCredential user) {
		user.setId(10L);
		return user;
	}

}

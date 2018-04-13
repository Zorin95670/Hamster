package com.vmp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vmp.web.UserCredentialWebClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		UserCredentialWebClient gwc = new UserCredentialWebClient();

		System.out.println(gwc.getResult());
	}
}
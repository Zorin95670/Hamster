package com.vmp.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserCredentialRouter {

	@Bean
	public RouterFunction<ServerResponse> route(UserCredentialHandler handler) {

		return RouterFunctions.route(RequestPredicates.GET("/API/").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				handler::hello);
	}
}

package com.example.webflux.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class AccountRouter {

    @Bean
    public RouterFunction route(final AccountHandler handler) {
        return RouterFunctions.route()
                .GET("/accounts", handler::findAll)
                .POST("/accounts", accept(MediaType.APPLICATION_JSON), handler::create)
                .build();
    }
}

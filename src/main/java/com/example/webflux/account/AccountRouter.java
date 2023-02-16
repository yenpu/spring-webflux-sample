package com.example.webflux.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration(proxyBeanMethods = false)
public class AccountRouter {

    @Bean
    public RouterFunction route(final AccountHandler handler) {
        return RouterFunctions
                .route(GET("/accounts"), handler::findAll);
    }
}

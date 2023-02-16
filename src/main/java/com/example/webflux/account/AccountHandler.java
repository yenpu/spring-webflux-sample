package com.example.webflux.account;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class AccountHandler {

    private final AccountRepository repository;

    public AccountHandler(final AccountRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.repository.findAll(), Account.class);
    }

    public Mono<ServerResponse> create(final ServerRequest request) {
        return request.bodyToMono(Account.class)
                .flatMap(repository::save)
                .map(acct -> repository.save(acct))
                .flatMap(account -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(account, Account.class));
    }
}

package com.example.webflux.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;


@Component
public class AccountHandler {

    private final AccountRepository repository;
    private final AccountPaginationRepository paginationRepository;

    public AccountHandler(final AccountRepository repository, final AccountPaginationRepository paginationRepository) {
        this.repository = repository;
        this.paginationRepository = paginationRepository;
    }

    public Mono<ServerResponse> findAll(final ServerRequest request) {
        final String sleepParam = request.queryParam("sleep").orElse("0");
        final Integer sleep = Integer.valueOf(sleepParam);

        final PageRequest pageRequest = PageRequest.of(0, 100);
        final Mono<Page<Account>> mono = this.paginationRepository.findAllBy(pageRequest)
                .collectList()
                .delaySubscription(Duration.ofSeconds(sleep))
                .zipWith(this.paginationRepository.count())
                .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Account.class);
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

package com.example.webflux.account;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountPaginationRepository extends ReactiveSortingRepository<Account, Long> {

    Flux<Account> findAllBy(Pageable pageable);

    Mono<Long> count();

}

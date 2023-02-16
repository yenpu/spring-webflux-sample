package com.example.webflux.account;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {

    @Query("SELECT * FROM account WHERE last_name = :lastname")
    Flux<Account> findByLastName(final String lastName);

}

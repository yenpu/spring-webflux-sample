package com.example.webflux.account;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private Long id;

    private final String firstName;

    private final String lastName;

    public Account(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Account[id=%d], firstName='%s', lastName='%s'", id, firstName, lastName);
    }
}

package com.github.buslocator;

public class Driver {
    private final long id;

    private final String firstName;

    private final String lastName;

    public Driver(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

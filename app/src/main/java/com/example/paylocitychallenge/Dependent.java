package com.example.paylocitychallenge;

/**
 * This class models a dependent.
 */
public class Dependent {
    public static final double ANNUAL_BENEFITS_COST = 500;
    private final String firstName;
    private final String lastName;

    public Dependent(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

package com.example.paylocitychallenge;

import java.util.List;

/**
 * This class models an employee.
 */
public class Employee {
    public static final double PAYCHECK_VALUE = 2000;
    public static final double NUMBER_OF_PAYCHECKS = 26;
    public static final double ANNUAL_BENEFITS_COST = 1000;

    private final String firstName;
    private final String lastName;
    private final List<Dependent> dependents;

    public Employee(String firstName, String lastName, List<Dependent> dependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dependents = dependents;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

}

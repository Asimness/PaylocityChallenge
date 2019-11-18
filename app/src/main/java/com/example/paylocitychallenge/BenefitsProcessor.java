package com.example.paylocitychallenge;

import java.util.HashMap;

/**
 * This class is responsible for the logic in calculating the annual benefits cost, per paycheck
 * benefits cost, annual employee salary after benefits, and employee paycheck value after benefits.
 */
public class BenefitsProcessor {
    private static final double DISCOUNT_COST = .9;
    private static final String SPECIAL_LETTER = "A";

    public static final int ANNUAL_BENEFITS_COST_KEY = 1;
    public static final int PAYCHECK_BENEFITS_COST_KEY = 2;
    public static final int ANNUAL_EMPLOYEE_SALARY_KEY = 3;
    public static final int EMPLOYEE_PAYCHECK_SALARY_KEY = 4;

    /**
     * This method processes the cost summary values and adds them the a map.
     * @param employee who's benefits are being calculated
     * @return map containing the benefit costs and employee salary
     */
    public static HashMap<Integer, Double> proccessBenefits(Employee employee) {
        HashMap<Integer, Double> map = new HashMap<>();
        double employeesBenefitCost = getEmployeesBenefitsCost(employee);
        double dependentsBenefitsCost = getDependentsBenefitsCost(employee);
        double annualBenefitsCost = employeesBenefitCost + dependentsBenefitsCost;
        double paycheckBenefitsCost = annualBenefitsCost / Employee.NUMBER_OF_PAYCHECKS;
        double annualEmployeeSalary =
                (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - annualBenefitsCost;
        double employeePaycheckSalary = annualEmployeeSalary / Employee.NUMBER_OF_PAYCHECKS;
        map.put(ANNUAL_BENEFITS_COST_KEY, annualBenefitsCost);
        map.put(PAYCHECK_BENEFITS_COST_KEY, paycheckBenefitsCost);
        map.put(ANNUAL_EMPLOYEE_SALARY_KEY, annualEmployeeSalary);
        map.put(EMPLOYEE_PAYCHECK_SALARY_KEY, employeePaycheckSalary);
        return map;
    }

    private static double getEmployeesBenefitsCost(Employee employee) {
        double baseCost = Employee.ANNUAL_BENEFITS_COST;
        // Check both first and last name, make entire string uppercase and check first letter for "A".
        if (employee.getFirstName().toUpperCase().startsWith(SPECIAL_LETTER) ||
                employee.getLastName().toUpperCase().startsWith(SPECIAL_LETTER)) {
            return DISCOUNT_COST * baseCost;
        }
        return baseCost;
    }

    private static double getDependentsBenefitsCost(Employee employee) {
        double total = 0;
        double baseCost = Dependent.ANNUAL_BENEFITS_COST;
        for (Dependent dependent : employee.getDependents()) {
            // Check both first and last name, make entire string uppercase and check first letter for "A".
            if (dependent.getFirstName().toUpperCase().startsWith(SPECIAL_LETTER) ||
                    dependent.getLastName().toUpperCase().startsWith(SPECIAL_LETTER)) {
                total += (baseCost * DISCOUNT_COST);
            } else {
                total += baseCost;
            }
        }
        return total;
    }
}

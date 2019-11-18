package com.example.paylocitychallenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BenefitsProcessorTest {

    private Employee employee1 = new Employee("A", "B", new ArrayList<Dependent>());
    private Employee employee2 = new Employee("B", "A", new ArrayList<Dependent>());
    private Employee employee3 = new Employee("B", "C", new ArrayList<Dependent>());
    private Employee employee4 = new Employee("A", "A", new ArrayList<Dependent>());
    private Employee employee5 = new Employee("a", "B", new ArrayList<Dependent>());
    private Employee employee6 = new Employee("B", "a", new ArrayList<Dependent>());

    @Test
    public void processBenefitsEmployeeNoDependentsNoDiscount() {
        HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee3);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 1000, .1);
        assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 1000 / Employee.NUMBER_OF_PAYCHECKS, .1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1000, .1);
        assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(),   ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1000) / 26, .1);
    }

    @Test
    public void processBenefitsEmployeeNoDependentsWithDiscount() {
        Employee[] employees = new Employee[] {employee1, employee2, employee4, employee5, employee6};
        for(Employee employee : employees) {
            HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee1);
            assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 900, .1);
            assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 900 / Employee.NUMBER_OF_PAYCHECKS, .1);
            assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 900, .1);
            assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(), ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 900) / 26, .1);
        }
    }

    @Test
    public void processBenefitsEmployeeWithDependentNoDiscount() {
        employee3.getDependents().add(new Dependent("C", "B"));
        HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee3);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 1500, .1);
        assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 1500 / Employee.NUMBER_OF_PAYCHECKS, .1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1500, .1);
        assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(),   ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1500) / 26, .1);
    }

    @Test
    public void processBenefitsSingleEmployeeWithDependentsNoDiscount() {
        employee3.getDependents().add(new Dependent("A", "B"));
        employee3.getDependents().add(new Dependent("C", "a"));
        HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee3);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 1900, .1);
        assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 1900 / Employee.NUMBER_OF_PAYCHECKS, .1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1900, .1);
        assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(),   ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1900) / 26, .1);
    }

    @Test
    public void processBenefitsSingleEmployeeWithDependentWithDiscount() {
        employee1.getDependents().add(new Dependent("C", "B"));
        HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 1400, .1);
        assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 1400 / Employee.NUMBER_OF_PAYCHECKS, .1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1400, .1);
        assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(),   ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1400) / 26, .1);
    }

    @Test
    public void processBenefitsSingleEmployeeWithDependentsWithDiscount() {
        employee1.getDependents().add(new Dependent("A", "B"));
        HashMap<Integer, Double> map = BenefitsProcessor.proccessBenefits(employee1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY).doubleValue(), 1350, .1);
        assertEquals(map.get(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY).doubleValue(), 1350 / Employee.NUMBER_OF_PAYCHECKS, .1);
        assertEquals(map.get(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY).doubleValue(), (Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1350, .1);
        assertEquals(map.get(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY).doubleValue(),   ((Employee.NUMBER_OF_PAYCHECKS * Employee.PAYCHECK_VALUE) - 1350) / 26, .1);
    }
}

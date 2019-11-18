package com.example.paylocitychallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeInformationActivity extends AppCompatActivity {
    private static final String ANNUAL_BENEFITS_COST = "Annual benefits cost: ";
    private static final String PAYCHECK_BENEFITS_COST = "Paycheck benefits cost: ";
    private static final String ANNUAL_EMPLOYEE_SALARY = "Annual employee salary: ";
    private static final String EMPLOYEE_PAYCHECK_VALUE = "Employee paycheck value: ";
    private static final String ADD = "ADD";
    private static final String CANCEL = "CANCEL";
    private RecyclerView dependentRecyclerView;
    private RecyclerView.Adapter dependentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextInputEditText employeeFirstNameEditText;
    private TextInputEditText employeeLastNameEditText;
    private ArrayList<Dependent> dependents;
    private TextView dependentsListTitle;
    private TextView annualBenefitsCostTextView;
    private TextView paycheckBenefitsCostTextView;
    private TextView employeeAnnualSalaryTextView;
    private TextView employeePaycheckSalaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_information);
        dependents = new ArrayList<>();
        employeeFirstNameEditText =
                findViewById(R.id.employee_first_name_edit_text);
        employeeFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            /**
             * Checks name after a user updates the first name text field to update the cost summary.
             */
            public void afterTextChanged(Editable editable) {
                updateCostSummary();
            }
        });
        employeeLastNameEditText = findViewById(R.id.employee_last_name_edit_text);
        employeeLastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            /**
             * Checks name after a user updates the last name text field to update the cost summary.
             */
            public void afterTextChanged(Editable editable) {
                updateCostSummary();
            }
        });

        dependentRecyclerView = findViewById(R.id.dependent_recycler_view);
        dependentRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        dependentRecyclerView.setLayoutManager(layoutManager);
        // Callback for removing dependents to update the cost summary
        dependentAdapter = new DependentAdapter(dependents) {
            @Override
            public void callBack() {
                updateCostSummary();
            }
        };
        dependentRecyclerView.setAdapter(dependentAdapter);
        dependentsListTitle = findViewById(R.id.dependent_list_title);
        annualBenefitsCostTextView = findViewById(R.id.annual_benefits_cost_text_view);
        paycheckBenefitsCostTextView = findViewById(R.id.paycheck_benefits_cost_text_view);
        employeeAnnualSalaryTextView = findViewById(R.id.annual_employee_pay_text_view);
        employeePaycheckSalaryTextView = findViewById(R.id.employee_paycheck_text_view);
        updateCostSummary();
    }

    @SuppressLint("DefaultLocale")
    /**
     * This method updates the cost summary with the values from BenefitsProcessor.
     */
    public void updateCostSummary() {
        dependents = ((DependentAdapter)dependentAdapter).getDependents();
        if (!dependents.isEmpty()) {
            dependentsListTitle.setText(R.string.dependent_list_title);
        } else {
            dependentsListTitle.setText(R.string.no_dependents_title);
        }
        Employee employee = new Employee(employeeFirstNameEditText.getText().toString(),
                employeeLastNameEditText.getText().toString(), dependents);
        if(employee.getFirstName().isEmpty() || employee.getLastName().isEmpty()){
            annualBenefitsCostTextView.setText("");
            paycheckBenefitsCostTextView.setText("");
            employeeAnnualSalaryTextView.setText("");
            employeePaycheckSalaryTextView.setText("");
            return;
        }
        HashMap<Integer, Double> valueMap = BenefitsProcessor.proccessBenefits(employee);
        Double annualBenefitsCost = valueMap.getOrDefault(BenefitsProcessor.ANNUAL_BENEFITS_COST_KEY, -1.0);
        Double paycheckBenefitsCost = valueMap.getOrDefault(BenefitsProcessor.PAYCHECK_BENEFITS_COST_KEY, -1.0);
        Double employeeAnnualSalary = valueMap.getOrDefault(BenefitsProcessor.ANNUAL_EMPLOYEE_SALARY_KEY, -1.0);
        Double employeePaycheckSalary = valueMap.getOrDefault(BenefitsProcessor.EMPLOYEE_PAYCHECK_SALARY_KEY, -1.0);
        annualBenefitsCostTextView.setText(String.format ("%s$%.2f", ANNUAL_BENEFITS_COST, annualBenefitsCost));
        paycheckBenefitsCostTextView.setText(String.format("%s$%.2f", PAYCHECK_BENEFITS_COST, paycheckBenefitsCost));
        employeeAnnualSalaryTextView.setText(String.format("%s$%.2f", ANNUAL_EMPLOYEE_SALARY, employeeAnnualSalary));
        employeePaycheckSalaryTextView.setText(String.format("%s$%.2f", EMPLOYEE_PAYCHECK_VALUE, employeePaycheckSalary));
    }

    /**
     * This method launches the dialog to add a dependent.
     * @param view the Add Dependent button
     */
    public void onClickAddDependent(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeInformationActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_dependent_dialog, null, false);
        builder.setView(dialogView);
        final TextInputEditText dependentFirstName = dialogView.findViewById(R.id.dependent_first_name_edit_text);
        final TextInputEditText dependentLastName = dialogView.findViewById(R.id.dependent_last_name_edit_text);
        builder.setPositiveButton(ADD, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dependent dependent = new Dependent(dependentFirstName.getText().toString(),
                        dependentLastName.getText().toString());
                dependents.add(dependent);
                dependentAdapter.notifyDataSetChanged();
                updateCostSummary();
            }
        });
        builder.setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button negButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0,0,24,0);
                negButton.setLayoutParams(params);
            }
        });
        alertDialog.show();
    }

}

package com.wwmanley.freshdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wwmanley.freshdb.Model.Employee;
import com.wwmanley.freshdb.Database.EmployeeOperations;

public class AdminAddUpdateEmployee extends AppCompatActivity
{
    private static final String EXTRA_EMPLOYEE_ID = "com.EId";
    private static final String EXTRA_ADD_UPDATE = "com.add_update";

    private RadioGroup radioGroup;
    private RadioButton trueRadioButton, falseRadioButton;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText jobTitleEditText;
    private EditText salaryEditText;

    private Button addUpdateButton;

    private Employee newEmployee;
    private Employee oldEmployee;

    private String mode;

    private long employeeID;

    private EmployeeOperations employeeData;


    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_update_employee);

        newEmployee = new Employee();
        oldEmployee = new Employee();

        firstNameEditText = (EditText)findViewById(R.id.edit_text_first_name);
        lastNameEditText = (EditText)findViewById(R.id.edit_text_last_name);
        passwordEditText = (EditText)findViewById(R.id.edit_text_employeepassword);
        jobTitleEditText = (EditText)findViewById(R.id.edit_text_jobtitle);
        salaryEditText = (EditText)findViewById(R.id.edit_text_salary);

        radioGroup = (RadioGroup)findViewById(R.id.radio_working);

        trueRadioButton = (RadioButton)findViewById(R.id.radio_true);
        falseRadioButton = (RadioButton)findViewById(R.id.radio_false);

        addUpdateButton = (Button)findViewById(R.id.button_add_update_employee);

        employeeData = new EmployeeOperations(this);
        employeeData.open();
        newEmployee.setIsworking("True");

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId == R.id.radio_true)
                {
                    newEmployee.setIsworking("True");
                    if(mode.equals("Update"))
                    {
                        oldEmployee.setIsworking("True");
                    }
                }

                else if (checkedId == R.id.radio_false)
                {
                    newEmployee.setIsworking("False");
                    if(mode.equals("Update"))
                    {
                        oldEmployee.setIsworking("False");
                    }
                }
            }
        });

        addUpdateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mode.equals("Add"))
                {
                    newEmployee.setFirstname(firstNameEditText.getText().toString());
                    newEmployee.setLastname(lastNameEditText.getText().toString());
                    newEmployee.setPassword(passwordEditText.getText().toString());
                    newEmployee.setJob(jobTitleEditText.getText().toString());
                    newEmployee.setSalary(salaryEditText.getText().toString());

                    if(firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty() || jobTitleEditText.getText().toString().isEmpty() || salaryEditText.getText().toString().isEmpty())
                    {
                        Toast.makeText(AdminAddUpdateEmployee.this,"Field or Fields cannot be empty !!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        employeeData.addEmployee(newEmployee);
                        Toast.makeText(AdminAddUpdateEmployee.this, "Employee " + newEmployee.getFirstname()+" has been added successfully !", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AdminAddUpdateEmployee.this,AdminActivity.class);
                        startActivity(i);
                    }
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_return, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_cancel:
                actionCancel();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        actionCancel();
    }

    private void actionCancel()
    {
        finish();
    }

    private void initializeEmployee(long employeeID)
    {
        oldEmployee = employeeData.getEmployee(employeeID);

        firstNameEditText.setText(oldEmployee.getFirstname());
        lastNameEditText.setText(oldEmployee.getLastname());
        passwordEditText.setText(oldEmployee.getPassword());

        radioGroup.check(oldEmployee.getIsworking().equals("True") ? R.id.radio_true : R.id.radio_false);
        jobTitleEditText.setText(oldEmployee.getJob());
        salaryEditText.setText(oldEmployee.getSalary());
    }
}

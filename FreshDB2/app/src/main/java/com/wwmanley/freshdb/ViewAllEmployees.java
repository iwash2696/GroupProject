package com.wwmanley.freshdb;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuItem;

import com.wwmanley.freshdb.Database.EmployeeDatabaseHandler;
import com.wwmanley.freshdb.Database.EmployeeOperations;
import com.wwmanley.freshdb.Model.Employee;

import java.util.List;

public class ViewAllEmployees extends ListActivity
{
    private EmployeeOperations employeeOps;
    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employees);

        employeeOps = new EmployeeOperations(this);
        employeeOps.open();
        employees = employeeOps.getAllEmployees();
        employeeOps.close();

        ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this,R.layout.simple_list_item_1,employees);
        setListAdapter(adapter);
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

}

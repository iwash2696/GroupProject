package com.wwmanley.freshdb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwmanley.freshdb.Model.Employee;
import com.wwmanley.freshdb.Database.EmployeeOperations;
import com.wwmanley.freshdb.Database.EmployeeDatabaseHandler;


public class Emp_Login extends AppCompatActivity
{
    private static final String TAG = "Employee Exits";

    public EditText idEditText;
    public EditText passEditText;

    public EditText EmpFN_EditTextShow;
    public EditText EmpLN_EditTextShow;
    public EditText EmpWorking_EditTextShow;
    public EditText EmpJob_EditTextShow;
    public EditText EmpSalary_EditTextShow;


    public Button LoginEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp__login);

        idEditText = (EditText)findViewById(R.id.edit_text_emp_id_login);
        passEditText = (EditText)findViewById(R.id.edit_text_emp_pass_login);

        EmpFN_EditTextShow = (EditText)findViewById(R.id.edit_text_emp_firstname_show);
        EmpLN_EditTextShow = (EditText)findViewById(R.id.edit_text_emp_lastname_show);
        EmpWorking_EditTextShow = (EditText)findViewById(R.id.edit_text_emp_working_show);
        EmpJob_EditTextShow = (EditText)findViewById(R.id.edit_text_emp_job_show);
        EmpSalary_EditTextShow = (EditText)findViewById(R.id.edit_text_emp_salary_show);

        LoginEmp = (Button)findViewById(R.id.button_login_emp);

        LoginEmp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(idEditText.getText().toString().isEmpty() || passEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(Emp_Login.this,"Empty fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(check_login(idEditText.getText().toString(),passEditText.getText().toString()) == true)
                    {
                        Toast.makeText(Emp_Login.this,"Successful login",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Emp_Login.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public boolean check_login(String emp_id, String emp_pass)
    {
        SQLiteOpenHelper database = new EmployeeDatabaseHandler(this);
        SQLiteDatabase db = database.getWritableDatabase();

        String select = "SELECT * FROM employees WHERE employeeID ='" + emp_id + "' AND password='" + emp_pass + "'";

        Cursor c = db.rawQuery(select,null);

        if(c.moveToFirst())
        {
            Log.d(TAG,"Employee exists");

            EmpFN_EditTextShow.setText(c.getString(1));
            EmpLN_EditTextShow.setText(c.getString(2));
            EmpWorking_EditTextShow.setText(c.getString(4));
            EmpJob_EditTextShow.setText(c.getString(5));
            EmpSalary_EditTextShow.setText(c.getString(6));

            return true;
        }
        if(c!=null)
        {
            c.close();
        }
        db.close();
        return false;
    }
}

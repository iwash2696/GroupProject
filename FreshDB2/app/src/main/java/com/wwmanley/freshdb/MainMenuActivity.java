package com.wwmanley.freshdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity
{

    private Button AdminButton;
    private Button EmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        AdminButton = (Button)findViewById(R.id.button_admin);
        EmployeeButton = (Button)findViewById(R.id.button_employee);
    }

    public void goToAdminLogin(View view)
    {
        AdminButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainMenuActivity.this, AdminLogin.class);
                startActivity(i);
            }
        });
    }

   public void goToEmployeeLogin(View view)
    {
        EmployeeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainMenuActivity.this, Emp_Login.class);
                startActivity(i);
            }
        });
    }
}

package com.wwmanley.freshdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity
{

    public EditText usernameEditText;
    public EditText passwordEditText;

    public Button loginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        usernameEditText = (EditText)findViewById(R.id.edit_text_username);
        passwordEditText = (EditText)findViewById(R.id.edit_text_password);

        loginAdmin = (Button)findViewById(R.id.button_loginAdmin);

        loginAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (usernameEditText.getText().toString().equals("Admin") && passwordEditText.getText().toString().equals("Admin123"))
                {
                    Intent i = new Intent(AdminLogin.this , AdminActivity.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(AdminLogin.this, "Incorrect Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

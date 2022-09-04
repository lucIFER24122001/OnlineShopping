package com.example.onlineshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {


    EditText email,pass,name;
    Button login;
    TextView reg;
    TextInputLayout email_layout , pass_layout;
    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =(EditText) findViewById(R.id.editTextEmail1);
        pass =(EditText) findViewById(R.id.editTextPassword1);
        login =(Button) findViewById(R.id.cirLoginButton);
        reg = (TextView) findViewById(R.id.registertxt);
        name =(EditText)findViewById(R.id.editTextName) ;

        email_layout = (TextInputLayout) findViewById(R.id.textInputEmail1);
        pass_layout = (TextInputLayout) findViewById(R.id.textInputPassword1);


        databaseHelper = new DatabaseHelper(LoginActivity.this);
        inputValidation = new InputValidation(LoginActivity.this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(!inputValidation.isInputEditTextFilled(email,email_layout,"Enter Valid Email"))
                {
                    return;
                }

                if(!inputValidation.isInputEditTextEmail(email,email_layout,"Enter Valid Email"))
                {
                    return;
                }
                if(!inputValidation.isInputEditTextFilled(email,email_layout,"Enter Valid Email"))
                {
                    return;
                }

                if(!inputValidation.isInputEditTextFilled(pass,pass_layout, "Enter Valid Password"))
                {
                    return;
                }
                if(databaseHelper.checkuser(email.getText().toString().trim() , pass.getText().toString().trim()))
                {

                    Intent accountsIntent =  new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(accountsIntent);
                    finish();

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Email Doesn't Exists", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
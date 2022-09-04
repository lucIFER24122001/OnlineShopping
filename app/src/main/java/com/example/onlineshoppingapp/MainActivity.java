package com.example.onlineshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    TextInputLayout name_lay,email_lay,pass_lay,conf_lay;
    EditText name_reg,email_reg,pass_reg,confirmPass_reg;
    Button register;
    TextView already_ac;


    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
     User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_lay =(TextInputLayout) findViewById(R.id.textInputName);
        email_lay =(TextInputLayout) findViewById(R.id.textInputEmail);
        pass_lay =(TextInputLayout) findViewById(R.id.textInputpassword);
        conf_lay =(TextInputLayout) findViewById(R.id.textInputConfirmPassword);

        name_reg = (EditText)findViewById(R.id.editTextName);
        email_reg = (EditText)findViewById(R.id.editTextEmail);
        pass_reg = (EditText)findViewById(R.id.editTextPassword);
        confirmPass_reg = (EditText)findViewById(R.id.editTextConfirmPassword);

        register=(Button) findViewById(R.id.cirRegisterButton);
        already_ac=(TextView) findViewById(R.id.logintxt);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        inputValidation = new InputValidation(MainActivity.this);
        user = new User();


        already_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!inputValidation.isInputEditTextFilled(name_reg,name_lay,"Enter full Name"))
                {
                    return;
                }

                if(!inputValidation.isInputEditTextEmail(email_reg,email_lay,"Enter Valid Email"))

                {
                    return;
                }

                if(!inputValidation.isInputEditTextPassword(pass_reg,pass_lay,"Enter Valid Password"))

                {
                    return;
                }

                if(!inputValidation.isInputEditTextMatches(pass_reg,confirmPass_reg,conf_lay,"Password Does Not Matches"))

                {
                    return;
                }

                if(!databaseHelper.checkUser(email_reg.getText().toString().trim()))
                {
                    user.setName(name_reg.getText().toString().trim());
                    user.setEmail(email_reg.getText().toString().trim());
                    user.setPassword(pass_reg.getText().toString().trim());
                    databaseHelper.addUser(user);


                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

                }

                else
                {
                    Toast.makeText(MainActivity.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                }

                String name = name_reg.getText().toString();
                SharedPreferences shrd = getSharedPreferences("demo",MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor =shrd.edit();
                editor.putString("name", name);
                editor.apply();





            }

        });





    }
}
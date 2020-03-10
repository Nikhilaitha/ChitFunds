package com.example.chitfunds.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chitfunds.DAO.ContractForUserData;
import com.example.chitfunds.DAO.DatabaseControllerForUserData;
import com.example.chitfunds.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class sign_in extends AppCompatActivity implements View.OnClickListener{
    TextInputLayout layout_E_mail,layout_password;
    TextInputEditText edittext_E_mail,editText_password;
    Button sign_in,sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
       signinReferences();
    }
    public void signinReferences(){
        layout_E_mail = findViewById(R.id.signin_textinputlayout_email);
        layout_password = findViewById(R.id.signin_textinputlayout_password);
        edittext_E_mail = findViewById(R.id.signin_textinputedittext_email);
        editText_password = findViewById(R.id.signin_textinputedittext_password);
        sign_in = findViewById(R.id.button_singin);
        sign_up = findViewById(R.id.button_signup);

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_singin:
            {

                if (isEmailValid() && isPasswordValid())
                {
                    if (checkUserNameAndPasswordInDb())
                    {
                        Toast.makeText(getApplicationContext(),"successfully loggedin",Toast.LENGTH_SHORT).show();
                        Intent signin_intent = new Intent(getApplicationContext(), com.example.chitfunds.home.home.class);
                        startActivity(signin_intent);
                    }
                }
                break;
            }
            case R.id.button_signup:
            {
                    Toast.makeText(getApplicationContext(),"going to signup page",Toast.LENGTH_SHORT).show();
                    Intent signup_intent = new Intent(getApplicationContext(), sign_up.class);
                    startActivity(signup_intent);
                    break;
            }
        }

    }

    private boolean checkUserNameAndPasswordInDb(){

        DatabaseControllerForUserData object = new DatabaseControllerForUserData(getApplicationContext());
        String[] arr=object.getUserNameAndPassword(edittext_E_mail.getText().toString());
        if (arr!=null)
        {
           if(arr[1].contentEquals(editText_password.getText().toString()))
           {
               layout_password.setError(null);
               return true;
           }
           else
           {
               layout_password.setError("Password incorrect");
               return false;
           }

        }
        else
        {
           layout_E_mail.setError("Email Doesn't exist!!");
            return false;
        }
    }

    private boolean isEmailValid()
    {
        String email= edittext_E_mail.getText().toString();
        if(email.isEmpty())
        {
            layout_E_mail.setError("Email cannot be Empty!");
            return false;
        }
        else
        {
            if(Pattern.matches("^(.+)@(.+)$",email))
            {
                layout_E_mail.setError("");
                return true;
            }
            else
            {
                layout_E_mail.setError("Not Valid Email!");
                return false;
            }
        }
    }

    private boolean isPasswordValid()
    {
        String password=editText_password.getText().toString();
        if (password.isEmpty())
        {
            layout_password.setError("Password cannot be empty!");
            return false;
        }
        else
        {
            if(Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{3,20})",password))
            {
                layout_password.setError("");
                return true;
            }
            else
            {
                layout_password.setError("Password is weak!");
                return false;
            }
        }
    }

    private void loadCurrentUserData()
    {
      DatabaseControllerForUserData object=new DatabaseControllerForUserData(getApplicationContext());
      object.getCurrentUserData(edittext_E_mail.getText().toString());
    }

}

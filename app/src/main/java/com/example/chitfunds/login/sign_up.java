package com.example.chitfunds.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chitfunds.DAO.DatabaseControllerForUserData;
import com.example.chitfunds.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout layout_email,layout_password,layout_confirm_password,layout_phone_number;
    TextInputEditText editText_email,editText_password,editText_confirm_password,editText_phone_number;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         signupReferences();

    }

    public void signupReferences(){
        layout_email = findViewById(R.id.signup_textinputlayout_email);
        layout_password = findViewById(R.id.signup_textinputlayout_password);
        layout_confirm_password = findViewById(R.id.signup_textinputlayout_confirm_password);
        layout_phone_number = findViewById(R.id.signup_textinputlayout_phone_number);
        editText_email = findViewById(R.id.signup_textinputedittext_email);
        editText_password = findViewById(R.id.signup_textinputedittext_password);
        editText_confirm_password = findViewById(R.id.signup_textinputedittext_confirm_password);
        editText_phone_number = findViewById(R.id.signup_textinputedittext_phone_number);
        next = findViewById(R.id.button_signup_next);

       next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_signup_next:
                if(isEmailValid() && isPasswordValid() && isConformPasswordValid() && isPhoneNumberValid())
                {
                    if(!isUserExistIndb())
                    {
                        Intent next_intent = new Intent(sign_up.this,sign_up2.class);
                        next_intent.putExtra("EmailOfUser",editText_email.getText().toString());
                        next_intent.putExtra("PasswordOfUser",editText_password.getText().toString());
                        next_intent.putExtra("PhoneOfUser",editText_phone_number.getText().toString());
                        startActivity(next_intent);
                    }
                    else
                    {
                        Toast.makeText(this, "User Already exist", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private boolean isUserExistIndb()
    {
        DatabaseControllerForUserData object=new DatabaseControllerForUserData(getApplicationContext());
        return  object.isEmailExists(editText_email.getText().toString());
    }
    private boolean isEmailValid()
    {
      String email=editText_email.getText().toString();
      if(email.isEmpty())
      {
          layout_email.setError("Email cannot be Empty!");
          return false;
      }
      else
      {
          if(Pattern.matches("^(.+)@(.+)$",email))
          {
              layout_email.setError("");
              return true;
          }
          else
          {
              layout_email.setError("Not Valid Email!");
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
    private boolean isConformPasswordValid()
    {
      String password=editText_password.getText().toString();
      String confirmpassword=editText_confirm_password.getText().toString();
      if(confirmpassword.isEmpty())
      {
        layout_confirm_password.setError("Confirm password cannot be empty!");
        return false;
      }
      else if (password.contentEquals(confirmpassword))
      {
          layout_confirm_password.setError("");
          return true;
      }
      else
      {
          layout_confirm_password.setError("Passwords are not equal");
          return false;
      }
    }
    private boolean isPhoneNumberValid() {
        String number=editText_phone_number.getText().toString();
        if(number.isEmpty())
        {
            layout_phone_number.setError("Phone Number Cannot be empty");
            return false;
        }
        else
        {
            if(Pattern.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$",number))
            {
                layout_phone_number.setError("");
                return true;
            }
            else
            {
                layout_phone_number.setError("Not Valid Phone Number");
                return false;
            }
        }

    }

}

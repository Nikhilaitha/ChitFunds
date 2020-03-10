package com.example.chitfunds.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chitfunds.DAO.DatabaseControllerForUserData;
import com.example.chitfunds.DTO.UserDetailDTO;
import com.example.chitfunds.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class sign_up2 extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout layout_full_name,layout_nominee,layout_phone_number,layout_location;
    TextInputEditText editText_full_name,editText_nominee,editText_phone_number,editText_location;
    Button button_register;
   String email,phone,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

       findViewByIds();


    }
    public void findViewByIds(){
        layout_full_name = findViewById(R.id.signup_textinputlayout_full_name);
        layout_nominee = findViewById(R.id.signup_textinputlayout_nominee_name);
        layout_phone_number = findViewById(R.id.signup_textinputlayout_nominee_phone_number);
        layout_location = findViewById(R.id.signup_textinputlayout_loaction);
        editText_full_name = findViewById(R.id.signup_textinputedittext_full_name);
        editText_nominee =findViewById(R.id.signup_textinputedittext_nominee_name);
        editText_phone_number = findViewById(R.id.signup_textinputedittext_nominee_phone_number);
        editText_location = findViewById(R.id.signup_textinputedittext_location);
        button_register = findViewById(R.id.button_signup_register);

        button_register.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
      switch(v.getId())
      {
          case R.id.button_signup_register:
              if(isNameValid() && isNomineeValid() && isPhoneNumberValid() && isLocationValid())
              {
                  getDataFromIntent();

                     if (storeUserDataIntoDb())
                     {
                         Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_SHORT).show();
                         Intent register_intent = new Intent(sign_up2.this, sign_in.class);
                         startActivity(register_intent);
                     }
                     else
                     {
                         Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                     }

              }
              break;
      }
    }

    private boolean storeUserDataIntoDb()
    {

        DatabaseControllerForUserData object=new DatabaseControllerForUserData(getApplicationContext());
        UserDetailDTO userDetailDTO=new UserDetailDTO();
        userDetailDTO.setEmail(this.email);
        userDetailDTO.setFullName(editText_full_name.getText().toString());
        userDetailDTO.setPassword(this.password);
        userDetailDTO.setNomineeName(editText_nominee.getText().toString());
        userDetailDTO.setNomineePhoneNumber(editText_phone_number.getText().toString());
        userDetailDTO.setUserAddress(editText_location.getText().toString());
        userDetailDTO.setPlans(" ");
        userDetailDTO.setUserPhoneNumber(this.phone);
        return object.signUpWithUserData(userDetailDTO);
    }



    private boolean isNameValid()
    {
       String name=editText_full_name.getText().toString();
       if(name.isEmpty())
       {
           layout_full_name.setError("Name cannot be empty");
           return false;
       }
       else if(name.length()<2)
       {
           layout_full_name.setError("Length is too small");
           return false;
       }
       else
       {
           layout_full_name.setError(null);
           return true;
       }

    }

    private boolean isNomineeValid()
    {
        String name=editText_nominee.getText().toString();
        if(name.isEmpty())
        {
            layout_nominee.setError("Name cannot be empty");
            return false;
        }
        else if(name.length()<2)
        {
            layout_nominee.setError("Length is too small");
            return false;
        }
        else
        {
            layout_nominee.setError(null);
            return true;
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
                layout_phone_number.setError(null);
                return true;
            }
            else
            {
                layout_phone_number.setError("Not Valid Phone Number");
                return false;
            }
        }

    }

    private boolean isLocationValid()
    {
        String location=editText_location.getText().toString();
        if(location.isEmpty())
        {
            layout_location.setError("Location cannot be Empty!");
            return false;
        }
        else
        {
            layout_location.setError(null);
            return true;
        }
    }

   private void getDataFromIntent()
   {
       this.email=getIntent().getStringExtra("EmailOfUser");
       this.password=getIntent().getStringExtra("PasswordOfUser");
       this.phone=getIntent().getStringExtra("PhoneOfUser");
   }

}

package com.example.chitfunds.home.myprofile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chitfunds.DAO.DatabaseControllerForUserData;
import com.example.chitfunds.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MyProfile extends AppCompatActivity implements View.OnClickListener {
    TextView MyProfile_fullName,MyProfile_fullNamegiven,MyProfile_PhoneNumber,MyProfile_PhoneNumbergiven,MyProfile_EMail,MyProfile_EMailgiven;
    String nameFromAlertDialog,oldname;
    TextInputLayout alertnamealayout;
    TextInputEditText alertnameedittext;
   String userFullNameFromDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        findViewByIds();


    }
    private void findViewByIds()
    {
        MyProfile_fullNamegiven = findViewById(R.id.text_myprofile_fullnamegiven);
        MyProfile_PhoneNumbergiven = findViewById(R.id.textview_myprofile_phone_numbergiven);
        MyProfile_EMailgiven = findViewById(R.id.textview_myprofile_email_given);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.textview_myprofile_fullname:
                 String name=getNameFromAlertBox();
                break;
        }
    }

    private void updateNameIntoDb(String name)
    {
        DatabaseControllerForUserData databaseControllerForUserData=new DatabaseControllerForUserData(getApplicationContext());
        if (databaseControllerForUserData.updateUserFullName(name,oldname))
        {
          MyProfile_fullNamegiven.setText(name);
        }
    }
    private String getNameFromAlertBox()
    {
        alertnamealayout=new TextInputLayout(getApplicationContext());
        alertnamealayout.setErrorEnabled(true);
        alertnameedittext.setInputType(InputType.TYPE_CLASS_TEXT);
        alertnameedittext.setHint("Name:");
        alertnamealayout.addView(alertnameedittext);
        LinearLayout linearLayout=new LinearLayout(getApplicationContext());
        linearLayout.addView(alertnamealayout);

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(getApplicationContext());
        alertDialog.setView(linearLayout);
        alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(alertnameedittext.getText().toString().isEmpty())
                {
                    alertnamealayout.setError("Cannot be empty!!");
                }
                else if(alertnameedittext.getText().toString().length()<3)
                {
                    alertnamealayout.setError("cannot be less than 3");
                }
                else
                {
                    alertnamealayout.setError(null);
                    nameFromAlertDialog=alertnameedittext.getText().toString();
                    dialog.cancel();
                }
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        if (nameFromAlertDialog.isEmpty() || nameFromAlertDialog==null)
        {
            return userFullNameFromDb;
        }
        else
        {
            return nameFromAlertDialog;
        }
    }


}

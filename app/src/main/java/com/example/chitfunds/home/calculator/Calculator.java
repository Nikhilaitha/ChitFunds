package com.example.chitfunds.home.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitfunds.R;

import java.util.regex.Pattern;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    EditText amount,month,members;
    Button calculate;
    TextView getamount,note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        References();
    }
    public void References(){
        amount=findViewById(R.id.calculator_amount);
        month=findViewById(R.id.calculator_month);
        members=findViewById(R.id.calculator_members);
        calculate=findViewById(R.id.button_calculate);
        getamount=findViewById(R.id.getamount);
        note=findViewById(R.id.note);

        calculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_calculate:
            {
                closeKeyBoard();
                if (isAmountValid() && isMonthsValid() && isMembersValid()){
                int vamount = Integer.parseInt(amount.getText().toString());
                int vmonth = Integer.parseInt(month.getText().toString());
                int vmembers = Integer.parseInt(members.getText().toString());
                int total=(vmembers*vamount)+(vmonth*(vamount/5))-vamount-(vamount/5);
                getamount.setText("Amount = "+String.valueOf(total));
                note.setText("The person who have already taken the chit must pay extra amount of "+String.valueOf(vamount/5)+" per month");
                getamount.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
            }

            }
        }

    }
    public boolean isAmountValid(){
        String camount = amount.getText().toString();
        if (camount.isEmpty()){
            amount.setError("Please enter the amount");
            Toast.makeText(this, "Please enter the amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (camount.length()>3){
                return true;
            }
            else {
                amount.setError("enter a valid amount");
                return false;
            }
        }

    }
    public boolean isMonthsValid(){
        String cmonth = month.getText().toString();
        if (cmonth.isEmpty()){
            month.setError("Enter the number of months");
            Toast.makeText(this, "Please enter months", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (cmonth.length()>0){
                return true;
            }
            else {
                return false;
            }
        }

    }
    public boolean isMembersValid(){
        String cmembers = members.getText().toString();
        if (cmembers.isEmpty()){
            members.setError("Please number of members");
            Toast.makeText(this, "Please enter months", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (cmembers.length()>0){
                return true;
            }
            else {
                return false;
            }
        }



    }
    public void closeKeyBoard(){
        View view = this.getCurrentFocus();
        if (view!=null){
            InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(view.getWindowToken(),0);

        }
    }
}

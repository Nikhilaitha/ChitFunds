package com.example.chitfunds.home.faqs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.chitfunds.R;

public class Faqs extends AppCompatActivity implements View.OnClickListener {
    TextView qstn1,qstn2,qstn3,qstn4,qstn5,qstn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs_questions);
        References();
    }
    public void References(){
        qstn1 = findViewById(R.id.question1);
        qstn2 = findViewById(R.id.question2);
        qstn3 = findViewById(R.id.question3);
        qstn4 = findViewById(R.id.question4);
        qstn5 = findViewById(R.id.question5);
        qstn6 = findViewById(R.id.question6);

        qstn1.setOnClickListener(this);
        qstn2.setOnClickListener(this);
        qstn3.setOnClickListener(this);
        qstn4.setOnClickListener(this);
        qstn5.setOnClickListener(this);
        qstn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.question1 :{
                Question_one viewclicked = new Question_one();
                viewclicked.show(getSupportFragmentManager(),"exampleBottomSheet");
                break;
            }
            case R.id.question2:{
                Question_two object = new Question_two();
                object.show(getSupportFragmentManager(),"Faqs");
                break;
            }
            case R.id.question3:{
                Question_three object = new Question_three();
                object.show(getSupportFragmentManager(),"Faqs");
                break;
            }
            case R.id.question4:{
                Question_four object = new Question_four();
                object.show(getSupportFragmentManager(),"Faqs");
                break;
            }
            case R.id.question5:{
                Question_five object = new Question_five();
                object.show(getSupportFragmentManager(),"Faqs");
                break;
            }
            case R.id.question6:{
                Question_six object = new Question_six();
                object.show(getSupportFragmentManager(),"Faqs");
                break;
            }
        }
    }
}

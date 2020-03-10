package com.example.chitfunds.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.chitfunds.R;
import com.example.chitfunds.home.aboutus.Aboutus;
import com.example.chitfunds.home.calculator.Calculator;
import com.example.chitfunds.home.faqs.Faqs;
import com.example.chitfunds.home.homepage.Infowithpayment;
import com.example.chitfunds.home.myprofile.MyProfile;
import com.example.chitfunds.home.plans.Plans;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GridLayout grid;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        grid = findViewById(R.id.grid);
        setSingleEvent(grid);
    }

    private void setSingleEvent(GridLayout grid) {
        for (int i=0;i<grid.getChildCount();i++){
            CardView cardView = (CardView)grid.getChildAt(i);
            final int final1=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (final1==0){
                        Intent intent = new Intent(home.this, Infowithpayment.class);
                        startActivity(intent);
                    }
                    else if (final1==1){
                        Intent intent = new Intent(home.this, MyProfile.class);
                        startActivity(intent);
                    }
                    else if (final1==2){
                        Intent intent = new Intent(home.this, Calculator.class);
                        startActivity(intent);
                    }
                    else if (final1==3){
                        Intent intent = new Intent(home.this, Plans.class);
                        startActivity(intent);
                    }
                    else if (final1==4){
                        Intent intent = new Intent(home.this, Faqs.class);
                        startActivity(intent);
                    }
                    else if (final1==5){
                        Intent intent = new Intent(home.this, Aboutus.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}

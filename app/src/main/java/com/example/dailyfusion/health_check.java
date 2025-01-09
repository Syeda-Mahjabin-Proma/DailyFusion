package com.example.dailyfusion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class health_check extends AppCompatActivity {
    private Button bmi, bmr, water, bsi, calories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_check);
        findViews();
        onClick();
    }

    private void findViews() {
        bmi = findViewById(R.id.bmi);
        bmr = findViewById(R.id.bmr);
        water = findViewById(R.id.water);
        bsi = findViewById(R.id.bsi);
        calories = findViewById(R.id.calories);
    }

    private void onClick() {
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_check.this, calcBMI.class));
            }
        });

        bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_check.this, calcBMR.class));
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_check.this, water_need.class));

            }
        });
        bsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_check.this, calcBSA.class));
            }
        });
        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_check.this, calories_burn.class));
            }
        });
    }
}

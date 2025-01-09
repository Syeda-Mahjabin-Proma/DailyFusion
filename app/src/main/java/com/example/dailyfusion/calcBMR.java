package com.example.dailyfusion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class calcBMR extends AppCompatActivity {
    private RadioButton radio_button_male, radio_button_female;
    private EditText age, height_feet, height_inch, weight;
    private Button calculate;
    private TextView reset_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_bmr);
        findViews();
        setUpButtonOnClickListener();
    }

    private void findViews() {
        radio_button_male = findViewById(R.id.radio_button_male);
        radio_button_female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.age);
        height_feet = findViewById(R.id.height_feet);
        height_inch = findViewById(R.id.height_inch);
        weight = findViewById(R.id.weight);
        calculate = findViewById(R.id.calculate);
        reset_text = findViewById(R.id.reset_text);
    }

    private void setUpButtonOnClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio_button_male.isChecked() || radio_button_female.isChecked()) {
                    double[] bmr_height = calculateBmr();
                    String ageText = age.getText().toString();
                    if (ageText.isEmpty()) {
                        reset_text.setText("Enter Age");
                    } else {
                        int age = Integer.parseInt(ageText);
                        if (age >= 18) {
                            finalDecisionAdult(bmr_height);
                        } else {
                            reset_text.setText("As You are Under 18, Consult With Your Doctor");
                        }
                    }
                } else {
                    reset_text.setText("Select Gender");
                }
            }
        });
    }

    private double[] calculateBmr() {
        String feetText = height_feet.getText().toString();
        String inchText = height_inch.getText().toString();
        String weightText = weight.getText().toString();
        String ageText = age.getText().toString();
        double[] bmr_height;
        if (feetText.isEmpty() || inchText.isEmpty() || weightText.isEmpty()) {
            bmr_height = new double[]{0, 0};
        } else {
            int feet = Integer.parseInt(feetText);
            float inch = Float.parseFloat(inchText);
            float weight = Float.parseFloat(weightText);
            int age = Integer.parseInt(ageText);
            double total_height = ((feet * 12) + inch) * 0.0254;
            double bmr = 0;
            if (radio_button_female.isChecked()) {
                bmr = 447.593 + (9.247 * weight) + (3.098 * total_height) - (4.330 * age);
            }
            if (radio_button_male.isChecked()) {
                bmr = 88.362 + (13.397 * weight) + (4.799 * total_height) - (5.677 * age);
            }
            bmr_height = new double[]{bmr, total_height};
        }
        return bmr_height;
    }

    private void finalDecisionAdult(double[] bmr_height) {
        double bmr = bmr_height[0];
        double height = bmr_height[1];
        String bmrString = String.format("%.1f", bmr);
        if (bmr == 0) {
            reset_text.setText("Please Fill-up Height & Weight Properly");
        } else {

            reset_text.setText("You Need " + bmrString + " Calories Daily");
        }
        Toast.makeText(calcBMR.this, "Your BMR Calculation is Done!!!", Toast.LENGTH_SHORT).show();
    }

}
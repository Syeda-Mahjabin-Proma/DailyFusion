package com.example.dailyfusion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class calcBMI extends AppCompatActivity {
    private RadioButton radio_button_male, radio_button_female;
    private EditText age, height_feet, height_inch, weight;
    private Button calculate;
    private TextView reset_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_bmi);
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
                    double[] bmi_height = calculateBmi();
                    String ageText = age.getText().toString();
                    if (ageText.isEmpty()) {
                        reset_text.setText("Enter Age");
                    } else {
                        int age = Integer.parseInt(ageText);
                        if (age >= 18) {
                            finalDecisionAdult(bmi_height);
                        } else {
                            finalDecisionKids();
                        }
                    }
                } else {
                    reset_text.setText("Select Gender");
                }
            }
        });
    }

    private double[] calculateBmi() {
        String feetText = height_feet.getText().toString();
        String inchText = height_inch.getText().toString();
        String weightText = weight.getText().toString();
        double[] bmi_height;
        if (feetText.isEmpty() || inchText.isEmpty() || weightText.isEmpty()) {
            bmi_height = new double[]{0, 0};
        } else {
            int feet = Integer.parseInt(feetText);
            float inch = Float.parseFloat(inchText);
            float weight = Float.parseFloat(weightText);
            double total_height = ((feet * 12) + inch) * 0.0254;
            double bmi = ((weight / (total_height * total_height)));
            bmi_height = new double[]{bmi, total_height};
        }
        return bmi_height;
    }

    private void finalDecisionAdult(double[] bmi_height) {
        double bmi = bmi_height[0];
        double height = bmi_height[1];
        double min_weight = 18.5 * (height * height);
        double max_weight = 24.9 * (height * height);
        String preference = "Your Healthy Weight Range is from " + String.format("%.0f", min_weight) + " kg to " + String.format("%.0f", max_weight) + " kg. ";
        String bmiString = String.format("%.1f", bmi);
        String finalOutcome;
        if (bmi == 0) {
            finalOutcome = ("Please Fill-up Height & Weight Properly");
            reset_text.setText(finalOutcome);
        } else if (bmi < 18.5) {
            finalOutcome = ("Your BMI is: " + bmiString + " and You are UNDERWEIGHT. ");
            reset_text.setText(finalOutcome + "\n" + preference);
        } else if (bmi < 25.0) {
            finalOutcome = ("Your BMI is: " + bmiString + " and You are HEALTHY. ");
            reset_text.setText(finalOutcome + "\n" + preference);
        } else if (bmi < 30.0) {
            finalOutcome = ("Your BMI is: " + bmiString + " and You are OVERWEIGHT. ");
            reset_text.setText(finalOutcome + "\n" + preference);
        } else {
            finalOutcome = ("Your BMI is: " + bmiString + " and You are OBESE. ");
            reset_text.setText(finalOutcome + "\n" + preference);
        }
        Toast.makeText(calcBMI.this, "Your BMA Calculation is Done!!!", Toast.LENGTH_SHORT).show();
    }

    private void finalDecisionKids() {
        if (radio_button_male.isChecked()) {
            reset_text.setText("As You are Under 18, Consult With Your Doctor about Boys Healthy Weight Range");
        } else if (radio_button_female.isChecked()) {
            reset_text.setText("As You are Under 18, Consult With Your Doctor about Girls Healthy Weight Range");
        }
    }


}
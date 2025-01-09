package com.example.dailyfusion;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class memory extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private TextView val1, val2, val3, val4, val5, scoreVal, lifeVal, result_text, careful, scoreText, lifeText, divider;
    private Button b1, b2, b3;
    private ArrayList<Integer> values = new ArrayList<>();
    private int score = 0;
    private int lives = 3;
    private LinearLayout all_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);
        findViews();
        startGame();
    }

    private void findViews() {
        setContentView(R.layout.memory);

        val1 = findViewById(R.id.val_1);
        val2 = findViewById(R.id.val_2);
        val3 = findViewById(R.id.val_3);
        val4 = findViewById(R.id.val_4);
        val5 = findViewById(R.id.val_5);
        scoreVal = findViewById(R.id.score_val);
        lifeVal = findViewById(R.id.life_val);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        result_text = findViewById(R.id.final_result);
        careful = findViewById(R.id.careful);
        scoreText = findViewById(R.id.score_text);
        lifeText = findViewById(R.id.life_text);
        divider = findViewById(R.id.divider);
        all_result = findViewById(R.id.all_result);
    }

    private void startGame() {
        generateRandomValues();
        displayValues();
        b1.setText(" X ");
        b2.setText(" Y ");
        b3.setText(" Z ");
        setButtonsEnabled(false);

        handler.postDelayed(() -> {
            hideValues();
            setButtonOptions();
            setButtonsEnabled(true);
        }, 3000);

        setupButtonListeners();
    }

    private void generateRandomValues() {
        values.clear();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            values.add(random.nextInt(100));
        }
    }

    private void displayValues() {
        val1.setText(String.valueOf(values.get(0)));
        val2.setText(String.valueOf(values.get(1)));
        val3.setText(String.valueOf(values.get(2)));
        val4.setText(String.valueOf(values.get(3)));
        val5.setText(String.valueOf(values.get(4)));
    }

    private void hideValues() {
        val1.setText(" A ");
        val2.setText(" B ");
        val3.setText(" C ");
        val4.setText(" D ");
        val5.setText(" E ");
    }

    private void setButtonOptions() {
        Random random = new Random();
        ArrayList<Integer> buttonOptions = new ArrayList<>(values);

        while (buttonOptions.size() < 8) {
            int randomValue = random.nextInt(100);
            if (!values.contains(randomValue)) {
                buttonOptions.add(randomValue);
            }
        }

        Collections.shuffle(buttonOptions);
        b1.setText(String.valueOf(buttonOptions.get(0)));
        b2.setText(String.valueOf(buttonOptions.get(1)));
        b3.setText(String.valueOf(buttonOptions.get(2)));
    }

    private void setupButtonListeners() {
        View.OnClickListener buttonListener = view -> {
            Button clickedButton = (Button) view;
            int selectedValue = Integer.parseInt(clickedButton.getText().toString());

            if (values.contains(selectedValue)) {
                score++;
                scoreVal.setText(String.valueOf(score));
            } else {
                lives--;
                lifeVal.setText(String.valueOf(lives));
            }

            if (lives <= 0) {
                endGame();
            } else {
                startGame();
            }
        };

        b1.setOnClickListener(buttonListener);
        b2.setOnClickListener(buttonListener);
        b3.setOnClickListener(buttonListener);
    }

    private void setButtonsEnabled(boolean enabled) {
        b1.setEnabled(enabled);
        b2.setEnabled(enabled);
        b3.setEnabled(enabled);
    }

    private void endGame() {
        careful.setVisibility(View.GONE);
        val1.setVisibility(View.GONE);
        val2.setVisibility(View.GONE);
        val3.setVisibility(View.GONE);
        val4.setVisibility(View.GONE);
        val5.setVisibility(View.GONE);
        result_text.setText("Game Over" + "\n" + "Your Score is: " + score);
        scoreText.setVisibility(View.GONE);
        scoreVal.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        lifeText.setVisibility(View.GONE);
        lifeVal.setVisibility(View.GONE);
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        Button newStartButton = new Button(this);
        newStartButton.setText("Play Again");
        all_result.addView(newStartButton);
        Button newStopButton = new Button(this);
        newStopButton.setText("Exit");
        all_result.addView(newStopButton);
        newStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        newStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.dailyfusion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button playGame, weightCheck, todo, motivation, moneyCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onClick();
    }

    private void findViews() {
        playGame = findViewById(R.id.playGame);
        weightCheck = findViewById(R.id.weightCheck);
        todo = findViewById(R.id.todo);
        motivation = findViewById(R.id.motivation);
        moneyCalculate = findViewById(R.id.moneyCalculate);
    }

    private void onClick() {
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, gameplay.class));
            }
        });

        weightCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, health_check.class));
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, workList.class));

            }
        });
        motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, motivate.class));
            }
        });
        moneyCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, calcMoney.class));
            }
        });
    }
}
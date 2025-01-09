package com.example.dailyfusion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class gameplay extends AppCompatActivity {
    private Button numberChoose, tictactoe, memory, word, typing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        findViews();
        onClick();
    }

    private void findViews() {
        numberChoose = findViewById(R.id.numberChoose);
        tictactoe = findViewById(R.id.tictactoe);
        memory = findViewById(R.id.memory);
        word = findViewById(R.id.word);
        typing = findViewById(R.id.typing);
    }

    private void onClick() {
        numberChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameplay.this, right_wrong.class));
            }
        });

        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameplay.this, tictactoe.class));
            }
        });
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameplay.this, memory.class));

            }
        });
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameplay.this, word_game.class));
            }
        });
        typing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameplay.this, rock_paper_scissor.class));
            }
        });
    }
}

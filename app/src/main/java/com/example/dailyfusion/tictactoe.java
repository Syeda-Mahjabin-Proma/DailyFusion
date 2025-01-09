package com.example.dailyfusion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tictactoe extends AppCompatActivity {

    private TextView[] boxes = new TextView[9];
    private TextView player1Score, player2Score;
    private Button playAgainButton, endGameButton, resetButton;
    private int currentPlayer = 1;
    private int player1Points = 0;
    private int player2Points = 0;
    private boolean gameActive = true;
    private int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        boxes[0] = findViewById(R.id.box1);
        boxes[1] = findViewById(R.id.box2);
        boxes[2] = findViewById(R.id.box3);
        boxes[3] = findViewById(R.id.box4);
        boxes[4] = findViewById(R.id.box5);
        boxes[5] = findViewById(R.id.box6);
        boxes[6] = findViewById(R.id.box7);
        boxes[7] = findViewById(R.id.box8);
        boxes[8] = findViewById(R.id.box9);
        player1Score = findViewById(R.id.player1_score);
        player2Score = findViewById(R.id.player2_score);
        playAgainButton = findViewById(R.id.play_again);
        endGameButton = findViewById(R.id.end_game);
        resetButton = findViewById(R.id.start_over);


        for (int i = 0; i < 9; i++) {
            final int index = i;
            boxes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBoxClick(index);
                }
            });
        }

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void onBoxClick(int index) {
        if (!gameActive || gameState[index] != 0) {
            return;
        }

        if (currentPlayer == 1) {
            boxes[index].setText("X");
            gameState[index] = 1;
            currentPlayer = 2;
        } else {
            boxes[index].setText("O");
            gameState[index] = 2;
            currentPlayer = 1;
        }
        checkForWinner();
    }

    private void checkForWinner() {
        int[][] winningCombinations = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {
            int pos1 = combination[0];
            int pos2 = combination[1];
            int pos3 = combination[2];

            if (gameState[pos1] == gameState[pos2] && gameState[pos2] == gameState[pos3] && gameState[pos1] != 0) {
                gameActive = false;
                String winner = (gameState[pos1] == 1) ? "Player 1 (X)" : "Player 2 (O)";
                Toast.makeText(tictactoe.this, winner + " wins!", Toast.LENGTH_SHORT).show();

                boxes[pos1].setTextColor(Color.parseColor("#B22222"));
                boxes[pos2].setTextColor(Color.parseColor("#B22222"));
                boxes[pos3].setTextColor(Color.parseColor("#B22222"));

                if (gameState[pos1] == 1) {
                    player1Points++;
                    player1Score.setText(String.valueOf(player1Points));
                } else {
                    player2Points++;
                    player2Score.setText(String.valueOf(player2Points));
                }
                return;
            }
        }

        boolean isDraw = true;
        for (int state : gameState) {
            if (state == 0) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            gameActive = false;
            Toast.makeText(tictactoe.this, "It's a Draw!", Toast.LENGTH_SHORT).show();
        }
    }

    private void playAgain() {
        for (int i = 0; i < 9; i++) {
            gameState[i] = 0;
            boxes[i].setText("");
            boxes[i].setTextColor(Color.parseColor("#000000"));
            boxes[i].setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        gameActive = true;
        currentPlayer = 1;
    }

}

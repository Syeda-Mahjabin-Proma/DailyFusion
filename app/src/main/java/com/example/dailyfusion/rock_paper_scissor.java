package com.example.dailyfusion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class rock_paper_scissor extends AppCompatActivity {
    int yourFinalScore = 0;
    int computerFinalScore = 0;
    private TextView welcome, computerSelect, playerSelect, winnerSelection, winnerText,
            playerScore, computerScore, selectOne;
    private RadioButton rock, paper, scissor;
    private RadioGroup radioGroup;
    private Button submit, rePlay, exit, endGame;
    private LinearLayout newBtn, computerSelectionLayout, playerSelectionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rock_paper_scissor);
        findViews();
        setUpButtonOnClickListener();
        endButton();
    }

    private void endButton() {
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome.setText("Game Over");
                selectOne.setVisibility(View.GONE);
                radioGroup.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
                computerSelectionLayout.setVisibility(View.GONE);
                playerSelectionLayout.setVisibility(View.GONE);
                winnerText.setText("Game Winner: ");
                if (yourFinalScore == computerFinalScore) {
                    winnerSelection.setText("DRAW");
                } else if (yourFinalScore > computerFinalScore) {
                    winnerSelection.setText("YOU");
                } else {
                    winnerSelection.setText("COMPUTER");
                }
                endGame.setVisibility(View.GONE);
                newBtn.setVisibility(View.VISIBLE);
                rePlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }

    private void setUpButtonOnClickListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String computerChoose = computerSelectOption();
                String playerChoose = playerSelectOption();
                if (computerChoose.equals(playerChoose)) {
                    winnerSelection.setText("DRAW");
                    yourFinalScore += 1;
                    playerScore.setText(String.valueOf(yourFinalScore));
                    computerFinalScore += 1;
                    computerScore.setText(String.valueOf(computerFinalScore));
                } else if (computerChoose.equals("Rock")) {
                    if (playerChoose.equals("Paper")) {
                        winnerSelection.setText("YOU");
                        yourFinalScore += 1;
                        playerScore.setText(String.valueOf(yourFinalScore));
                    } else if (playerChoose.equals("Scissor")) {
                        winnerSelection.setText("COMPUTER");
                        computerFinalScore += 1;
                        computerScore.setText(String.valueOf(computerFinalScore));
                    }
                } else if (computerChoose.equals("Paper")) {
                    if (playerChoose.equals("Scissor")) {
                        winnerSelection.setText("YOU");
                        yourFinalScore += 1;
                        playerScore.setText(String.valueOf(yourFinalScore));
                    } else if (playerChoose.equals("Rock")) {
                        winnerSelection.setText("COMPUTER");
                        computerFinalScore += 1;
                        computerScore.setText(String.valueOf(computerFinalScore));
                    }
                } else if (computerChoose.equals("Scissor")) {
                    if (playerChoose.equals("Rock")) {
                        winnerSelection.setText("YOU");
                        yourFinalScore += 1;
                        playerScore.setText(String.valueOf(yourFinalScore));
                    } else if (playerChoose.equals("Paper")) {
                        winnerSelection.setText("COMPUTER");
                        computerFinalScore += 1;
                        computerScore.setText(String.valueOf(computerFinalScore));
                    }
                }
                radioGroup.clearCheck();
            }
        });
    }

    private String playerSelectOption() {
        String playerChoose = "";
        if (rock.isChecked()) {
            playerChoose = "Rock";
        } else if (paper.isChecked()) {
            playerChoose = "Paper";
        } else if (scissor.isChecked()) {
            playerChoose = "Scissor";
        }
        playerSelect.setText(playerChoose);
        return playerChoose;
    }

    private String computerSelectOption() {
        Random random = new Random();
        int choice = random.nextInt(3);

        if (choice == 0) {
            computerSelect.setText("Rock");
            return "Rock";
        } else if (choice == 1) {
            computerSelect.setText("Paper");
            return "Paper";
        } else {
            computerSelect.setText("Scissor");
            return "Scissor";
        }

    }

    private void findViews() {
        welcome = findViewById(R.id.welcome);
        computerSelect = findViewById(R.id.computerSelect);
        playerSelect = findViewById(R.id.playerSelect);
        winnerSelection = findViewById(R.id.winnerSelection);
        winnerText = findViewById(R.id.winnerText);
        playerScore = findViewById(R.id.playerScore);
        computerScore = findViewById(R.id.computerScore);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        submit = findViewById(R.id.submit);
        rePlay = findViewById(R.id.rePlay);
        exit = findViewById(R.id.exit);
        newBtn = findViewById(R.id.newBtn);
        endGame = findViewById(R.id.endGame);
        selectOne = findViewById(R.id.selectOne);
        radioGroup = findViewById(R.id.radioGroup);
        computerSelectionLayout = findViewById(R.id.computerSelectionLayout);
        playerSelectionLayout = findViewById(R.id.playerSelectionLayout);
    }

}

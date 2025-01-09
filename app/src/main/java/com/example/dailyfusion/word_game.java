package com.example.dailyfusion;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class word_game extends AppCompatActivity {

    private ArrayList<String> words;
    private String currentWord;
    private String shuffledWord;
    private int score = 0;
    private int lives = 3;

    private TextView givenWord, score_val, life_val, finalResultTextView, welcome, wordgen, playerGuess;
    private EditText playerWord;
    private Button checkButton, playAgain, exit;
    private LinearLayout newButton, scoreLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_game);
        welcome = findViewById(R.id.welcome);
        wordgen = findViewById(R.id.wordGenerate);
        playerGuess = findViewById(R.id.playerGuess);
        givenWord = findViewById(R.id.givenWord);
        score_val = findViewById(R.id.score_val);
        life_val = findViewById(R.id.life_val);
        finalResultTextView = findViewById(R.id.finalResult);
        playerWord = findViewById(R.id.playerWord);
        checkButton = findViewById(R.id.check);
        playAgain = findViewById(R.id.playAgain);
        exit = findViewById(R.id.exit);
        newButton = findViewById(R.id.newBtn);
        scoreLife = findViewById(R.id.scoreLife);

        words = loadWordsFromAssets();

        if (words != null && !words.isEmpty()) {
            setNewWord();
        } else {
            Toast.makeText(this, "Error loading words from assets!", Toast.LENGTH_LONG).show();
        }

        checkButton.setOnClickListener(v -> checkPlayerGuess());
    }

    private ArrayList<String> loadWordsFromAssets() {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            AssetManager assetManager = getAssets();
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("words.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    wordList.add(line.trim());
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordList;
    }

    private void setNewWord() {
        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size()));
        shuffledWord = shuffleWord(currentWord);
        givenWord.setText(shuffledWord);
        playerWord.setText("");
    }

    private String shuffleWord(String word) {
        ArrayList<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }
        return shuffled.toString();
    }

    private void checkPlayerGuess() {
        String playerGuess = playerWord.getText().toString().trim();
        if (playerGuess.isEmpty()) {
            Toast.makeText(this, "Please enter a guess!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (playerGuess.equalsIgnoreCase(currentWord)) {
            score++;
            score_val.setText(String.valueOf(score));
            Toast.makeText(this, "Correct! +1 Score!", Toast.LENGTH_SHORT).show();
            setNewWord();
        } else {
            lives--;
            life_val.setText(String.valueOf(lives));
            Toast.makeText(this, "Wrong! -1 Life!", Toast.LENGTH_SHORT).show();
            playerWord.setText("");
            if (lives == 0) {
                endGame();
            }
        }
    }

    private void endGame() {
        welcome.setText("Game Over!!!");
        finalResultTextView.setVisibility(View.VISIBLE);
        finalResultTextView.setText("Your Score: " + score);
        wordgen.setText("The Word Was: ");
        givenWord.setText(currentWord);
        playerGuess.setVisibility(View.GONE);
        playerWord.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
        scoreLife.setVisibility(View.GONE);
        newButton.setVisibility(View.VISIBLE);
        playAgain.setOnClickListener(new View.OnClickListener() {
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
}

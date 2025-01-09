package com.example.dailyfusion;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class motivate extends AppCompatActivity {
    private TextView showQuote;
    private Button more, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motivate);
        findViews();
        ArrayList<String> quoteList = loadQuotesFromAssets();
        displayQuote(quoteList);
        showMore(quoteList);
        exitNow();
    }

    private void exitNow() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showMore(final ArrayList<String> quoteList) {
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuote(quoteList);
            }
        });
    }

    private void displayQuote(ArrayList<String> quoteList) {
        if (quoteList.isEmpty()) {
            showQuote.setText("You are Beautiful.");
            return;
        }
        Random random = new Random();
        String randomQuote = quoteList.get(random.nextInt(quoteList.size()));
        showQuote.setText(randomQuote);
    }

    private ArrayList<String> loadQuotesFromAssets() {
        ArrayList<String> quoteList = new ArrayList<>();
        try {
            AssetManager assetManager = getAssets();
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("quotes.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    quoteList.add(line.trim());
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            showQuote.setText("You are Beautiful.");
        }

        return quoteList;
    }

    private void findViews() {
        showQuote = findViewById(R.id.showQuote);
        more = findViewById(R.id.more);
        exit = findViewById(R.id.exit);
    }
}

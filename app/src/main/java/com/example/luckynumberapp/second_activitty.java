package com.example.luckynumberapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class second_activitty extends AppCompatActivity {
    TextView text2, text3 ;
    Button share_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_activitty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        share_btn = findViewById(R.id.share_btn);

        Intent i = getIntent();
        String username = i.getStringExtra("name");

        int random_num = generateRandomNumber();
        text3.setText(String.valueOf(random_num));

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(username, random_num);
            }
        });






    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;

    }

    public void shareData(String username, int random_num) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT, "Lucky Number");
        i.putExtra(Intent.EXTRA_TEXT, "Hi " + username + " your lucky number is " + random_num);
        startActivity(Intent.createChooser(i, "Share to"));

    }
}
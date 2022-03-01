package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activity_result extends AppCompatActivity {

    private TextView TXT_Gagnant;
    private TextView TXT_Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TXT_Gagnant = findViewById(R.id.result_winner_name);
        TXT_Points = findViewById(R.id.result_winner_points);
    }
}
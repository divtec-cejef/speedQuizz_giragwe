package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_result extends AppCompatActivity {

    private TextView TXT_Gagnant;
    private TextView TXT_Points;
    private Button BT_Replay;
    private Button BT_Quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TXT_Gagnant = findViewById(R.id.result_winner_name);
        TXT_Points = findViewById(R.id.result_winner_points);
        BT_Replay = findViewById(R.id.buttonRejouer);
        BT_Quit = findViewById(R.id.buttonQuitter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String joueurGagnant = intent.getStringExtra("nomGagnant");
        String joueursPoints = intent.getStringExtra("pointsGagnant");
        String joueurPerdant = intent.getStringExtra("nomPerdant");
        TXT_Gagnant.setText(joueurGagnant);
        TXT_Points.setText("Avec " +  joueursPoints + " points !");

        BT_Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_result.this, MainActivity.class);
                activity_result.this.startActivity(intent);
            }
        });

        BT_Replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_result.this, activity_game.class);
                intent.putExtra("joueur1", joueurGagnant);
                intent.putExtra("joueur2", joueurPerdant);
                activity_result.this.startActivity(intent);
            }
        });
    }
}
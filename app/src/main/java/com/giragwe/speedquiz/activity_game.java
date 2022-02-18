package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

enum Joueurs {
    JOUEUR1,
    JOUEUR2;
}


public class activity_game extends AppCompatActivity {

    private Button BT_ReponseJoueur1;
    private Button BT_ReponseJoueur2;
    private TextView TX_QuestionJ1;
    private TextView TX_QuestionJ2;
    private TextView TX_ptsJ1;
    private TextView TX_ptsJ2;

    ArrayList<Question> questionsList = new ArrayList<>();

    Question questionEnCours;

    int ptsJ1 = 0;
    int ptsJ2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        BT_ReponseJoueur1 = findViewById(R.id.buttonResponseJ1);
        BT_ReponseJoueur2 = findViewById(R.id.buttonResponseJ2);
        TX_QuestionJ1 = findViewById(R.id.questionJ1);
        TX_QuestionJ2 = findViewById(R.id.questionJ2);
        TX_ptsJ1 = findViewById(R.id.ptsJ1);
        TX_ptsJ2 = findViewById(R.id.ptsJ2);

        BT_ReponseJoueur1.setBackgroundColor(Color.GRAY);
        BT_ReponseJoueur2.setBackgroundColor(Color.GRAY);

        questionsList.add(new Question("Le Java est une language de programmation", true));
        questionsList.add(new Question("Le Javascript est un cousin du Java", false));
        questionsList.add(new Question("Le CSS est un language de programmation", false));
        questionsList.add(new Question("Le HTML est un langage de balisage", true));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String joueur1 = intent.getStringExtra("joueur1");
        String joueur2 = intent.getStringExtra("joueur2");

        BT_ReponseJoueur1.setText(joueur1);
        BT_ReponseJoueur2.setText(joueur2);

        questionEnCours = questionsList.get(0);
        TX_QuestionJ1.setText(questionEnCours.getQuestion());
        TX_QuestionJ2.setText(questionEnCours.getQuestion());

        BT_ReponseJoueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repondre(Joueurs.JOUEUR1);
            }
        });

        BT_ReponseJoueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repondre(Joueurs.JOUEUR2);
            }
        });

    }

    private void repondre(Joueurs joueur) {
        BT_ReponseJoueur1.setBackgroundColor(Color.GRAY);
        BT_ReponseJoueur2.setBackgroundColor(Color.GRAY);
        switch (joueur) {
            case JOUEUR1:
                if (questionEnCours.getReponse()) {
                    BT_ReponseJoueur1.setBackgroundColor(Color.GREEN);
                    ptsJ1++;
                } else {
                    BT_ReponseJoueur1.setBackgroundColor(Color.RED);
                }
                break;
            case JOUEUR2:
                if (questionEnCours.getReponse()) {
                    BT_ReponseJoueur2.setBackgroundColor(Color.GREEN);
                    ptsJ2++;
                } else {
                    BT_ReponseJoueur2.setBackgroundColor(Color.RED);
                }
                break;
        }

        TX_ptsJ1.setText(Integer.toString(ptsJ1));
        TX_ptsJ2.setText(Integer.toString(ptsJ2));
        questionSuivante();
    }

    private void questionSuivante() {
        int indice = questionsList.indexOf(questionEnCours);

        if(indice == questionsList.size() - 1) {
            indice = 0;
        } else {
            indice++;
        }

        questionEnCours = questionsList.get(indice);
        TX_QuestionJ1.setText(questionEnCours.getQuestion());
        TX_QuestionJ2.setText(questionEnCours.getQuestion());
    }
}
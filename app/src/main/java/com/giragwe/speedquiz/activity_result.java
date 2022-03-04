package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_result extends AppCompatActivity {

    //déclaration des variables pour les éléments graphiques
    private TextView TXT_Titre;
    private TextView TXT_Gagnant;
    private TextView TXT_NomJ1;
    private TextView TXT_PtsJ1;
    private TextView TXT_NomJ2;
    private TextView TXT_PtsJ2;
    private Button BT_Replay;
    private Button BT_Quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //récupération des éléments graphiques
        TXT_Titre = findViewById(R.id.result_title);
        TXT_Gagnant = findViewById(R.id.result_nom_vainqueur);
        TXT_NomJ1 = findViewById(R.id.result_nom_J1);
        TXT_PtsJ1 = findViewById(R.id.result_pts_J1);
        TXT_NomJ2 = findViewById(R.id.result_nom_J2);
        TXT_PtsJ2 = findViewById(R.id.result_pts_J2);
        BT_Replay = findViewById(R.id.buttonRejouer);
        BT_Quit = findViewById(R.id.buttonQuitter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //récupération des informations passées par l'activité Jeu
        Intent intent = getIntent();
        String nomJ1 = intent.getStringExtra("nomJ1");
        String nomJ2 = intent.getStringExtra("nomJ2");
        String ptsJ1 = intent.getStringExtra("ptsJ1");
        String ptsJ2 = intent.getStringExtra("ptsJ2");
        String gagnant = intent.getStringExtra("gagnant");

        //Change le texte de l'activité pour le vainqueur
        if(gagnant.equals("J1")) {
            TXT_Titre.setText(R.string.result_title_vainqueur);
            TXT_Gagnant.setText(nomJ1);

        } else if (gagnant.equals("J2")) {
            TXT_Titre.setText(R.string.result_title_vainqueur);
            TXT_Gagnant.setText(nomJ2);

        } else if (gagnant.equals("egal")) {
            TXT_Titre.setText(R.string.result_title_egalite);
        }

        //affiche les noms et les points des joueurs
        TXT_NomJ1.setText(nomJ1);
        TXT_NomJ2.setText(nomJ2);
        TXT_PtsJ1.setText(ptsJ1);
        TXT_PtsJ2.setText(ptsJ2);


        //Clique sur le bouton quitter
        BT_Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_result.this, MainActivity.class);
                activity_result.this.startActivity(intent);
            }
        });

        //clique sur le bouton rejouer
        BT_Replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_result.this, activity_game.class);
                intent.putExtra("joueur1", nomJ1);
                intent.putExtra("joueur2", nomJ2);
                activity_result.this.startActivity(intent);
            }
        });
    }
}
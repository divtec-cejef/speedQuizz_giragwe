package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

import com.giragwe.speedquiz.Controllers.QuestionManager;
import com.giragwe.speedquiz.Models.Question;

import java.util.ArrayList;

//Type énuméré pour les joueurs
enum Joueurs {
    JOUEUR1,
    JOUEUR2
}

/**
 * Activité de jeu
 */
public class activity_game extends AppCompatActivity {

    //Création des variables pour les éléments graphique
    private Button BT_ReponseJoueur1;
    private Button BT_ReponseJoueur2;
    private TextView TX_QuestionJ1;
    private TextView TX_QuestionJ2;
    private TextView TX_ptsJ1;
    private TextView TX_ptsJ2;

    //Créer une instance vide de QuestionManager
    QuestionManager manager;

    //Variable qui contiendra la question en cours
    Question questionEnCours;

    Runnable questionRunnable = null;

    //Variables pour stocker le nom des joueurs et leurs nombre de points
    String J1 = "";
    String J2 = "";
    int ptsJ1 = 0;
    int ptsJ2 = 0;

    /**
     * Création de l'acrivité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fixe l'orientation de l'activité (paysage)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        //Récuperation des éléments graphiques
        BT_ReponseJoueur1 = findViewById(R.id.buttonResponseJ1);
        BT_ReponseJoueur2 = findViewById(R.id.buttonResponseJ2);
        TX_QuestionJ1 = findViewById(R.id.questionJ1);
        TX_QuestionJ2 = findViewById(R.id.questionJ2);
        TX_ptsJ1 = findViewById(R.id.ptsJ1);
        TX_ptsJ2 = findViewById(R.id.ptsJ2);

        //Fixe la couleur des boutons
        BT_ReponseJoueur1.setBackgroundColor(Color.GRAY);
        BT_ReponseJoueur2.setBackgroundColor(Color.GRAY);
        BT_ReponseJoueur1.setEnabled(false);
        BT_ReponseJoueur2.setEnabled(false);

        //remplis l'instance de QuestionManager
        manager = new QuestionManager(this);

        //récuperation des nom des joueurs depuis l'activité qui à lancé cette activité
        Intent intent = getIntent();
        J1 = intent.getStringExtra("joueur1");
        J2 = intent.getStringExtra("joueur2");
    }


    /**
     * Lancement de l'activité
     */
    @Override
    protected void onStart() {
        super.onStart();

        //Handler pour le défilement automatique des questions
        Handler handler = new Handler();

        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if (manager.getQuestionsList().size() == 0) {

                    handler.removeCallbacks(this);

                    Intent intent = new Intent(activity_game.this, activity_result.class);

                    //Envoie les infos des joueurs à l'activité résultat
                    intent.putExtra("nomJ1", J1);
                    intent.putExtra("nomJ2", J2);
                    intent.putExtra("ptsJ1", Integer.toString(ptsJ1));
                    intent.putExtra("ptsJ2", Integer.toString(ptsJ2));

                    //envoie le résulats de la partie à l'activité résultat
                    if (ptsJ1 > ptsJ2) {
                        intent.putExtra("gagnant", "J1");
                    } else if (ptsJ1 < ptsJ2){
                        intent.putExtra("gagnant", "J2");
                    } else {
                        intent.putExtra("gagnant", "egal");
                    }
                    activity_game.this.startActivity(intent);
                } else {
                    //réinitialise les boutons
                    BT_ReponseJoueur1.setEnabled(true);
                    BT_ReponseJoueur2.setEnabled(true);
                    BT_ReponseJoueur1.setBackgroundColor(Color.GRAY);
                    BT_ReponseJoueur2.setBackgroundColor(Color.GRAY);

                    //récupère une question au hasard et l'affiche
                    questionEnCours = manager.getRandomQuestion();
                    TX_QuestionJ1.setText(questionEnCours.getQuestion());
                    TX_QuestionJ2.setText(questionEnCours.getQuestion());

                    //temps entre les questions
                    handler.postDelayed(this, 4000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 3000);


        //Affiche le nom des joueur sur les boutons
        BT_ReponseJoueur1.setText(J1);
        BT_ReponseJoueur2.setText(J2);

        //Faire répondre le joueur 1 au clic de son bouton de réponse
        BT_ReponseJoueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repondre(Joueurs.JOUEUR1);
            }
        });

        //Faire répondre le joueur 2 au clic de son bouton de réponse
        BT_ReponseJoueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repondre(Joueurs.JOUEUR2);
            }
        });
    }

    /**
     * Faire répondre un Joueur
     *
     * @param joueur à faire répondre
     */
    private void repondre(Joueurs joueur) {
        //Réinitialisation de la couleurs de boutons de réponse
        BT_ReponseJoueur1.setBackgroundColor(Color.GRAY);
        BT_ReponseJoueur2.setBackgroundColor(Color.GRAY);

        switch (joueur) {
            //Si c'est le joueur 1 qui répond
            case JOUEUR1:
                //La réponse est : vrai
                if (questionEnCours.getReponse() == 1) {
                    BT_ReponseJoueur1.setBackgroundColor(Color.GREEN);
                    ptsJ1++;
                }

                //La réponse était : faux
                else if (questionEnCours.getReponse() == 0) {
                    BT_ReponseJoueur1.setBackgroundColor(Color.RED);
                    if (ptsJ1 > 0) {
                        ptsJ1--;
                    }
                }
                break;

            //Si c'est le joueur 2 qui répond
            case JOUEUR2:
                //La réponse est : vrai
                if (questionEnCours.getReponse() == 1) {
                    BT_ReponseJoueur2.setBackgroundColor(Color.GREEN);
                    ptsJ2++;
                }

                //La réponse était : faux
                else if (questionEnCours.getReponse() == 0) {
                    BT_ReponseJoueur2.setBackgroundColor(Color.RED);
                    if (ptsJ2 > 0) {
                        ptsJ2--;
                    }
                }
                break;
        }

        //affiche les points des joueurs
        TX_ptsJ1.setText(Integer.toString(ptsJ1));
        TX_ptsJ2.setText(Integer.toString(ptsJ2));

        BT_ReponseJoueur1.setEnabled(false);
        BT_ReponseJoueur2.setEnabled(false);
    }
}

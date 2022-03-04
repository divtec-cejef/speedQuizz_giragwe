package com.giragwe.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activité principale
 */
public class MainActivity extends AppCompatActivity {

    //Déclatation des variables
    private EditText ED_NomJoueur1;
    private EditText ED_NomJoueur2;
    private Button BT_Jouer;

    /**
     * Création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récuperation des éléments graphique
        BT_Jouer = findViewById(R.id.buttonPlay);
        ED_NomJoueur1 = findViewById(R.id.nom_joueur_1);
        ED_NomJoueur2 = findViewById(R.id.nom_joueur_2);

        //Grisé le bouton Jouer
        BT_Jouer.setEnabled(false);
    }

    /**
     * Lancement de l'activité
     */
    @Override
    protected void onStart() {
        super.onStart();

        //Lorsque le texte de la textzone du Joueur 1 est changée
        ED_NomJoueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vérifier si il faut activé le bouton jouer
                enablePlay();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        //Lorsque le texte de la textzone du Joueur 2 est changée
        ED_NomJoueur2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vérifier si il faut activé le bouton jouer
                enablePlay();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //Lorsque le bouton Jouer est pressé
        BT_Jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Afficher l'écran de jeu en lui passant en paramètre les nom des deux joueurs
                Intent intent = new Intent(MainActivity.this, activity_game.class);
                intent.putExtra("joueur1", ED_NomJoueur1.getText().toString());
                intent.putExtra("joueur2", ED_NomJoueur2.getText().toString());
                MainActivity.this.startActivity(intent);
            }
        });
    }

    //Gestion du clic sur les menu de la top bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_parametres:
                //TODO: Écran de paramètres
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    /**
     * Active le bouton Jouer si les deux champs de saisies ne sont pas vides
     */
    private void enablePlay() {
        if(!ED_NomJoueur1.getText().toString().equals("") && !ED_NomJoueur2.getText().toString().equals("")) {
            BT_Jouer.setEnabled(true);
        } else {
            BT_Jouer.setEnabled(false);
        }
    }
}
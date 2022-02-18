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

public class MainActivity extends AppCompatActivity {

    private EditText ED_NomJoueur1;
    private EditText ED_NomJoueur2;
    private Button BT_Jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BT_Jouer = findViewById(R.id.buttonPlay);
        ED_NomJoueur1 = findViewById(R.id.nom_joueur_1);
        ED_NomJoueur2 = findViewById(R.id.nom_joueur_2);

        BT_Jouer.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ED_NomJoueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                enablePlay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ED_NomJoueur2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                enablePlay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        BT_Jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_game.class);
                intent.putExtra("joueur1", ED_NomJoueur1.getText().toString());
                intent.putExtra("joueur2", ED_NomJoueur2.getText().toString());
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public boolean onCreateOptonsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_parametres:
                Intent intent = new Intent(MainActivity.this, activityParams.class);
                MainActivity.this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void enablePlay() {
        if(!ED_NomJoueur1.getText().toString().equals("") && !ED_NomJoueur2.getText().toString().equals("")) {
            BT_Jouer.setEnabled(true);
        } else {
            BT_Jouer.setEnabled(false);
        }
    }
}
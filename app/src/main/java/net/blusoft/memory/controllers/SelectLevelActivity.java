package net.blusoft.memory.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.blusoft.memory.R;
import net.blusoft.memory.model.DataBase;
import net.blusoft.memory.model.ObjecteOpcions;

public class SelectLevelActivity extends AppCompatActivity implements View.OnClickListener {

    private Button easy;
    private Button medium;
    private Button hard;
    private Button highScores;
    private ObjecteOpcions opcions = new ObjecteOpcions();
    public final static int CARTES_FACIL = 8;
    public final static int CARTES_MEDIUM = 12;
    public final static int CARTES_HARD = 16;
    final static int TEMPS_MILLIS_FACIL = 45000;
    final static int TEMPS_MILLIS_MEDIUM = 45000;
    final static int TEMPS_MILLIS_HARD = 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);
        highScores = (Button) findViewById(R.id.highScores);


        easy.setOnClickListener(this);
        medium.setOnClickListener(this);
        hard.setOnClickListener(this);
        highScores.setOnClickListener(this);
        DataBase db = new DataBase(this);
        db.inicializarDB();
    }



    @Override
    public void onClick(View v) {
        Intent jugar = new Intent(SelectLevelActivity.this, MainActivity.class);
        if (v.getId() == R.id.easy) {
            //Lanzamos el nivel dificil.
            opcions = new ObjecteOpcions(CARTES_FACIL, TEMPS_MILLIS_FACIL);
            jugar.putExtra("valors", opcions);
            startActivity(jugar);
            System.exit(0);
        }
        if (v.getId() == R.id.medium) {
            //Lanzamos el nivel dificil.
            opcions = new ObjecteOpcions(CARTES_MEDIUM, TEMPS_MILLIS_MEDIUM);
            jugar.putExtra("valors", opcions);
            startActivity(jugar);
            System.exit(0);
        }
        if (v.getId() == R.id.hard) {
            //Lanzamos el nivel dificil.
            opcions = new ObjecteOpcions(CARTES_HARD, TEMPS_MILLIS_HARD);
            jugar.putExtra("valors", opcions);
            startActivity(jugar);
            System.exit(0);
        }
        if (v.getId() == R.id.highScores) {
            Intent highScores = new Intent(SelectLevelActivity.this, HighScoresActivity.class);
            startActivity(highScores);
        }

    }
}

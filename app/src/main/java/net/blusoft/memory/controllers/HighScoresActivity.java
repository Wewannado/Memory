package net.blusoft.memory.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.blusoft.memory.R;
import net.blusoft.memory.model.DataBase;
import net.blusoft.memory.model.ObjecteOpcions;


public class HighScoresActivity extends AppCompatActivity {
    TextView puntuacioEasy1;
    TextView puntuacioEasy2;
    TextView puntuacioEasy3;
    TextView puntuacioMedium1;
    TextView puntuacioMedium2;
    TextView puntuacioMedium3;
    TextView puntuacioHard1;
    TextView puntuacioHard2;
    TextView puntuacioHard3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        inicialitzarTextViews();
        omplirTextViews();


    }

    private void omplirTextViews() {
        DataBase db = new DataBase(this);
        if (db.moveToFirst()) {
            puntuacioEasy1.append(db.getNom(ObjecteOpcions.EASY, 1) + "  -  " + db.getPuntuacio(ObjecteOpcions.EASY, 1));
            puntuacioEasy2.append(db.getNom(ObjecteOpcions.EASY, 2) + "  -  " + db.getPuntuacio(ObjecteOpcions.EASY, 2));
            puntuacioEasy3.append(db.getNom(ObjecteOpcions.EASY, 3) + "  -  " + db.getPuntuacio(ObjecteOpcions.EASY, 3));

            puntuacioMedium1.append(db.getNom(ObjecteOpcions.MEDIUM, 1) + "  -  " + db.getPuntuacio(ObjecteOpcions.MEDIUM, 1));
            puntuacioMedium2.append(db.getNom(ObjecteOpcions.MEDIUM, 2) + "  -  " + db.getPuntuacio(ObjecteOpcions.MEDIUM, 2));
            puntuacioMedium3.append(db.getNom(ObjecteOpcions.MEDIUM, 3) + "  -  " + db.getPuntuacio(ObjecteOpcions.MEDIUM, 3));

            puntuacioHard1.append(db.getNom(ObjecteOpcions.HARD, 1) + "  -  " + db.getPuntuacio(ObjecteOpcions.HARD, 1));
            puntuacioHard2.append(db.getNom(ObjecteOpcions.HARD, 2) + "  -  " + db.getPuntuacio(ObjecteOpcions.HARD, 2));
            puntuacioHard3.append(db.getNom(ObjecteOpcions.HARD, 3) + "  -  " + db.getPuntuacio(ObjecteOpcions.HARD, 3));
        }
    }

    private void inicialitzarTextViews() {
        puntuacioEasy1 = (TextView) findViewById(R.id.score1Easy);
        puntuacioEasy2 = (TextView) findViewById(R.id.score2Easy);
        puntuacioEasy3 = (TextView) findViewById(R.id.score3Easy);

        puntuacioMedium1 = (TextView) findViewById(R.id.score1Medium);
        puntuacioMedium2 = (TextView) findViewById(R.id.score2Medium);
        puntuacioMedium3 = (TextView) findViewById(R.id.score3Medium);

        puntuacioHard1 = (TextView) findViewById(R.id.score1Hard);
        puntuacioHard2 = (TextView) findViewById(R.id.score2Hard);
        puntuacioHard3 = (TextView) findViewById(R.id.score3Hard);
    }
}

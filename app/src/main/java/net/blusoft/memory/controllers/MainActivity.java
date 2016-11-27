package net.blusoft.memory.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import net.blusoft.memory.R;
import net.blusoft.memory.model.DataBase;
import net.blusoft.memory.model.ObjecteOpcions;
import net.blusoft.memory.model.Partida;

public class MainActivity extends Activity {

    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private TextView errors;
    private ObjecteOpcions opcions;
    private DataBase db;

    public Partida getPartida() {
        return partida;
    }


    @Override
    protected void onStop() {
        super.onStop();
        partida.pausarCrono();
        //Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent selectNivell = new Intent(MainActivity.this, SelectLevelActivity.class);
        startActivity(selectNivell);
        System.exit(0);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        partida.reiniciarCrono();
        //Toast.makeText(this, "OnRestart", Toast.LENGTH_SHORT).show();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
        gv = (GridView) findViewById(R.id.gridViewMemory);
        if (savedInstanceState == null) {
            Intent extras = getIntent();
            opcions = (ObjecteOpcions) extras.getSerializableExtra("valors");
        }

        this.partida = new Partida(opcions, this);
        adapter = new ImageAdapter(this, partida);
        GeneralListener listener = new GeneralListener(this);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(listener);

        db = new DataBase(this);

    }

    /**
     * Refreshes the Gridview. Also checks if it's the end of the game
     */
    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
        errors = (TextView) findViewById(R.id.textErrors);
        errors.setText(getString(R.string.errors,partida.getErrors()));
        if (partida.esFiPartida()) {
            partida.pausarCrono();
            fiPartida(partida.getPuntuacio());
        }
    }


    /**
     * Adds 1 to the count of mistakes in the current game.
     */
    public void sumaError() {
        partida.sumaError();
    }


    /**
     * Creates a Dialog indicating that the time limit is reached, and asks the user if he wants to restart the level
     * or exit the APP
     */
    public void fiPartida() {
        final Intent jugar = new Intent(this, MainActivity.class);
        jugar.putExtra("valors", opcions);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.endGameMessageTimeLimit).setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(jugar);
                System.exit(0);
            }
        }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setCancelable(false).show();
    }

    /**
     * Creates a Dialog showing the score of the game and asks the user if he wants to restart the level
     * or exit the APP.
     */
    public void fiPartida(final long puntuacio) {
        if (db.isHighScore(partida.getPuntuacio(), partida.getDificultat())) {
            System.out.println("Puntuacio record" + partida.getPuntuacio());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final LayoutInflater inflater = this.getLayoutInflater();
            final View promptsView = inflater.inflate(R.layout.dialog_high_score, null);
            builder.setView(promptsView)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            final EditText userInput = (EditText) promptsView.findViewById(R.id.username);
                            db.inserirPuntuacio(userInput.getText().toString(), puntuacio, partida.getDificultat());
                            dialogReiniciar(puntuacio);
                        }
                    }).setCancelable(false).show();
        } else {
            System.out.println("No es puntuacio record");
            dialogReiniciar(puntuacio);
        }

    }

    public void dialogReiniciar(long puntuacio) {
        final Intent jugar = new Intent(this, MainActivity.class);
        jugar.putExtra("valors", opcions);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.pointMessageNoHighScore, puntuacio)).setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(jugar);
                System.exit(0);
            }
        }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setCancelable(false).show();
    }
}



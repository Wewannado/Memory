package com.exemple.profedam.memory.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.profedam.memory.R;
import com.exemple.profedam.memory.model.ObjecteOpcions;
import com.exemple.profedam.memory.model.Partida;

public class MainActivity extends Activity {

    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private TextView errors;
    private ObjecteOpcions opcions;


    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    protected void onStop() {
        super.onStop();
        partida.pausarCrono();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent selectNivell = new Intent(MainActivity.this, SelectLevelActivity.class);
        startActivity(selectNivell);
        System.exit(0);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        partida.reiniciarCrono();
        Toast.makeText(this, "OnRestart", Toast.LENGTH_SHORT).show();
        //TODO Posar el cronometre als segons adecuats
    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
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


    }


    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
        errors = (TextView) findViewById(R.id.textErrors);
        errors.setText("Errors comesos: " + partida.getErrors());
        if (partida.esFiPartida()) {
            partida.pausarCrono();
            if (false) {
                //TODO felicitats i tota la pesca.
            }
            reiniciarPartida();
        }
    }


    public void sumaError() {
        partida.sumaError();
    }

    public void reiniciarPartida() {
        final Intent intent = new Intent(this, MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Fi de la partida. La teva puntuacio ha estat de: " + partida.getPuntuacio()).setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
                System.exit(0);
            }
        }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setCancelable(false).show();
    }
}



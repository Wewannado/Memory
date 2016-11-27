package net.blusoft.memory.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import net.blusoft.memory.R;
import net.blusoft.memory.model.ObjecteOpcions;
import net.blusoft.memory.model.Partida;

public class MainActivity extends Activity {

    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private TextView errors;
    private ObjecteOpcions opcions;

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


    }


    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
        errors = (TextView) findViewById(R.id.textErrors);
        errors.setText(getString(R.string.errors,partida.getErrors()));
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
        final Intent jugar = new Intent(this, MainActivity.class);
        jugar.putExtra("valors", opcions);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.pointMessage,partida.getPuntuacio())).setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
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
}



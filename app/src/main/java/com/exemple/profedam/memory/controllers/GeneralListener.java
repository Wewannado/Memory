package com.exemple.profedam.memory.controllers;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.exemple.profedam.memory.model.Carta;


/**
 * Created by ALUMNEDAM on 02/02/2016.
 */
public class GeneralListener implements AdapterView.OnItemClickListener, Runnable {

    private MainActivity tauler;
    private Carta cartaOnClick;
    private Carta cartaOnClick2;
    private int cartesGiradesTorn = 0;
    private boolean isActive = true;




    public GeneralListener(MainActivity tauler) {
        this.tauler = tauler;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (tauler.getPartida().getLlistaCartes().get(position).getEstat() == Carta.Estat.BACK && (isActive)) {
                if (cartesGiradesTorn == 0) {
                    cartaOnClick = tauler.getPartida().getLlistaCartes().get(position);
                    cartaOnClick.girar();
                    tauler.refrescarTablero();
                    cartesGiradesTorn++;
                } else {
                    isActive = false;
                    cartaOnClick2 = tauler.getPartida().getLlistaCartes().get(position);
                    cartaOnClick2.girar();
                    tauler.refrescarTablero();
                    cartesGiradesTorn = 0;
                    if (cartaOnClick.getFrontImage() == cartaOnClick2.getFrontImage()) {
                        Handler delay = new Handler();
                        delay.postDelayed(this, 250);
                    } else {
                        tauler.sumaError();
                        Handler delay = new Handler();
                        delay.postDelayed(this, 1000);
                    }
                }
            }
        }

    @Override
    public void run() {
        if (cartaOnClick.getFrontImage() == cartaOnClick2.getFrontImage()) {
            cartaOnClick.bloqueja();
            cartaOnClick2.bloqueja();
        }
        else {
            cartaOnClick.girar();
            cartaOnClick2.girar();
        }
        tauler.refrescarTablero();
        isActive = true;
    }
}



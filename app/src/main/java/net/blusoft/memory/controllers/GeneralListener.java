package net.blusoft.memory.controllers;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import net.blusoft.memory.model.Carta;
import net.blusoft.memory.model.Torn;


class GeneralListener implements AdapterView.OnItemClickListener{

    private MainActivity tauler;
    private Carta cartaOnClick;
    private Torn torn;


    GeneralListener(MainActivity tauler) {
        this.tauler = tauler;
        torn = new Torn(tauler);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        cartaOnClick = tauler.getPartida().getLlistaCartes().get(position);
        torn.putCarta(cartaOnClick);
    }
}



package net.blusoft.memory.model;

import android.os.Handler;

import net.blusoft.memory.controllers.MainActivity;

public class Torn implements Runnable {
    private MainActivity tauler;
    private int cartesGiradesTorn = 0;
    private Carta carta1 = null;
    private Carta carta2 = null;
    private boolean isActive = true;
    private EstatTorn estat;

    private enum EstatTorn {OK, ERROR}

    public Torn(MainActivity tauler) {
        this.tauler = tauler;
    }

    public void putCarta(Carta carta) {
        if (carta.getEstat() == Carta.Estat.BACK && (isActive)) {
            if (cartesGiradesTorn == 0) {
                carta1 = carta;
                carta1.girar();
                cartesGiradesTorn++;
            } else {
                isActive = false;
                carta2 = carta;
                carta2.girar();
                cartesGiradesTorn = 0;
                if (carta1.getFrontImage() == carta2.getFrontImage()) {
                    estat = EstatTorn.OK;
                    Handler delay = new Handler();
                    delay.postDelayed(this, 250);
                } else {
                    tauler.sumaError();
                    estat = EstatTorn.ERROR;
                    Handler delay = new Handler();
                    delay.postDelayed(this, 1000);
                }
            }
            tauler.refrescarTablero();
        }
    }

    @Override
    public void run() {
        if (estat == EstatTorn.OK) {
            carta1.bloqueja();
            carta2.bloqueja();
        } else {
            carta1.girar();
            carta2.girar();
        }
        tauler.refrescarTablero();
        isActive = true;
    }


    public boolean isActive() {
        return isActive;
    }

    public void release() {
        isActive = true;
    }
}

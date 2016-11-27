package net.blusoft.memory.model;

import net.blusoft.memory.R;
import net.blusoft.memory.controllers.Cronometro;
import net.blusoft.memory.controllers.MainActivity;
import net.blusoft.memory.controllers.SelectLevelActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Partida {

    private int totalCartes[] = {
            R.drawable.c0, R.drawable.c1,
            R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7,
            R.drawable.c8, R.drawable.c9,
            R.drawable.c10, R.drawable.c11
    };
    private ArrayList<Carta> llistaCartes;
    private int numeroCartes;
    private boolean finalized = false;
    private int comptadorErrors = 0;
    private static final int NUM_CARTES_BIBLIOTECA = 12;
    private Cronometro cronometro;
    private MainActivity tauler;


    public Partida(ObjecteOpcions opcions, MainActivity tauler) {
        this.tauler = tauler;
        this.numeroCartes = opcions.getNumCartes();
        llistaCartes = new ArrayList<>();
        omplirLlistaCartes();
        cronometro = new Cronometro(opcions.getDificultat(), 1000, tauler);
        cronometro.start();
    }

    private void omplirLlistaCartes() {
        int[] aleatoris = arrayDesordenat();
        int pos = 0;
        for (int contador = 0; contador < getNumeroCartes() / 2; contador++) {
            llistaCartes.add(new Carta(totalCartes[aleatoris[pos]]));
            llistaCartes.add(new Carta(totalCartes[aleatoris[pos++]]));
        }
        Collections.shuffle(llistaCartes);
    }

    private int[] arrayDesordenat() {
        int[] aleatoris = new int[NUM_CARTES_BIBLIOTECA];
        boolean[] aux = new boolean[NUM_CARTES_BIBLIOTECA];
        int num;
        Random rand = new Random();
        for (int i = 0; i < NUM_CARTES_BIBLIOTECA; ) {
            num = rand.nextInt(NUM_CARTES_BIBLIOTECA);
            if (!aux[num]) {
                aleatoris[i] = num;
                aux[num] = true;
                i++;
            }
        }
        return aleatoris;
    }

    public ArrayList<Carta> getLlistaCartes() {
        return llistaCartes;
    }

    public int getNumeroCartes() {
        return numeroCartes;
    }

    public boolean esFiPartida() {
        finalized = true;
        for (Carta carta : llistaCartes) {
            if (carta.getEstat() != Carta.Estat.FIXED) {
                finalized = false;
                break;
            }
        }
        return finalized;
    }

    public void sumaError() {
        this.comptadorErrors++;
    }

    public int getErrors() {
        return comptadorErrors;
    }

    public long getPuntuacio() {
        return (long) ((cronometro.getSecondsLeft() * 100 - getErrors() * 10) * numeroCartes);
    }

    public void pausarCrono() {
        cronometro.pausar();
    }

    public void reiniciarCrono() {
        //Tot i que tecnicament no el reiniciem, ja que el que fem es reescriure l'objecte
        cronometro = new Cronometro(cronometro.getSecondsLeft() * 1000, 1000, tauler);
        cronometro.start();
    }

    public int getDificultat() {
        int result = 0;
        switch (numeroCartes) {
            case SelectLevelActivity.CARTES_FACIL:
                result = 1;
                break;
            case SelectLevelActivity.CARTES_MEDIUM:
                result = 2;
                break;
            case SelectLevelActivity.CARTES_HARD:
                result = 3;
                break;
        }
        return result;
    }
}

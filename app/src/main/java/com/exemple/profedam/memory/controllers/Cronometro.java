package com.exemple.profedam.memory.controllers;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.widget.TextView;
import com.exemple.profedam.memory.R;


/**
 * Created by IES on 20/11/2016.
 */

public class Cronometro extends CountDownTimer{
private MainActivity tauler;
    private TextView textView;
    private int secondsLeft;
    public Cronometro( long millisInFuture, long countDownInterval, MainActivity tauler)
    {
        super(millisInFuture, countDownInterval);
        this.tauler = tauler;
        textView = (TextView)tauler.findViewById(R.id.textTimeLeft);
    }

    @Override
    public void onTick(long millisUntilFinished) {

        this.secondsLeft= ((int) (millisUntilFinished / 1000));
        textView.setText("Tiempo restante:" +secondsLeft);
    }

    @Override
    public void onFinish() {
    this.onTick(0);
        textView.setText("listo");
        //AlertDialog.Builder builder = new AlertDialog.Builder(tauler);
        tauler.reiniciarPartida();
    }

    public void pausar(){
        this.secondsLeft=getSecondsLeft();
        cancel();
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}


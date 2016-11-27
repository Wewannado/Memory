package net.blusoft.memory.controllers;


import android.os.CountDownTimer;
import android.widget.TextView;
import net.blusoft.memory.R;



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
        textView.setText(tauler.getString(R.string.timeLeft,secondsLeft));
    }

    @Override
    public void onFinish() {
    this.onTick(0);
        textView.setText(R.string.endTime);
        //AlertDialog.Builder builder = new AlertDialog.Builder(tauler);
        tauler.fiPartida();
    }

    public void pausar(){
        this.secondsLeft=getSecondsLeft();
        cancel();
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}


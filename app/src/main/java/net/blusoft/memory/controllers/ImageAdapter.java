package net.blusoft.memory.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import net.blusoft.memory.model.Partida;

class ImageAdapter extends BaseAdapter {

    private Activity mainActivity;
    private Partida partida;
    private final int ALTURARESOURCECARTA = 72;
    private final int AMPLERESOURCECARTA = 98;
    private final int NUMCOLUMNESGRIDVIEW =4;
    private int ampladaCarta;
    private int alturaCarta;


    ImageAdapter(Activity c, Partida p) {

        this.mainActivity = c;
        this.partida = p;
        calculaTamanyCartes();
    }

    private int getPantallaWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mainActivity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void calculaTamanyCartes() {
        int pantallaWidth = getPantallaWidth();
        this.ampladaCarta = pantallaWidth / NUMCOLUMNESGRIDVIEW;
        this.alturaCarta = AMPLERESOURCECARTA * ampladaCarta / ALTURARESOURCECARTA;
    }

    public int getCount() {
        /* Devuelve el número de cartas a cargar o el número
        de elementos que tiene que mostrar el gridView
         */
        return partida.getNumeroCartes();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mainActivity.getApplicationContext());
            imageView.setLayoutParams(new GridView.LayoutParams(ampladaCarta, alturaCarta));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(partida.getLlistaCartes().get(position).getActive());
        } else {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }


}

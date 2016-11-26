package com.exemple.profedam.memory.controllers;

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
import android.widget.Spinner;

import com.exemple.profedam.memory.R;
import com.exemple.profedam.memory.model.Partida;

public class ImageAdapter extends BaseAdapter {

    private Activity mainActivity;
    private int numColumnas;
    private Partida partida;
    private final int ALTURARESOURCECARTA = 72;
    private final int AMPLERESOURCECARTA = 98;
    private int ampladaCarta;
    private int alturaCarta;
    private int PantallaWidth;


    public ImageAdapter(Activity c, Partida p) {

        this.mainActivity = c;
        this.partida = p;
        calculaTamanyCartes();
    }

    public int getPantallaWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mainActivity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void calculaTamanyCartes() {
        int pantallaWidth = getPantallaWidth();
        GridView gridView = (GridView) mainActivity.findViewById(R.id.gridViewMemory);
        //TODO el 4 s'ha de cambiar per el tamany del gridview gridView.getNumColumns()
        int aux = pantallaWidth * 10 / 100;
        int aux2 = gridView.getNumColumns();
        this.ampladaCarta = pantallaWidth / 4;
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
            //TODO modificar el tamany de les cartes depenent de l'ample de la pantalla.

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

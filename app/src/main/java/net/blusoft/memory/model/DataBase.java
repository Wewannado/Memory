package net.blusoft.memory.model;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by IES on 27/11/2016.
 */

public class DataBase {

    Context context;
    SQLiteDatabase db;
    Cursor c;
    final int NUM_REGISTRES_PER_NIVELL = 3;

    public DataBase(Context context) {
        this.context = context;
        dbSQLLiteHelper hsdb = new dbSQLLiteHelper(context, "highScores", null, 6);
        db = hsdb.getWritableDatabase();
        c = db.rawQuery("SELECT nom, puntuacio FROM puntuacions", null);
    }

    public void inicializarDB() {
        if (db != null) {
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Mike',25000,0)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Lucas',22000,1)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Sara',15000,2)");

            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Luna',25000,3)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Spike',21000,4)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Denia',18000,5)");

            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('George',43000,6)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Mary',38500,7)");
            db.execSQL("INSERT INTO puntuacions (nom, puntuacio, posicio) VALUES ('Marga',33000,8)");
            db.close();
        }
    }

    public boolean isHighScore(long puntuacio, int nivell) {
        c = db.rawQuery("SELECT puntuacio FROM puntuacions", null);
        c.moveToFirst();
        boolean resultat = false;
        switch (nivell) {
            case 1:
                for (int i = 0; i < NUM_REGISTRES_PER_NIVELL; i++) {
                    if (c.getInt(0) < puntuacio) {
                        resultat = true;
                        break;
                    }
                    c.moveToNext();
                }
                break;
            case 2:
                c.moveToPosition(3);
                for (int i = NUM_REGISTRES_PER_NIVELL; i < (NUM_REGISTRES_PER_NIVELL * nivell); i++) {
                    if (c.getInt(0) < puntuacio) {
                        resultat = true;
                        break;
                    }
                    c.moveToNext();
                }
                break;
            case 3:
                c.moveToPosition(6);
                for (int i = NUM_REGISTRES_PER_NIVELL * (nivell - 1); i < (NUM_REGISTRES_PER_NIVELL * nivell); i++) {
                    if (c.getInt(0) < puntuacio) {
                        resultat = true;
                        break;
                    }
                    c.moveToNext();
                }
                break;
        }
        return resultat;
    }


    public void inserirPuntuacio(String nom, long puntuacio, int dificultat) {
        ObjectePuntuacio puntuacio1;
        ObjectePuntuacio puntuacio2;
        ObjectePuntuacio puntuacio3;
        c = db.rawQuery("SELECT nom, puntuacio, posicio FROM puntuacions", null);
        c.moveToFirst();
        c.moveToPosition((dificultat - 1) * NUM_REGISTRES_PER_NIVELL);
        String sql;
        boolean inserit = false;
        if (c.getLong(1) < puntuacio) {
            puntuacio2 = new ObjectePuntuacio(c.getString(0), c.getLong(1), c.getInt(2) + 1);
            puntuacio1 = new ObjectePuntuacio(DatabaseUtils.sqlEscapeString(nom), puntuacio, (dificultat - 1) * NUM_REGISTRES_PER_NIVELL);
            c.moveToNext();
            puntuacio3 = new ObjectePuntuacio(c.getString(0), c.getLong(1), c.getInt(2) + 1);
            update(puntuacio1, puntuacio2, puntuacio3);
            inserit = true;
        }
        c.moveToNext();
        if (c.getLong(1) < puntuacio && !inserit) {
            puntuacio1 = new ObjectePuntuacio(DatabaseUtils.sqlEscapeString(nom), puntuacio, ((dificultat - 1) * NUM_REGISTRES_PER_NIVELL) + 1);
            puntuacio2 = new ObjectePuntuacio(c.getString(0), c.getLong(1), c.getInt(2) + 1);
            update(puntuacio1, puntuacio2);
            inserit = true;
        }
        c.moveToNext();
        if (c.getLong(1) < puntuacio && !inserit) {
            puntuacio1 = new ObjectePuntuacio(DatabaseUtils.sqlEscapeString(nom), puntuacio, ((dificultat - 1) * NUM_REGISTRES_PER_NIVELL) + 2);
            update(puntuacio1);
        }
    }

    private void update(ObjectePuntuacio puntuacio1, ObjectePuntuacio puntuacio2, ObjectePuntuacio puntuacio3) {
        String sql = "UPDATE puntuacions SET nom=" + puntuacio1.getNom() +
                " , puntuacio='" + puntuacio1.getPuntuacio() +
                "' WHERE posicio=" + puntuacio1.getPosicio();
        db.execSQL(sql);

        sql = "UPDATE puntuacions SET nom='" + puntuacio2.getNom() +
                "' , puntuacio='" + puntuacio2.getPuntuacio() +
                "' WHERE posicio=" + puntuacio2.getPosicio();
        db.execSQL(sql);

        sql = "UPDATE puntuacions SET nom='" + puntuacio3.getNom() +
                "' , puntuacio='" + puntuacio3.getPuntuacio() +
                "' WHERE posicio=" + puntuacio3.getPosicio();
        db.execSQL(sql);
    }

    private void update(ObjectePuntuacio puntuacio1, ObjectePuntuacio puntuacio2) {
        String sql = "UPDATE puntuacions SET nom=" + puntuacio1.getNom() +
                " , puntuacio='" + puntuacio1.getPuntuacio() +
                "' WHERE posicio=" + puntuacio1.getPosicio();
        db.execSQL(sql);

        sql = "UPDATE puntuacions SET nom='" + puntuacio2.getNom() +
                "' , puntuacio='" + puntuacio2.getPuntuacio() +
                "' WHERE posicio=" + puntuacio2.getPosicio();
        db.execSQL(sql);
    }

    private void update(ObjectePuntuacio puntuacio1) {
        String sql = "UPDATE puntuacions SET nom=" + puntuacio1.getNom() +
                " , puntuacio='" + puntuacio1.getPuntuacio() +
                "' WHERE posicio=" + puntuacio1.getPosicio();
        db.execSQL(sql);
    }

    public String getNom(int dificultat, int posicio) {
        c = db.rawQuery("SELECT nom FROM puntuacions", null);
        c.moveToPosition((dificultat - 1) * 3);
        String nom = "";
        switch (posicio) {
            case 1:
                nom = c.getString(0);
                break;
            case 2:
                c.moveToNext();
                nom = c.getString(0);
                break;
            case 3:
                c.moveToNext();
                c.moveToNext();
                nom = c.getString(0);
                break;
        }
        return nom;
    }

    public int getPuntuacio(int dificultat, int posicio) {
        c = db.rawQuery("SELECT puntuacio FROM puntuacions", null);
        c.moveToPosition((dificultat - 1) * 3);
        int puntuacio = 0;
        switch (posicio) {
            case 1:
                puntuacio = c.getInt(0);
                break;
            case 2:
                c.moveToNext();
                puntuacio = c.getInt(0);
                break;
            case 3:
                c.moveToNext();
                c.moveToNext();
                puntuacio = c.getInt(0);
                break;
        }
        return puntuacio;
    }


    public boolean moveToFirst() {
        return c.moveToFirst();
    }
}

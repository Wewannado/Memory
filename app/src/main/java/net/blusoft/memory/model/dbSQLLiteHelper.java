package net.blusoft.memory.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IES on 27/11/2016.
 */

public class dbSQLLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE puntuacions (nom TEXT, puntuacio INTEGER, posicio INTEGER)";

    public dbSQLLiteHelper(Context contexto, String nombre,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS puntuacions");
        db.execSQL(sqlCreate);
    }
}


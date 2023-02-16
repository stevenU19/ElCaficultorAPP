package com.cdp.elcaficultorapp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "elCaficultorAPP.db";
    public static final String TABLE_REGISTRO = "t_registro";

    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTRO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fechaInicio TEXT," +
                "fechaFinal TEXT," +
                "pesoRecoleccion FLOAT," +
                "estadoCafe TEXT," +
                "fechaVenta TEXT," +
                "pesoVenta FLOAT," +
                "precioKG FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_REGISTRO);
        onCreate(sqLiteDatabase);
    }


}

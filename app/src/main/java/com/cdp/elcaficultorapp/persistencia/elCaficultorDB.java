package com.cdp.elcaficultorapp.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.elcaficultorapp.entidades.Registro;

import java.util.ArrayList;

public class elCaficultorDB extends dbHelper {

    Context context;

    public elCaficultorDB(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //------------------ IMPLEMENTACIÓN DEL CRUD BÁSICO ------------------
    public long insertarRegistro(String fechaInicio, String fechaFinal, int pesoRecoleccion,  String estadoCafe, String fechaVenta, int pesoVenta, int precioKG) {
        long id = 0;
        try {
            dbHelper dbHelper = new dbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("fechaInicio", fechaInicio);
            values.put("fechaFinal", fechaFinal);
            values.put("pesoRecoleccion", pesoRecoleccion);
            values.put("estadoCafe", estadoCafe);
            values.put("fechaVenta", fechaVenta);
            values.put("pesoVenta", pesoVenta);
            values.put("precioKG", precioKG);

            id = db.insert(TABLE_REGISTRO, null, values);
        }catch(Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Registro> mostrarRegistros() {

        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Registro> listaRegistros = new ArrayList<>();
        Registro registro;
        Cursor cursorRegistros;

        cursorRegistros = db.rawQuery("SELECT * FROM " + TABLE_REGISTRO + " ORDER BY id ASC", null);

        if (cursorRegistros.moveToFirst()) {
            do {
                registro = new Registro();
                registro.setId(cursorRegistros.getInt(0));
                registro.setFechaInicio(cursorRegistros.getString(1));
                registro.setFechaFinal(cursorRegistros.getString(2));
                registro.setPesoRecoleccion(cursorRegistros.getInt(3));
                registro.setEstadoCafe(cursorRegistros.getString(4));
                registro.setFechaVenta(cursorRegistros.getString(5));
                registro.setPesoVenta(cursorRegistros.getInt(6));
                registro.setPrecioKG(cursorRegistros.getInt(7));
                listaRegistros.add(registro);
            } while (cursorRegistros.moveToNext());
        }
        cursorRegistros.close();
        return listaRegistros;
    }

    // ------------------ Obtener un Registro ------------------
    public Registro obtenerRegistro(int id) {

        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Registro registro = null;
        Cursor cursorRegistros;

        cursorRegistros = db.rawQuery("SELECT * FROM " + TABLE_REGISTRO + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorRegistros.moveToFirst()) {
            registro = new Registro();
            registro.setId(cursorRegistros.getInt(0));
            registro.setFechaInicio(cursorRegistros.getString(1));
            registro.setFechaFinal(cursorRegistros.getString(2));
            registro.setPesoRecoleccion(cursorRegistros.getInt(3));
            registro.setEstadoCafe(cursorRegistros.getString(4));
            registro.setFechaVenta(cursorRegistros.getString(5));
            registro.setPesoVenta(cursorRegistros.getInt(6));
            registro.setPrecioKG(cursorRegistros.getInt(7));
        }
        cursorRegistros.close();
        return registro;
    }

    // ------------------ Actualizar o editar un Registro ------------------
    public boolean actualizarRegistro(int id, String fechaInicio, String fechaFinal, float pesoRecoleccion,  String estadoCafe, String fechaVenta, float pesoVenta, float precioKG) {
        boolean correcto = false;
        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_REGISTRO + " SET fechaInicio='"+fechaInicio+"', fechaFinal='"+fechaFinal+"', pesoKG_recoleccion='"+pesoRecoleccion+"'," +
                    "estadoCafe='"+estadoCafe+"', fechaVenta='"+fechaVenta+"', pesoKG_venta='"+pesoVenta+"', precioKG='"+precioKG+"' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    // ------------------ Eliminar un Registro ------------------
    public boolean eliminarRegistro(int id) {
        boolean correcto = false;

        dbHelper dbHelper = new dbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_REGISTRO + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}

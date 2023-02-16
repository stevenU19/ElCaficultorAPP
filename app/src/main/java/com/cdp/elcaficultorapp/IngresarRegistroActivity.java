package com.cdp.elcaficultorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.elcaficultorapp.persistencia.elCaficultorDB;

public class IngresarRegistroActivity extends AppCompatActivity {

    EditText txtFechaInicio, txtFechaFinal, txtPesoRecoleccion, txtEstadoCafe, txtFechaVenta, txtPesoVenta, txtPrecioKG;
    Button btnGuardar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_registro);

        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFinal = findViewById(R.id.txtFechaFinal);
        txtPesoRecoleccion = findViewById(R.id.txtPesoRecoleccion);
        txtEstadoCafe = findViewById(R.id.txtEstadoCafe);
        txtFechaVenta = findViewById(R.id.txtFechaVenta);
        txtPesoVenta = findViewById(R.id.txtPesoVenta);
        txtPrecioKG = findViewById(R.id.txtPrecioKG);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSalir = findViewById(R.id.btnSalir);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtFechaInicio.getText().toString().equals("") && !txtFechaFinal.getText().toString().equals("")) {
                    elCaficultorDB db = new elCaficultorDB(IngresarRegistroActivity.this);
                    long id = db.insertarRegistro(txtFechaInicio.getText().toString(), txtFechaFinal.getText().toString(), Integer.valueOf(txtPesoRecoleccion.getText().toString()), txtEstadoCafe.getText().toString(), txtFechaVenta.getText().toString(), Integer.valueOf(txtPesoVenta.getText().toString()), Integer.valueOf(txtPrecioKG.getText().toString()));
                    if (id > 0) {
                        Toast.makeText(IngresarRegistroActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(IngresarRegistroActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(IngresarRegistroActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
                salir();
            }
        });
    }

    private void limpiar() {
        txtFechaInicio.setText("");
        txtFechaFinal.setText("");
        txtPesoRecoleccion.setText("");
        txtEstadoCafe.setText("");
        txtFechaVenta.setText("");
        txtPesoVenta.setText("");
        txtPrecioKG.setText("");
    }

    private void salir(){
        Intent intent = new Intent(this, PaginaPrincipalActivity.class);
        startActivity(intent);
    }
}
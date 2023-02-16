package com.cdp.elcaficultorapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.cdp.elcaficultorapp.entidades.Registro;
import com.cdp.elcaficultorapp.persistencia.elCaficultorDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarRegistroActivity extends AppCompatActivity {

    EditText txtFechaInicio, txtFechaFinal, txtPesoRecoleccion, txtEstadoCafe, txtFechaVenta, txtPesoVenta, txtPrecioKG;
    Button btnGuardar, btnEditar, btnEliminar;
    //FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Registro registro;
    int id = 0;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFinal = findViewById(R.id.txtFechaFinal);
        txtPesoRecoleccion = findViewById(R.id.txtPesoRecoleccion);
        txtEstadoCafe = findViewById(R.id.txtEstadoCafe);
        txtFechaVenta = findViewById(R.id.txtFechaVenta);
        txtPesoVenta = findViewById(R.id.txtPesoVenta);
        txtPrecioKG = findViewById(R.id.txtPrecioKG);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final elCaficultorDB db = new elCaficultorDB(EditarRegistroActivity.this);
        registro = db.obtenerRegistro(id);

        if (registro != null) {
            txtFechaInicio.setText(registro.getFechaInicio());
            txtFechaFinal.setText(registro.getFechaFinal());
            //txtPesoRecoleccion.setText(registro.getPesoRecoleccion());
            //txtEstadoCafe.setText(registro.getEstadoCafe());
            //txtFechaVenta.setText(registro.getFechaVenta());
            //txtPesoVenta.setText(registro.getPesoVenta());
            //txtPrecioKG.setText(registro.getPrecioKG());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtFechaInicio.getText().toString().equals("") && !txtFechaFinal.getText().toString().equals("")) {
                    correcto = db.actualizarRegistro(id, txtFechaInicio.getText().toString(), txtFechaFinal.getText().toString(), Integer.valueOf(txtPesoRecoleccion.getText().toString()), txtEstadoCafe.getText().toString(), txtFechaVenta.getText().toString(), Integer.valueOf(txtPesoVenta.getText().toString()), Integer.valueOf(txtPrecioKG.getText().toString()));

                    if(correcto){
                        Toast.makeText(EditarRegistroActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarRegistroActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarRegistroActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, RegistroActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
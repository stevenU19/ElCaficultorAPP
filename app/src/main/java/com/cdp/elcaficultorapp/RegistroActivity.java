package com.cdp.elcaficultorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.elcaficultorapp.entidades.Registro;
import com.cdp.elcaficultorapp.persistencia.elCaficultorDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistroActivity extends AppCompatActivity {

    EditText txtFechaInicio, txtFechaFinal, txtPesoRecoleccion, txtEstadoCafe, txtFechaVenta, txtPesoVenta, txtPrecioKG;
    Button btnGuardar, btnEditar, btnEliminar;
    //FloatingActionButton btnEditar, btnEliminar;

    Registro registro;
    int id = 0;

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

        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setEnabled(false);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final elCaficultorDB db = new elCaficultorDB(RegistroActivity.this);
        registro = db.obtenerRegistro(id);

        if(registro != null){
            txtFechaInicio.setText(registro.getFechaInicio());
            txtFechaFinal.setText(registro.getFechaFinal());
            txtPesoRecoleccion.setText(String.valueOf(registro.getPesoRecoleccion()));
            txtEstadoCafe.setText(registro.getEstadoCafe());
            txtFechaVenta.setText(registro.getFechaVenta());
            txtPesoVenta.setText(String.valueOf(registro.getPesoVenta()));
            txtPrecioKG.setText(String.valueOf(registro.getPrecioKG()));
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, EditarRegistroActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                builder.setMessage("¿Está seguro de eliminar este registro? Esta acción no se puede deshacer.")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(db.eliminarRegistro(id)){
                                    salir();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void salir(){
        Intent intent = new Intent(this, BalanceActivity.class);
        startActivity(intent);
    }
}
package com.cdp.elcaficultorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PaginaPrincipalActivity extends AppCompatActivity {

    Button btnNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        getSupportActionBar().hide();
    }

    // Método el botón IngresarRegistro
    public void ingresarRegistro (View view) {
        Intent intent = new Intent(this, IngresarRegistroActivity.class);
        startActivity(intent);
    }

    // Método el botón ConsultarBalances
    public void consultarBalances (View view) {
        Intent siguiente = new Intent(this, BalanceActivity.class);
        startActivity(siguiente);
    }

    // Método el botón Documentacion
    public void documentacion (View view) {
        Intent siguiente = new Intent(this, DocumentacionActivity.class);
        startActivity(siguiente);
    }

    // Método el botón Acerca De
    public void acercaDe(View view){
        Intent siguiente = new Intent(this, AcercaDeActivity.class);
        startActivity(siguiente);
    }

}
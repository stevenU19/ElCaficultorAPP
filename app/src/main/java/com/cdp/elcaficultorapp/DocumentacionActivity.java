package com.cdp.elcaficultorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocumentacionActivity extends AppCompatActivity {

    private Button btnFNC;
    private Button btnPrecio;
    private Button btnYarumo;
    private Button btnAtras;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentacion);

        // Redirección a la página de la FNC
        btnFNC = findViewById(R.id.btnFNC);
        btnFNC.setOnClickListener(new View.OnClickListener() {
            private String url = "https://federaciondecafeteros.org/wp/";
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Redirección a la página con el precio del café dado por la FNC
        btnPrecio = findViewById(R.id.btnPrecio);
        btnPrecio.setOnClickListener(new View.OnClickListener() {
            private String url = "https://federaciondecafeteros.org/app/uploads/2019/10/precio_cafe.pdf";
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Redirección al canal de youtube de FNC
        btnYarumo = findViewById(R.id.btnYarumo);
        btnYarumo.setOnClickListener(new View.OnClickListener() {
            private String url = "https://www.youtube.com/@FederacionCafeteros/";
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        /*
        // Regresar
        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });*/
    }
    public void regresar (View view) {
        Intent intent = new Intent(this, PaginaPrincipalActivity.class);
        startActivity(intent);
    }
}
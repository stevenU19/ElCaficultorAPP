package com.cdp.elcaficultorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class PrimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        getSupportActionBar().hide();

        final android.os.Handler handler = new android.os.Handler();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PrimerActivity.this, PaginaPrincipalActivity.class);
                startActivity(intent);
                overridePendingTransition(R.transition.fade_in,R.transition.fade_out);
            }
        }, 2000);
    }
}
package com.cdp.elcaficultorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.SearchView;
import android.widget.Toast;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import com.cdp.elcaficultorapp.adaptadores.ListaRegistrosAdapter;
import com.cdp.elcaficultorapp.entidades.Registro;
import com.cdp.elcaficultorapp.persistencia.elCaficultorDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BalanceActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaRegistros;
    ArrayList<Registro> listaArrayRegistros;
    ListaRegistrosAdapter adapter;
    String tituloTexto = "BALANCE DE LA PRODUCCIÓN DE CAFÉ";
    String descripcionTexto = "A continuación de presenta los balances de la producción generados por el aplicativo El Caficultor App V1.1 (Todos los derechos reservados).";

    Context context;
    elCaficultorDB db = new elCaficultorDB(context);
    Registro r = new Registro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaRegistros = findViewById(R.id.listaRegistros);
        listaRegistros.setLayoutManager(new LinearLayoutManager(this));

        elCaficultorDB db = new elCaficultorDB(BalanceActivity.this);

        listaArrayRegistros = new ArrayList<>();
        adapter = new ListaRegistrosAdapter(db.mostrarRegistros());
        listaRegistros.setAdapter(adapter);
        txtBuscar.setOnQueryTextListener(this);

        if(checkPermission()) {
            Toast.makeText(this, "Permiso Otorgado", Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.acercaDe:
                irAcercaDe();
                return true;
            case R.id.pdf:
                generarPDF();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, IngresarRegistroActivity.class);
        startActivity(intent);
    }

    private void irAcercaDe(){
        Intent intent = new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }

    public void generarPDF() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        TextPaint titulo = new TextPaint();
        TextPaint descripcion = new TextPaint();
        Bitmap bitmap, bitmapEscala;

        PdfDocument.PageInfo paginaInfo = new PdfDocument.PageInfo.Builder(816,1054,1).create();
        PdfDocument.Page pagina1 = pdfDocument.startPage(paginaInfo);

        Canvas canvas = pagina1.getCanvas();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        bitmapEscala = Bitmap.createScaledBitmap(bitmap, 80,80,false);
        canvas.drawBitmap(bitmapEscala,368,20,paint);

        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titulo.setTextSize(20);
        canvas.drawText(tituloTexto, 10, 150,titulo);

        descripcion.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        descripcion.setTextSize(14);

        String[] arrDescripcion = descripcionTexto.split("\n");

        int y = 200;
        for(int i=0; i<arrDescripcion.length; i++) {
            canvas.drawText(arrDescripcion[i], 10,y,descripcion);
            y += 15;
        }

        pdfDocument.finishPage(pagina1);

        File file = new File(Environment.getExternalStorageDirectory(),"Balance Cosecha.pdf");
        //File ruta = new File(context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS), "ElCaficultorAPP", "Balance Cosecha.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this,"PDF creado exitosamente", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            e.printStackTrace();
        }

        pdfDocument.close();
    }

    private boolean checkPermission(){
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 200) {
            if(grantResults.length > 0) {
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(writeStorage && readStorage) {
                    Toast.makeText(this, "Permiso Concedido",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permiso Denegado",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}
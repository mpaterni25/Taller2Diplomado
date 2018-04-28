package com.example.android.taller2carros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    private RecyclerView lstOpciones;
    private Intent i;
    private ArrayList<Carro> personas;
    private AdaptadorCarro adapter;
    private LinearLayoutManager llm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstOpciones = findViewById(R.id.lstOpciones);
        personas = Datos.getCarros();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new AdaptadorCarro(personas);

        lstOpciones.setLayoutManager(llm);
        lstOpciones.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Principal.this,Agregar_carro.class);
                startActivity(i);
            }
        });

    }

}










package com.example.android.taller2carros;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agregar_carro extends AppCompatActivity {

    TextView txtPlaca, txtPrecio;
    Spinner cmbMarca, cmbModelo, cmbColor;
    private ArrayAdapter<String> adapterMarca, adapterModelo, adapterColor;
    private String opc[];
    private ArrayList<Integer> fotos;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtPlaca = findViewById(R.id.txtPlaca);
        cmbMarca = findViewById(R.id.cmbMarca);
        cmbModelo = findViewById(R.id.cmbModelo);
        cmbColor = findViewById(R.id.cmbColor);

        opc = this.getResources().getStringArray(R.array.color_spinner);
        adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        cmbColor.setAdapter(adapterColor);

        opc = this.getResources().getStringArray(R.array.marca_spinner);
        adapterMarca = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        cmbMarca.setAdapter(adapterMarca);

        opc = this.getResources().getStringArray(R.array.modelo_spinner);
        adapterModelo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        cmbModelo.setAdapter(adapterModelo);


        fotos = new ArrayList<Integer>();
        fotos.add(R.drawable.audi);
        fotos.add(R.drawable.cheverolet);
        fotos.add(R.drawable.ferari);
        fotos.add(R.drawable.nissan);
        fotos.add(R.drawable.mercedes);
    }

    public static int fotoAleatoria(ArrayList<Integer> fotos) {
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void registrar(View v) {
        String placa, precio;
        int marca, modelo, color, foto;

        if(validar()) {

            placa = txtPlaca.getText().toString();
            marca = cmbMarca.getSelectedItemPosition();
            modelo = cmbModelo.getSelectedItemPosition();
            color = cmbColor.getSelectedItemPosition();
            precio = txtPrecio.getText().toString();
            foto = fotoAleatoria(fotos);


            Carro c = new Carro(placa, marca, modelo, color, precio, foto);
            Datos.guardar(c);
            Snackbar.make(v, getResources().getString(R.string.mensaje_guardado), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

        }

    }

    public boolean validar(){

        String evalplaca = txtPlaca.getText().toString().trim();
        String evalPrecio = txtPrecio.getText().toString().trim();

        if (evalplaca.isEmpty()){
            txtPlaca.setError(getResources().getString(R.string.error3));
            txtPlaca.requestFocus();
            return false;
        }else if (evalplaca.length() == 6) {
            Pattern p = Pattern.compile("^[a-zA-Z]*$");
            Matcher m = p.matcher(evalplaca.substring(0, 3));
            boolean b = m.matches();

            if (b == false) {
                txtPlaca.setError(getResources().getString(R.string.error1));
                txtPlaca.requestFocus();
                return false;
            }
            p = Pattern.compile("^[0-9]*$");
            m = p.matcher(evalplaca.substring(3, 6));
            b = m.matches();

            if (b == false) {
                txtPlaca.setError(getResources().getString(R.string.error2));
                txtPlaca.requestFocus();
                return false;
            }
        }else{
            txtPlaca.setError(getResources().getString(R.string.error4));
            txtPlaca.requestFocus();
            return false;
        }
        if (evalPrecio.isEmpty()){
            txtPrecio.setError(getResources().getString(R.string.error3));
            txtPrecio.requestFocus();
            return false;
        }

        return true;
    }


    public void limpiar() {
        txtPlaca.setText("");
        txtPrecio.setText("");
        cmbModelo.setSelection(0);
        cmbMarca.setSelection(0);
        cmbColor.setSelection(0);
        txtPlaca.requestFocus();
    }


    public void limpiar(View v) {
        limpiar();
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(Agregar_carro.this, Principal.class);
        startActivity(i);
    }

}

package com.example.android.taller2carros;

import java.util.ArrayList;

/**
 * Created by android on 28/04/2018.
 */

public class Datos {
    private static ArrayList<Carro> lista = new ArrayList<Carro>();

    public static void guardar(Carro o) {
        lista.add(o);
    }

    public static ArrayList<Carro> getCarros() {
        return lista;
    }



}

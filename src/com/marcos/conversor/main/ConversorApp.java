package com.marcos.conversor.main;

import com.marcos.conversor.models.ConversorApi;
import com.marcos.conversor.models.Monedas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);

        int opcion = 0;

        List<String> historial = new ArrayList<>();

        ConversorApi conversorApi = new ConversorApi();

        while(true){

            System.out.println("""
                    *******************************************************************+
                    Sea bienvenido al conversor de monedas

                    1) Dolar => Peso argentino
                    2) Peso argentino => Dolár
                    3) Dolar => Real brasileño
                    4) Real brasileño => Dolar
                    5) Dolar => Peso colombiano
                    6) Peso colombiano => Dolar
                    7) Salir

                    Elija una opcion válida
                    """);
        opcion = sn.nextInt();


        if (opcion == 7 ){
            System.out.println("Finalizando programa");
            System.out.println(conversorApi.getHistorial());
            break;
        }

        switch (opcion){
            case 1:
                conversorApi.convertir(Monedas.USD,Monedas.ARS);
                break;
            case 2:
                conversorApi.convertir(Monedas.ARS,Monedas.USD);
                break;
            case 3:
                conversorApi.convertir(Monedas.USD,Monedas.BRL);
                break;
            case 4:
                conversorApi.convertir(Monedas.BRL,Monedas.USD);
                break;
            case 5:
                conversorApi.convertir(Monedas.USD,Monedas.COP);
                break;
            case 6:
                conversorApi.convertir(Monedas.COP,Monedas.USD);
                break;
            default:
                System.out.println("Elija una opcion válida");
                break;
        }

    }
    }

    }

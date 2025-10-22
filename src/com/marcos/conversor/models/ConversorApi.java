package com.marcos.conversor.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marcos.conversor.main.Monedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApi {

    private List<String> historial = new ArrayList<>();

    public List<String> getHistorial() {
        return historial;
    }

    public  void convertir(Monedas monedaInicial, Monedas monedaConvertir){

        Scanner sn = new Scanner(System.in);

        System.out.println("Ingrese el monto a convertir");
        double monto = sn.nextDouble();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();
        String url = "https://v6.exchangerate-api.com/v6/d2614f51a5c0f587e7698852/latest/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + monedaInicial.name()))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            MonedaApi monedaApi = gson.fromJson(response.body(), MonedaApi.class);
            double cambioMoneda = monedaApi.conversion_rates().get(monedaConvertir.name());
            double total  = monto * cambioMoneda;
            String mensaje = "El valor de %.1f [%s] corresponde al valor final de ===> %.2f [%s]"
                    .formatted(monto, monedaInicial.name(),total, monedaConvertir.name() );
            this.historial.add(mensaje);

            System.out.println(mensaje);

        }catch (IOException | InterruptedException e){
           throw new RuntimeException("NO SE PUDO ENCONTRAR EL VALOR DE LA MONEDA INDICADA: " + monedaInicial.name());
        }


    }



}

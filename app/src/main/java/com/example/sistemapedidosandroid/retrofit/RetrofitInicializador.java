package com.example.sistemapedidosandroid.retrofit;

import com.example.sistemapedidosandroid.services.ClienteService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:8000/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public ClienteService getClienteService(){
        return retrofit.create(ClienteService.class);
    }

}

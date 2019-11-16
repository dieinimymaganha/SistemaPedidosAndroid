package com.example.sistemapedidosandroid.retrofit;

import com.example.sistemapedidosandroid.services.UsuarioService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.101:3333/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public UsuarioService getUsuarioService() {
        return retrofit.create(UsuarioService.class);
    }
}

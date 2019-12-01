package com.example.sistemapedidosandroid.retrofit;

import com.example.sistemapedidosandroid.services.ClienteService;
import com.example.sistemapedidosandroid.services.ProdutoService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.25.132:8080/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public ClienteService getClienteService() {
        return retrofit.create(ClienteService.class);
    }

    public ProdutoService getProdutoService() {
        return retrofit.create(ProdutoService.class);
    }

}

package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {

    @POST("clientes/")
    Call<Void> cadastrar(@Body Cliente cliente);


    @GET("clientes/")
    Call<List<Cliente>> lista();

    @GET("clientes/cpf/{nCpf}")
    Call <Cliente> pesquisar_cpf(@Path("nCpf") String nCpf);

    @DELETE("clientes/{id}")
    default Call<Void> deletar(@Path("id") Long id) {
        return null;
    }


    @PUT("clientes/{id}")
    Call<Void> alterar(@Path("id") Long id, @Body Cliente cliente);

}

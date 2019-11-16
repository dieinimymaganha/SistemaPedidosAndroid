package com.example.sistemapedidosandroid.services;

import com.example.sistemapedidosandroid.modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {

    @POST("users")
    Call<Void> cadastrar(@Body Usuario usuario);
}

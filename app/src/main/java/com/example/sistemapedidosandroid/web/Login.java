package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Usuario;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText usuario, senha;

    Button login, cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);

        cadastrar = findViewById(R.id.cadastrar);
        login = findViewById(R.id.login);

        Usuario user = new Usuario();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, CadastrarUsuario.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setEmail(usuario.getText().toString());
                user.setPassword(senha.getText().toString());

                Call call = new RetrofitInicializador().getUsuarioService().login(user);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.i("onResponse", "Requisição com sucesso");
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("onFailure","Requisão falhou");
                    }
                });


            }
        });
    }
}

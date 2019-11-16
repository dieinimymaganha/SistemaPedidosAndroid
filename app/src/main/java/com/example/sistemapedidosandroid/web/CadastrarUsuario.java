package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Usuario;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarUsuario extends AppCompatActivity {

    EditText usuario, email, senha;
    Button login, cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        usuario = findViewById(R.id.usuario);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        cadastrar = findViewById(R.id.cadastrar);

        Usuario user = new Usuario();


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setUsername(usuario.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(senha.getText().toString());

                Call call = new RetrofitInicializador().getUsuarioService().cadastrar(user);

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

                Toast.makeText(CadastrarUsuario.this,"Usuario " + user.getUsername()
                + "Salvo!", Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }
}

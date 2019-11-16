package com.example.sistemapedidosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.modelo.Usuario;

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

        Usuario user = new Usuario();

        user.setUsuario(usuario.getText().toString());
        user.setEmail(email.getText().toString());
        user.setSenha(senha.getText().toString());

    }
}

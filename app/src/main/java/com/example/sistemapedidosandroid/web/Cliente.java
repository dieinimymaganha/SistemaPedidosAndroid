package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sistemapedidosandroid.R;

public class Cliente extends AppCompatActivity {

    Button btCadastrar, btPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btCadastrar = findViewById(R.id.cadastrar);
        btPesquisar = findViewById(R.id.pesquisar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cliente.this, CadastrarCliente.class);
                startActivity(i);
            }
        });


    }
}

package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.R;

public class Inicio extends AppCompatActivity {

    Button btCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btCliente = findViewById(R.id.clientes);

        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, Cliente.class);
                startActivity(i);
            }
        });

    }
}

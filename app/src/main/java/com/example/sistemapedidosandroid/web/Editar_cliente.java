package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.sistemapedidosandroid.R;

public class Editar_cliente extends AppCompatActivity {

    EditText edId, edNome, edSobrenome, edCpf;
    Button btAtualizar, btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        edId = findViewById(R.id.idCliente);
        edNome = findViewById(R.id.nome);
        edSobrenome = findViewById(R.id.sobrenome);
        edCpf = findViewById(R.id.cpf);

        btAtualizar = findViewById(R.id.alterar);
        btCancelar = findViewById(R.id.cancelar);

        Intent i = getIntent();


        Long id = i.getLongExtra("id", 0);
        String id2 = id.toString();
        String nome = i.getStringExtra("nome").toString();
        String sobrenome = i.getStringExtra("sobrenome").toString();
        String cpf = i.getStringExtra("cpf").toString();

        edId.setText(id2);
        edNome.setText(nome);
        edSobrenome.setText(sobrenome);
        edCpf.setText(cpf);


    }
}

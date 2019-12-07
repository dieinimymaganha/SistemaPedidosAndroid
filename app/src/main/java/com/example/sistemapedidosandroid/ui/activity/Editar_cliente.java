package com.example.sistemapedidosandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Cliente;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // Criando mascara para cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);
        // Fim mascara

        edId.setText(id2);
        edId.setEnabled(false);
        edNome.setText(nome);
        edSobrenome.setText(sobrenome);
        edCpf.setText(cpf);

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id = Long.valueOf(edId.getText().toString());
                String nome = edNome.getText().toString();
                String sobrenome = edSobrenome.getText().toString();
                String cpf = edCpf.getText().toString();

                Cliente cliente = new Cliente();

                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCpf(cpf);

                if (nome == null || nome.equals("")) {
                    edNome.setError("Este campo é obrigatório");
                }

                if (sobrenome == null || sobrenome.equals("")) {

                    edSobrenome.setError(("Este campo é obrigatório"));
                }
                if (cpf == null || cpf.equals("")) {

                    edCpf.setError("Este Campo é obrigatório");
                } else {
                    Call call = new RetrofitInicializador().getClienteService().alterar(id, cliente);

                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            int resposta = response.code();
                            Log.i("onResponse", "Requisição com sucesso");
                            Toast.makeText(Editar_cliente.this, "Cliente " + cliente.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Editar_cliente.this, ListarCliente.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });

                }


            }
        });


    }

    //Cria o menu para enviar para o home
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_home_home) {
            startActivity(new Intent(this, Inicio.class));
        }

        return super.onOptionsItemSelected(item);
    }

    // fim do menu home
}
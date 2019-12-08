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

public class CadastrarCliente extends AppCompatActivity {

    public static final String CADASTRAR_CLIENTE = "Cadastrar Cliente";
    Button btCadastrar, btCancelar;
    EditText edNome, edSobrenome, edCpf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        setTitle(CADASTRAR_CLIENTE);
        inicializacaoDosCampos();
        mascaraCpf();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edNome.getText().toString();
                String sobrenome = edSobrenome.getText().toString();
                String cpf = edCpf.getText().toString();

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCpf(cpf);

                if (nome == null || nome.equals("")) {
                    edNome.setError("Este campo é obrigatório");
                }else if (sobrenome == null || sobrenome.equals("")) {
                    edSobrenome.setError(("Este campo é obrigatório"));
                }else if (cpf == null || cpf.equals("")) {
                    edCpf.setError("Este Campo é obrigatório");
                } else {
                    cadastraCliente(cliente);
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inicializacaoDosCampos() {
        edNome = findViewById(R.id.nome);
        edSobrenome = findViewById(R.id.sobrenome);
        edCpf = findViewById(R.id.cpf);
        btCadastrar = findViewById(R.id.cadastrar);
        btCancelar = findViewById(R.id.cancelar);
    }

    private void cadastraCliente(Cliente cliente) {
        Call call = new RetrofitInicializador().getClienteService().cadastrar(cliente);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                validaCpf(response, cliente);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure", "Requisão falhou");
            }
        });
    }

    private void validaCpf(Response response, Cliente cliente) {
        int resposta = response.code();

        if (resposta == 500) {
            edCpf.setError("CPF já cadastrado");
        } else {
            Log.i("onResponse", "Requisição com sucesso");
            Toast.makeText(CadastrarCliente.this, "Cliente " + cliente.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(CadastrarCliente.this, ListarCliente.class);
            startActivity(i);
        }
    }

    private void mascaraCpf() {
        // Criando mascara para cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);
        // Fim mascara
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

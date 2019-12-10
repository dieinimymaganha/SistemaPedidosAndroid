package com.example.sistemapedidosandroid.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.PedidoCad;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

public class CadastrarPedido extends AppCompatActivity {

    EditText edCpf, edQuantidade;
    Button btCadatrar, btCancelar;
    Spinner spinner_produtos;

    ArrayAdapter arrayAdapterProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_cadastrar_pedido);
        spinner_produtos = findViewById(R.id.activity_cad_pedido_spinner_produto);
        edCpf = findViewById(R.id.activity_cad_pedido_cpf);
        edQuantidade = findViewById(R.id.activity_cad_pedido_quantidade);
        btCadatrar = findViewById(R.id.activity_cad_pedido_pedido_cadastrar);
        btCancelar = findViewById(R.id.activity_cad_pedido_pedido_cancelar);


        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);

        carregaProdutos();

        btCadatrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PedidoCad pedidoCad = new PedidoCad();

                String qtd = edQuantidade.getText().toString();
                String cpf = edCpf.getText().toString();

                if (cpf == null || cpf.equals("")) {
                    edCpf.setError("Este campo é obrigatório");
                } else if (qtd == null || qtd.equals("")) {
                    edQuantidade.setError("Este campo é obrigatório");

                } else {
                    int quantidade = Integer.parseInt(qtd);
                    pedidoCad.setCpf(cpf);
                    pedidoCad.setDescricaoProduto(spinner_produtos.getSelectedItem().toString());
                    pedidoCad.setQuantidade(quantidade);

                    Call<Void> call = new RetrofitInicializador().getPedidoCadService().cadastrar(pedidoCad);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int resposta = response.code();

                            if (resposta == 204) {
                                Toast.makeText(CadastrarPedido.this, "Cliente Não localizado", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.i(">>>>", "cadastrou ");
                                Toast.makeText(CadastrarPedido.this, "Pedido cadastrado", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {


                        }
                    });
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

    private void carregaProdutos() {
        Call<List<Produto>> call = new RetrofitInicializador().getProdutoService().lista();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {

                List<Produto> produtos = response.body();
                arrayAdapterProduto = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, produtos);
                spinner_produtos.setAdapter(arrayAdapterProduto);

            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {


            }
        });
    }

}

package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Produto;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.ui.activity.ConstantesActivities.DESCRICAO_PRODUTO;
import static com.example.sistemapedidosandroid.ui.activity.ConstantesActivities.ID_PRODUTO;

public class EditarProduto extends AppCompatActivity {

    EditText edId, edDescricao;
    Button btAtualizar, btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        edId = findViewById(R.id.actitivity_editar_produto_id);
        edDescricao = findViewById(R.id.actitivity_editar_produto_descricao);

        btAtualizar = findViewById(R.id.actitivity_editar_produto_alterar);
        btCancelar = findViewById(R.id.actitivity_editar_produto_cancelar);

        Intent i = getIntent();

        Long id = i.getLongExtra(ID_PRODUTO, 0);
        String id2 = id.toString();
        String descricao = i.getStringExtra(DESCRICAO_PRODUTO).toString();

        edId.setText(id2);
        edId.setEnabled(false);

        edDescricao.setText(descricao);

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Long id = Long.valueOf(edId.getText().toString());
                String descricao = edDescricao.getText().toString();

                Produto produto = new Produto();

                produto.setDescricao(descricao);

                if (descricao == null || descricao.equals("")) {
                    edDescricao.setError("Este campo é obrigatório");
                } else {
                    Call<Void> call = new RetrofitInicializador().getProdutoService().alterar(id, produto);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int resposta = response.code();

                            Toast.makeText(EditarProduto.this, "Produto Atualizado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditarProduto.this, ListarProdutos.class));
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });


                }
            }
        });


    }
}

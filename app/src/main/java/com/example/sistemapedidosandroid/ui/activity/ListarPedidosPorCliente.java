package com.example.sistemapedidosandroid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Pedido;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.example.sistemapedidosandroid.ui.activity.adapter.ListaPedidosPorClienteAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarPedidosPorCliente extends AppCompatActivity {

    public static final String PEDIDOS = "Pedidos";
    SearchView searchView;
    ListView lista_pedidos;
    ArrayAdapter arrayAdapter;

    Long id;

    ListaPedidosPorClienteAdapter adapterPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos_por_cliente);
        setTitle(PEDIDOS);
        inicializacaoDosCampos();
//        configuraFabNovoPedido();

        Intent i = getIntent();
        id = i.getLongExtra("id", 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDadosPedidos();
    }

    private void carregaDadosPedidos() {


        Call<List<Pedido>> call = new RetrofitInicializador().getPedidoService().listarPedidosCliente(id);

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                Log.i("Código >>>>>>>>>>>>>>>", " " + response.code());
                criaAdapterListView(response);
//                criarSearchView();

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("Erro >>>>>>>", "onFailure: " + t.getMessage());


            }
        });

    }

    private void criaAdapterListView(Response<List<Pedido>> response) {
        List<Pedido> pedidos = response.body();
        adapterPedidos = new ListaPedidosPorClienteAdapter(this, pedidos);
        lista_pedidos.setAdapter(adapterPedidos);
    }

    private void inicializacaoDosCampos() {
        lista_pedidos = findViewById(R.id.activity_lista_pedidos);
    }
}

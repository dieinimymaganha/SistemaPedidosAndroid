package com.example.sistemapedidosandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.Pedido;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;
import com.example.sistemapedidosandroid.ui.activity.adapter.ListaPedidosPorClienteAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarPedidos extends AppCompatActivity {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDadosPedidos();
    }

    private void carregaDadosPedidos() {
        Call<List<Pedido>> call = new RetrofitInicializador().getPedidoService().lista();
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                Log.i("Código >>>>>>>>>>>>>>>", " " + response.code());
                criaAdapterListView(response);
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

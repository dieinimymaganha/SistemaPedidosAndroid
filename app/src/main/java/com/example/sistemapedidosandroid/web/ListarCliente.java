package com.example.sistemapedidosandroid.web;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sistemapedidosandroid.R;
import com.example.sistemapedidosandroid.modelo.ClienteModel;
import com.example.sistemapedidosandroid.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sistemapedidosandroid.R.layout.support_simple_spinner_dropdown_item;

public class ListarCliente extends AppCompatActivity {

    ListView lista_cliente;


    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        lista_cliente = findViewById(R.id.lista_cliente);

        Call<List<ClienteModel>> call = new RetrofitInicializador().getClienteService().lista();

        call.enqueue(new Callback<List<ClienteModel>>() {
            @Override
            public void onResponse(Call<List<ClienteModel>> call, Response<List<ClienteModel>> response) {
                List<ClienteModel> clientes = response.body();


                arrayAdapter = new ArrayAdapter(getBaseContext(), support_simple_spinner_dropdown_item, clientes);

                lista_cliente.setAdapter(arrayAdapter);

//
                String[] cli = new String[clientes.size()];


                for (int i = 0; i < clientes.size();
                     i++) {
                    cli[i] = clientes.get(i).getNome() + " " + clientes.get(i).getSobrenome();

                }

                lista_cliente.setAdapter(new ArrayAdapter<String>(getApplicationContext(), support_simple_spinner_dropdown_item, cli));

                registerForContextMenu(lista_cliente);
                Log.i("OnResponse", response.message());

            }

            @Override
            public void onFailure(Call<List<ClienteModel>> call, Throwable t) {
                Log.e("OnResponse", t.getMessage());
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_cliente_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();


        if (itemId == R.id.excluir) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            ClienteModel clienteEscolhido = (ClienteModel) arrayAdapter.getItem(menuInfo.position);
            Long c = clienteEscolhido.getId();
            Call<Void> call = new RetrofitInicializador().getClienteService().deletar(c);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    int resposta = response.code();
                    Log.i("onResponse", "Requisição com sucesso " + clienteEscolhido.getId());

                    if (resposta == 500) {
                        Toast.makeText(ListarCliente.this, "Cliente Possui pedido em aberto"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("onFailure", "Requisão falhou");
                }
            });

        }

//        if(itemId == R.id.alterar){
//            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            ClienteModel clienteEscolhido = (ClienteModel) arrayAdapter.getItem(menuInfo.position);
//            Long c = clienteEscolhido.getId();
//
//            Call<ClienteModel> call = new RetrofitInicializador().getClienteService().find_cliente(c);
//
//            call.enqueue(new Callback<ClienteModel>() {
//                @Override
//                public void onResponse(Call<ClienteModel> call, Response<ClienteModel> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<ClienteModel> call, Throwable t) {
//
//                }
//            });
//
//        }


        return super.onContextItemSelected(item);
    }
}

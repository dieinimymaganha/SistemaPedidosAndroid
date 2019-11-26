package com.example.sistemapedidosandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


    ArrayList<String> titles = new ArrayList<String>();

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

                String[] cli = new String[clientes.size()];

                for (int i = 0; i < clientes.size();
                i++){
                    cli[i] = clientes.get(i).getNome() +  " " + clientes.get(i).getSobrenome();

                }

                lista_cliente.setAdapter(new ArrayAdapter<String>(getApplicationContext(), support_simple_spinner_dropdown_item, cli));


                Log.i("OnResponse", response.message());

            }

            @Override
            public void onFailure(Call<List<ClienteModel>> call, Throwable t) {
                Log.e("OnResponse", t.getMessage());
            }
        });


//        arrayAdapter = new ArrayAdapter();


    }
}

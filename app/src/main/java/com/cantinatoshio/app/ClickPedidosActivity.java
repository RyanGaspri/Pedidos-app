package com.cantinatoshio.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ClickPedidosActivity extends AppCompatActivity
{

    TextView idPedido, statusPedido, dataPedido, valorPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_pedidos);

        idPedido = findViewById(R.id.txtClickIDPedido);
        statusPedido = findViewById(R.id.txtStatusPedido);
        dataPedido = findViewById(R.id.dataPedido);
        valorPedido = findViewById(R.id.valorPedido);

        Intent intent = getIntent();

        //pegando a variável
        String ClickDataPedido = intent.getStringExtra("DataPedido");
        String ClickidPedido = intent.getStringExtra("IDPedido");
        String ClickStatus = intent.getStringExtra("StatusPedido");

        idPedido.setText(ClickidPedido);
        dataPedido.setText(ClickDataPedido);
        valorPedido.setText(intent.getStringExtra("ValorPedido"));
        statusPedido.setText(ClickStatus);

        //pode dar erro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
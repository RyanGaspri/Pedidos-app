package com.cantinatoshio.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cantinatoshio.app.api.Admin;

public class PedidoRealizado extends AppCompatActivity {

    TextView textView;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_realizado);
        textView = findViewById(R.id.num_pedido);
        btnReturn = findViewById(R.id.returnMenu);

        if(Admin.adminIsLogged)
        {
            textView.setText(Admin.idPedidoAdmin);
            Toast.makeText(this, "Você está logado como administrador.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String newID = "#00" + Pedido.returnNewIDPedido();
            textView.setText(newID);
        }
        btnReturn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


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
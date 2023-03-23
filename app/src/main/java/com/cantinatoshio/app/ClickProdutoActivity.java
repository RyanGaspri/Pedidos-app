package com.cantinatoshio.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cantinatoshio.app.Database.PedidoHelper;

public class ClickProdutoActivity extends AppCompatActivity
{
    //para o click

    int qtdProd;


    TextView nomeProduto, precoProduto, qtdeProduto, descProduto, recebeID;
    ImageView imgProduto, clickMais, clickMenos;
    Button addtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_produto);



        nomeProduto = findViewById(R.id.txtNomeProduto);
        precoProduto = findViewById(R.id.valorProduto);
        qtdeProduto = findViewById(R.id.qtdeProduto);
        descProduto = findViewById(R.id.contentDescricao);
        imgProduto = findViewById(R.id.imagemProduto);
        clickMais = findViewById(R.id.clickProdutobtnMais);
        clickMenos = findViewById(R.id.clickProdutobtnMenos);
        addtoCart = findViewById(R.id.addtoCart);
        recebeID = findViewById(R.id.recebeIDProduto);

        Intent intent = getIntent();

        //getExtra
        nomeProduto.setText(intent.getStringExtra("Titulo"));
        precoProduto.setText(intent.getStringExtra("Preco"));
       // qtdeProduto.setText(intent.getStringExtra("qtdeProduto"));
        descProduto.setText(intent.getStringExtra("Descricao"));
        imgProduto.setImageResource(intent.getIntExtra("Imagem", 0));
        recebeID.setText(intent.getStringExtra("ID"));

    //fazer click do carrinho e save cart
        addtoCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (qtdeProduto.getText().toString().equals("0"))
                {
                    Toast.makeText(ClickProdutoActivity.this, "Você ainda não escolheu uma quantidade!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String idItem = recebeID.getText().toString();
                    String nomeItem = nomeProduto.getText().toString();
                    String precoItem = precoProduto.getText().toString();
                    String qtdItem = qtdeProduto.getText().toString();
                    System.out.println("CHEGANDO NO CARRINHO");
                    System.out.println(idItem);
                    System.out.println(nomeItem);
                    System.out.println(precoItem);
                    System.out.println(qtdItem);
                    SaveCart(idItem, nomeItem, precoItem, qtdItem);
                    Intent intent = new Intent(ClickProdutoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //mudar quantidade e valor. Funcionando para todas as telas (não devia)
        //inicializando valor do produto
        double valorProd = Double.parseDouble(String.valueOf(precoProduto.getText()));

        clickMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                double valModProd = valorProd;
                qtdProd = Integer.parseInt(String.valueOf(qtdeProduto.getText()));
                qtdProd++;
                valModProd = valModProd*qtdProd;
                qtdeProduto.setText(String.valueOf(qtdProd));
                precoProduto.setText(String.valueOf(valModProd));
            }

        });


        clickMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                double valModProd = valorProd;
                qtdProd = Integer.parseInt(String.valueOf(qtdeProduto.getText()));
                if (qtdProd>0)
                {
                    qtdProd--;
                }
                valModProd = valModProd*qtdProd;
                qtdeProduto.setText(String.valueOf(qtdProd));
                precoProduto.setText(String.valueOf(valModProd));
            }
        });

    }

    private void SaveCart(String id, String nome, String preco, String qtd)
    {
        String insert = new PedidoHelper(this).inputPedido(id, nome, preco, qtd);
        Toast.makeText(this, insert, Toast.LENGTH_SHORT).show();
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
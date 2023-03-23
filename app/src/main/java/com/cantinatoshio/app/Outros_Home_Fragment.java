package com.cantinatoshio.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Outros_Home_Fragment extends Fragment
{


    RecyclerView lista_outros;
    ArrayList<Produto> outros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_outros_home, container, false);
        lista_outros = v.findViewById(R.id.lista_outros);
        outros = new ArrayList<>();

        outros.add(new Produto("10","Combo batata frita e bacon", "Cheddar opcional", "10.00", "outros", R.drawable.batatabacon));
        outros.add(new Produto("11","Combo Coca e burger", "Hamburguer simples", "10.0", "outros" ,R.drawable.hamburgercoca));
        outros.add(new Produto("12", "Combo café e pão de queijo", "Acompanha 10 pães de queijo", "8.00", "outros" ,R.drawable.paocafe));

        AdapterOutros adapterOutros = new AdapterOutros(getContext(), outros);

        lista_outros.setLayoutManager(new GridLayoutManager(getContext(), 1));

        lista_outros.hasFixedSize();

        lista_outros.setAdapter(adapterOutros);

        return v;
    }

    private class AdapterOutros extends RecyclerView.Adapter<AdapterOutros.ViewHolder>
    {
        private ArrayList<Produto> outros;
        private Context context;

        public AdapterOutros(Context context, ArrayList<Produto> outros)
        {
            this.outros = outros;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterOutros.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_feed, parent, false);
            return new AdapterOutros.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterOutros.ViewHolder holder, int position)
        {
            holder.idProduto.setText(outros.get(position).getIdProduto());
            holder.nomeProduto.setText(outros.get(position).getNomeProduto());
            holder.descProduto.setText(outros.get(position).getDescProduto());
            holder.precoProduto.setText(outros.get(position).getPrecoProduto());
            //holder.mqtdProduto.setText(doces.get(position).getQtdeProduto());
            holder.imgProduto.setImageResource(outros.get(position).getImgProduto());

            holder.cardModelo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickProdutoActivity.class);
                    intent.putExtra("ID", outros.get(position).getIdProduto());
                    intent.putExtra("Titulo", outros.get(position).getNomeProduto());
                    intent.putExtra("Descricao", outros.get(position).getDescProduto());
                    intent.putExtra("Imagem", outros.get(position).getImgProduto());
                    intent.putExtra("Preco", outros.get(position).getPrecoProduto());

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        @Override
        public int getItemCount()
        {
            return outros.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            CardView cardModelo;
            TextView descProduto, nomeProduto, precoProduto, idProduto;
            ImageView imgProduto;

            public ViewHolder(View view)
            {
                super(view);
                idProduto = itemView.findViewById(R.id.idProduto);
                cardModelo = itemView.findViewById(R.id.cardProduto);
                nomeProduto = itemView.findViewById(R.id.nomeProduto);
                descProduto = itemView.findViewById(R.id.descProduto);
                precoProduto = itemView.findViewById(R.id.precoProduto);
                imgProduto = itemView.findViewById(R.id.imgProduto);
            }
        }
    }



}
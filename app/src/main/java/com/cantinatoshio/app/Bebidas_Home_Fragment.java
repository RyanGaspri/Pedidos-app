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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Bebidas_Home_Fragment extends Fragment
{

    RecyclerView lista_bebidas;
    ArrayList<Produto> bebidas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bebidas_home, container, false);
        lista_bebidas = v.findViewById(R.id.lista_bebidas);
        bebidas = new ArrayList<>();

        bebidas.add(new Produto("4","Suco natural", "Vários sabores", "5.00", "bebida", R.drawable.suco));
        bebidas.add(new Produto("5","Refrigerante", "Vários sabores", "5.00", "bebida" ,R.drawable.refri));
        bebidas.add(new Produto("6", "Água", "Sem gás", "2.00", "bebida" ,R.drawable.agua));


        AdapterBebidas adapterBebidas = new AdapterBebidas(getContext(), bebidas);

        lista_bebidas.setLayoutManager(new GridLayoutManager(getContext(), 1));

        lista_bebidas.hasFixedSize();

        lista_bebidas.setAdapter(adapterBebidas);


        return v;
    }


    private class AdapterBebidas extends RecyclerView.Adapter<AdapterBebidas.ViewHolder>
    {
        private ArrayList<Produto> bebidas;
        private Context context;

        public AdapterBebidas(Context context, ArrayList<Produto> bebidas)
        {
            this.bebidas = bebidas;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterBebidas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_feed, parent, false);
            return new AdapterBebidas.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            holder.idProduto.setText(bebidas.get(position).getIdProduto());
            holder.nomeProduto.setText(bebidas.get(position).getNomeProduto());
            holder.descProduto.setText(bebidas.get(position).getDescProduto());
            holder.precoProduto.setText(bebidas.get(position).getPrecoProduto());
            //holder.mqtdProduto.setText(doces.get(position).getQtdeProduto());
            holder.imgProduto.setImageResource(bebidas.get(position).getImgProduto());

            holder.cardModelo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickProdutoActivity.class);
                    intent.putExtra("ID", bebidas.get(position).getIdProduto());
                    intent.putExtra("Titulo", bebidas.get(position).getNomeProduto());
                    intent.putExtra("Descricao", bebidas.get(position).getDescProduto());
                    intent.putExtra("Imagem", bebidas.get(position).getImgProduto());
                    intent.putExtra("Preco", bebidas.get(position).getPrecoProduto());

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        @Override
        public int getItemCount()
        {
            return bebidas.size();
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
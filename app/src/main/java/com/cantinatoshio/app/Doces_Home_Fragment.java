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
import java.util.List;


public class Doces_Home_Fragment extends Fragment
{

    RecyclerView lista_doces;
    ArrayList<Produto> doces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doces_home, container, false);
        lista_doces = v.findViewById(R.id.lista_doces);
        doces = new ArrayList<>();

        doces.add(new Produto("1","Fatia de bolo", "Bolo de chocolate com morango", "4.00", "doce", R.drawable.bolo));
        doces.add(new Produto("2","Brigadeiro", "Chocolate com granulados", "2.00", "doce" ,R.drawable.brigadeiro));
        doces.add(new Produto("3", "Mousse", "Mousse de chocolate", "10.00", "doce" ,R.drawable.mousse));


        AdapterDoces adapterDoces = new AdapterDoces(getContext(), doces);

        lista_doces.setLayoutManager(new GridLayoutManager(getContext(), 1));

        lista_doces.hasFixedSize();

        lista_doces.setAdapter(adapterDoces);

        return v;

    }


    private class AdapterDoces extends RecyclerView.Adapter<AdapterDoces.ViewHolder>
    {
        private ArrayList<Produto> doces;
        private Context context;

        public AdapterDoces(Context context, ArrayList<Produto> doces)
        {
            this.doces = doces;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterDoces.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_feed, parent, false);
            return new AdapterDoces.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            holder.idProduto.setText(doces.get(position).getIdProduto());
            holder.nomeProduto.setText(doces.get(position).getNomeProduto());
            holder.descProduto.setText(doces.get(position).getDescProduto());
            holder.precoProduto.setText(doces.get(position).getPrecoProduto());
            //holder.mqtdProduto.setText(doces.get(position).getQtdeProduto());
            holder.imgProduto.setImageResource(doces.get(position).getImgProduto());

            holder.cardModelo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickProdutoActivity.class);
                    intent.putExtra("ID", doces.get(position).getIdProduto());
                    intent.putExtra("Titulo", doces.get(position).getNomeProduto());
                    intent.putExtra("Descricao", doces.get(position).getDescProduto());
                    intent.putExtra("Imagem", doces.get(position).getImgProduto());
                    intent.putExtra("Preco", doces.get(position).getPrecoProduto());

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        @Override
        public int getItemCount()
        {
            return doces.size();
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
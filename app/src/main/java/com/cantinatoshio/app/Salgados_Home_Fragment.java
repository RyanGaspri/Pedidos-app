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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Salgados_Home_Fragment extends Fragment
{

    RecyclerView lista_salgados;
    ArrayList<Produto> salgados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salgados_home, container, false);
        lista_salgados = view.findViewById(R.id.lista_salgados);
        salgados = new ArrayList<>();

        salgados.add(new Produto("7","Coxinha", "Frango com catupiry", "6.00", "salgado", R.drawable.coxinha));
        salgados.add(new Produto("8","Pão de Queijo", "Quente e crocante", "2.50", "salgado" ,R.drawable.fpaodequeijo));
        salgados.add(new Produto("9", "Misto Quente", "Com queijo e presunto", "6.00", "salgado" ,R.drawable.misto));

        AdapterSalgados adapterSalgados = new AdapterSalgados(getContext(), salgados);

        lista_salgados.setLayoutManager(new GridLayoutManager(getContext(), 1));

        lista_salgados.hasFixedSize();

        lista_salgados.setAdapter(adapterSalgados);


        return view;
    }
    private class AdapterSalgados extends RecyclerView.Adapter<AdapterSalgados.ViewHolder>
    {
        private ArrayList<Produto> salgados;
        private Context context;

        public AdapterSalgados(Context context, ArrayList<Produto> salgados)
        {
            this.salgados = salgados;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterSalgados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_feed, parent, false);
            return new AdapterSalgados.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            holder.idProduto.setText(salgados.get(position).getIdProduto());
            holder.nomeProduto.setText(salgados.get(position).getNomeProduto());
            holder.descProduto.setText(salgados.get(position).getDescProduto());
            holder.precoProduto.setText(salgados.get(position).getPrecoProduto());
            //holder.mqtdProduto.setText(doces.get(position).getQtdeProduto());
            holder.imgProduto.setImageResource(salgados.get(position).getImgProduto());

            holder.cardModelo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickProdutoActivity.class);
                    intent.putExtra("ID", salgados.get(position).getIdProduto());
                    intent.putExtra("Titulo", salgados.get(position).getNomeProduto());
                    intent.putExtra("Descricao", salgados.get(position).getDescProduto());
                    intent.putExtra("Imagem", salgados.get(position).getImgProduto());
                    intent.putExtra("Preco", salgados.get(position).getPrecoProduto());

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        @Override
        public int getItemCount()
        {
            return salgados.size();
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
package com.cantinatoshio.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cantinatoshio.app.api.Admin;
import com.cantinatoshio.app.api.Api;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;



public class PedidosFragment extends Fragment
{

    RecyclerView listapedidos;
    ArrayList<Pedido> pedidos;
    Button entrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

      View view = inflater.inflate(R.layout.fragment_pedidos, container, false);
      pedidos = new ArrayList<>();
      listapedidos = view.findViewById(R.id.lst_pedidos);

      if(Cliente.isLoggedIn)
      {
          pedidos = Cliente.pedidos;
          if(pedidos == null || pedidos.size() == 0)
          {
              view = inflater.inflate(R.layout.modelo_empty_pedidos, container, false);
          }
      }
      else if(Admin.adminIsLogged)
      {
        pedidos = Admin.adminPedidos;
        if(pedidos == null || pedidos.size() == 0)
        {
           view = inflater.inflate(R.layout.modelo_empty_pedidos, container, false);
        }
      }
      else
      {
          view = inflater.inflate(R.layout.modelo_pedidos_deslogado, container, false);
          entrar = view.findViewById(R.id.btnEntrar);
          entrar.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View view)
              {
                  Intent intent = new Intent(getContext(), LogarActivity.class);
                  startActivity(intent);
              }
          });
      }


      AdapterPedidos adapterPedidos = new AdapterPedidos(getContext(), pedidos);

      listapedidos.setLayoutManager(new GridLayoutManager(getContext(), 1));

      listapedidos.hasFixedSize();

      listapedidos.setAdapter(adapterPedidos);

      //clique pra abrir nova janela

      return view;
    }

    //adaptador de pedidos como classe interna

    private class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.ViewHolder>
    {
        private ArrayList<Pedido> pedidos;
        private Context context;
        String statusPedido = "5";

        public AdapterPedidos(Context context, ArrayList<Pedido> pedidos)
        {
            this.pedidos = pedidos;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterPedidos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_pedido_realizado, parent, false);
            return new AdapterPedidos.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdapterPedidos.ViewHolder holder, int position)
        {
            String id  = "#00" + pedidos.get(position).getIdPedido();



            holder.txt_idPedido.setText(id);
            holder.txt_dataPedido.setText(pedidos.get(position).getData());
            holder.txtValorPedido.setText(String.valueOf(pedidos.get(position).getValorPedido()));

            statusPedido = String.valueOf(pedidos.get(position).getStatusPedido());
            if(statusPedido.equals("0") || statusPedido.equals("1"))
            {
                statusPedido = "Em espera";
                holder.txtStatusPedido.setText(statusPedido);
                holder.txtStatusPedido.setTextColor(Color.rgb(198, 181, 0));
            }
            if(statusPedido.equals("2"))
            {
                statusPedido = "Recusado";
                holder.txtStatusPedido.setText(statusPedido);
                holder.txtStatusPedido.setTextColor(Color.parseColor("#d00000"));
            }
            if(statusPedido.equals("3"))
            {
                statusPedido = "Finalizado";
                holder.txtStatusPedido.setText(statusPedido);
                holder.txtStatusPedido.setTextColor(Color.rgb(48, 231, 0));
            }


            holder.cardPedidos.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickPedidosActivity.class);
                    intent.putExtra("IDPedido", id);
                    intent.putExtra("DataPedido", pedidos.get(position).getData());
                    intent.putExtra("ValorPedido", String.valueOf(pedidos.get(position).getValorPedido()));
                    intent.putExtra("StatusPedido",statusPedido);

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        @Override
        public int getItemCount()
        {
            return pedidos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            CardView cardPedidos;
            TextView txt_dataPedido, txt_idPedido, txtValorPedido, txtStatusPedido;


            public ViewHolder(View view)
            {
                super(view);
                cardPedidos = view.findViewById(R.id.cardPedidos);
                txt_dataPedido = view.findViewById(R.id.txt_dataPedido);
                txt_idPedido = view.findViewById(R.id.txt_idPedido);
                txtValorPedido = view.findViewById(R.id.txtValorPedido);
                txtStatusPedido = view.findViewById(R.id.txt_Status);
            }
        }
    }


}
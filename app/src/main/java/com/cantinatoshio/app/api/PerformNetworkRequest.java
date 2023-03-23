package com.cantinatoshio.app.api;


import android.os.AsyncTask;
import android.util.Log;


import com.cantinatoshio.app.Cliente;
import com.cantinatoshio.app.Pedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PerformNetworkRequest extends AsyncTask<Void, Void, String>
{
    String url;
    HashMap<String, String> params;
    int requestCode;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;


    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode)
    {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
    }


    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        // progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
//            progressBar.setVisibility(GONE);

        //fazer switch aqui
        try
        {
            JSONObject object = new JSONObject(s);
            if (!object.getBoolean("error"))
            {
                if (Objects.equals(url, Api.URL_CLIENTE_PEDIDOS))
                {
                    setPedidos(object);
                }
                if (Objects.equals(url, Api.URL_LOGAR))
                {
                    getDadosCliente(object);
                }

            }
        } catch (JSONException e)
        {

            Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + s);
        }
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        RequestHandler requestHandler = new RequestHandler();

        if (requestCode == CODE_POST_REQUEST)
            return requestHandler.sendPostRequest(url, params);


        if (requestCode == CODE_GET_REQUEST)
            return requestHandler.sendGetRequest(url);

        return null;
    }

    private void setPedidos(JSONObject object) throws JSONException
    {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        System.out.println("SAINDO DO PERFORM");
        JSONArray jsonArray = object.getJSONArray("PedidosCliente");
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            pedidos.add(new Pedido(
                    obj.getInt("IDPedido"),
                    obj.getInt("IDCliente"),
                    obj.getString("DataPedido"),
                    obj.getDouble("ValorPedido"),
                    obj.getInt("Confirmado")

            ));
        }
        new Cliente().setPedidos(pedidos);
    }

    private void getDadosCliente(JSONObject object) throws JSONException
    {
        System.out.println("FUNÇÃO GET DADOS CLIENTE EXECUTADA!");


        String message = object.getString("message");
        System.out.println("PRINTANDO O ARRAY 2");
        System.out.println(message);

        if(message.equals("logado com sucesso"))
        {
            JSONArray jsonArray = object.getJSONArray("Dados");
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject dados = jsonArray.getJSONObject(i);
                Cliente.idCliente = dados.getInt("IDCliente");
                Cliente.isLoggedIn = dados.getBoolean("statusLogin");
                Cliente.nomeCliente = dados.getString("Nome");
                Cliente.telefoneCliente = dados.getString("Telefone");
                Cliente.emailCliente = dados.getString("Email");
                Cliente.senhaCliente = dados.getString("Senha");
            }
        }
        else
        {
            Cliente.isLoggedIn = false;
        }
        System.out.println("Funcionou");
        System.out.println(Cliente.isLoggedIn);
    }



}


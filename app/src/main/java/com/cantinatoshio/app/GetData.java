package com.cantinatoshio.app;



import com.cantinatoshio.app.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

public class GetData extends Thread
{

    String id;
    String data = "";
    StringBuilder sb = new StringBuilder();


    @Override
    public void run()
    {

        try {
            URL url = new URL(Api.URL_LAST_IDPEDIDO);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //propriedades da conexão
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response;
                response = bufferedReader.readLine();

                //em vez de while, use if
                if (response != null) {
                    sb.append(response);
                }
            }
            data = sb.toString();


            if (!(data.isEmpty()))
            {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("LastID");
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject ids = jsonArray.getJSONObject(i);
                    id = ids.getString("IDPedido");
                    new Pedido().setNumPedidos(Integer.parseInt(id));
                }
            }


        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

    }


}

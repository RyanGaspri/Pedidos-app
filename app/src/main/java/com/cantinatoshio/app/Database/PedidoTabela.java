package com.cantinatoshio.app.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class PedidoTabela
{
    public PedidoTabela(){}

    public static final String CONTENT_AUTHORITY = "com.cantinatoshio.app";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH = "pedidos";

    public static abstract class EntradaPedido implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);
        public static final String TABLE_NAME = "pedidos";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ID = "idProduto";
        public static final String COLUMN_NAME = "nomeProduto";
        public static final String COLUMN_QUANTITY = "qtdProduto";
        public static final String COLUMN_PRICE = "precoProduto";


    }


}

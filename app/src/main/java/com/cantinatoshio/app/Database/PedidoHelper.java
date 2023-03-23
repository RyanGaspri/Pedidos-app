package com.cantinatoshio.app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class PedidoHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pedido.db";


    public PedidoHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_TABLE = "CREATE TABLE " + PedidoTabela.EntradaPedido.TABLE_NAME + "("
                + PedidoTabela.EntradaPedido._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  PedidoTabela.EntradaPedido.COLUMN_ID + " TEXT NOT NULL, "
                +  PedidoTabela.EntradaPedido.COLUMN_NAME + " TEXT NOT NULL, "
                +  PedidoTabela.EntradaPedido.COLUMN_QUANTITY + " TEXT NOT NULL, "
                +  PedidoTabela.EntradaPedido.COLUMN_PRICE + " TEXT NOT NULL);";

                db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    //criando novos métodos de input e leitura
    public String inputPedido(String id, String titulo, String preco, String qtd)
    {


      SQLiteDatabase db = this.getWritableDatabase();
      float insert = 0;
      if (checaCarrinho(id))
      {
          atualizaQtd(id, qtd, db);
          atualizaPreco(id, preco, db);
          return "Quantidade atualizada!";
      }
      else
      {
          ContentValues values = new ContentValues();
          values.put(PedidoTabela.EntradaPedido.COLUMN_ID, id);
          values.put(PedidoTabela.EntradaPedido.COLUMN_NAME, titulo);
          values.put(PedidoTabela.EntradaPedido.COLUMN_QUANTITY, qtd);
          values.put(PedidoTabela.EntradaPedido.COLUMN_PRICE, preco);
          insert = db.insert(PedidoTabela.EntradaPedido.TABLE_NAME, null, values);
      }
        if(insert==-1)
        {
            return "Falha ao adicionar ao carrinho.";

        }
        else
        {
            return "Adicionado ao carrinho!";
        }
    }

    //função auxiliar para o input
    public boolean checaCarrinho(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + PedidoTabela.EntradaPedido.TABLE_NAME + " WHERE " + PedidoTabela.EntradaPedido.COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.getCount() > 0;
    }

    public void atualizaQtd(String id, String qtd, SQLiteDatabase db)
    {
        String sql = "UPDATE " + PedidoTabela.EntradaPedido.TABLE_NAME +
                " SET " + PedidoTabela.EntradaPedido.COLUMN_QUANTITY + " = "
                + PedidoTabela.EntradaPedido.COLUMN_QUANTITY +
                " + " + qtd +  " where " + PedidoTabela.EntradaPedido.COLUMN_ID + " = " + id;
        db.execSQL(sql);
    }
    public void atualizaPreco(String id, String preco, SQLiteDatabase db)
    {
        String sql = "UPDATE " + PedidoTabela.EntradaPedido.TABLE_NAME +
                " SET " + PedidoTabela.EntradaPedido.COLUMN_PRICE + " = " + PedidoTabela.EntradaPedido.COLUMN_PRICE + " + " +
                preco + " where " + PedidoTabela.EntradaPedido.COLUMN_ID + " = " + id;
        db.execSQL(sql);
    }


    public Cursor getPedido()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + PedidoTabela.EntradaPedido.TABLE_NAME + " ORDER BY " + PedidoTabela.EntradaPedido._ID;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public void limparCarrinho(Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + PedidoTabela.EntradaPedido.TABLE_NAME;
        try
        {
            db.execSQL(sql);
            Toast.makeText(context, "Carrinho esvaziado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Não foi possível limpar seu carrinho.", Toast.LENGTH_SHORT).show();
        }
    }

    public void removerItem(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + PedidoTabela.EntradaPedido.TABLE_NAME + " WHERE " + PedidoTabela.EntradaPedido.COLUMN_ID + " = " + id;
        db.execSQL(sql);
    }

}

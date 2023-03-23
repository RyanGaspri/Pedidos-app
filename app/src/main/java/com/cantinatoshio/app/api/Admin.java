package com.cantinatoshio.app.api;

import com.cantinatoshio.app.Pedido;

import java.util.ArrayList;

public abstract class Admin
{
    public static int idAdmin = 0;
    public static String emailAdmin = "admin@admin.com";
    public static String passAdmin = "admin";
    public static String idPedidoAdmin = "#00";
    public static boolean adminIsLogged;
    public static ArrayList<Pedido> adminPedidos = new ArrayList<>();
}

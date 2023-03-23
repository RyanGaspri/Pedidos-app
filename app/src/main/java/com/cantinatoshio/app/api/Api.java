package com.cantinatoshio.app.api;

public class Api
{
    private static final String ROOT_URL = "http://192.168.1.14/CantinaAPI/CantinaAPI/v1/Api.php?apicall=";

    public static final String URL_CREATE_PEDIDO = ROOT_URL + "createPedido";

    public static final String URL_CADASTRA_ITENS = ROOT_URL + "cadastraItens";

    public static final String URL_CLIENTE_PEDIDOS = ROOT_URL + "getClientePedidos";

    public static final String URL_LAST_IDPEDIDO = "http://192.168.1.14/CantinaApi/CantinaAPI/includes/getPedidoID.php";


    public static final String URL_LOGAR = ROOT_URL + "logar";




}

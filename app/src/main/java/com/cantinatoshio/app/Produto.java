package com.cantinatoshio.app;

public class Produto
{


    private String idProduto;
    private String nomeProduto;
    private String precoProduto;
    private int imgProduto;
    private String qtdeProduto;
    private String descProduto;
    private String tipoProduto;

    public String getIdProduto()
    {
        return idProduto;
    }

    public void setIdProduto(String idProduto)
    {
        this.idProduto = idProduto;
    }

    public int getImgProduto()
    {
        return imgProduto;
    }

    public String getPrecoProduto()
    {
        return precoProduto;
    }

    public String getQtdeProduto()
    {
        return qtdeProduto;
    }

    public void setQtdeProduto(String qtdeProduto)
    {
        this.qtdeProduto = qtdeProduto;
    }

    public String getDescProduto()
    {
        return descProduto;
    }

    public String getNomeProduto()
    {
        return nomeProduto;
    }

    public String getTipoProduto()
    {
        return tipoProduto;
    }

    public Produto()
    {

    }

    public Produto(String descProduto, String tipoProduto, String nomeProduto, String precoProduto, int imgProduto, String qtdeProduto)
    {
        this.imgProduto = imgProduto;
        this.descProduto = descProduto;
        this.tipoProduto = tipoProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.qtdeProduto = qtdeProduto;
    }

    //sem img
    public Produto(String descProduto, String tipoProduto, String nomeProduto, String precoProduto, String qtdeProduto)
    {
        this.descProduto = descProduto;
        this.tipoProduto = tipoProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.qtdeProduto = qtdeProduto;
    }

    //sem tipo
    public Produto(String descProduto, String nomeProduto, String precoProduto, String qtdeProduto)
    {
        this.descProduto = descProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.qtdeProduto = qtdeProduto;
    }

    //pro Feed
    public Produto(String idProduto, String nomeProduto, String descProduto, String precoProduto, String tipoProduto, int imgProduto)
    {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descProduto = descProduto;
        this.precoProduto = precoProduto;
        this.tipoProduto = tipoProduto;
        this.imgProduto = imgProduto;
    }

    //só nome, preco, qtd e img
    public Produto(String idProduto, String nomeProduto, String qtdProduto, String precoProduto, int imgProduto)
    {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdeProduto = qtdProduto;
        this.precoProduto = precoProduto;
        this.imgProduto = imgProduto;
    }

}

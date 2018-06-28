/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author kelvin
 */
public class Produto {
    private String name;
    private String marca;
    private String categoria;
    private String cor;
    private String tamanho;
    private int preco;
    private int qtd;
    private String loja;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     *
     * @param name
     * @param marca
     * @param categoria
     * @param cor
     * @param tamanho
     * @param preco
     * @param qtd
     * @param loja
     * @param id
     */
    public Produto(String name, String marca, String categoria, String cor,
            String tamanho, int preco, int qtd, String loja, String id) {
        this.categoria = categoria;
        this.cor = cor;
        this.marca = marca;
        this.name = name;
        this.preco = preco;
        this.qtd = qtd;
        this.tamanho = tamanho;
        this.loja = loja;
        this.id = id;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = this.qtd - qtd;        
    }
    
    @Override
    public String toString() {
        return this.name;
        
    }
    
    
    
}

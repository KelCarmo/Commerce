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
    private String preco;
    private String qtd;
    
    public Produto(String name, String marca, String categoria, String cor,
            String tamanho, String preco, String qtd) {
        this.categoria = categoria;
        this.cor = cor;
        this.marca = marca;
        this.name = name;
        this.preco = preco;
        this.qtd = qtd;
        this.tamanho = tamanho;
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }
    
    
    
}

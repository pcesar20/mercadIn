package br.com.pauloc.mercadin.model;

import java.io.Serializable;

public class Produto implements Serializable {
    public long id;

    public String descricao;
    public String validade;
    public String valor;
    public int qntItens;
    public String marca;
    public String categoria;

    public Produto(long id, String descricao, String validade, String valor, int qntItens, String marca, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.validade = validade;
        this.valor = valor;
        this.qntItens = qntItens;
        this.marca = marca;
        this.categoria = categoria;
    }

    public Produto() {


    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getQntItens() {
        return qntItens;
    }

    public void setQntItens(int qntItens) {
        this.qntItens = qntItens;
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

    @Override
    public String toString(){
        return descricao;
    }
}

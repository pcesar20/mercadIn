package br.com.pauloc.mercadin.model;

import java.io.Serializable;

public class Produto implements Serializable {
    public long id;
    public String descricao;
    public String validade;
    public Double valor;
    public int qntItens;

    public Produto(long id, String descricao, String validade, Double valor, int qntItens) {
        this.id = id;
        this.descricao = descricao;
        this.validade = validade;
        this.valor = valor;
        this.qntItens = qntItens;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQntItens() {
        return qntItens;
    }

    public void setQntItens(int qntItens) {
        this.qntItens = qntItens;
    }

    @Override
    public String toString(){
        return descricao;
    }
}

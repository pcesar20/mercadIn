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

    public Produto(String descricao, String validade, Double valor, int qntItens) {
        this(0, descricao, validade, valor, qntItens);

    }

    @Override
    public String toString(){
        return descricao;
    }
}

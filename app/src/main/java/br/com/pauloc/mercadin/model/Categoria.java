package br.com.pauloc.mercadin.model;

import java.io.Serializable;

public class Categoria implements Serializable {
    public long id;
    public String cat_descricao;
    public boolean perecivel;

    public Categoria(long id, String cat_descricao, boolean perecivel) {
        this.id = id;
        this.cat_descricao = cat_descricao;
        this.perecivel = perecivel;
    }

    public Categoria() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCat_descricao() {
        return cat_descricao;
    }

    public void setCat_descricao(String cat_descricao) {
        this.cat_descricao = cat_descricao;
    }

    public boolean isPerecivel() {
        return perecivel;
    }

    public void setPerecivel(boolean perecivel) {
        this.perecivel = perecivel;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "cat_descricao='" + cat_descricao + '\'' +
                '}';
    }
}

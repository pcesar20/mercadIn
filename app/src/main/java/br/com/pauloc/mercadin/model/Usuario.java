package br.com.pauloc.mercadin.model;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    public long id;
    public String email;
    public String senha;
    public boolean logado;

    public Usuario() {

    }

    public Usuario(long id, String email, String senha, boolean logado) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.logado = logado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado = true;
    }

    public boolean desLogado(){
        return logado = false;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

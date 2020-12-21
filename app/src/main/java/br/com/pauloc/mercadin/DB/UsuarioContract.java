package br.com.pauloc.mercadin.DB;

import android.provider.BaseColumns;

public class UsuarioContract {
    private String TABELA_USUARIO = "usuario";

    static final class UsuarioColumns implements BaseColumns {
        static String EMAIL = "email";
        static String SENHA = "senha";
        static String LOGADO = "logado";
    }
}


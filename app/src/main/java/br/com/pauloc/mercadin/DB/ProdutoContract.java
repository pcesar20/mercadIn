package br.com.pauloc.mercadin.DB;

import android.provider.BaseColumns;

public class ProdutoContract {

    private String TABELA_PRODUTO = "produto";

    static final class ProdutoColumns implements BaseColumns{
        static String DESCRICAO = "descricao";
        static String QNT_ITEM = "qnt_item";
        static String MARCA = "marca";
        static String CATEGORIA = "categoria";
        static String VALOR = "valor";
        static String VALIDADE = "Validade";
    }
}

package br.com.pauloc.mercadin.DB

import android.provider.BaseColumns

class ProdutoContract {
    private val TABELA_PRODUTO = "produto"

    internal object ProdutoColumns : BaseColumns {
        var DESCRICAO = "descricao"
        var QNT_ITEM = "qnt_item"
        var MARCA = "marca"
        var CATEGORIA = "categoria"
        var VALOR = "valor"
        var VALIDADE = "Validade"
    }
}
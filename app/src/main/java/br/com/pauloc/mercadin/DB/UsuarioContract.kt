package br.com.pauloc.mercadin.DB

import android.provider.BaseColumns

class UsuarioContract {
    private val TABELA_USUARIO = "usuario"

    internal object UsuarioColumns : BaseColumns {
        var EMAIL = "email"
        var SENHA = "senha"
        var LOGADO = "logado"
    }
}
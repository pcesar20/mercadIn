package br.com.pauloc.mercadin.common

import android.content.Context
import android.content.SharedPreferences
import br.com.pauloc.mercadin.R

class ConfigSharedPreferences(private val context: Context) {
    private val sharedPreferences: SharedPreferences
    fun guardarPreferencia(logado: Boolean, utilizador: String?) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(context.resources.getString(R.string.loginlogado), logado)
        editor.putString(context.resources.getString(R.string.loginutilizador), utilizador)
        editor.commit()
    }

    fun obterPreferencia(): String? {
        val utilizador: String?
        utilizador = sharedPreferences.getString(context.resources.getString(R.string.loginutilizador), "")
        return utilizador
    }

    init {
        sharedPreferences = context.getSharedPreferences(context.resources.getString(R.string.preferencia_login), Context.MODE_PRIVATE)
    }
}
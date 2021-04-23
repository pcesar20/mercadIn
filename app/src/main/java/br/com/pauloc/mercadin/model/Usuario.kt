package br.com.pauloc.mercadin.model

import java.io.Serializable

class Usuario : Serializable {
    var id: Long = 0
    var email: String? = null
    var senha: String? = null
    private var logado: Boolean  = false

    constructor() {}

    constructor(id: Long, email: String?, senha: String?, logado: Boolean) {
        this.id = id
        this.email = email
        this.senha = senha
        this.logado = logado
    }

    fun isLogado(): Boolean {
        return true
    }

    fun desLogado(): Boolean {
        return false
    }

    fun setLogado(logado: Boolean) {
        this.logado = logado
    }

    override fun toString(): String {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}'
    }
}
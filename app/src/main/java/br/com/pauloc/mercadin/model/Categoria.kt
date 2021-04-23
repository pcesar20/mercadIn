package br.com.pauloc.mercadin.model

import java.io.Serializable

class Categoria : Serializable {
    var id: Long = 0
    var cat_descricao: String? = null
    var isPerecivel = false

    constructor(id: Long, cat_descricao: String?, perecivel: Boolean) {
        this.id = id
        this.cat_descricao = cat_descricao
        isPerecivel = perecivel
    }

    constructor() {}

    override fun toString(): String {
        return "Categoria{" +
                "cat_descricao='" + cat_descricao + '\'' +
                '}'
    }
}
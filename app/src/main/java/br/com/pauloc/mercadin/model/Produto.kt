package br.com.pauloc.mercadin.model

import java.io.Serializable

open class Produto : Serializable {

    open var id: Long = 0
    var descricao: String? = null
    var validade: String? = null
    var valor: String? = null
    var qntItens = 0
    open var marca: String? = null
    open var categoria: String? = null
    var status = 0

    constructor(id: Long, descricao: String?, validade: String?, valor: String?, qntItens: Int, marca: String?, categoria: String?, status: Int) {
        this.id = id
        this.descricao = descricao
        this.validade = validade
        this.valor = valor
        this.qntItens = qntItens
        this.marca = marca
        this.categoria = categoria
        this.status = status
    }

    constructor() {}

    override fun toString(): String {
        return descricao!!
    }
}
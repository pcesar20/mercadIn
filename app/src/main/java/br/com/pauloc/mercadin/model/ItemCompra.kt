package br.com.pauloc.mercadin.model

class ItemCompra : Produto() {
    override var id: Long = 0
    var produto_id: Long = 0
    var nome_produto: String? = null
    var qntProduto = 0
    var precoProduto = 0.0
    override var categoria: String? = null
    override var marca: String? = null
    var precoTotal = 0.0
}
package br.com.pauloc.mercadin.adapters

import android.app.Activity
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.pauloc.mercadin.R
import br.com.pauloc.mercadin.model.ItemCompra
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio
import java.util.*

class ItemCompraAdapter(private val activity: Activity) : RecyclerView.Adapter<ItemCompraAdapter.ProdutoViewHolder>() {
    var listaItemCompra: ArrayList<ItemCompra>? = null
        private set
    private val produtoRepositorio: ProdutoRepositorio? = null
    fun setlistaItemCompra(listaItemCompra: ArrayList<ItemCompra>?) {
        this.listaItemCompra = listaItemCompra
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_itens_compras, parent, false)
        return ProdutoViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.txtDescricaoProduto.text = listaItemCompra!![position].nome_produto
        holder.txtQnt.text = Integer.toString(listaItemCompra!![position].qntProduto)
        holder.txtMarca.text = listaItemCompra!![position].marca
        holder.txtCategoria.text = listaItemCompra!![position].categoria
        holder.txtValidade.text = listaItemCompra!![position].validade
        holder.txtValor.text = listaItemCompra!![position].valor
        holder.txtPrecoTotal.setText(listaItemCompra!![position].precoProduto.toInt())
    }

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnCreateContextMenuListener {
        var txtDescricaoProduto: TextView
        var txtQnt: TextView
        var txtMarca: TextView
        var txtValor: TextView
        var txtCategoria: TextView
        var txtValidade: TextView
        var txtPrecoTotal: TextView
        var cvProduto: CardView? = null
        override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo) {
            menu.setHeaderTitle("Selecione uma opção")
            menu.add(this.adapterPosition, 0, 0, "Apagar")
            menu.add(this.adapterPosition, 1, 1, "Estocado")
        }

        init {
            txtDescricaoProduto = itemView.findViewById(R.id.text_nomeproduto)
            txtQnt = itemView.findViewById(R.id.text_precoQtd)
            txtMarca = itemView.findViewById(R.id.text_produtoMarca)
            txtCategoria = itemView.findViewById(R.id.text_produtoCateg)
            txtValor = itemView.findViewById(R.id.text_precoProduto)
            txtPrecoTotal = itemView.findViewById(R.id.text_precoUnitario)
            txtValidade = itemView.findViewById(R.id.tv_item_validade)
        }
    }

    override fun getItemCount(): Int {
        return listaItemCompra!!.size
    }

    fun removeItem(posicao: Int) {
        listaItemCompra!!.removeAt(posicao)
        notifyItemRemoved(posicao)
    }
}
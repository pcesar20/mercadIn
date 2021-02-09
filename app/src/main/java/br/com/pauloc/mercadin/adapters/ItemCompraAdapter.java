package br.com.pauloc.mercadin.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.pauloc.mercadin.CustomOnItemClickListener;
import br.com.pauloc.mercadin.FormAddProduto;
import br.com.pauloc.mercadin.R;
import br.com.pauloc.mercadin.model.ItemCompra;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

public class ItemCompraAdapter extends RecyclerView.Adapter<ItemCompraAdapter.ProdutoViewHolder> {

    private ArrayList<ItemCompra> listaItemCompra;
    private Activity activity;
    private ProdutoRepositorio produtoRepositorio;

    public ItemCompraAdapter(Activity activity){
        this.activity = activity;
    }

    public ArrayList<ItemCompra> getListaItemCompra(){ return listaItemCompra; }

    public void setlistaItemCompra(ArrayList<ItemCompra> listaItemCompra) {
        this.listaItemCompra = listaItemCompra;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itens_compras, parent, false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProdutoViewHolder holder, final int position) {
        holder.txtDescricaoProduto.setText(getListaItemCompra().get(position).getNome_produto());
        holder.txtQnt.setText(Integer.toString(getListaItemCompra().get(position).getQntProduto()));
        holder.txtMarca.setText(getListaItemCompra().get(position).getMarca());
        holder.txtCategoria.setText(getListaItemCompra().get(position).getCategoria());
        holder.txtValidade.setText(getListaItemCompra().get(position).getValidade());
        holder.txtValor.setText(getListaItemCompra().get(position).getValor());
        holder.txtPrecoTotal.setText((int) getListaItemCompra().get(position).getPrecoProduto());


    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtDescricaoProduto, txtQnt, txtMarca, txtValor, txtCategoria, txtValidade, txtPrecoTotal;
        CardView cvProduto;

        public ProdutoViewHolder(View itemView){
            super(itemView);
            txtDescricaoProduto = itemView.findViewById(R.id.text_nomeproduto);
            txtQnt = itemView.findViewById(R.id.text_precoQtd);
            txtMarca = itemView.findViewById(R.id.text_produtoMarca);
            txtCategoria = itemView.findViewById(R.id.text_produtoCateg);
            txtValor = itemView.findViewById(R.id.text_precoProduto);
            txtPrecoTotal = itemView.findViewById(R.id.text_precoUnitario);
            txtValidade =itemView.findViewById(R.id.tv_item_validade);


        }
        
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
            menu.setHeaderTitle("Selecione uma opção");
            menu.add(this.getAdapterPosition(), 0, 0, "Apagar");
            menu.add(this.getAdapterPosition(), 1, 1, "Estocado");
        }
    }


    @Override
    public int getItemCount() {
        return getListaItemCompra().size();
    }

    public void removeItem(int posicao){
        listaItemCompra.remove(posicao);
        this.notifyItemRemoved(posicao);
    }



}

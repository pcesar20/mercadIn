package br.com.pauloc.mercadin.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import br.com.pauloc.mercadin.DB.DataBaseSQLHelper;
import br.com.pauloc.mercadin.FormAddProduto;
import br.com.pauloc.mercadin.MinhaDispensa;
import br.com.pauloc.mercadin.R;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private ArrayList<Produto> listaProduto;
    private Activity activity;
    private ProdutoRepositorio produtoRepositorio;

    public ProdutoAdapter(Activity activity){
        this.activity = activity;
    }

    public ArrayList<Produto> getListaProduto(){ return listaProduto; }

    public void setListaProduto(ArrayList<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProdutoViewHolder holder, final int position) {
        holder.txtDescricaoProduto.setText("Produto: " + getListaProduto().get(position).getDescricao());
        holder.txtQnt.setText("Qnt: " + Integer.toString(getListaProduto().get(position).getQntItens()));
        holder.txtMarca.setText("Marca: " + getListaProduto().get(position).getMarca());
        holder.txtCategoria.setText("Categoria: " + getListaProduto().get(position).getCategoria());
        holder.txtValidade.setText("Validade: " + getListaProduto().get(position).getValidade());
        holder.txtValor.setText("Valor: " + getListaProduto().get(position).getValor());
        holder.cvProduto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback(){
            @Override
            public void onItemClicked(final View view, final int posicao) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("MercadIn");
                builder.setMessage("Escolha uma ação:");

                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity, FormAddProduto.class);
                        intent.putExtra(FormAddProduto.EXTRA_QUANTIDADE, getListaProduto().get(position).getQntItens());
                        intent.putExtra(FormAddProduto.EXTRA_DESCRICAO, getListaProduto().get(position).getDescricao());
                        intent.putExtra(FormAddProduto.EXTRA_MARCA, getListaProduto().get(position).getMarca());
                        intent.putExtra(FormAddProduto.EXTRA_CATEGORIA, getListaProduto().get(position).getCategoria());
                        intent.putExtra(FormAddProduto.EXTRA_VALOR, getListaProduto().get(position).getValor());
                        intent.putExtra(FormAddProduto.EXTRA_VALIDADE, getListaProduto().get(position).getValidade());
                        activity.startActivityForResult(intent, FormAddProduto.REQUEST_UPDATE);
                    }
                });

                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(view.getContext());
                        produtoRepositorio.open();
                        produtoRepositorio.delete((int) getListaProduto().get(posicao).getId());
                    }
                });

                builder.create().show();
            }


        }));

    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtDescricaoProduto, txtQnt, txtMarca, txtValor, txtCategoria, txtValidade;
        CardView cvProduto;

        public ProdutoViewHolder(View itemView){
            super(itemView);
            txtDescricaoProduto = itemView.findViewById(R.id.tv_item_produto);
            txtQnt = itemView.findViewById(R.id.tv_item_qntd);
            txtMarca = itemView.findViewById(R.id.tv_item_marca);
            txtCategoria = itemView.findViewById(R.id.tv_item_categoria);
            txtValor = itemView.findViewById(R.id.tv_item_valor);
            txtValidade =itemView.findViewById(R.id.tv_item_validade);
            cvProduto = itemView.findViewById(R.id.cv_item_produto);
            cvProduto.setOnCreateContextMenuListener(this);

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
        return getListaProduto().size();
    }

    public void removeItem(int posicao){
        listaProduto.remove(posicao);
        this.notifyItemRemoved(posicao);
    }



}
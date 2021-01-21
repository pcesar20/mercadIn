package br.com.pauloc.mercadin.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, final int position) {
        holder.txtDescricaoProduto.setText(getListaProduto().get(position).getDescricao());
        holder.txtQnt.setText(Integer.toString(getListaProduto().get(position).getQntItens()));
//        holder.cvProduto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback(){
//            @Override
//            public void onItemClicked(View view, int posicao) {
//                Intent intent = new Intent(activity, FormAddProduto.class);
//                intent.putExtra(FormAddProduto.EXTRA_QUANTIDADE, posicao);
//                intent.putExtra(FormAddProduto.EXTRA_DESCRICAO, getListaProduto().get(posicao));
//                activity.startActivityForResult(intent, FormAddProduto.REQUEST_UPDATE);
//            }
//
//
//        }));

    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtDescricaoProduto, txtQnt;
        CardView cvProduto;

        public ProdutoViewHolder(View itemView){
            super(itemView);
            txtDescricaoProduto = itemView.findViewById(R.id.tv_item_produto);
            txtQnt = itemView.findViewById(R.id.tv_item_qntd);
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

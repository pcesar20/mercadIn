package br.com.pauloc.mercadin.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import br.com.pauloc.mercadin.CustomOnItemClickListener;
import br.com.pauloc.mercadin.R;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.FormAddProduto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private LinkedList<Produto> listaProduto;
    private Activity activity;

    public ProdutoAdapter(Activity activity){
        this.activity = activity;
    }

    public LinkedList<Produto> getListaProduto(){ return listaProduto; }

    public void setListaProduto(LinkedList<Produto> listaProduto) {
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
        holder.cvProduto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback(){
            @Override
            public void onItemClicked(View view, int posicao) {
                Intent intent = new Intent(activity, FormAddProduto.class);
                intent.putExtra(FormAddProduto.EXTRA_QUANTIDADE, posicao);
                intent.putExtra(FormAddProduto.EXTRA_DESCRICAO, getListaProduto().get(posicao));
                activity.startActivityForResult(intent, FormAddProduto.REQUEST_UPDATE);
            }
        }));

    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder{
        TextView txtDescricaoProduto, txtQnt;
        CardView cvProduto;

        public ProdutoViewHolder(View itemView){
            super(itemView);
            txtDescricaoProduto = itemView.findViewById(R.id.tv_item_produto);
            txtQnt = itemView.findViewById(R.id.tv_item_qntd);
            cvProduto = itemView.findViewById(R.id.cv_item_produto);

        }
    }


    @Override
    public int getItemCount() {
        return getListaProduto().size();
    }
}

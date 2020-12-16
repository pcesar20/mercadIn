package br.com.pauloc.mercadin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import br.com.pauloc.mercadin.adapters.ProdutoAdapter;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.FormAddProduto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

public class MinhaDispensa extends AppCompatActivity {

    RecyclerView rvProd;
    ProgressBar progressBar;
    FloatingActionButton fab;
    private LinkedList<Produto> lista;
    private ProdutoRepositorio produtoRepositorio;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_dispensa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab_add);
        rvProd = findViewById(R.id.rv_clientes);
        rvProd.setHasFixedSize(true);
        rvProd.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressbar);

        produtoRepositorio = new ProdutoRepositorio(this);
        produtoRepositorio.open();

        lista = new LinkedList<>();

        adapter = new ProdutoAdapter(this);
        adapter.setListaProduto(lista);
        rvProd.setAdapter(adapter);

        new LoadProdutoAsync().execute();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Ainda em desenvolvimento", Snackbar.LENGTH_LONG)
//                        .setAction("MercadIn", null).show();

        if (view.getId() == R.id.fab_add){
            Intent intent = new Intent(MinhaDispensa.this, FormAddProduto.class);
        try{
            startActivityForResult(intent, FormAddProduto.REQUEST_ADD);
        } catch(Exception e){
            e.printStackTrace();
        }

        }

            }
        });


    }

    private class LoadProdutoAsync extends AsyncTask<Void, Void, ArrayList<Produto>>{
        @Override
        protected ArrayList<Produto> doInBackground(Void... voids) {
            return produtoRepositorio.query();
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

            if (lista.size() > 0){
                lista.clear();
            }
    }

        @Override
        protected void onPostExecute(ArrayList<Produto> produtos){
            super.onPostExecute(produtos);

            progressBar.setVisibility(View.GONE);
            lista.addAll(produtos);
            adapter.setListaProduto(lista);
            adapter.notifyDataSetChanged();

            if(lista.size() == 0){
                showSnackbarMessage("Não há produtos");
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FormAddProduto.REQUEST_ADD){
            if (resultCode == FormAddProduto.RESULT_ADD){
                new LoadProdutoAsync().execute();

                showSnackbarMessage("Novo produto adicionado");
            }
        } else if (requestCode == FormAddProduto.REQUEST_UPDATE) {
            if (resultCode == FormAddProduto.RESULT_UPDATE) {
                new LoadProdutoAsync().execute();

                showSnackbarMessage("Produto Editado");
            } else if (resultCode == FormAddProduto.RESULT_DELETE) {
                int posicao = data.getIntExtra(FormAddProduto.EXTRA_DESCRICAO, 0);

                lista.remove(posicao);

                adapter.setListaProduto(lista);

                adapter.notifyDataSetChanged();

                showSnackbarMessage("Produto Eliminado");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (produtoRepositorio != null){
            produtoRepositorio.close();
        }
    }

    private void showSnackbarMessage(String message){
        Snackbar.make(rvProd, message, Snackbar.LENGTH_SHORT).show();
    }


}
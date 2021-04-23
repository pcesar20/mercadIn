package br.com.pauloc.mercadin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import br.com.pauloc.mercadin.DB.DataBaseSQLHelper;
import br.com.pauloc.mercadin.adapters.ProdutoAdapter;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

public class
MinhaDispensa extends AppCompatActivity {

    private String email = "";
    RecyclerView rvProd;
    ProgressBar progressBar;
    FloatingActionButton fab;
    private ArrayList<Produto> lista;
    private ProdutoRepositorio produtoRepositorio;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_dispensa);
        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab_add);
        rvProd = findViewById(R.id.rv_clientes);
        rvProd.setHasFixedSize(true);
        rvProd.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvProd);
        progressBar = findViewById(R.id.progressbar);
        //rvProd.scrollToPosition(0);

        produtoRepositorio = new ProdutoRepositorio(this);
        produtoRepositorio.open();

        lista = new ArrayList<>();

        adapter = new ProdutoAdapter(this);
        adapter.setListaProduto(lista);
        rvProd.setAdapter(adapter);


        new LoadProdutoAsync().execute();

        Intent it = getIntent();

        if (it != null) {
            Bundle b = it.getExtras();

            if (b != null) {
                email = b.getString("nome", "");
            }
        }

        //bemVindo(email);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.fab_add) {
                    Intent intent = new Intent(getApplicationContext(), ComprasActivity.class);
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Esconde nav bar e status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    private class LoadProdutoAsync extends AsyncTask<Void, Void, ArrayList<Produto>> {
        @Override
        protected ArrayList<Produto> doInBackground(Void... voids) {
            try {
                produtoRepositorio.query();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return produtoRepositorio.query();
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

            if (lista.size() > 0) {
                lista.clear();
            }
        }


        @Override
        protected void onPostExecute(ArrayList<Produto> produtos) {
            super.onPostExecute(produtos);

            progressBar.setVisibility(View.GONE);
            lista.addAll(produtos);
            adapter.setListaProduto(lista);
            adapter.notifyDataSetChanged();


            if (lista.size() == 0) {
                showSnackbarMessage("Não há produtos");
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FormAddProduto.REQUEST_ADD) {
            if (resultCode == FormAddProduto.RESULT_ADD) {
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

        if (produtoRepositorio != null) {
            produtoRepositorio.close();
        }
    }


    private void showSnackbarMessage(String message) {
        Snackbar.make(rvProd, message, Snackbar.LENGTH_SHORT).show();
    }

    private void bemVindo(String email) {
        Toast.makeText(this, "Bem vindo, " + email, Toast.LENGTH_LONG).show();

//        Snackbar.make(, "Bem vindo, " + email, Snackbar.LENGTH_LONG)
//                .setAction("MercadIn", null).show();

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            try {
            Integer position = viewHolder.getAdapterPosition();

                if(position != null){
                    produtoRepositorio.open();
                    produtoRepositorio.delete((int) lista.get(position).getId());
                    adapter.removeItem(position);
                    adapter.notifyItemRemoved(position);
                    showSnackbarMessage(getString(R.string.prod_delete));
                } else{
                    showSnackbarMessage(getString(R.string.erro));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                adapter.removeItem(item.getGroupId());
                lista.remove(item.getGroupId());
                adapter.notifyItemRemoved(item.getItemId());
                produtoRepositorio.open();
                produtoRepositorio.delete(item.getItemId());
                //lista.notify();
                adapter.notifyDataSetChanged();
                return true;
            case 1:
                Toast.makeText(this, "teste", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }


}
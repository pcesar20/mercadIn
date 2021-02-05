package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

public class ComprasActivity extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoRepositorio produtoRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        spnProdutos = findViewById(R.id.spnProduto);

        produtoRepositorio = new ProdutoRepositorio(this);
        produtoRepositorio.open();
        listaProduto = produtoRepositorio.query();
        ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<Produto>(this, R.layout.support_simple_spinner_dropdown_item, listaProduto);
        spnProdutos.setAdapter(spnProdutoAdapter);


    }
}
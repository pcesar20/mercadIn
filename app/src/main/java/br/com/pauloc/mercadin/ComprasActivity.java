
package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import br.com.pauloc.mercadin.adapters.ItemCompraAdapter;
import br.com.pauloc.mercadin.model.ItemCompra;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

public class ComprasActivity extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ArrayList<ItemCompra> listaItemCompra;
    private ProdutoRepositorio produtoRepositorio;
    MaterialButton btnAddProd;
    EditText edt_qntProduto;
    ListView listProduto;
    ItemCompraAdapter itemCompraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        spnProdutos = findViewById(R.id.spnProduto);
        btnAddProd = findViewById(R.id.btnAdicionar);
        edt_qntProduto = findViewById(R.id.edt_qntProduto);
        listProduto = findViewById(R.id.listProduto);

        listaItemCompra = new ArrayList<>();

        itemCompraAdapter = new ItemCompraAdapter(this);
        itemCompraAdapter.setlistaItemCompra(listaItemCompra);

        produtoRepositorio = new ProdutoRepositorio(this);
        produtoRepositorio.open();
        listaProduto = produtoRepositorio.query();
        final ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<Produto>(this, R.layout.support_simple_spinner_dropdown_item, listaProduto);
        spnProdutos.setAdapter(spnProdutoAdapter);

        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemCompra itemCompra = new ItemCompra();
                Produto produtoSelecionado = (Produto) spnProdutos.getSelectedItem();
                int qntProduto = Integer.parseInt(edt_qntProduto.getText().toString());

                itemCompra.setNome_produto(produtoSelecionado.getDescricao());
                itemCompra.setQntProduto(qntProduto);
                itemCompra.setPrecoProduto(Double.parseDouble(produtoSelecionado.getValor()));
                itemCompra.setPrecoTotal(itemCompra.getQntProduto() * itemCompra.getPrecoProduto());

            }
        });


    }
}
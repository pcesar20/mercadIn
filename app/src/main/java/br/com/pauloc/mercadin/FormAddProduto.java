package br.com.pauloc.mercadin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import br.com.pauloc.mercadin.R;
import br.com.pauloc.mercadin.model.Produto;
import br.com.pauloc.mercadin.repositories.ProdutoRepositorio;

import static java.security.AccessController.getContext;

public class FormAddProduto extends AppCompatActivity {
    EditText edtDescricao, edtQtd;
    AutoCompleteTextView filled_exposed_dropdown;
    Button btnEnviar;

    public static String EXTRA_DESCRICAO = "extra_descricao";
    public static String EXTRA_QUANTIDADE = "extra_quantidade";
    private boolean isEdit = false;
    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;
    private Produto produto;
    private int posicao;
    private ProdutoRepositorio produtoRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_produto);
        filled_exposed_dropdown = findViewById(R.id.filled_exposed_dropdown);
        edtDescricao = findViewById(R.id.edt_descricao);
        edtQtd = findViewById(R.id.edt_qntProduto);
        btnEnviar = findViewById(R.id.btn_enviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_enviar) {
                    String descricao = edtDescricao.getText().toString().trim();
                    int qntItem = Integer.parseInt(edtQtd.getText().toString().trim());
                    String qnt = String.valueOf(qntItem);

                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(descricao) || TextUtils.isEmpty(qnt)) {
                        isEmpty = true;

                        edtDescricao.setError("Preencha o campo nome");
                        edtQtd.setError("Existem erros");
                    }
                    if (!isEmpty) {
                        Produto novoProduto = new Produto();

                        novoProduto.setDescricao(descricao);
                        novoProduto.setQntItens(qntItem);

                        Intent intent = new Intent();


                        if (isEdit) {
                            novoProduto.setValidade(produto.getValidade());
                            novoProduto.setValor(produto.getValor());

                            produtoRepositorio.update(novoProduto);
                            intent.putExtra(EXTRA_QUANTIDADE, posicao);

                            setResult(RESULT_UPDATE, intent);

                            finish();
                        } else {
                            produtoRepositorio.inserir(novoProduto);

                            setResult(RESULT_ADD);

                            finish();
                        }
                    }
                }
            }
        });


        produtoRepositorio = new ProdutoRepositorio(this);
        produtoRepositorio.open();

        //produto = getIntent().getParcelableExtra(EXTRA_DESCRICAO);

        if (produto != null) {
            posicao = getIntent().getIntExtra(EXTRA_QUANTIDADE, 0);
            isEdit = false;

        }

        String actionBarTitle = null;
        String btnCliente = null;

        if (isEdit) {
            actionBarTitle = "Editando produto";
            btnCliente = "Editar";
            edtDescricao.setText(produto.getDescricao());
            edtQtd.setText(produto.getQntItens());
        } else {
            actionBarTitle = "Criar Produto";
            btnCliente = "Criar";
        }

        try{
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            btnEnviar.setText(btnCliente);
        } catch (Exception e){
            e.printStackTrace();
        }


        String[] categorias = getResources().getStringArray(R.array.array_categoria);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categorias);
        filled_exposed_dropdown.setAdapter(adapter);


    }

}
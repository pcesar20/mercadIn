package br.com.pauloc.mercadin.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.pauloc.mercadin.DB.ProdutoSQLHelper;
import br.com.pauloc.mercadin.model.Produto;

public class ProdutoRepositorio {

    private ProdutoSQLHelper helper;

    public ProdutoRepositorio(Context context){
        helper = new ProdutoSQLHelper(context);

    }

    private long inserir(Produto produto){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ProdutoSQLHelper.COLUNA_DESCRICAO, produto.descricao);
        cv.put(ProdutoSQLHelper.COLUNA_VALOR, produto.valor);
        cv.put(ProdutoSQLHelper.COLUNA_VALIDADE, produto.validade);
        cv.put(ProdutoSQLHelper.COLUNA_QNT, produto.qntItens);
        long id = db.insert(ProdutoSQLHelper.TABELA_PRODUTO, null, cv);
        if (id != -1){
            produto.id = id;

        }
        db.close();
        return id;
    }
}

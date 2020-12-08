package br.com.pauloc.mercadin.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.com.pauloc.mercadin.DB.ProdutoSQLHelper;
import br.com.pauloc.mercadin.model.Produto;

import static android.provider.BaseColumns._ID;
import static br.com.pauloc.mercadin.DB.ProdutoSQLHelper.COLUNA_DESCRICAO;
import static br.com.pauloc.mercadin.DB.ProdutoSQLHelper.COLUNA_QNT;
import static br.com.pauloc.mercadin.DB.ProdutoSQLHelper.COLUNA_VALIDADE;
import static br.com.pauloc.mercadin.DB.ProdutoSQLHelper.COLUNA_VALOR;
import static br.com.pauloc.mercadin.DB.ProdutoSQLHelper.TABELA_PRODUTO;

public class ProdutoRepositorio {
    private static String BASEDADOS_TABELA = TABELA_PRODUTO;
    private ProdutoSQLHelper helper;
    private Context context;
    private SQLiteDatabase database;


    public ProdutoRepositorio(Context context){
       this.context = context;

    }

    public long inserir(Produto produto){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ProdutoSQLHelper.COLUNA_DESCRICAO, produto.descricao);
        cv.put(ProdutoSQLHelper.COLUNA_VALOR, produto.valor);
        cv.put(ProdutoSQLHelper.COLUNA_VALIDADE, produto.validade);
        cv.put(ProdutoSQLHelper.COLUNA_QNT, produto.qntItens);
        long id = db.insert(TABELA_PRODUTO, null, cv);
        if (id != -1){
            produto.id = id;

        }
        db.close();
        return id;
    }

    public ProdutoRepositorio open() throws SQLException{
        helper = new ProdutoSQLHelper(context);

        database = helper.getWritableDatabase();
        return this;
    }

    public int update(Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(COLUNA_QNT, produto.getQntItens());
        contentValues.put(COLUNA_VALIDADE, produto.getValidade());
        contentValues.put(COLUNA_VALOR, produto.getValor());

        return database.update(
                BASEDADOS_TABELA,
                contentValues,
                _ID + "= '" + produto.getId() + "'",
                null
        );
    }

    public int insert(Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(COLUNA_QNT, produto.getQntItens());
        contentValues.put(COLUNA_VALIDADE, produto.getValidade());
        contentValues.put(COLUNA_VALOR, produto.getValor());

        return (int) database.insert(BASEDADOS_TABELA, null, contentValues);
    }
}

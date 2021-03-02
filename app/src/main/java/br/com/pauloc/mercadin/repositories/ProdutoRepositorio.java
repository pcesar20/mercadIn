package br.com.pauloc.mercadin.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import java.util.ArrayList;

import br.com.pauloc.mercadin.DB.DataBaseSQLHelper;
import br.com.pauloc.mercadin.model.Produto;

import static android.provider.BaseColumns._ID;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_DESCRICAO;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_PRODUTO_CATEGORIA;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_PRODUTO_MARCA;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_PRODUTO_STATUS;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_QNT;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_VALIDADE;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_VALOR;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.TABELA_PRODUTO;

public class ProdutoRepositorio {
    private static String BASEDADOS_TABELA = TABELA_PRODUTO;
    private DataBaseSQLHelper helper;
    private Context context;
    private SQLiteDatabase database;


    public ProdutoRepositorio(Context context) {
        this.context = context;

    }


    public void close() {
        helper.close();
    }

    public long inserir(Produto produto) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_DESCRICAO, produto.descricao);
        cv.put(COLUNA_VALOR, produto.valor);
        cv.put(COLUNA_VALIDADE, produto.validade);
        cv.put(COLUNA_QNT, produto.qntItens);
        cv.put(COLUNA_PRODUTO_MARCA, produto.marca);
        cv.put(COLUNA_PRODUTO_CATEGORIA, produto.categoria);
        cv.put(COLUNA_PRODUTO_STATUS, produto.status);
        long id = db.insert(TABELA_PRODUTO, null, cv);
        if (id != -1) {
            produto.id = id;

        }
        db.close();
        return id;
    }

    public ProdutoRepositorio open() throws SQLException {
        helper = new DataBaseSQLHelper(context);

        database = helper.getWritableDatabase();
        return this;
    }

    public int update(Produto produto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(COLUNA_QNT, produto.getQntItens());
        contentValues.put(COLUNA_PRODUTO_MARCA, produto.getMarca());
        contentValues.put(COLUNA_PRODUTO_CATEGORIA, produto.getCategoria());
        contentValues.put(COLUNA_VALIDADE, produto.getValidade());
        contentValues.put(COLUNA_VALOR, produto.getValor());

        return database.update(
                BASEDADOS_TABELA,
                contentValues,
                _ID + "= '" + produto.getId() + "'",
                null
        );
    }

    public int delete(int id) {
        return database.delete(TABELA_PRODUTO, _ID + " = '" + id + "'", null);
    }

    public int insert(Produto produto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(COLUNA_QNT, produto.getQntItens());
        contentValues.put(COLUNA_VALIDADE, produto.getValidade());
        contentValues.put(COLUNA_VALOR, produto.getValor());

        return (int) database.insert(BASEDADOS_TABELA, null, contentValues);
    }

    public ArrayList<Produto> query() {
        ArrayList<Produto> arrayList = new ArrayList<Produto>();

        Cursor cursor = database.rawQuery(
                "select * from produto where status =? ORDER BY descricao asc",
                new String[] {"0"});

//        Cursor cursor = database.query(
//                BASEDADOS_TABELA,
//                null,
//                null,
//                null,
//                null,
//                null,
//                MediaStore.Audio.Playlists.Members._ID + " DESC",
//                null
//        );

        cursor.moveToFirst();

        Produto produto;

        if (cursor.getCount() > 0) {
            do {
                produto = new Produto();

                produto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members._ID)));
                produto.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_DESCRICAO)));
                produto.setQntItens(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_QNT)));
                produto.setMarca(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PRODUTO_MARCA)));
                produto.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PRODUTO_CATEGORIA)));
                produto.setValor(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_VALOR)));
                produto.setValidade(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_VALIDADE)));
                produto.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_PRODUTO_STATUS)));

                arrayList.add(produto);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();

        return arrayList;
    }


}



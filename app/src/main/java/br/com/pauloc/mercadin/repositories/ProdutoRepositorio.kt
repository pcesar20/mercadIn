package br.com.pauloc.mercadin.repositories

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.provider.MediaStore
import br.com.pauloc.mercadin.DB.DataBaseSQLHelper
import br.com.pauloc.mercadin.model.Produto
import java.util.*

class ProdutoRepositorio(private val context: Context) {
    private var helper: DataBaseSQLHelper? = null
    private var database: SQLiteDatabase? = null
    fun close() {
        helper!!.close()
    }

    fun inserir(produto: Produto): Long {
        val db = helper!!.writableDatabase
        val cv = ContentValues()
        cv.put(DataBaseSQLHelper.COLUNA_DESCRICAO, produto.descricao)
        cv.put(DataBaseSQLHelper.COLUNA_VALOR, produto.valor)
        cv.put(DataBaseSQLHelper.COLUNA_VALIDADE, produto.validade)
        cv.put(DataBaseSQLHelper.COLUNA_QNT, produto.qntItens)
        cv.put(DataBaseSQLHelper.COLUNA_PRODUTO_MARCA, produto.marca)
        cv.put(DataBaseSQLHelper.COLUNA_PRODUTO_CATEGORIA, produto.categoria)
        cv.put(DataBaseSQLHelper.COLUNA_PRODUTO_STATUS, produto.status)
        val id = db.insert(DataBaseSQLHelper.TABELA_PRODUTO, null, cv)
        if (id != -1L) {
            produto.id = id
        }
        db.close()
        return id
    }

    fun open(): ProdutoRepositorio {
        helper = DataBaseSQLHelper(context)
        database = helper!!.writableDatabase
        return this
    }

    fun update(produto: Produto): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_DESCRICAO, produto.descricao)
        contentValues.put(DataBaseSQLHelper.COLUNA_QNT, produto.qntItens)
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_MARCA, produto.marca)
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_CATEGORIA, produto.categoria)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALIDADE, produto.validade)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALOR, produto.valor)
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                BaseColumns._ID + "= '" + produto.id + "'",
                null
        )
    }

    fun updateStatus(produto: Produto): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_DESCRICAO, produto.descricao)
        contentValues.put(DataBaseSQLHelper.COLUNA_QNT, produto.qntItens)
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_MARCA, produto.marca)
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_CATEGORIA, produto.categoria)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALIDADE, produto.validade)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALOR, produto.valor)
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_STATUS, 0)
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                BaseColumns._ID + "= '" + produto.id + "'",
                null
        )
    }

    fun updateStatusAll(): Int{
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_STATUS, 0);
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                null,
                null
        )
    }

    fun updateStatus2(id: Int): Int{
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_PRODUTO_STATUS, 1);
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                BaseColumns._ID + "= '" + id + "'",
                null
        )
    }

    fun delete(id: Int): Int {
        return database!!.delete(DataBaseSQLHelper.TABELA_PRODUTO, BaseColumns._ID + " = '" + id + "'", null)
    }

    fun insert(produto: Produto): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_DESCRICAO, produto.descricao)
        contentValues.put(DataBaseSQLHelper.COLUNA_QNT, produto.qntItens)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALIDADE, produto.validade)
        contentValues.put(DataBaseSQLHelper.COLUNA_VALOR, produto.valor)
        return database!!.insert(BASEDADOS_TABELA, null, contentValues).toInt()
    }

    fun query(): ArrayList<Produto> {
        val arrayList = ArrayList<Produto>()
        val cursor = database!!.rawQuery(
                "select * from produto where status =? ORDER BY descricao asc", arrayOf("0"))

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
        cursor.moveToFirst()
        var produto: Produto
        if (cursor.count > 0) {
            do {
                produto = Produto()
                produto.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members._ID)).toLong()
                produto.descricao = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_DESCRICAO))
                produto.qntItens = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_QNT))
                produto.marca = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_PRODUTO_MARCA))
                produto.categoria = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_PRODUTO_CATEGORIA))
                produto.valor = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_VALOR))
                produto.validade = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_VALIDADE))
                produto.status = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_PRODUTO_STATUS))
                arrayList.add(produto)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    companion object {
        private const val BASEDADOS_TABELA = DataBaseSQLHelper.TABELA_PRODUTO
    }
}
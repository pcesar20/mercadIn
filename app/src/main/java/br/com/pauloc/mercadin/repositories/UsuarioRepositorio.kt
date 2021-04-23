package br.com.pauloc.mercadin.repositories

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.provider.MediaStore
import br.com.pauloc.mercadin.DB.DataBaseSQLHelper
import br.com.pauloc.mercadin.model.Usuario
import java.util.*

class UsuarioRepositorio(private val context: Context) {
    private var helper: DataBaseSQLHelper? = null
    private var database: SQLiteDatabase? = null
    fun close() {
        helper!!.close()
    }

    fun inserir(usuario: Usuario): Long {
        val db = helper!!.writableDatabase
        val cv = ContentValues()
        cv.put(DataBaseSQLHelper.COLUNA_EMAIL, usuario.email)
        cv.put(DataBaseSQLHelper.COLUNA_SENHA, usuario.senha)
        cv.put(DataBaseSQLHelper.COLUNA_LOGADO, usuario.isLogado())
        val id = db.insert(DataBaseSQLHelper.TABELA_USUARIO, null, cv)
        if (id != -1L) {
            usuario.id = id
        }
        db.close()
        return id
    }

    fun update(usuario: Usuario): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_EMAIL, usuario.email)
        contentValues.put(DataBaseSQLHelper.COLUNA_SENHA, usuario.senha)
        contentValues.put(DataBaseSQLHelper.COLUNA_LOGADO, usuario.isLogado())
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                BaseColumns._ID + "= '" + usuario.id + "'",
                null
        )
    }

    fun updateLogado(usuario: Usuario): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseSQLHelper.COLUNA_LOGADO, usuario.isLogado())
        return database!!.update(
                BASEDADOS_TABELA,
                contentValues,
                BaseColumns._ID + "= '" + usuario.id + "'",
                null
        )
    }

    //@Throws(SQLException::class)
    fun open(): UsuarioRepositorio {
        helper = DataBaseSQLHelper(context)
        database = helper!!.writableDatabase
        return this
    }

    fun query(): ArrayList<Usuario> {
        val arrayList = ArrayList<Usuario>()
        val cursor = database!!.query(
                BASEDADOS_TABELA,
                null,
                null,
                null,
                null,
                null,
                MediaStore.Audio.Playlists.Members._ID + " DESC",
                null
        )
        cursor.moveToFirst()
        var usuario: Usuario
        if (cursor.count > 0) {
            do {
                usuario = Usuario()
                //val valor = cursor.getInt(DataBaseSQLHelper.COLUNA_LOGADO.toInt()) > 0
                //val valor = cursor.getInt(DataBaseSQLHelper.COLUNA_LOGADO).toInt() > 0
                usuario.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members._ID)).toLong()
                usuario.email = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_EMAIL))
                usuario.senha = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseSQLHelper.COLUNA_SENHA))
                //usuario.setLogado(valor)
                arrayList.add(usuario)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun getUser(email: String, senha: String): Boolean {
        val db = helper!!.readableDatabase
        val selectionArgs = arrayOf(email, senha)
        val cursor = db.rawQuery("select * from usuario where email=? and senha=?", selectionArgs)
        val count = cursor.count
        cursor.close()
        db.close()
        return if (count > 0) true else false
    }

    companion object {
        private const val BASEDADOS_TABELA = DataBaseSQLHelper.TABELA_USUARIO
    }
}



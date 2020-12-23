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
import br.com.pauloc.mercadin.model.Usuario;

import static android.provider.BaseColumns._ID;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_DESCRICAO;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_EMAIL;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_LOGADO;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_QNT;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.COLUNA_SENHA;
import static br.com.pauloc.mercadin.DB.DataBaseSQLHelper.TABELA_USUARIO;

public class UsuarioRepositorio {
    private static String BASEDADOS_TABELA = TABELA_USUARIO;
    private DataBaseSQLHelper helper;
    private Context context;
    private SQLiteDatabase database;

    public UsuarioRepositorio(Context context){
        this.context = context;
    }

    public void close(){
        helper.close();
    }

    public long inserir(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_EMAIL, usuario.email);
        cv.put(COLUNA_SENHA, usuario.senha);
        cv.put(COLUNA_LOGADO, usuario.logado);
        long id = db.insert(TABELA_USUARIO, null, cv);
        if (id != -1){
            usuario.id = id;

        }
        db.close();
        return id;
    }

    public int update(Usuario usuario){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_EMAIL, usuario.getEmail());
        contentValues.put(COLUNA_SENHA, usuario.getSenha());
        contentValues.put(COLUNA_LOGADO, usuario.isLogado());

        return database.update(
                BASEDADOS_TABELA,
                contentValues,
                _ID + "= '" + usuario.getId() + "'",
                null
        );
    }

    public UsuarioRepositorio open() throws SQLException {
        helper = new DataBaseSQLHelper(context);
        database = helper.getWritableDatabase();

        return this;
    }

    public ArrayList<Usuario> query(){
        ArrayList<Usuario> arrayList = new ArrayList<>();

        Cursor cursor = database.query(
                BASEDADOS_TABELA,
                null,
                null,
                null,
                null,
                null,
                MediaStore.Audio.Playlists.Members._ID + " DESC",
                null
        );

        cursor.moveToFirst();

        Usuario usuario;

        if (cursor.getCount() > 0) {
            do {
                usuario = new Usuario();

                boolean valor = cursor.getInt(Integer.parseInt(COLUNA_LOGADO)) > 0;

                usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members._ID)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_EMAIL)));
                usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_SENHA)));
                usuario.setLogado(valor);

                arrayList.add(usuario);

                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();

        return arrayList;
    }

}

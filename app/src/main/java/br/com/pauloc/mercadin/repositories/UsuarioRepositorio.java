package br.com.pauloc.mercadin.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.com.pauloc.mercadin.DB.ProdutoSQLHelper;
import br.com.pauloc.mercadin.DB.UsuarioSQLHelper;
import br.com.pauloc.mercadin.model.Usuario;

import static br.com.pauloc.mercadin.DB.UsuarioSQLHelper.TABELA_USUARIO;

public class UsuarioRepositorio {
    private static String BASEDADOS_TABELA = TABELA_USUARIO;
    private UsuarioSQLHelper helper;
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
        cv.put(UsuarioSQLHelper.COLUNA_EMAIL, usuario.email);
        cv.put(UsuarioSQLHelper.COLUNA_SENHA, usuario.senha);
        cv.put(UsuarioSQLHelper.COLUNA_LOGADO, usuario.logado);
        long id = db.insert(TABELA_USUARIO, null, cv);
        if (id != -1){
            usuario.id = id;

        }
        db.close();
        return id;
    }

    public UsuarioRepositorio open() throws SQLException {
        helper = new UsuarioSQLHelper(context);

        database = helper.getWritableDatabase();
        return this;
    }
}

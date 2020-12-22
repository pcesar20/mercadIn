package br.com.pauloc.mercadin.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuarioSQLHelper extends SQLiteOpenHelper {
    public static final String NOME_BASEDADOS = "dbMercadin";
    public static final int VERSAO_BANCODEDADOS = 3;
    public static final String TABELA_USUARIO = "usuario";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_LOGADO = "logado";

    public UsuarioSQLHelper(Context context) {
        super(context, NOME_BASEDADOS, null, VERSAO_BANCODEDADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABELA_USUARIO + " (" +
                        COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_EMAIL + " TEXT NOT NULL, " +
                        COLUNA_SENHA + " TEXT NOT NULL, " +
                        COLUNA_LOGADO + " BOOLEAN)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

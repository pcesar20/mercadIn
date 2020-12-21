package br.com.pauloc.mercadin.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdutoSQLHelper extends SQLiteOpenHelper {
    public static final String NOME_BASEDADOS = "dbMercadin";
    public static final int VERSAO_BANCODEDADOS = 3;
    public static final String TABELA_PRODUTO = "produto";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_VALIDADE = "validade";
    public static final String COLUNA_QNT = "qntItens";
    
    public ProdutoSQLHelper(Context context) {
        super(context, NOME_BASEDADOS, null, VERSAO_BANCODEDADOS);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABELA_PRODUTO + " (" +
                        COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_DESCRICAO + " TEXT NOT NULL, " +
                        COLUNA_VALOR + " REAL, " +
                        COLUNA_VALIDADE + " TEXT, " +
                        COLUNA_QNT + " INT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    }

package br.com.pauloc.mercadin.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseSQLHelper extends SQLiteOpenHelper {
    public static final String NOME_BASEDADOS = "dbMercadin";
    public static final int VERSAO_BANCODEDADOS = 7;
    //TABELA PRODUTO
    public static final String TABELA_PRODUTO = "produto";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_VALIDADE = "validade";
    public static final String COLUNA_QNT = "qntItens";
    //TABELA USUARIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String COLUNA_USUR_ID = "_id";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_LOGADO = "logado";

    public DataBaseSQLHelper(Context context) {
        super(context, NOME_BASEDADOS, null, VERSAO_BANCODEDADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABELA_USUARIO + " (" +
                        COLUNA_USUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_EMAIL + " TEXT NOT NULL, " +
                        COLUNA_SENHA + " TEXT NOT NULL, " +
                        COLUNA_LOGADO + " BOOLEAN)"
        );

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
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);

        onCreate(db);
    }

    public boolean checkUser(String email, String senha) throws SQLException{
        String[] columns = {COLUNA_USUR_ID};
        SQLiteDatabase db = getReadableDatabase();

        String selection = "email=? and senha=?";
        String[] selectionArgs = {email, senha};

        Cursor cursor = db.rawQuery("select * from usuario where email=? and senha=?", selectionArgs);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public String getUsername() throws SQLException {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(TABELA_USUARIO, new String[]{COLUNA_EMAIL}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }

    public boolean getUser(String email, String senha){
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {email, senha};

        Cursor cursor = db.rawQuery("select * from usuario where email=? and senha=?", selectionArgs);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }



}

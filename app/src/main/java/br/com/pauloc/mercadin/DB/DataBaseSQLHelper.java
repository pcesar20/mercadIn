package br.com.pauloc.mercadin.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;

public class DataBaseSQLHelper extends SQLiteOpenHelper {
    public static final String NOME_BASEDADOS = "dbMercadin";
    public static final int VERSAO_BANCODEDADOS = 12;
    //TABELA PRODUTO
    public static final String TABELA_PRODUTO = "produto";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_PRODUTO_MARCA = "marca";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_VALIDADE = "validade";
    public static final String COLUNA_QNT = "qntItens";
    public static final String COLUNA_PRODUTO_STATUS = "status";
    public static final String COLUNA_PRODUTO_CATEGORIA = "categoria";
    //TABELA USUARIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String COLUNA_USUR_ID = "_id";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_LOGADO = "logado";
    //TABELA CATEGORIA
    public static final String TABELA_CATEGORIA = "categoria";
    public static final String COLUNA_CAT_ID = "_id";
    public static final String COLUNA_CAT_DESCRICAO = "cat_descricao";
    public static final String COLUNA_PERECIVEL = "perecivel";
    //TABELA COMPRAS
    public static final String TABELA_COMPRAS = "compras";
    public static final String COLUNA_COMPRAS_ID = "id";
    public static final String COLUNA_USUARIO_ID = "usuario_id";
    public static final String COLUNA_MES_REF = "mes_referencia";
    public static final String COLUNA_STATUS = "status";
    //TABELA COMPRAS ITENS
    public static final String TABELA_COMPRAS_ITENS = "compras_itens";
    public static final String COLUNA_CITENS_ID = "id";
    public static final String COLUNA_COMPRA_ID = "compra_id";
    public static final String COLUNA_USURITENS_ID = "usuario_id";
    public static final String COLUNA_PROD_CI = "produto_id";
    public static final String COLUNA_QTNITENS = "qntItens";
    public static final String COLUNA_VLTOTAL = "valortotal";
    //TABELA DISPENSA
    public static final String TABELA_DISPENSA = "dispensa";
    public static final String COLUNA_DISPENSA_ID = "id";
    public static final String COLUNA_PROD_ID = "produto_id";
    public static final String COLUNA_PROD_QNT = "produto_qnt";
    public static final String COLUNA_PROD_VAL = "validade";


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
                        COLUNA_PRODUTO_MARCA + " TEXT, " +
                        COLUNA_VALOR + " REAL, " +
                        COLUNA_PRODUTO_STATUS + " INTEGER, " +
                        COLUNA_VALIDADE + " TEXT, " +
                        COLUNA_PRODUTO_CATEGORIA + " TEXT NOT NULL, " +
                        COLUNA_QNT + " INT)"

        );

        db.execSQL(
                "CREATE TABLE " + TABELA_CATEGORIA + " (" +
                        COLUNA_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_CAT_DESCRICAO + " TEXT NOT NULL, " +
                        COLUNA_PERECIVEL + "BOOLEAN )"
        );

        db.execSQL(
                "CREATE TABLE " + TABELA_COMPRAS + " (" +
                        COLUNA_COMPRAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_USUARIO_ID + " TEXT NOT NULL, " +
                        COLUNA_MES_REF + " DATE," +
                        COLUNA_STATUS + " TEXT NOT NULL," +
                        "FOREIGN KEY ("+COLUNA_USUARIO_ID+") REFERENCES "+TABELA_USUARIO+"("+COLUNA_USUR_ID+"))"
        );

        db.execSQL(
                "CREATE TABLE " + TABELA_COMPRAS_ITENS + " (" +
                        COLUNA_CITENS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_COMPRA_ID + " INT NOT NULL, " +
                        COLUNA_USURITENS_ID + " INT NOT NULL," +
                        COLUNA_PROD_CI + " INT NOT NULL, " +
                        COLUNA_QTNITENS + " INT NOT NULL," +
                        COLUNA_VLTOTAL + " REAL NOT NULL," +
                        "FOREIGN KEY ("+COLUNA_PROD_CI+") REFERENCES "+TABELA_PRODUTO+"("+COLUNA_ID+")," +
                        "FOREIGN KEY ("+COLUNA_COMPRA_ID+") REFERENCES "+TABELA_COMPRAS+"("+COLUNA_COMPRAS_ID+")," +
                        "FOREIGN KEY ("+COLUNA_USURITENS_ID+") REFERENCES "+TABELA_USUARIO+"("+COLUNA_USUR_ID+"))"
        );

        db.execSQL(
                "CREATE TABLE " + TABELA_DISPENSA + " (" +
                        COLUNA_DISPENSA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_PROD_ID + " INT NOT NULL, " +
                        COLUNA_PROD_QNT + " INT NOT NULL," +
                        COLUNA_PROD_VAL + " REAL NOT NULL," +
                        "FOREIGN KEY ("+COLUNA_PROD_ID+") REFERENCES "+TABELA_PRODUTO+"("+COLUNA_ID+"))"
        );

        db.execSQL(sqlInsert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_COMPRAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_COMPRAS_ITENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DISPENSA);


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

    public void deleteProduto(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PRODUTO, COLUNA_ID + " = ?",new String[] {String.valueOf(id)});
    }


    private String sqlInsert = "insert into produto (_id, descricao, marca, valor, status, validade, categoria, qntItens) " +
            "values (null, \"teste\", null, null, null, null, \"Outros\", null)," +
            "(null, \"teste2\", null, null, null, null, \"Outros\", null);";


}

package br.com.pauloc.mercadin.common;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.pauloc.mercadin.R;

public class ConfigSharedPreferences {
    private SharedPreferences sharedPreferences;
    private Context context;

    public ConfigSharedPreferences(Context context){
        this.context=context;

        this.sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.preferencia_login), Context.MODE_PRIVATE);
    }

    public void guardarPreferencia(boolean logado, String utilizador){
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.loginlogado), logado);
        editor.putString(context.getResources().getString(R.string.loginutilizador), utilizador);

        editor.commit();
    }

    public String obterPreferencia(){
        String utilizador;

        utilizador=sharedPreferences.getString(context.getResources().getString(R.string.loginutilizador), "");

        return utilizador;
    }
}

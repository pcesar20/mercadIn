package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.com.pauloc.mercadin.common.ConfigSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    private ConfigSharedPreferences configSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configSharedPreferences = new ConfigSharedPreferences(this);
        try {
            if (!configSharedPreferences.obterPreferencia().equals("")) {
                Intent intent = new Intent(this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
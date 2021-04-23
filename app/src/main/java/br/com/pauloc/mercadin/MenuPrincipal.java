package br.com.pauloc.mercadin;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import br.com.pauloc.mercadin.common.ConfigSharedPreferences;

public class MenuPrincipal extends AppCompatActivity {
    private ConfigSharedPreferences configSharedPreferences;
    private String email = "";
    MaterialCardView btnProduto,btnCompras, btnResumoVendas, btnFerramentas, btnCategorias, btnClientes;
    TextView textBemVindo;
    ImageView imgInternetConection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        textBemVindo = findViewById(R.id.text_bemvindo);
        btnProduto = findViewById(R.id.btnProduto);
        btnCompras  = findViewById(R.id.btnCompras);
        btnClientes = findViewById(R.id.btnClientes);
        btnResumoVendas = findViewById(R.id.btnResumoVendas);
        btnFerramentas = findViewById(R.id.btnFerramentas);
        btnCategorias = findViewById(R.id.btnCategorias);
        configSharedPreferences = new ConfigSharedPreferences(this);

        String usuario = configSharedPreferences.obterPreferencia();
        textBemVindo.setText("Bem vindo, " + usuario + "!");


        Intent it = getIntent();

        if (it != null) {
           Bundle b = it.getExtras();

            if (b != null) {
                email = b.getString("nome", "");
            }
        }
        bemVindo(email);

        btnResumoVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emDev(v);
            }
        });

        btnFerramentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emDev(v);
            }
        });

        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emDev(v);
            }
        });

        btnCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(getApplicationContext(), MinhaDispensa.class);
                    startActivity(i);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(getApplicationContext(), CadastroUserActivity.class);
                    startActivity(i);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

       // verificarConexao(this);

        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, FormAddProduto.class);
                try {
                    startActivityForResult(intent, FormAddProduto.REQUEST_ADD);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String usuario = configSharedPreferences.obterPreferencia();
        textBemVindo.setText("Bem vindo, " + usuario + "!");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Esconde nav bar e status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String usuario = configSharedPreferences.obterPreferencia();
        textBemVindo.setText("Bem vindo, " + usuario + "!");
       // verificarConexao(this);
    }

    private void bemVindo(String email) {
        textBemVindo.setText("Bem vindo, " + email + "!");

    }

    private void emDev(View v){
        Snackbar.make(v, "Em desenvolvimento", Snackbar.LENGTH_LONG).setAction("MercadIn", null).show();
    }

    private void sair(){
        configSharedPreferences.guardarPreferencia(false, "");
        Intent imd = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(imd);
        finish();
    }

    private void linksDuvidas(){
         String url = "https://linktr.ee/pcesar20";
         Uri uri = Uri.parse(url);
         Intent i = new Intent(Intent.ACTION_VIEW, uri);
         startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.optSair:
                sair();
                return true;
            case R.id.optDuvidas:
                linksDuvidas();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
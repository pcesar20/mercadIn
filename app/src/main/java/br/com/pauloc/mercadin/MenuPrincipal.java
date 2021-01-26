package br.com.pauloc.mercadin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MenuPrincipal extends AppCompatActivity {
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

        Intent it = getIntent();

        if (it != null) {
           Bundle b = it.getExtras();

            if (b != null) {
                email = b.getString("nome", "");
            }
        }

        bemVindo(email);

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
        //verificarConexao(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // verificarConexao(this);
    }

    private void bemVindo(String email) {
        textBemVindo.setText("Bem vindo, " + email + "!");

    }
}
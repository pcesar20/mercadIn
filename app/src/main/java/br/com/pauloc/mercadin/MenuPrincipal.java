package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;

public class MenuPrincipal extends AppCompatActivity {
    MaterialCardView btnProduto,btnCompras, btnResumoVendas, btnFerramentas, btnCategorias, btnClientes;
    ImageView imgInternetConection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        imgInternetConection = findViewById(R.id.imgInternetConection);

        verificarConexao(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificarConexao(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        verificarConexao(this);
    }

    public void verificarConexao(Context context){
        if(isConnected(context) == true){
            imgInternetConection.setImageResource(R.drawable.ic_maxima_nuvem_conectado);
        } else{
            imgInternetConection.setImageResource(R.drawable.ic_maxima_nuvem_desconectado);
        }
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

            return ni != null && ni.isConnected();
        }
        return false;
    }
}
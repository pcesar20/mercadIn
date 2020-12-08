package br.com.pauloc.mercadin;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import br.com.pauloc.mercadin.repositories.FormAddProduto;

public class MinhaDispensa extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_dispensa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Ainda em desenvolvimento", Snackbar.LENGTH_LONG)
//                        .setAction("MercadIn", null).show();

        if (view.getId() == R.id.fab){
            Intent intent = new Intent(MinhaDispensa.this, FormAddProduto.class);
        try{
            startActivityForResult(intent, FormAddProduto.REQUEST_ADD);
        } catch(Exception e){
            e.printStackTrace();
        }

        }

            }
        });


    }
}
package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    Button btnLogar, btnCadastrar, btnSemCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnSemCadastro = findViewById(R.id.btnSemCadastro);

        edtEmail.requestFocus();

        btnLogar.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                                    Snackbar.make(v, "Ainda em desenvolvimento", Snackbar.LENGTH_LONG)
                                                            .setAction("MercadIn", null).show();

                                        }
        }

        );

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadastroUserActivity.class);
                startActivity(i);
            }
        });

        btnSemCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AcessoSemCadastro acessoSemCadastro = new AcessoSemCadastro();
            acessoSemCadastro.show(getSupportFragmentManager(), "Acesso sem Login");
            }
        });

    }
}
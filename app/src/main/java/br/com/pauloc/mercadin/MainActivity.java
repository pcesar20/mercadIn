package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                                          Toast toast = Toast.makeText(getApplicationContext(), "Em desenvolvimento", Toast.LENGTH_LONG);
                                          toast.show();
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

    }
}
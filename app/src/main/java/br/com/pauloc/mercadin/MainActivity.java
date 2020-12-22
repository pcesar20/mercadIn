package br.com.pauloc.mercadin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import br.com.pauloc.mercadin.repositories.UsuarioRepositorio;

public class MainActivity extends AppCompatActivity{

    EditText edtEmail, edtSenha;
    Button btnLogar, btnCadastrar, btnSemCadastro;
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnSemCadastro = findViewById(R.id.btnSemCadastro);
        AcessoSemCadastro acessoSemCadastro = new AcessoSemCadastro();
        edtEmail.requestFocus();

        btnLogar.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {

                                            String email = edtEmail.getText().toString();
                                            String senha = edtSenha.getText().toString();
                                            try {
                                                if (email.equals("") || senha.equals("")){
                                                    Toast toast = Toast.makeText(getApplicationContext(), "Campo obrigatório vazio", Toast.LENGTH_LONG);
                                                    toast.show();
                                                }
                                                else if(email.equals("adm") && senha.equals("123")){
                                                    Intent imd = new Intent(getApplicationContext(), MinhaDispensa.class);
                                                    startActivity(imd);
                                                } else{
                                                    Snackbar.make(v, "Cadastro não encontrado", Snackbar.LENGTH_LONG)
                                                            .setAction("MercadIn", null).show();
                                                }
                                            } catch (Exception e){
                                                e.printStackTrace();
                                            }


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

    @Override
    protected void onResume() {
        super.onResume();
        usuarioRepositorio.open();
    }
}
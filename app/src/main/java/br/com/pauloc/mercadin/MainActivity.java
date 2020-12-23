package br.com.pauloc.mercadin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.pauloc.mercadin.DB.DataBaseSQLHelper;
import br.com.pauloc.mercadin.repositories.UsuarioRepositorio;

public class MainActivity extends AppCompatActivity {
    private String emailCad, senhaCad;
    EditText edtEmail, edtSenha;
    Button btnLogar, btnCadastrar, btnSemCadastro;
    DataBaseSQLHelper db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


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
        final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(this);
        mAuth = FirebaseAuth.getInstance();

        Intent it = getIntent();

        if(it != null){
            Bundle b = it.getExtras();

            if(b != null){
                emailCad = b.getString("email", "");
                senhaCad = b.getString("senha", "");
            }
        }

        edtEmail.setText(emailCad);
        edtSenha.setText(senhaCad);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }

            }
        };

        mAuth.signInWithEmailAndPassword("paulocesar.araujo.ads@gmail.com", "araujo").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Log.w("AUTH", "Falha ao efetuar o Login: ", task.getException());
                } else {
                    Log.d("AUTH", "Login Efetuado com sucesso!!!");
                }
            }
        });

        usuarioRepositorio.open();

        btnLogar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String email = edtEmail.getText().toString().trim();
                                            String senha = edtSenha.getText().toString().trim();

                                            boolean res = usuarioRepositorio.getUser(email, senha);

                                            try {
                                                if (res == true) {
                                                    try {

                                                        Bundle b = new Bundle();
                                                        b.putString("nome", email);
                                                        Intent imd = new Intent(getApplicationContext(), MinhaDispensa.class);
                                                        imd.putExtras(b);
                                                        startActivity(imd);

                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }


                                                } else {
                                                    Snackbar.make(v, "Cadastro não encontrado", Snackbar.LENGTH_LONG)
                                                            .setAction("MercadIn", null).show();
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


//                                            try {
//                                                if (email.equals("") || senha.equals("")) {
//                                                    Toast toast = Toast.makeText(getApplicationContext(), "Campo obrigatório vazio", Toast.LENGTH_LONG);
//                                                    toast.show();
//                                                } else if (email.equals("adm") && senha.equals("123")) {
//                                                    Intent imd = new Intent(getApplicationContext(), MinhaDispensa.class);
//                                                    startActivity(imd);
//                                                } else {
//                                                    Snackbar.make(v, "Cadastro não encontrado", Snackbar.LENGTH_LONG)
//                                                            .setAction("MercadIn", null).show();
//                                                }
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }


                                        }
                                    }

        );

        usuarioRepositorio.close();

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

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
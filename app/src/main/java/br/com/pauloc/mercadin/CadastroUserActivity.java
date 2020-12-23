package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.pauloc.mercadin.model.Usuario;
import br.com.pauloc.mercadin.repositories.UsuarioRepositorio;


public class CadastroUserActivity extends AppCompatActivity {
    EditText edtCadastroEmail, edtCadastroSenha;
    Button btnCriar, btnLimpar;
    ImageView imageVoltar;
    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;
    public static String EXTRA_EMAIL = "extra_email";
    public static String EXTRA_SENHA = "extra_senha";
    private boolean isEdit = false;
    private int posicao;
    private Usuario usuario;
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);
        edtCadastroEmail = findViewById(R.id.edtCadastroEmail);
        edtCadastroSenha = findViewById(R.id.edtCadastroSenha);
        btnCriar = findViewById(R.id.btnCriar);
        btnLimpar = findViewById(R.id.btnLimpar);
        imageVoltar = findViewById(R.id.imageVoltar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnCriar) {
                    String email = edtCadastroEmail.getText().toString();
                    String senha = edtCadastroSenha.getText().toString();
                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
                        isEmpty = true;
                        edtCadastroEmail.setError("preencha o campo");
                        edtCadastroSenha.setError("preencha o campo");
                    }
                    if (!isEmpty) {
                        Usuario novoUsuario = new Usuario();

                        novoUsuario.setEmail(email);
                        novoUsuario.setSenha(senha);
                        novoUsuario.setLogado(true);

                        Intent intent = new Intent();

                        if (isEdit) {
                            novoUsuario.setEmail(usuario.getEmail());
                            novoUsuario.setSenha(usuario.getSenha());
                            novoUsuario.setLogado(usuario.isLogado());

                            usuarioRepositorio.update(novoUsuario);
                            intent.putExtra(EXTRA_EMAIL, posicao);

                            setResult(RESULT_UPDATE, intent);

                            finish();
                        } else {
                            usuarioRepositorio.inserir(novoUsuario);

                            setResult(RESULT_ADD);

                            finish();
                        }
                    }
                }
            }
        });

        usuarioRepositorio = new UsuarioRepositorio(this);
        usuarioRepositorio.open();

        usuario = getIntent().getParcelableExtra(EXTRA_EMAIL);

        if (usuario != null) {
            posicao = getIntent().getIntExtra(EXTRA_SENHA, 0);
            isEdit = false;
        }

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });

        imageVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              if(edtCadastroEmail == null && edtCadastroSenha == null){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
//              }else{
//                  Toast toast = Toast.makeText(getApplicationContext(), "Finalize o cadastro ou limpe os campos", Toast.LENGTH_LONG);
//                  toast.show();
//              }

            }
        });

    }

    public void validaUsuario(String email, String senha) {
        if (email.equals("ADM") || senha.equals("123456")) {
            Toast toast = Toast.makeText(getApplicationContext(), "E-mail ou senha invalidos", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Cadastro valido", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void limparCampos() {
        edtCadastroSenha.setText("");
        edtCadastroEmail.setText("");
    }
}
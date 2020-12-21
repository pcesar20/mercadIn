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
                if(v.getId() == R.id.btnCriar) {
                    String email = edtCadastroEmail.getText().toString();
                    String senha = edtCadastroSenha.getText().toString();
                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
                        isEmpty = true;
                        edtCadastroEmail.setError("preencha o campo");
                        edtCadastroSenha.setError("preencha o campo");
                    }
                    if (!isEmpty) {

                        try {
                        Usuario usuario = new Usuario(email, senha, true);
//                        usuario.setEmail(edtCadastroEmail.getText().toString());
//                        usuario.setSenha(edtCadastroSenha.getText().toString());
//                        usuario.setLogado(true);
                        usuarioRepositorio.open();
                        usuarioRepositorio.inserir(usuario);
                        finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }


            }
        });

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

    public void validaUsuario(String email, String senha){
       if (email.equals("ADM") || senha.equals("123456")){
           Toast toast = Toast.makeText(getApplicationContext(), "E-mail ou senha invalidos", Toast.LENGTH_LONG);
            toast.show();
       } else{
            Toast toast = Toast.makeText(getApplicationContext(), "Cadastro valido", Toast.LENGTH_LONG);
            toast.show();
       }

    };

    public void limparCampos(){
        edtCadastroSenha.setText("");
        edtCadastroEmail.setText("");
    }
}
package br.com.pauloc.mercadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CadastroUserActivity extends AppCompatActivity {
    EditText edtCadastroEmail, edtCadastroSenha;
    Button btnCriar, btnLimpar;
    ImageView imageVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);
        edtCadastroEmail = findViewById(R.id.edtCadastroEmail);
        edtCadastroSenha = findViewById(R.id.edtCadastroSenha);
        btnCriar = findViewById(R.id.btnCriar);
        btnLimpar = findViewById(R.id.btnLimpar);
        imageVoltar = findViewById(R.id.imageVoltar);


        Toast toast = Toast.makeText(getApplicationContext(), "você está em outra activity", Toast.LENGTH_LONG);
        toast.show();

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaUsuario(edtCadastroEmail.getText().toString(), edtCadastroSenha.getText().toString());
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
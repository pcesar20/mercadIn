package br.com.pauloc.mercadin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class AcessoSemCadastro extends DialogFragment {

    boolean logado;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Deseja mesmo acessar sem um login?");


        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Snackbar.make(getView(), "Utilizando sem cadastro!", Snackbar.LENGTH_LONG)
//                        .setAction("MercadIn", null).show();
                logado = true;
                Intent i2 = new Intent(getContext(), MenuPrincipal.class);
                startActivity(i2);
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Realize um cadastro conforme", Toast.LENGTH_LONG).show();
                logado = false;
                Intent i = new Intent(getContext(), CadastroUserActivity.class);
                startActivity(i);
            }
        });

        return builder.create();
    }

}

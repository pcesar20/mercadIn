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

public class AcessoSemCadastro extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Deseja mesmo acessar sem um login?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "Sem login", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(getContext(), MinhaDispensa.class);
                startActivity(i2);
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Realize um cadastro conforme", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), CadastroUserActivity.class);
                startActivity(i);
            }
        });

        return builder.create();
    }
}

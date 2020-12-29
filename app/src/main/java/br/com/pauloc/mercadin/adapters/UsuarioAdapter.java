package br.com.pauloc.mercadin.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    @NonNull
    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        EditText edtCadastroSenha, edtCadastroEmail;
        Button btnCriar, btnLimpar;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

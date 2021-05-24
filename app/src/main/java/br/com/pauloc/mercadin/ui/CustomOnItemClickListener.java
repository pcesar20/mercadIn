package br.com.pauloc.mercadin.ui;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {

    private int posicao;

    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int posicao, OnItemClickCallback onItemClickCallback) {
        this.posicao = posicao;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, posicao);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int posicao);
    }
}
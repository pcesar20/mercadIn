package br.com.pauloc.mercadin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AcessoSemCadastro : DialogFragment() {
    var logado = false
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Deseja mesmo acessar sem um login?")
        builder.setPositiveButton("Sim") { dialog, which -> //                Snackbar.make(getView(), "Utilizando sem cadastro!", Snackbar.LENGTH_LONG)
//                        .setAction("MercadIn", null).show();
            logado = true
            val i2 = Intent(context, MenuPrincipal::class.java)
            startActivity(i2)
        }
        builder.setNegativeButton("Não") { dialog, which ->
            Toast.makeText(activity, "Realize um cadastro conforme", Toast.LENGTH_LONG).show()
            logado = false
            val i = Intent(context, CadastroUserActivity::class.java)
            startActivity(i)
        }
        return builder.create()
    }
}
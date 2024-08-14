package com.example.givinglandv1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.givinglandv1.ui.MainActivity
import com.example.givinglandv1.ui.user.DeleteAccountViewModel


class ConfirmacioneliminacionFragment : DialogFragment() {

    private lateinit var viewModel: DeleteAccountViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = (requireActivity() as IngresarpasswordActivity).viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Confirmar eliminación")
            .setMessage("¿Está seguro de que desea eliminar su cuenta?")
            .setPositiveButton("Confirmar") { _, _ ->
                val password = arguments?.getString("password") ?: ""
                viewModel.deleteAccount(password)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    companion object {
        fun newInstance(password: String): ConfirmacioneliminacionFragment {
            val fragment = ConfirmacioneliminacionFragment()
            val args = Bundle()
            args.putString("password", password)
            fragment.arguments = args
            return fragment
        }
    }
}
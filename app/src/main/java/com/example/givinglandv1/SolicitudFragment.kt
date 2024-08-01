package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.givinglandv1.ui.login.LoginActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SolicitudFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solicitud, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el botón de inicio de sesión
        val loginButton: Button = view.findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            // Navegar a la actividad de inicio de sesión
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SolicitudFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
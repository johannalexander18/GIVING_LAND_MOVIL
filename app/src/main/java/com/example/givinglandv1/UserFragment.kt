package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.givinglandv1.databinding.FragmentUserBinding



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.configButton.setOnClickListener {
            val intent = Intent(activity, ConfigurationActivity::class.java)
            startActivity(intent)
        }

        // Configurar el RecyclerView para mostrar las publicaciones
        val publications = listOf(
            Publication(R.drawable.producto1, "Celular", "Se intercambia iphone por ...", "Intercambio", "Popayán"),
            Publication(R.drawable.producto1, "Celular", "Se intercambia iphone por ...", "Intercambio", "Popayán"),
            Publication(R.drawable.producto1, "Celular", "Se intercambia iphone por ...", "Intercambio", "Popayán"),
            Publication(R.drawable.producto1, "Celular", "Se intercambia iphone por ...", "Intercambio", "Popayán")

            // Agrega más publicaciones aquí
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = PublicationAdapter(publications)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
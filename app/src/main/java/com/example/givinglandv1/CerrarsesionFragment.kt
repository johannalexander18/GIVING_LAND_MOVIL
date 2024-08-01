package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.api.UserApi
import com.example.givinglandv1.databinding.FragmentCerrarsesionBinding
import com.example.givinglandv1.util.SharedPrefs
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class CerrarsesionFragment : DialogFragment() {

    private var _binding: FragmentCerrarsesionBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPrefs: SharedPrefs
    private lateinit var api: UserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = SharedPrefs(requireContext())
        api = RetrofitInstance.api
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCerrarsesionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.acceptButton.setOnClickListener {
            lifecycleScope.launch {
                logoutUser()
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private suspend fun logoutUser() {
        val authToken = sharedPrefs.authToken

        if (!authToken.isNullOrEmpty()) {
            try {
                val response = api.logout("Bearer $authToken")
                if (response.isSuccessful) {
                    sharedPrefs.authToken = null
                    (requireActivity().application as MyApplication).setUserLoggedIn(false)
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    dismiss()
                } else {
                    showToast("Error al cerrar sesión: ${response.message()}")
                }
            } catch (e: Exception) {
                showToast("Error al cerrar sesión: ${e.message}")
            }
        } else {
            showToast("Token de autenticación no encontrado")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CerrarsesionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
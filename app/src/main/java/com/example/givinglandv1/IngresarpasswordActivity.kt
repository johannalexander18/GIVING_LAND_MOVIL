package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.repository.UserRepository
import com.example.givinglandv1.databinding.ActivityIngresarpasswordBinding
import com.example.givinglandv1.ui.MainActivity
import com.example.givinglandv1.ui.user.DeleteAccountViewModel
import com.example.givinglandv1.util.SharedPrefs

class IngresarpasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngresarpasswordBinding
    lateinit var viewModel: DeleteAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = UserRepository(RetrofitInstance.api, SharedPrefs(this))
        viewModel = DeleteAccountViewModel(userRepository)

        binding.continueButton.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            if (password.isNotEmpty()) {
                showConfirmationDialog(password)
            } else {
                Toast.makeText(this, "Por favor, ingrese su contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.deleteAccountResult.observe(this) { result ->
            result.fold(
                onSuccess = { message ->
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    navigateToMainActivity()
                },
                onFailure = { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    private fun showConfirmationDialog(password: String) {
        ConfirmacioneliminacionFragment.newInstance(password)
            .show(supportFragmentManager, "ConfirmacionEliminacion")
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}

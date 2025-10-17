package com.alexandre.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.alexandre.kanban.R
import com.alexandre.kanban.databinding.FragmentRegisterBinding
import com.alexandre.kanban.util.initToolbar
import com.alexandre.kanban.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextSenha.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                binding.progressBar.isVisible = true
                registerUser(email, senha)
            }
            else {
                showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            }

        }
        else {
            showBottomSheet(message = getString(R.string.email_empty_register_fragment))
        }

    }

    private fun registerUser(email: String, password: String) {
        try {
            val auth: FirebaseAuth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment)
                }
                else {
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.progressBar.isVisible = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
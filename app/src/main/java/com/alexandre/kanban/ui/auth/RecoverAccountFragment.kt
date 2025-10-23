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
import com.alexandre.kanban.databinding.FragmentRecoverAccountBinding
import com.alexandre.kanban.util.initToolbar
import com.alexandre.kanban.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRecover.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        val email = binding.editTextEmail.text.toString().trim()

        if (email.isNotBlank()) {
            recoverAccountUser(email)
        }
        else {
            showBottomSheet(message = getString(R.string.email_empty))
        }

    }

    private fun recoverAccountUser(email: String) {

        binding.progressBar.isVisible = true

        try {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    showBottomSheet(message = getString(R.string.text_message_recover_account_fragment))
                }
                else {
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }

                binding.progressBar.isVisible = false
            }
        }
        catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            binding.progressBar.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
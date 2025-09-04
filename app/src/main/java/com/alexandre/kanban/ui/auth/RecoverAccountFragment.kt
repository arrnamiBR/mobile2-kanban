package com.alexandre.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.alexandre.kanban.R
import com.alexandre.kanban.databinding.FragmentRecoverAccountBinding
import com.alexandre.kanban.util.initToolbar
import com.alexandre.kanban.util.showBottomSheet


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

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
            Toast.makeText(requireContext(), "Tudo certo!", Toast.LENGTH_SHORT).show()
        }
        else {
            showBottomSheet(message = R.string.email_empty)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
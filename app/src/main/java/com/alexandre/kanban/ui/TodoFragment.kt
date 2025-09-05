package com.alexandre.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexandre.kanban.R
import com.alexandre.kanban.data.model.Status
import com.alexandre.kanban.data.model.Task
import com.alexandre.kanban.databinding.FragmentDoneBinding
import com.alexandre.kanban.databinding.FragmentTodoBinding
import com.alexandre.kanban.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {

    var _binding: FragmentTodoBinding? = null
    val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {

        taskAdapter = TaskAdapter(requireContext()) {task, option -> optionSelected(task, option)}

        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }

    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Próximo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Anterior ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task("0", "Ir para a academia", Status.TODO),
            Task("1", "Tomar creatina", Status.TODO)
        )
        taskAdapter.submitList(taskList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.alexandre.kanban.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.alexandre.kanban.R
import com.alexandre.kanban.data.model.Status
import com.alexandre.kanban.data.model.Task
import com.alexandre.kanban.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task,Int) -> Unit
): RecyclerView.Adapter<TaskAdapter.MyViewHolder> () {

    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVER: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    override fun getItemCount() = taskList.size

    private fun setIndicators(task: Task, holder: MyViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.buttonBack.isVisible = false
                holder.binding.buttonFoward.setOnClickListener {taskSelected(task, SELECT_NEXT)}
            }
            Status.DOING -> {
                holder.binding.buttonBack.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.buttonFoward.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))
                holder.binding.buttonBack.setOnClickListener {taskSelected(task, SELECT_BACK)}
                holder.binding.buttonFoward.setOnClickListener {taskSelected(task, SELECT_NEXT)}
            }
            Status.DONE -> {
                holder.binding.buttonFoward.isVisible = false
                holder.binding.buttonBack.setOnClickListener {taskSelected(task, SELECT_BACK)}
            }
        }

        holder.binding.buttonDelete.setOnClickListener {taskSelected(task, SELECT_REMOVER)}
        holder.binding.buttonEditar.setOnClickListener {taskSelected(task, SELECT_EDIT)}
        holder.binding.buttonDetails.setOnClickListener {taskSelected(task, SELECT_DETAILS)}

    }

    inner class MyViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {

    }

}
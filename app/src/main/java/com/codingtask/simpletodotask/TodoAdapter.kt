package com.codingtask.simpletodotask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingtask.simpletodotask.databinding.ItemTodoBinding

class TodoAdapter(val list: List<TodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position]) // we are passing the object of the list that we made in the ToDoModel.kt
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    // view holder is present inside the recycler view
    class TodoViewHolder(val itemTodoBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemTodoBinding.root) {
        fun bind(todoModel: TodoModel) {
            itemTodoBinding.tvTitle.text = todoModel.title

            if (todoModel.description.isNullOrEmpty()) itemTodoBinding.tvDescription.visibility =
                View.GONE
            else itemTodoBinding.tvDescription.visibility = View.VISIBLE

            itemTodoBinding.tvDescription.text = todoModel.description


            if (todoModel.isPending) {
                itemTodoBinding.tvStatus.text = "Pending"
            } else {
                itemTodoBinding.tvStatus.text = "completed"
            }
        }

    }

}


